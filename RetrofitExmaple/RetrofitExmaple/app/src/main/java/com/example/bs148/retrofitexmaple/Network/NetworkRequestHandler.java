package com.example.bs148.retrofitexmaple.Network;

import android.content.Context;

import retrofit2.Call;

/**
 * Created by BS148 on 8/18/2016.
 */
public class NetworkRequestHandler {

    public static<T> void sentRequest(Call<T> call, Context context){
        CustomCallback<T> customCallback=new CustomCallback<T>(context);
        call.enqueue(customCallback);
    }
}
