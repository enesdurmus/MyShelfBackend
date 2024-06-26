package com.enesd.myshelfbackend.config;

import com.enesd.myshelfbackend.utils.CustomModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CustomModelMapper customModelMapper() {
        return new CustomModelMapper();
    }
}