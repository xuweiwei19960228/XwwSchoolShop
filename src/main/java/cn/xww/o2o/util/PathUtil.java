package cn.xww.o2o.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PathUtil {
    private static String separator = System.getProperty("file.separator");
    //返回图片根路径路径
    public static  String getImgBasePath() throws UnsupportedEncodingException {
        //系统类型
        String os = System.getProperty("os.name");
        String basePath = "";
        //Windows系统
        if(os.toLowerCase().startsWith("win")){
            basePath = "E:/projectdev/image/";
        }else{
            //其他系统
            basePath = "/home/xww/image";
        }
        //将分隔符用系统分隔符替换
        basePath = basePath.replace("/",separator);

        return basePath;
    }

    //返回项目图片子路径(存放位置
    public static String getShopImagePath(long shopId){
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/",separator);
    }

}
