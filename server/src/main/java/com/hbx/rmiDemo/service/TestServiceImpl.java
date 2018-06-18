package com.hbx.rmiDemo.service;

import com.hbx.rmiDemo.annotion.RpcAnnotation;

@RpcAnnotation(ITestService.class)
public class TestServiceImpl implements ITestService{
    public void testSay(String some) {
        System.out.println("打印==================");
        System.out.println(some);
    }

    public Object testReturnObj() {
        return "abcdefg===============";
    }
}
