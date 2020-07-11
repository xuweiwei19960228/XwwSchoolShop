package cn.xww.o2o.service.Impl;

import cn.xww.o2o.cache.JedisUtil;
import cn.xww.o2o.dao.HeadLineDao;
import cn.xww.o2o.domain.HeadLine;
import cn.xww.o2o.service.HeadLineService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    private JedisUtil.Strings jedisStrings;
    private static String HLLISTKEY = "headlinelist";

    @Transactional
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
        //定义redis的key前缀
        String key = HLLISTKEY;
        //定义接收对象
        List<HeadLine> headLineList = null;
        //定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        //拼接处redis的key
        if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
            key = key + "_" + headLineCondition.getEnableStatus();
        }
        //判断key是否存在
        if (!jedisKeys.exists(key)) {
            //不存在从数据库取
            headLineList = headLineDao.queryHeadLine(headLineCondition);
            //以string形式存入redis
            String jsonString = null;
            try {
                jsonString = mapper.writeValueAsString(headLineList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();

            }
            jedisStrings.set(key, jsonString);

        } else {//若存在直接取出
            String jsonString = jedisStrings.get(key);
            //指定要将String转成的集合类型
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
            try {
                headLineList = mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //return headLineDao.queryHeadLine(headLineCondition);
        return headLineList;
    }
}
