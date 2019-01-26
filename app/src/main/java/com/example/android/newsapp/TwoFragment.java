package com.example.android.newsapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * Created by User on 10/7/2018.
 */

public class TwoFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabaseReference;
    ProgressBar progressBar;
    FloatingActionButton fab;


    public TwoFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.first, container, false);
        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Notice");
        mDatabaseReference.keepSynced(true);

        mRecyclerView=(RecyclerView)rootView.findViewById(R.id.myrecycle);
        progressBar=(ProgressBar)rootView.findViewById(R.id.loader);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        progressBar.setVisibility(View.VISIBLE);
        fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(TwoFragment.this).attach(TwoFragment.this).commit();
            }
        });



        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog, MainActivity.BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, MainActivity.BlogViewHolder>
                (Blog.class,R.layout.myrownew,MainActivity.BlogViewHolder.class,mDatabaseReference) {
            @Override
            protected void populateViewHolder(MainActivity.BlogViewHolder viewHolder, final Blog model, int position) {

                progressBar.setVisibility(View.GONE);
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getActivity().getApplicationContext(),model.getImage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), DetailsActivity.class);
                        intent.putExtra("id", model.getTitle());
                        intent.putExtra("id2",model.getDesc());
                        intent.putExtra("id3",model.getImage());
                        startActivity(intent);
                    }
                });


            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }



    public  static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;


        public BlogViewHolder(View itemview)
        {
            super(itemview);


            mView=itemview;
        }
        public void setTitle(String title)
        {
            TextView posttitle=(TextView)mView.findViewById(R.id.posttitle);
            posttitle.setText(title);


        }
        public void setDesc(String desc)
        {
            TextView postdesc=(TextView)mView.findViewById(R.id.aa_desc);
            postdesc.setText(desc);

        }
        public void setImage(Context context, String image)
        {
            ImageView postimage=(ImageView) mView.findViewById(R.id.postimage);
            Picasso.get().load(image).into(postimage);
        }


    }

}
