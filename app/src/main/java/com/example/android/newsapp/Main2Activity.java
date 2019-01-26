package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Notice");
        mDatabaseReference.keepSynced(true);

        mRecyclerView=(RecyclerView)findViewById(R.id.myrecycle1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog, MainActivity.BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, MainActivity.BlogViewHolder>
                (Blog.class,R.layout.myrownew,MainActivity.BlogViewHolder.class,mDatabaseReference) {
            @Override
            protected void populateViewHolder(MainActivity.BlogViewHolder viewHolder, final Blog model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
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
