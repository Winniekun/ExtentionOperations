package com.wkk.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/3/8
 */
@Slf4j
public class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {

    }

    private static final JacksonAnnotationIntrospector IGNOREJSONTYPEINFOINTROSPECTOR = new JacksonAnnotationIntrospector() {
        @Override
        public JsonInclude.Value findPropertyInclusion(Annotated a) {
            return JsonInclude.Value.construct(JsonInclude.Include.ALWAYS, JsonInclude.Include.ALWAYS);
        }
    };

    static {
        init();
    }

    private static void init() {
        OBJECT_MAPPER.setAnnotationIntrospector(IGNOREJSONTYPEINFOINTROSPECTOR);
        // 已明确空值类型设置对应默认值
        // 其余未设置默认值的空值类型返回null
//        OBJECT_MAPPER.setSerializerFactory(OBJECT_MAPPER.getSerializerFactory()
//                .withSerializerModifier(new SpecialSerializerModifier()));
    }

    /**
     * null字段也返回
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> String bean2Json(T bean) {
        try {
            return OBJECT_MAPPER.writeValueAsString(bean);
        } catch (Exception var2) {
            log.error(var2.getMessage(), var2);
            return "";
        }
    }

}
