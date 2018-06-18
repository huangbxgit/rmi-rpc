package com.hbx.rmiDemo.zkConfig;

import com.hbx.rmiDemo.loadbalance.LoadBanalce;
import com.hbx.rmiDemo.loadbalance.RandomLoadBanalce;
import com.hbx.rmiDemo.util.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

public class FindPathServiceImpl implements IFindPathService {

    List<String> repos=new ArrayList<String>();

    private String address;

    private CuratorFramework curatorFramework;

    public FindPathServiceImpl(String address) {
        this.address = address;

        curatorFramework=CuratorFrameworkFactory.builder().
                connectString(address).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,
                        10)).build();
        curatorFramework.start();
    }

    public String findPathByServiceName(String serviceName) {
        String path=Constants.ZK_REGISTER_PATH+"/"+serviceName;
        System.out.println(path);
        try {
            repos=curatorFramework.getChildren().forPath(path);

        } catch (Exception e) {
            throw new RuntimeException("获取子节点异常："+e);
        }
        //动态发现服务节点的变化
        registerWatcher(path);
        LoadBanalce loadBanalce = new RandomLoadBanalce();
        return loadBanalce.selectHost(repos);
    }

    private void registerWatcher(final String path) {
        PathChildrenCache childrenCache=new PathChildrenCache
                (curatorFramework,path,true);

        PathChildrenCacheListener pathChildrenCacheListener=new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                repos=curatorFramework.getChildren().forPath(path);
            }
        };
        childrenCache.getListenable().addListener(pathChildrenCacheListener);
        try {
            childrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("注册PatchChild Watcher 异常"+e);
        }
    }
}
