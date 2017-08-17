package com.iss.mis.sbs.zk.app.spof;

import com.iss.mis.sbs.zk.app.config.ZKUtil;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by yufei on 2017/8/3.
 */
public class CoreServer {
    private String hostName;

    public CoreServer(String hostName) {
        this.hostName = hostName;
    }

    public void start() {
        ZkClient zk = ZKUtil.getZkClient();

        if(!zk.exists(ZKUtil.CORE_SERVER_NODE_NAME)) {
            zk.createPersistent(ZKUtil.CORE_SERVER_NODE_NAME);
        }
        zk.createEphemeralSequential(ZKUtil.CORE_SERVER_NODE_NAME + "/server", hostName);
        System.out.println(hostName + " is running...");
    }

    public String getHostName() {
        return this.hostName;
    }
}
