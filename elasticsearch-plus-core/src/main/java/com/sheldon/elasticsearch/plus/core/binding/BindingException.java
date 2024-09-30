package com.sheldon.elasticsearch.core.binding;

public class BindingException extends RuntimeException {

  private static final long serialVersionUID = -1040693388723224252L;

  public BindingException() {
  }

  public BindingException(String message) {
    super(message);
  }

  public BindingException(String message, Throwable cause) {
    super(message, cause);
  }

  public BindingException(Throwable cause) {
    super(cause);
  }
}
