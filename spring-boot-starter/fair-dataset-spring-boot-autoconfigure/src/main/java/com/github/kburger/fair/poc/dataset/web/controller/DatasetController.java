package com.github.kburger.fair.poc.dataset.web.controller;

import java.net.URI;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.kburger.fair.poc.dataset.model.DatasetShape;

@RestController
@RequestMapping(DatasetController.PATH)
public class DatasetController {
    public static final String PATH = "${fair.root:/fdp}/dataset";

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public DatasetShape getDataset(@PathVariable String id) {
        var dataset = new DatasetShape();
        
        dataset.setConformsTo(URI.create("http://purl.org/fair/profile/core"));
        
        return dataset;
    }
}
