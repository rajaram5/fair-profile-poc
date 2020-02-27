package com.github.kburger.fair.poc.dataset.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DatasetController.PATH)
public class DatasetController {
    public static final String PATH = "${fair.root:/fdp}/dataset";

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Object getDataset(@PathVariable String id) {
        return "hello dataset " + id;
    }
}
