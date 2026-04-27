package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestFinalInjection {

    @Autowired
    private final Foo foo;

    public TestFinalInjection(Foo foo) {
        this.foo = foo;
    }

    public void test() {
        foo.print();
    }
}
