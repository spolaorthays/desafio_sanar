package com.example.sanarproject.service;

public interface ServiceListener {

    void onSuccess(Object object);

    void onError(Throwable throwable);
}
