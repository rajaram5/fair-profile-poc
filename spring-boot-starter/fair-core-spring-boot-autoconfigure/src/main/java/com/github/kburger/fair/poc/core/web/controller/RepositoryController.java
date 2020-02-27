package com.github.kburger.fair.poc.core.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RepositoryController.PATH)
public class RepositoryController {
    public static final String PATH = "${fair.root:/fdp}";

    @RequestMapping(method = RequestMethod.GET)
    public Object getRepository() {
        return "hello world";
    }
}
