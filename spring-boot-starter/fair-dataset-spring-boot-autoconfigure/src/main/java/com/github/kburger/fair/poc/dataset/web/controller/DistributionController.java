package com.github.kburger.fair.poc.dataset.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DistributionController.PATH)
public class DistributionController {
    public static final String PATH = "${fair.root:/fdp}/distribution";

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Object getDistribution(@PathVariable String id) {
        return "hello distribution " + id;
    }
}
