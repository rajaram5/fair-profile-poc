package com.github.kburger.fair.poc.workflow;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.github.kburger.fair.poc.workflow.web.WorkflowWebConfig;

@Configuration
@Import(WorkflowWebConfig.class)
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "fair.workflow.enabled", havingValue = "true", matchIfMissing = true)
public class WorkflowAutoConfiguration {

}
