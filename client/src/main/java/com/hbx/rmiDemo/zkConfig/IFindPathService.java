package com.hbx.rmiDemo.zkConfig;

public interface IFindPathService {

    /**
     * 根据请求的服务名称，获得对应的调用地址
     * @param serviceName
     * @return
     */
    public String findPathByServiceName(String serviceName);
}
