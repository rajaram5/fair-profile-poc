package com.github.kburger.fair.poc.core.web.converter;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.rdf4j.common.lang.FileFormat;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import com.github.kburger.rdf.transmog.api.TransmogException;
import com.github.kburger.rdf.transmog.core.CoreMapper;
import com.github.kburger.requestresolver.api.RequestUrlResolver;

public class TransmogMapperHttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    @Autowired
    private CoreMapper mapper;
    @Autowired
    private RequestUrlResolver resolver;
    
    @Autowired
    private HttpServletRequest request;
    
    private final RDFFormat format;
    
    public TransmogMapperHttpMessageConverter(RDFFormat format) {
        super(parseMimeTypes(format));
        
        this.format = format;
    }
    
    public void configure(ContentNegotiationConfigurer configurer) {
        configurer.mediaType(format.getDefaultFileExtension(), MediaType.parseMediaType(format.getDefaultMIMEType()));
    }
    
    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.getPackageName().startsWith("com.github.kburger.fair.poc");
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        var subject = resolver.resolve(request);
        
        var obj = mapper.read(inputMessage.getBody(), clazz, subject, format);
        
        if (obj.isEmpty()) {
            throw new HttpMessageNotReadableException("", inputMessage);
        }
        
        return obj.orElseThrow();
    }

    @Override
    protected void writeInternal(Object t, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        var subject = resolver.resolve(request);
        
        try {
            mapper.write(outputMessage.getBody(), t, subject, format);
        } catch (TransmogException e) {
            throw new HttpMessageNotWritableException(e.getMessage());
        }
    }
    
    private static MediaType[] parseMimeTypes(FileFormat format) {
        return format.getMIMETypes()
                .stream()
                .map(MediaType::parseMediaType)
                .toArray(MediaType[]::new);
    }
}
