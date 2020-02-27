package com.github.kburger.fair.poc.dataset;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.github.kburger.fair.poc.dataset.web.DatasetWebConfig;

@Configuration
@Import(DatasetWebConfig.class)
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "fair.dataset.enabled", havingValue = "true", matchIfMissing = true)
public class DatasetAutoConfiguration {

}
