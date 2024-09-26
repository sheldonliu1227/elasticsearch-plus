package com.sheldon.elasticsearch.core;


import com.sheldon.elasticsearch.core.injector.AbstractMethod;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Configuration {
    private final Map<String, AbstractMethod> methods = new ConcurrentHashMap();

    public void addMethod(String name, AbstractMethod method) {

    }
}
