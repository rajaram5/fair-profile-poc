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
import com.github.kburger.fair.poc.workflow.model.SubcomponentShape;
import com.github.kburger.rdf.transmog.repository.RepositoryMapper;
import com.github.kburger.requestresolver.api.RequestUrlResolver;

@RestController
@RequestMapping(SubcomponentController.PATH)
public class SubcomponentController {
    public static final String PATH = "${fair.root:/fdp}/subcomponent";
    private static final Logger logger = LoggerFactory.getLogger(SubcomponentController.class);
    
    @Autowired
    private RequestUrlResolver resolver;
    @Autowired
    private Repository repo;
    @Autowired
    private RepositoryMapper mapper;
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public SubcomponentShape getSubcomponent(@PathVariable String id, HttpServletRequest request) {
        var subject = resolver.resolve(request);
        var component = mapper.read(repo, SubcomponentShape.class, subject);
        
        if (component.isEmpty()) {
            logger.info("Could not find subcomponent for iri {}", subject);
            return null;
        }
        
        return component.orElseThrow();
    }
}
