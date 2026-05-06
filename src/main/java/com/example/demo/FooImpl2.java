package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class FooImpl2 implements Foo {

    @Override
    public void print() {
        System.out.println("FooImpl2 working");
    }
}
