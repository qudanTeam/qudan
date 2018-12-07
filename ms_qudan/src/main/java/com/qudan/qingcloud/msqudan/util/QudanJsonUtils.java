package com.qudan.qingcloud.msqudan.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by word on 2016/6/16.
 */
public class QudanJsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(QudanJsonUtils.class);

    /**
     * json字符串解析成map
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parseJSONToMap(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            map = objectMapper.readValue(jsonStr, Map.class);
        } catch (JsonParseException e) {
            logger.error("json decode error:", e);
        } catch (JsonMappingException e) {
            logger.error("json decode error:", e);
        } catch (IOException e) {
            logger.error("json decode error:", e);
        }
        return map;
    }

    /**
     * 将json string反序列化成对象
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T parseJSONToObject(String json, Class<T> valueType){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return  new ObjectMapper().readValue(json, valueType);
        } catch (JsonParseException e) {
            logger.error("json encode error", e);
        } catch (JsonMappingException e) {
            logger.error("json encode error", e);
        } catch (IOException e) {
            logger.error("json encode error", e);
        } catch (Exception e){
            logger.error("json encode error", e);
        }
        return null;
    }

    public static String parseMapToJSON(Map<String, Object> map){
        try {
            return  new ObjectMapper().writeValueAsString(map);
        } catch (JsonParseException e) {
            logger.error("json encode error", e);
        } catch (JsonMappingException e) {
            logger.error("json encode error", e);
        } catch (IOException e) {
            logger.error("json encode error", e);
        }
        return null;
    }

    public static String parseObjectToJSON(Object object){
        try {
            return  new ObjectMapper().writeValueAsString(object);
        } catch (JsonParseException e) {
            logger.error("json encode error", e);
        } catch (JsonMappingException e) {
            logger.error("json encode error", e);
        } catch (IOException e) {
            logger.error("json encode error", e);
        }
        return null;
    }
}
