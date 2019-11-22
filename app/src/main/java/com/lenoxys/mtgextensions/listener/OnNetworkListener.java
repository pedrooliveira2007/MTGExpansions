package com.lenoxys.mtgextensions.listener;

import okhttp3.Request;

public interface OnNetworkListener {

    void onFailure(Exception e);

    void onSuccess();
}
