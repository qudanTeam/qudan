package com.qudan.qingcloud.msqudan.util.responses.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

/**
 * Created by word on 2016/7/4.
 */
public class NullListSerializer extends JsonSerializer<List> {
    @Override
    public void serialize(List value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if(value == null){
            gen.writeObject(Lists.newArrayList());
        } else {
            gen.writeObject(value);
        }
    }
}
