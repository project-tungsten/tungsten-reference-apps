package com.tungsten.reference.query.listener;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@StreamListener
public @interface EventHandler {

  @AliasFor(annotation = StreamListener.class, attribute = "target")
  String value() default Sink.INPUT;

  @AliasFor(attribute = "value")
  String target() default Sink.INPUT;

  @AliasFor(annotation = StreamListener.class, attribute = "condition")
  String condition() default "";
}
