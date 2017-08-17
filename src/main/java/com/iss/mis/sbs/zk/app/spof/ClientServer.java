package com.iss.mis.sbs.zk.app.spof;

import com.iss.mis.sbs.zk.app.config.ZKUtil;
import org.I0Itec.zkclient.ZkClient;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yufei on 2017/8/3.
 */
public class ClientServer {
    private String getCoreServer() {
        ZkClient zk = ZKUtil.getZkClient();
        List<String> servers = ZKUtil.getZkClient().getChildren(ZKUtil.CORE_SERVER_NODE_NAME);
        if (servers.size() <= 0) {
            return null;
        }
        for (String s : servers) {
            System.out.println(s);
        }
        Object[] arr = servers.toArray();
        Arrays.sort(arr);
        String data = zk.readData(ZKUtil.CORE_SERVER_NODE_NAME + "/" + arr[0].toString());
        System.out.println("node: " + arr[0].toString() + ", data:" + data);
        return data;
    }

    public void run() {
        System.out.println("客户端应用运行中，正在调用：" + getCoreServer() + " 上的服务");
    }
}
