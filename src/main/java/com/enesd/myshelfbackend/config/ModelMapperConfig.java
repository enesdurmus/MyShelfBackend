package com.enesd.myshelfbackend.config;

import com.enesd.myshelfbackend.utils.CustomModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new CustomModelMapper();
        return modelMapper;
    }
}