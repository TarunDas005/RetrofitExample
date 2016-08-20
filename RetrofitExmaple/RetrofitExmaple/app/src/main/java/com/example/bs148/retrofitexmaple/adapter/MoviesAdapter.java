package com.example.bs148.retrofitexmaple.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bs148.retrofitexmaple.AdapterPosition;
import com.example.bs148.retrofitexmaple.R;
import com.example.bs148.retrofitexmaple.activity.MainActivity;
import com.example.bs148.retrofitexmaple.model.Result;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by BS148 on 8/18/2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Result> movies;
    private int rowLayout;
    private Context context;
    private static RecyclerViewClickListener onRecyclerViewClickListener;
    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;


        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            /*int x=getAdapterPosition();
            AdapterPosition adapterPosition=new AdapterPosition(x);
            EventBus.getDefault().post(adapterPosition);*/
            if(onRecyclerViewClickListener!=null){
               // int x=getAdapterPosition();
                onRecyclerViewClickListener.setRecyclerViewItemClicked(getAdapterPosition());
            }

        }
    }


    public MoviesAdapter(List<Result> movies, int rowLayout, Context context,MainActivity activity) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.onRecyclerViewClickListener= (RecyclerViewClickListener) activity;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface RecyclerViewClickListener{
        public void setRecyclerViewItemClicked(int position);
    }

}
