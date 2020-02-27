package com.github.kburger.fair.poc.workflow.web.controller;

import java.net.URI;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.kburger.fair.poc.workflow.model.WorkflowShape;

@RestController
@RequestMapping(WorkflowController.PATH)
public class WorkflowController {
    public static final String PATH = "${fair.root:/fdp}/workflow";

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public WorkflowShape getWorkflow(@PathVariable String id) {
        var workflow = new WorkflowShape();
        
        workflow.setWorkflowsubcomponent(List.of(URI.create("http://example.com/")));
        
        return workflow;
    }
}
