package com.qudan.qingcloud.msqudan.util.responses.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by word on 2016/7/7.
 */
public class NullDataSerializer extends JsonSerializer<NullData> {

    @Override
    public void serialize(NullData value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeRaw("{}");
    }
}
