package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FooImpl implements Foo {

    @Override
    public void print() {
        System.out.println("FooImpl working");
    }
}
