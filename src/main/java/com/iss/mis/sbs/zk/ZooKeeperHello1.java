package com.iss.mis.sbs.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Created by yufei on 2017/7/18.
 */
public class ZooKeeperHello1 {

    public static void  main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 300000, new ZooKeeperHello.DemoWatcher());//连接zk server
        if (!zk.getState().equals(ZooKeeper.States.CONNECTED)) {
            while (true) {
                if (zk.getState().equals(ZooKeeper.States.CONNECTED)) {
                    break;
                }

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }




        String node = "/app1";
        Stat stat = zk.exists(node, false);//检测/app1是否存在
        if (stat == null) {
            //创建节点
            System.out.println("创建节点");
            String createResult = zk.create(node, "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(createResult);
        }

        byte[] b = zk.getData(node, false, stat);
        System.out.println("test:"+new String(b));
        zk.delete(node, -1);

    }

}
