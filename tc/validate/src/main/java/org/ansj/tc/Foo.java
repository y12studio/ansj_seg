package org.ansj.tc;

import org.apache.commons.collections.list.GrowthList;

public class Foo {
    private final String name;

    public Foo(String name) {
        this.name = name;
        new GrowthList();
    }

    public String getName() {
        return name;
    }
}
