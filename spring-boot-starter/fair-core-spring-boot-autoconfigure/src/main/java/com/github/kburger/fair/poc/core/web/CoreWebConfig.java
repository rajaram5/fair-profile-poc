package com.github.kburger.fair.poc.core.web;

import java.util.List;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParserRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.github.kburger.fair.poc.core.web.converter.TransmogMapperHttpMessageConverter;
import com.github.kburger.rdf.transmog.api.analyzer.Analyzer;
import com.github.kburger.rdf.transmog.core.CoreMapper;
import com.github.kburger.rdf.transmog.core.analyzer.CoreAnalyzer;
import com.github.kburger.requestresolver.api.RequestUrlResolver;
import com.github.kburger.requestresolver.core.CoreRequestUrlResolver;
import com.github.kburger.requestresolver.core.FileExtensionRequestTransformer;

@Configuration
@ComponentScan
public class CoreWebConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(CoreWebConfig.class);
    
    @Autowired
    private List<TransmogMapperHttpMessageConverter> converters;
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.parseMediaType(RDFFormat.TURTLE.getDefaultMIMEType()));
        
        converters.forEach(conv -> conv.configure(configurer));
    }
    
    @Bean
    @ConditionalOnMissingBean
    public static Analyzer anayzer() {
        logger.info("Creating transmog analyzer");
        
        return new CoreAnalyzer();
    }
    
    @Bean
    @ConditionalOnMissingBean
    public static CoreMapper mapper(Analyzer analyzer) {
        logger.info("Creating transmog mapper");
        
        var mapper = new CoreMapper(analyzer);
        
        mapper.registerDefaults();
        mapper.discover();
        
        return mapper;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public static RequestUrlResolver resolver() {
        var resolver = new CoreRequestUrlResolver();
        
        var transformer = new FileExtensionRequestTransformer();
        
        RDFParserRegistry.getInstance().getKeys()
                .stream()
                .map(RDFFormat::getDefaultFileExtension)
                .forEach(transformer::register);
        
        return resolver;
    }
}
