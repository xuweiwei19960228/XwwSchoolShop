package cn.xww.o2o.service.Impl;

import cn.xww.o2o.cache.JedisUtil;
import cn.xww.o2o.dao.AreaDao;
import cn.xww.o2o.domain.Area;
import cn.xww.o2o.exceptions.AreaOperationException;
import cn.xww.o2o.service.AreaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private JedisUtil.Keys keys;
    @Autowired
    private JedisUtil.Strings strings;

    private static String AREALISTKEY = "arealist";

    @Transactional
    public List<Area> getAreaList() {
        String key = AREALISTKEY;
        List<Area> areaList = null;
        ObjectMapper mapper = new ObjectMapper();
        //判断key是否存在于redis
        if (!keys.exists(key)) {
            areaList = areaDao.queryArea();
            //将相关实体类集合转成string，存入redis对应的key
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                throw new AreaOperationException(e.getMessage());
            }
            strings.set(key,jsonString);
        }else{//redis里已经存在该key,则从redis返回
            String jsonString = strings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,Area.class);
            try {
                areaList = mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }
}


