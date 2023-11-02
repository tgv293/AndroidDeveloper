package com.giapvantai.moviestreaming.Fragmentsnetflix;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.giapvantai.moviestreaming.Adapters.Moviesadapter;
import com.giapvantai.moviestreaming.R;
import com.giapvantai.moviestreaming.models.Moviemodel;
import com.giapvantai.moviestreaming.viewmodels.Movieviewmodel;

import java.util.List;


public class Morelikethisfragment extends Fragment {

RecyclerView mltrc;
int genreid;

Movieviewmodel movieviewmodel;

List<Moviemodel>morelikthisdata;

public  Morelikethisfragment(int genreid)
{
this.genreid=genreid;
}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_morelikethisfragment, container, false);

        mltrc=view.findViewById(R.id.mltRc);

        Log.e("#",genreid+"");

        movieviewmodel=new Movieviewmodel();
        movieviewmodel  = new ViewModelProvider(this).get(Movieviewmodel.class);

movieviewmodel.getMorelikethis().observe(getViewLifecycleOwner(), new Observer<List<Moviemodel>>() {
    @Override
    public void onChanged(List<Moviemodel> moviemodels) {
        Log.e("#","morevlike this  "+moviemodels.get(0).getImage());

        Moviesadapter moviesadapter=new Moviesadapter(getActivity(),moviemodels);
        GridLayoutManager layoutManageronlyn = new GridLayoutManager(getActivity(), 3);
        mltrc.setLayoutManager(layoutManageronlyn);
        mltrc.setAdapter(moviesadapter);

    }
});



        movieviewmodel.fetchMorelikethis(genreid);
        return  view;
    }
}