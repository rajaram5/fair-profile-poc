package com.github.kburger.fair.poc.dataset.web.controller;

import java.net.URI;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.kburger.fair.poc.dataset.model.DistributionShape;

@RestController
@RequestMapping(DistributionController.PATH)
public class DistributionController {
    public static final String PATH = "${fair.root:/fdp}/distribution";

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public DistributionShape getDistribution(@PathVariable String id) {
        var distribution = new DistributionShape();
        
        distribution.setConformsTo(URI.create("http://example.com/fair/profile/core"));
        
        return distribution;
    }
}
