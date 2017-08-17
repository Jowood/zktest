package com.iss.mis.sbs.zk.app.config;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by yufei on 2017/7/19.
 */
public class ZKUtil {
    public static final String FTP_CONFIG_NODE_NAME = "/config/ftp";
    public static final String CORE_SERVER_NODE_NAME = "/core-servers";

    public static ZkClient getZkClient() {
        return new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");

    }
}
