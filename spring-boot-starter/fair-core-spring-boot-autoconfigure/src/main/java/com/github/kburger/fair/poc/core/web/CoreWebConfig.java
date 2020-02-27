package com.github.kburger.fair.poc.core.web;

import java.io.IOException;
import java.util.List;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.repository.util.Repositories;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParserRegistry;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.github.kburger.fair.poc.core.web.converter.TransmogMapperHttpMessageConverter;
import com.github.kburger.rdf.transmog.api.analyzer.Analyzer;
import com.github.kburger.rdf.transmog.core.CoreMapper;
import com.github.kburger.rdf.transmog.core.analyzer.CoreAnalyzer;
import com.github.kburger.rdf.transmog.repository.RepositoryMapper;
import com.github.kburger.requestresolver.api.RequestUrlResolver;
import com.github.kburger.requestresolver.core.CoreRequestUrlResolver;
import com.github.kburger.requestresolver.core.FileExtensionRequestTransformer;

@Configuration
@ComponentScan
public class CoreWebConfig implements WebMvcConfigurer, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(CoreWebConfig.class);
    
    @Autowired
    private List<TransmogMapperHttpMessageConverter> converters;
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.parseMediaType(RDFFormat.TURTLE.getDefaultMIMEType()));
        
        converters.forEach(conv -> conv.configure(configurer));
    }
    
    @Value("classpath:/data/bootstrap.ttl")
    private Resource bootstrap;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        var repo = repository();
        
        Repositories.consume(repo, conn -> {
            try {
                conn.add(bootstrap.getInputStream(), "", RDFFormat.TURTLE);
            } catch (IOException e) {
                logger.warn("Failed to bootstrap data", e);
            }
            
            logger.info("Any results from query? {}", conn.prepareBooleanQuery("ASK { ?s ?p ?o }").evaluate());
        });
    }
    
    @Bean(initMethod = "init", destroyMethod = "shutDown")
    @ConditionalOnMissingBean
    public Repository repository() {
        return new SailRepository(new MemoryStore());
    }
    
    @Bean
    @ConditionalOnMissingBean
    public RepositoryMapper repoMapper(Analyzer analyzer) {
        var mapper = new RepositoryMapper(analyzer);
        
        mapper.registerDefaults();
        mapper.discover();
        
        return mapper;
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
