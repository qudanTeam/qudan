package com.qudan.qingcloud.msqudan.util.responses;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * Created by word on 2015/12/23.
 */
public class JSONResponseUtil {

    private static Logger logger = LoggerFactory.getLogger(JSONResponseUtil.class);

    private static Charset charset = Charset.forName("UTF-8");


    public static void strictJsonResponse(HttpServletResponse response,
                                     Object data){
        JsonResponse(response, strictParseToJson(data));
    }

    public static void strictJsonResponse(HttpServletResponse response,
                                          Object data, int httpStatuCode){
        JsonResponse(response, strictParseToJson(data), httpStatuCode);
    }

    public static void JsonResponse(HttpServletResponse response,
                                          Object data) throws Exception{
        JsonResponse(response, parseToJson(data));
    }

    public static void JsonResponse(HttpServletResponse response,
                                    Object data, int httpStatuCode){
        JsonResponse(response, parseToJson(data), httpStatuCode);
    }

    /**
     * 返回生成json的数据流
     */
    public static void JsonResponse(HttpServletResponse response, String jsonString){
        PrintWriter out = null;
        try {
            StringBuffer sb = new StringBuffer();
            sb.append(jsonString);
            response.setContentType("application/json; charset=UTF-8");
            response.setContentLength(sb.toString().getBytes(charset).length);
            out = response.getWriter();
            out.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("response输出错误：",e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 返回生成json的数据流
     */
    public static void JsonResponse(HttpServletResponse response, String jsonString, int httpStatuCode) {
        PrintWriter out = null;
        response.setStatus(httpStatuCode);
        try {
            StringBuffer sb = new StringBuffer();
            sb.append(jsonString);
            response.setContentType("application/json; charset=UTF-8");
            response.setContentLength(sb.toString().getBytes(charset).length);
            out = response.getWriter();
            out.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("response输出错误：",e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 严格模式
     * @param data
     * @return
     */
    public static String strictParseToJson(Object data){
        JSONObject.DEFFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
        return JSONObject.toJSONString(data, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.PrettyFormat);
    }

    /**
     * 正常模式
     * @param data
     * @return
     */
    public static String parseToJson(Object data){
        return JSONObject.toJSONString(data);
    }


    /**
     * 把符合moore_app_API规范的JSON字符串写入respones
     * @param response
     * @param apiResponsesEntity
     * @throws Exception
     */
    public static void APIStrictJsonResponse(HttpServletResponse response,
                                          ApiResponseEntity apiResponsesEntity){
        JsonResponse(response, strictParseToJson(apiResponsesEntity.getResponseData()),apiResponsesEntity.getCode().value());
    }
}
