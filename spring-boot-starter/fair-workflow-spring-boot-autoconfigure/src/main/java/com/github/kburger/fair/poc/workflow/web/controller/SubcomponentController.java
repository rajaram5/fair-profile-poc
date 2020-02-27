package com.github.kburger.fair.poc.workflow.web.controller;

import java.net.URI;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.kburger.fair.poc.workflow.model.SubcomponentShape;

@RestController
@RequestMapping(SubcomponentController.PATH)
public class SubcomponentController {
    public static final String PATH = "${fair.root:/fdp}/subcomponent";
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public SubcomponentShape getSubcomponent(@PathVariable String id) {
        var component = new SubcomponentShape();
        
        component.setWorkflowdescription(URI.create("http://example.com/workflow/desc"));
        
        return component;
    }
}
