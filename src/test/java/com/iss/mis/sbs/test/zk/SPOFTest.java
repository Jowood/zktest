package com.iss.mis.sbs.test.zk;

import com.iss.mis.sbs.zk.app.spof.ClientServer;
import com.iss.mis.sbs.zk.app.spof.CoreServer;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by yufei on 2017/8/3.
 */
public class SPOFTest {
    
    @Test
    public void startCoreServer1() throws InterruptedException {
        CoreServer server1 = new CoreServer("server1");
        
        server1.start();

        while (true) {
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    public void startCoreServer2() throws InterruptedException {
        CoreServer server2 = new CoreServer("server2");
        server2.start();
        while(true) {
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    public void testSPOF() throws InterruptedException {
        ClientServer clientServer = new ClientServer();
        clientServer.run();

        TimeUnit.SECONDS.sleep(60);
        //再次运行
        clientServer.run();
    }
    

}