package com.wkk.singleton;

/**
 * @author weikunkun
 * @since 2021/4/15
 */
public class InternClassLoad {
    private InternClassLoad() {

    }

    private static class SingleTonHolder {
        private static InternClassLoad INSTANCE = new InternClassLoad();
    }

    public static  InternClassLoad getInstance() {
        return SingleTonHolder.INSTANCE;
    }
}
