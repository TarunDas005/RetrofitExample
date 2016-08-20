package com.example.bs148.retrofitexmaple.Network;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.bs148.retrofitexmaple.model.Example;
import com.example.bs148.retrofitexmaple.model.Result;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Ashraful on 11/5/2015.
 */
public class CustomCallback<T> implements Callback<T> {
    Context context;
    boolean isTranscactionRequest;

    public ProgressDialog pDialog;

    public void setProgressDialog() {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(true);
        pDialog.setCanceledOnTouchOutside(true);
        if (isTranscactionRequest) {
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);
        }
        pDialog.show();


    }

    public void dismissDialog() {
        try {
            if ((pDialog != null) && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();

        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            pDialog = null;
        }


    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        dismissDialog();
        t.printStackTrace();
        //     EventBus.getDefault().post(t);

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        dismissDialog();
        manipulateBaseResponse(response.body());

    }


    public CustomCallback(Context context) {
        this.context = context;
        setProgressDialog();
    }

    /*public CustomCallback(Context context, boolean isTranscactionRequest) {
        this.context = context;
        this.isTranscactionRequest = true;
        setProgressDialog();
    }*/

    public CustomCallback() {

    }


    public void manipulateBaseResponse(T t) {
        try {
            //Example example = (Example) t;
            //List<Result> results=example.getResults();
            EventBus.getDefault().post(t);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
