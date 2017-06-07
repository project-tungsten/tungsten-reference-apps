package com.tungsten.domain.referencedomainservice;

import com.tungsten.domain.referencedomainservice.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public IdGenerator idGenerator() {
    return new IdGenerator();
  }

  @Bean
  @Scope("prototype")
  Logger logger(InjectionPoint injectionPoint) {
    return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
  }

}
