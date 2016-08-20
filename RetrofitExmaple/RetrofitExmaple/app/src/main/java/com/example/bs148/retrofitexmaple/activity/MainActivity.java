package com.example.bs148.retrofitexmaple.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bs148.retrofitexmaple.AdapterPosition;
import com.example.bs148.retrofitexmaple.Network.ApiClient;
import com.example.bs148.retrofitexmaple.Network.ApiService;
import com.example.bs148.retrofitexmaple.Network.NetworkRequestHandler;
import com.example.bs148.retrofitexmaple.R;
import com.example.bs148.retrofitexmaple.adapter.MoviesAdapter;
import com.example.bs148.retrofitexmaple.constant.ApiKeyConstant;
import com.example.bs148.retrofitexmaple.model.Example;
import com.example.bs148.retrofitexmaple.model.Result;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.RecyclerViewClickListener{
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        if (ApiKeyConstant.API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        NetworkRequestHandler.sentRequest(ApiClient.getClient().create(ApiService.class).getTopRatedMovies(ApiKeyConstant.API_KEY),this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Subscribe
    public void onEvent(Example example){
        List<Result> results=example.getResults();
        //Toast.makeText(getApplicationContext(),""+results.size(),Toast.LENGTH_LONG).show();
        MoviesAdapter adapter=new MoviesAdapter(results, R.layout.list_item_movie, getApplicationContext(),MainActivity.this);
        recyclerView.setAdapter(adapter);
}

    @Override
    public void setRecyclerViewItemClicked(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Selected Position").setMessage("You have selected at position "+position)
                .setPositiveButton(R.string.edit_dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    /*@Subscribe
    public void onEvent(AdapterPosition adapterPosition){
        //Toast.makeText(getApplicationContext(),""+adapterPosition.getPosition(),Toast.LENGTH_LONG).show();
        new AlertDialog.Builder(this)
                .setTitle("Selected Position").setMessage("You have selected at position "+adapterPosition.getPosition())
                .setPositiveButton(R.string.edit_dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }*/
}
