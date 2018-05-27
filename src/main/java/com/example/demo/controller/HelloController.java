package com.example.demo.controller;

import com.example.demo.configure.FooProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * this code is investigation.
 */
@RestController
@RequestMapping(path = "hello")
public class HelloController {

    public HelloController(FooProperties foo, Environment env, BuildProperties prop) {
        this.foo = foo;
        this.env = env;
        this.prop = prop;
    }

    private final FooProperties foo;
    private final Environment env;
    private final BuildProperties prop;

    @GetMapping(path = "world")
    public String greeting() {
        System.out.println(prop.getGroup() + " " + prop.getName() + " " + prop.getVersion());
        System.out.println(foo);

        env.getProperty("my-app.my-module.foo.first-name");
        env.getProperty("java_home");

        return "hello world";
    }

}
