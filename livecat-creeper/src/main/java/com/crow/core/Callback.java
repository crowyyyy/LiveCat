package com.crow.core;

import java.io.Serializable;

@FunctionalInterface
public interface Callback<T> extends Serializable {

    long serialVersionUID = 1L;
    void callback(T obj);
}
