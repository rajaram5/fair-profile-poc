package com.github.kburger.fair.poc.core.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.eclipse.rdf4j.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.kburger.fair.poc.core.model.RepositoryShape;
import com.github.kburger.rdf.transmog.repository.RepositoryMapper;
import com.github.kburger.requestresolver.api.RequestUrlResolver;

@RestController
@RequestMapping(RepositoryController.PATH)
public class RepositoryController {
    public static final String PATH = "${fair.root:/fdp}";
    private static final Logger logger = LoggerFactory.getLogger(RepositoryController.class);
    
    @Autowired
    private RequestUrlResolver resolver;
    @Autowired
    private Repository repo;
    @Autowired
    private RepositoryMapper mapper;
    
    @RequestMapping(method = RequestMethod.GET)
    public RepositoryShape getRepository(HttpServletRequest request) {
        var subject = resolver.resolve(request);
        var repository = mapper.read(repo, RepositoryShape.class, subject);
        
        if (repository.isEmpty()) {
            logger.info("Could not find repository for iri {}", subject);
            return null;
            
        }
        
        return repository.orElseThrow();
    }
}
