package com.hbx.rmiDemo;

import com.hbx.rmiDemo.rpc.RpcClientProxy;
import com.hbx.rmiDemo.service.ITestService;
import com.hbx.rmiDemo.util.Constants;
import com.hbx.rmiDemo.zkConfig.FindPathServiceImpl;
import com.hbx.rmiDemo.zkConfig.IFindPathService;

public class TestClient {
    public static void main(String[] args) throws InterruptedException {
        IFindPathService findPathService=new
                FindPathServiceImpl(Constants.CONNNECTION_STR);

        RpcClientProxy rpcClientProxy=new RpcClientProxy(findPathService);

        for(int i=0;i<10;i++) {
            ITestService testService = (ITestService)rpcClientProxy.newProxyInstance
                    (ITestService.class, null);
            testService.testSay("测试rpc调用");
            System.out.println(testService.testReturnObj());
            Thread.sleep(1000);
        }
    }
}
