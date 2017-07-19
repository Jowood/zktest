package com.iss.mis.sbs.test.zk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yufei on 2017/7/18.
 */
public class BaseBean {
    protected Logger logger;

    public void setLoggerClass(Class aClass) {
        logger = LoggerFactory.getLogger(aClass);
    }
}
