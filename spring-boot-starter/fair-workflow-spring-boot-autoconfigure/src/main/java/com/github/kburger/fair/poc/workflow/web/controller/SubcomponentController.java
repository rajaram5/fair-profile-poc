package com.github.kburger.fair.poc.workflow.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SubcomponentController.PATH)
public class SubcomponentController {
    public static final String PATH = "${fair.root:/fdp}/subcomponent";
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Object getSubcomponent(@PathVariable String id) {
        return "hello subcomponent " + id;
    }
}
