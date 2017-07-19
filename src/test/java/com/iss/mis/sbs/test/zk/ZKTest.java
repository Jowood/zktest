package com.iss.mis.sbs.test.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by yufei on 2017/7/18.
 */
public class ZKTest extends BaseBean{

    private ZkClient zk;

    private String nodeName = "/myApp";

    @Before
    public void initTest() {
        super.setLoggerClass(this.getClass());
        zk = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
    }

    @After
    public void dispose() {
        zk.close();
        logger.info("zkclient closed!");
    }

    @Test
    public void testListener() throws InterruptedException{
        //监听指定节点的数据变化
        zk.subscribeDataChanges(nodeName, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                logger.info("node data changed!");
                logger.info("node=>" + s);
                logger.info("data=>" + o);
                logger.info("-------------------");
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                logger.info("node data deleted!");
                logger.info("s=>" + s);
                logger.info("-------------------");
            }
        });
        logger.info("ready!");
        /*while (true) {
            TimeUnit.SECONDS.sleep(5);

        }*/
    }


    @Test
    public void testUpdateConfig() throws InterruptedException {
        if (!zk.exists(nodeName)) {
            zk.createPersistent(nodeName);
        }
        zk.writeData(nodeName, "1");
        zk.writeData(nodeName, "2");
        zk.delete(nodeName);
        zk.delete(nodeName);  //删除一个不存在的node，并不会报错
    }
}
