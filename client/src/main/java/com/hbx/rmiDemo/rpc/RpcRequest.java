package com.hbx.rmiDemo.rpc;

import java.io.Serializable;

public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 3216344230397804558L;

    private String className;

    private String methodName;

    private Object[] args;

    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
