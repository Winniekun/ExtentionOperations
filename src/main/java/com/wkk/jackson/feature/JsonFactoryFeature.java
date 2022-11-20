package com.wkk.jackson.feature;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.junit.Test;

import java.io.IOException;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/11/13
 */
public class JsonFactoryFeature {
    @Test
    public void testParas() throws IOException {
        JsonFactory factory = new JsonFactory();
        factory.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);

        // 输出写到控制台
        String jsonStr = "{\"realName\" : \"孔维坤\",\"realName\" : \"kongwekun\"}";
        JsonParser jsonParser = factory.createParser(jsonStr);
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.getCurrentName();
            if ("name".equals(fieldname)) {
                System.out.println(jsonParser.nextTextValue());
            }
        }
        jsonParser.close();

    }
}
