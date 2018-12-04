package com.qudan.qingcloud.msqudan.util.responses.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class NullBigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if(value == null){
            gen.writeNumber(0);
        } else {
            gen.writeNumber(value.intValue());
        }
    }
}
