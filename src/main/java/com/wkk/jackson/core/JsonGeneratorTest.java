package com.wkk.jackson.core;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/11/7
 */
public class JsonGeneratorTest {

    @Test
    public void testJsonFactory() throws IOException {
        JsonFactory factory = new JsonFactory();

        // 此处最终输输出到OutputStreams输出流（此处输出到文件）
        JsonGenerator jsonGenerator = factory.createGenerator(new File("/Users/weikunkun/IdeaProjects/ExtentionOperations/src/main/resources/person.json"), JsonEncoding.UTF8);
        jsonGenerator.writeStartObject(); //开始写，也就是这个符号 {

        jsonGenerator.writeStringField("name", "孔维坤");
        jsonGenerator.writeNumberField("age", 18);

        // 写入Dog对象（枚举对象）
        jsonGenerator.writeFieldName("pet");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", " 局长");
        jsonGenerator.writeStringField("color", "蓝色");
        jsonGenerator.writeStringField("type", "猫");
        jsonGenerator.writeEndObject();

        //写入一个数组格式
        jsonGenerator.writeFieldName("hobbies"); // "hobbies" :
        jsonGenerator.writeStartArray(); // [
        jsonGenerator.writeString("篮球"); // "篮球"
        jsonGenerator.writeString("乒乓球"); // "football"
        jsonGenerator.writeEndArray(); // ]

        jsonGenerator.writeEndObject(); //结束写，也就是这个符号 }

        // 关闭IO流
        jsonGenerator.close();

    }
}
