package com.bus.tracking.bus_tracking_system.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dyefuw9od");
        config.put("api_key", "992211924573491");
        config.put("api_secret", "LeedHSyo7HTGW7iIuLpJ1mMVr5E");

        return new Cloudinary(config);
    }
}
