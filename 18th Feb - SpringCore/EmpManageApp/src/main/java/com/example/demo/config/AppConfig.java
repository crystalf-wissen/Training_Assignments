package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.example.demo.service.EmpFactory;
import com.example.demo.service.Menu;

@Configuration
@ComponentScan(basePackages = "com.example") 
public class AppConfig {
}