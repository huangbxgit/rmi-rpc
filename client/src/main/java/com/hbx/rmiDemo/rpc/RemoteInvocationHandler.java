package com.hbx.rmiDemo.rpc;

import com.hbx.rmiDemo.zkConfig.IFindPathService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

    private IFindPathService findPathService;

    private String version;

    public RemoteInvocationHandler(String version,IFindPathService findPathService) {
        this.findPathService = findPathService;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setArgs(args);
        request.setVersion(version);
        TcpTransmission tcp = new TcpTransmission(findPathService.findPathByServiceName(request.getClassName()));
        return tcp.send(request);
    }
}
