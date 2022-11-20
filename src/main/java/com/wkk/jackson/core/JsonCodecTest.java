package com.wkk.jackson.core;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/11/7
 */
public class JsonCodecTest {
    JsonFactory factory = new JsonFactory();

    @Test
    public void test() throws IOException {
        JsonParser jsonParser = factory.createParser(new File("/Users/weikunkun/IdeaProjects/ExtentionOperations/src/main/resources/person.json"));
        jsonParser.setCodec(new MyObjectCodec());

        Person person = jsonParser.readValueAs(Person.class);
        System.out.println(person);

        jsonParser.close();

    }
}
