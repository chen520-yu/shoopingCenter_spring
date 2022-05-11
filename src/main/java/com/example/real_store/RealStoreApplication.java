package com.example.real_store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;


@Configuration
@MapperScan("com.example.real_store.mapper")
@SpringBootApplication
public class RealStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealStoreApplication.class, args);
    }

    @Bean
    public MultipartConfigElement getMulipartConfigElement(){
        MultipartConfigFactory multipartConfigFactory=new MultipartConfigFactory();

        DataSize dataSize=DataSize.ofMegabytes(10);

        multipartConfigFactory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        multipartConfigFactory.setMaxRequestSize(DataSize.of(10,DataUnit.MEGABYTES));

        return multipartConfigFactory.createMultipartConfig();

    }



}
