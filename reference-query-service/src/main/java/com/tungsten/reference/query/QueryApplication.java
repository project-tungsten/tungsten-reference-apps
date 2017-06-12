package com.tungsten.reference.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binding.StreamListenerAnnotationBeanPostProcessor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.stream.config.BindingServiceConfiguration.STREAM_LISTENER_ANNOTATION_BEAN_POST_PROCESSOR_NAME;

@SpringBootApplication
@EnableBinding(Sink.class)
public class QueryApplication {

  private static final String AXON_MESSAGE_TYPE_PATTERN = "headers['axon-message-type']=='%s'";

  public static void main(String[] args) {
    SpringApplication.run(QueryApplication.class, args);
  }

  @Bean
  @Scope("prototype")
  Logger logger(InjectionPoint injectionPoint) {
    return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
  }

  /**
   * Override the default {@link StreamListenerAnnotationBeanPostProcessor} to inject value of
   * 'eventType' attribute into 'condition' expression.
   *
   * @return the bean post processor bean
   */
  @Bean(name = STREAM_LISTENER_ANNOTATION_BEAN_POST_PROCESSOR_NAME)
  public BeanPostProcessor streamListenerAnnotationBeanPostProcessor() {
    return new StreamListenerAnnotationBeanPostProcessor() {
      @Override
      protected StreamListener postProcessAnnotation(StreamListener originalAnnotation, Method annotatedMethod) {
        Map<String, Object> attributes = new HashMap<>(
            AnnotationUtils.getAnnotationAttributes(originalAnnotation));
        if (StringUtils.hasText(originalAnnotation.condition())) {
          String spelExpression = String.format(AXON_MESSAGE_TYPE_PATTERN, originalAnnotation.condition());
          attributes.put("condition", spelExpression);
        }
        return AnnotationUtils.synthesizeAnnotation(attributes, StreamListener.class, annotatedMethod);
      }
    };
  }

}
