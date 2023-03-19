package com.wkk.jackson.chapter02;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Test;

import java.io.IOException;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/11/20
 */
public class Chapter02 {
    @Test
    public void testJsonGenrator() throws IOException {
        JsonFactory factory = new JsonFactory();

        try(JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.writeStartObject();
            generator.writeStringField("name","孔维坤");
            generator.writeNumberField("age", 18);
            generator.writeEndObject();
        }


    }
}
