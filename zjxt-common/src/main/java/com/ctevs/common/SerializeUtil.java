package com.ctevs.common;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 序列化常用类
 *
 */
public class SerializeUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SerializeUtil.class);

    final static ObjectMapper mapper;
    static{
        mapper = new ObjectMapper();
    }

    public static String writeAsString(Object object){
        try {
            return mapper.writeValueAsString(object);
        }catch (Exception e) {
            LOG.warn("serialize object has error");
        }
        return "";
    }

    public static <T> T readValue(String content, Class<T> type) throws Exception{
        if(!CommonUtils.checkIfJSON(content)){
            throw new Exception("格式错误");
        }
        try {
            return mapper.readValue(content, type);
        } catch (Exception e) {
            LOG.warn("serialize parse value["+content+"] to object has error");
            throw new Exception("serialize object has error");
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T> T readValue(String content, TypeReference valueTypeRef) throws Exception{
        if(!CommonUtils.checkIfJSON(content)){
            return null;
        }
        try {
            return mapper.readValue(content, valueTypeRef);
        } catch (Exception e) {
            LOG.warn("serialize parse value["+content+"] to object has error");
            throw new Exception("serialize object has error");
        }
    }

    /**
     * Json字符串转Java对象
     */
    public static Object json2Object(String jsonString, Class<?> c) {

        if (jsonString == null || "".equals(jsonString)) {
            return "";
        } else {
            try {
                return mapper.readValue(jsonString, c);
            } catch (Exception e) {
                LOG.warn("json error:" + e.getMessage());
            }

        }
        return "";
    }

}
