package com.hbx.rmiDemo;

import com.hbx.rmiDemo.rpc.RpcServer;
import com.hbx.rmiDemo.service.ITestService;
import com.hbx.rmiDemo.service.TestServiceImpl;
import com.hbx.rmiDemo.zkConfig.IRegisterCenter;
import com.hbx.rmiDemo.zkConfig.RegisterCenterImpl;

public class TestStartServer {
    public static void main(String[] args) throws Exception{
        ITestService testService = new TestServiceImpl();
        IRegisterCenter registerCenter=new RegisterCenterImpl();

        RpcServer rpcServer=new RpcServer(registerCenter,"127.0.0.1:8080");
        rpcServer.bind(testService);
        rpcServer.publisher();
        System.in.read();
    }
}
