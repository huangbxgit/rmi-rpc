package com.hbx.rmiDemo.loadbalance;

import java.util.List;

public interface LoadBanalce {

    String selectHost(List<String> repos);
}


