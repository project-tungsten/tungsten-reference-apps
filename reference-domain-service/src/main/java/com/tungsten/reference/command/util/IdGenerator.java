package com.tungsten.reference.command.util;

import java.util.UUID;

public class IdGenerator {

  public String generate() {
    return UUID.randomUUID().toString();
  }
}
