package com.github.kburger.fair.poc.core.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CatalogController.PATH)
public class CatalogController {
    public static final String PATH = RepositoryController.PATH + "/catalog";

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Object getCatalog(@PathVariable String id) {
        return "hello catalog " + id;
    }
}
