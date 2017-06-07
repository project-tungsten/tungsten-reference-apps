package com.tungsten.domain.referencedomainservice.util;

import java.util.UUID;

public class IdGenerator {

  public String generate() {
    return UUID.randomUUID().toString();
  }
}
