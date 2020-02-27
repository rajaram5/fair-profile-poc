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
import com.github.kburger.fair.poc.dataset.model.DatasetShape;
import com.github.kburger.rdf.transmog.repository.RepositoryMapper;
import com.github.kburger.requestresolver.api.RequestUrlResolver;

@RestController
@RequestMapping(DatasetController.PATH)
public class DatasetController {
    public static final String PATH = "${fair.root:/fdp}/dataset";
    private static final Logger logger = LoggerFactory.getLogger(DatasetController.class);
    
    @Autowired
    private RequestUrlResolver resolver;
    @Autowired
    private Repository repo;
    @Autowired
    private RepositoryMapper mapper;
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public DatasetShape getDataset(@PathVariable String id, HttpServletRequest request) {
        var subject = resolver.resolve(request);
        var dataset = mapper.read(repo, DatasetShape.class, subject);
        
        if (dataset.isEmpty()) {
            logger.info("Could not find dataset for iri {}", subject);
            return null;
        }
        
        return dataset.orElseThrow();
    }
}
