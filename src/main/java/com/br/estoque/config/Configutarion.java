package com.br.estoque.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configutarion {

    @Bean
    public ModelMapper modelmapper() {
        return new ModelMapper();
    }
}
