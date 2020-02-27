package com.github.kburger.fair.poc.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.github.kburger.fair.poc.core.web.CoreWebConfig;

@Configuration
@Import(CoreWebConfig.class)
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "fair.core.enabled", havingValue = "true", matchIfMissing = true)
public class CoreAutoConfiguration {

}
