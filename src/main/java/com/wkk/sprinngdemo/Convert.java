package com.wkk.sprinngdemo;

/**
 * @author kongweikun@163.com
 * @date 2023/4/7
 */
public interface Convert<PARAM> {

    OperateLogDO convert(PARAM param);

}
