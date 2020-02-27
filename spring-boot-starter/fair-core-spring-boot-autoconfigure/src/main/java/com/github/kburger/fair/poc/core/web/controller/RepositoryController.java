package com.github.kburger.fair.poc.core.web.controller;

import java.net.URI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.kburger.fair.poc.core.model.RepositoryShape;

@RestController
@RequestMapping(RepositoryController.PATH)
public class RepositoryController {
    public static final String PATH = "${fair.root:/fdp}";

    @RequestMapping(method = RequestMethod.GET)
    public RepositoryShape getRepository() {
        var repository = new RepositoryShape();
        
        repository.setConformsTo(URI.create("http://purl.org/fair/profile/core"));
        
        return repository;
    }
}
