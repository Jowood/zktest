package com.iss.mis.sbs.zk;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by yufei on 2017/7/18.
 */
public class ZooKeeperHello2 {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
        String node = "/app2";
        if (!zkClient.exists(node)) {
            zkClient.createPersistent(node, "hello zk");
        }

        System.out.println(zkClient.readData(node));
        zkClient.delete(node);
    }
}
