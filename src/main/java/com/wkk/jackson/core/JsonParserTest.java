package com.wkk.jackson.core;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/11/7
 */
public class JsonParserTest {

    JsonFactory factory = new JsonFactory();

    @Test
    public void testParser() throws IOException {

        // 此处InputStream来自于文件
        JsonParser jsonParser = factory.createParser(new File("/Users/weikunkun/IdeaProjects/ExtentionOperations/src/main/resources/person.json"));

        // 只要还没到末尾，也就是}这个符号，就一直读取
        // {"name":"YourBatman","age":18,"dog":{"name":"旺财","color":"WHITE"},"hobbies":["篮球","football"]}
        JsonToken jsonToken = null; // token类型
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.getCurrentName();
            if ("name".equals(fieldname)) {
                jsonToken = jsonParser.nextToken();
                System.out.println("==============token类型是：" + jsonToken);
                System.out.println(jsonParser.getText());
            } else if ("age".equals(fieldname)) {
                jsonToken = jsonParser.nextToken();
                System.out.println("==============token类型是：" + jsonToken);
                System.out.println(jsonParser.getIntValue());
            } else if ("pet".equals(fieldname)) {
                jsonToken = jsonParser.nextToken();
                System.out.println("==============token类型是：" + jsonToken);
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    String petFieldName = jsonParser.getCurrentName();
                    if ("name".equals(petFieldName)) {
                        jsonToken = jsonParser.nextToken();
                        System.out.println("======================token类型是：" + jsonToken);
                        System.out.println(jsonParser.getText());
                    } else if ("color".equals(petFieldName)) {
                        jsonToken = jsonParser.nextToken();
                        System.out.println("======================token类型是：" + jsonToken);
                        System.out.println(jsonParser.getText());
                    } else if ("type".equals(jsonParser.getText())) {
                        jsonToken = jsonParser.nextToken();
                        System.out.println("======================token类型是：" + jsonToken);
                        System.out.println(jsonParser.getText());
                    }
                }
            } else if ("hobbies".equals(fieldname)) {
                jsonToken = jsonParser.nextToken();
                System.out.println("==============token类型是：" + jsonToken);
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    System.out.println(jsonParser.getText());
                }
            }
        }

        // 关闭IO流
        jsonParser.close();

    }
}
