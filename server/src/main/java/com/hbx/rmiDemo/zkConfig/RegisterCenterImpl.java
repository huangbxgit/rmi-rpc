package com.hbx.rmiDemo.zkConfig;

import com.hbx.rmiDemo.util.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class RegisterCenterImpl implements IRegisterCenter {

    private CuratorFramework curatorFramework;

    {
        curatorFramework=CuratorFrameworkFactory.builder().
                connectString(Constants.CONNNECTION_STR).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,
                        10)).build();
        curatorFramework.start();
    }

    public void register(String serviceName, String serviceAddress) {
        //注册相应的服务
        String servicePath=Constants.ZK_REGISTER_PATH+"/"+serviceName;

        try {
            //判断 /registrys/product-service是否存在，不存在则创建
            if(curatorFramework.checkExists().forPath(servicePath)==null){
                curatorFramework.create().creatingParentsIfNeeded().
                        withMode(CreateMode.PERSISTENT).forPath(servicePath,"0".getBytes());
            }

            String addressPath=servicePath+"/"+serviceAddress;
            String rsNode=curatorFramework.create().withMode(CreateMode.EPHEMERAL).
                    forPath(addressPath,"0".getBytes());
            System.out.println("服务注册成功："+rsNode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
