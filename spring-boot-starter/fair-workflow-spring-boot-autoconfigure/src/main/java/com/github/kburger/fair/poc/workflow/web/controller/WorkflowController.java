package com.github.kburger.fair.poc.workflow.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(WorkflowController.PATH)
public class WorkflowController {
    public static final String PATH = "${fair.root:/fdp}/workflow";

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Object getWorkflow(@PathVariable String id) {
        return "hello workflow " + id;
    }
}
