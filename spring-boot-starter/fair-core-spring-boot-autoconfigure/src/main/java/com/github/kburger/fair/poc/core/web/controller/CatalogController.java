package com.github.kburger.fair.poc.core.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.eclipse.rdf4j.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.kburger.fair.poc.core.model.CatalogShape;
import com.github.kburger.rdf.transmog.repository.RepositoryMapper;
import com.github.kburger.requestresolver.api.RequestUrlResolver;

@RestController
@RequestMapping(CatalogController.PATH)
public class CatalogController {
    public static final String PATH = RepositoryController.PATH + "/catalog";
    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);
    
    @Autowired
    private RequestUrlResolver resolver;
    @Autowired
    private Repository repo;
    @Autowired
    private RepositoryMapper mapper;
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CatalogShape getCatalog(@PathVariable String id, HttpServletRequest request) {
        var subject = resolver.resolve(request);
        var catalog = mapper.read(repo, CatalogShape.class, subject);
        
        if (catalog.isEmpty()) {
            logger.info("Could not find catalog for iri {}", subject);
            return null;
        }
        
        return catalog.orElseThrow();
    }
}
