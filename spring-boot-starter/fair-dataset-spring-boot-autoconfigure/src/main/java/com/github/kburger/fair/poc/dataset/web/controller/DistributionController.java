package com.github.kburger.fair.poc.dataset.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.eclipse.rdf4j.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.kburger.fair.poc.dataset.model.DistributionShape;
import com.github.kburger.rdf.transmog.repository.RepositoryMapper;
import com.github.kburger.requestresolver.api.RequestUrlResolver;

@RestController
@RequestMapping(DistributionController.PATH)
public class DistributionController {
    public static final String PATH = "${fair.root:/fdp}/distribution";
    private static final Logger logger = LoggerFactory.getLogger(DistributionController.class);
    
    @Autowired
    private RequestUrlResolver resolver;
    @Autowired
    private Repository repo;
    @Autowired
    private RepositoryMapper mapper;
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public DistributionShape getDistribution(@PathVariable String id, HttpServletRequest request) {
        var subject = resolver.resolve(request);
        var distribution = mapper.read(repo, DistributionShape.class, subject);
        
        if (distribution.isEmpty()) {
            logger.info("Could not find distribution for iri {}", subject);
            return null;
        }
        
        return distribution.orElseThrow();
    }
}
