package com.github.kburger.fair.poc.core.web.controller;

import java.net.URI;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.kburger.fair.poc.core.model.CatalogShape;

@RestController
@RequestMapping(CatalogController.PATH)
public class CatalogController {
    public static final String PATH = RepositoryController.PATH + "/catalog";

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CatalogShape getCatalog(@PathVariable String id) {
        var catalog = new CatalogShape();
        
        catalog.setConformsTo(URI.create("http://purl.org/fair/profile/core"));
        
        return catalog;
    }
}
