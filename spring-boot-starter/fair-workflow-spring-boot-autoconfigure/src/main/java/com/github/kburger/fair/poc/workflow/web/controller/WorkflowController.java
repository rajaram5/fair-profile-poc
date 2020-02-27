package com.github.kburger.fair.poc.workflow.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.eclipse.rdf4j.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.kburger.fair.poc.workflow.model.WorkflowShape;
import com.github.kburger.rdf.transmog.repository.RepositoryMapper;
import com.github.kburger.requestresolver.api.RequestUrlResolver;

@RestController
@RequestMapping(WorkflowController.PATH)
public class WorkflowController {
    public static final String PATH = "${fair.root:/fdp}/workflow";
    private static final Logger logger = LoggerFactory.getLogger(WorkflowController.class);
    
    @Autowired
    private RequestUrlResolver resolver;
    @Autowired
    private Repository repo;
    @Autowired
    private RepositoryMapper mapper;
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public WorkflowShape getWorkflow(@PathVariable String id, HttpServletRequest request) {
        var subject = resolver.resolve(request);
        var workflow = mapper.read(repo, WorkflowShape.class, subject);
        
        if (workflow.isEmpty()) {
            logger.info("Could not find workflow for iri {}", subject);
            return null;
        }
        
        return workflow.orElseThrow();
    }
}
