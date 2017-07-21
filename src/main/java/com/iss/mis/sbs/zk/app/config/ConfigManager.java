package com.iss.mis.sbs.zk.app.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by yufei on 2017/7/19.
 */
public class ConfigManager {
    private FtpConfig ftpConfig;

    public void loadConfigFromDB() {
        //query config from database
        //TODO...
        ftpConfig = new FtpConfig(21, "192.168.1.1", "test", "123456");
    }

    public void updateFtpConfigToDB(int port, String host, String user, String password) {
        if (ftpConfig == null) {
            ftpConfig = new FtpConfig();
        }
        ftpConfig.setHost(host);
        ftpConfig.setPort(port);
        ftpConfig.setUser(user);
        ftpConfig.setPassword(password);

        //write to db...
        //TODO...
    }

    public void syncFtpConfigToZk() throws JsonProcessingException {
        ZkClient zk = ZKUtil.getZkClient();
        if (!zk.exists(ZKUtil.FTP_CONFIG_NODE_NAME)) {
            zk.createPersistent(ZKUtil.FTP_CONFIG_NODE_NAME, true);
        }
        zk.writeData(ZKUtil.FTP_CONFIG_NODE_NAME, ftpConfig);
        zk.close();
    }

}
