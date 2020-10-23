package com.wkk.jackson;

import org.junit.Test;

/**
 * @author kongwiki@163.com
 * @since 2020/10/23
 */
public class DemoBeanTest {
    @Test
    public void test() throws Exception {
        DemoBean bean = DemoBean.builder().age("111").build();
        String s = JsonUtils.bean2Json(bean);
        System.out.println(s);
    }

}