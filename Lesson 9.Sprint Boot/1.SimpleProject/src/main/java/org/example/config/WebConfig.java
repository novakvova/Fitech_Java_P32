package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value( "${upload.dir}")
    private String uploadDir;

    @Value( "${upload.image}")
    private String uploadImage;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve /uploads/** URLs from the filesystem "uploads" folder
        registry.addResourceHandler("/"+uploadDir+"/**")
                .addResourceLocations("file:"+uploadDir+"/");

        registry.addResourceHandler("/"+uploadImage+"/**")
                .addResourceLocations("file:"+uploadImage+"/");
    }
}
