package com.apptware.interview.singleton;

public class Singleton {

  // Volatile keyword ensures that multiple threads handle the instance variable correctly
  private static volatile Singleton instance = null;

  public String s;

  // Private constructor to prevent instantiation
  private Singleton() {
    s = "Hello I am a string part of Singleton class";
  }

  // Double-checked locking for thread-safe lazy initialization
  public static Singleton getInstance() {
    if (instance == null) {
      synchronized (Singleton.class) {
        if (instance == null) {
          instance = new Singleton();
        }
      }
    }
    return instance;
  }

  // Ensure Singleton instance is maintained during deserialization
  private Object readResolve() {
    return getInstance();
  }

  // Prevent cloning
  @Override
  protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Cannot clone a Singleton");
  }

  // Ensure consistent hashCode
  @Override
  public int hashCode() {
    return System.identityHashCode(instance);
  }
}
