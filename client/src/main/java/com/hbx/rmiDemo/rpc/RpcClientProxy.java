package com.hbx.rmiDemo.rpc;

import com.hbx.rmiDemo.zkConfig.IFindPathService;

import java.lang.reflect.Proxy;

public class RpcClientProxy {

    private IFindPathService findPathService;

    public RpcClientProxy(IFindPathService findPathService) {
        this.findPathService = findPathService;
    }

    public <T>T newProxyInstance( Class<T> interfaceClass,String version){
        RemoteInvocationHandler invocate = new RemoteInvocationHandler(version,findPathService);
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[]{interfaceClass},invocate);
    }

}
