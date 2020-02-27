package com.github.kburger.fair.poc.core.web.converter;

import org.eclipse.rdf4j.rio.RDFParserRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

public class HttpMessageConverterInitializer implements ApplicationContextInitializer<GenericApplicationContext> {
    private static final Logger logger = LoggerFactory.getLogger(HttpMessageConverterInitializer.class);
    
    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        for (var format : RDFParserRegistry.getInstance().getKeys()) {
            logger.info("Registering message converter {} with extension {} and mime-type {}",
                    format.getName(), format.getDefaultFileExtension(), format.getDefaultMIMEType());
            
            applicationContext.registerBean(format.getName(), TransmogMapperHttpMessageConverter.class,
                    () -> new TransmogMapperHttpMessageConverter(format));
        }
    }
}
