package com.wkk.jackson.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/11/7
 */
public class MyObjectCodec extends ObjectCodec {
    @Override
    public Version version() {
        return null;
    }

    @Override
    public <T> T readValue(JsonParser jsonParser, Class<T> valueType) throws IOException {

        // {"name":"YourBatman","age":18,"dog":{"name":"旺财","color":"WHITE"},"hobbies":["篮球","football"]}
        // 使用JsonParser读取此json串，并且封装到valueType类型的JavaBean里
        // 重点说明：次数实例理应通过valueType来构造，且赋值方面大量用到反射。
        Person person = new Person();

        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.getCurrentName();
            if ("name".equals(fieldname)) {
                person.setName(jsonParser.nextTextValue());
            } else if ("age".equals(fieldname)) {
                person.setAge(jsonParser.nextIntValue(0));
            } else if ("pet".equals(fieldname)) {
                jsonParser.nextToken();
                // 构造一个dog实例（同样的，实际场景是利用反射构造的哦）
                Pet cat = new Pet();
                person.setPet(cat);

                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = jsonParser.getCurrentName();
                    if ("name".equals(fieldName)) {
                        cat.setName(jsonParser.nextTextValue());
                    } else if ("color".equals(fieldName)) {
                        cat.setColor(jsonParser.nextTextValue());
                    } else if ("type".equals(fieldName)) {
                        cat.setType(jsonParser.nextTextValue());
                    }
                }
            } else if ("hobbies".equals(fieldname)) {
                jsonParser.nextToken();

                List<String> hobbies = new ArrayList<>();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    // hobbies.add(jsonParser.nextTextValue()); // 请注意:此处不能用next哦~
                    hobbies.add(jsonParser.getText());
                }
                person.setHobbies(hobbies);
            }
        }
        return (T) person;

    }

    @Override
    public <T> T readValue(JsonParser p, TypeReference<T> valueTypeRef) throws IOException {
        return null;
    }

    @Override
    public <T> T readValue(JsonParser p, ResolvedType valueType) throws IOException {
        return null;
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser p, Class<T> valueType) throws IOException {
        return null;
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser p, TypeReference<T> valueTypeRef) throws IOException {
        return null;
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser p, ResolvedType valueType) throws IOException {
        return null;
    }

    @Override
    public void writeValue(JsonGenerator gen, Object value) throws IOException {

    }

    @Override
    public <T extends TreeNode> T readTree(JsonParser p) throws IOException {
        return null;
    }

    @Override
    public void writeTree(JsonGenerator gen, TreeNode tree) throws IOException {

    }

    @Override
    public TreeNode createObjectNode() {
        return null;
    }

    @Override
    public TreeNode createArrayNode() {
        return null;
    }

    @Override
    public JsonParser treeAsTokens(TreeNode n) {
        return null;
    }

    @Override
    public <T> T treeToValue(TreeNode n, Class<T> valueType) throws JsonProcessingException {
        return null;
    }
}
