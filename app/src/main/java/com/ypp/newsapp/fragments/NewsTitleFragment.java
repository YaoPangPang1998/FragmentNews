package com.ypp.newsapp.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ypp.newsapp.NewsContentActivity;
import com.ypp.newsapp.R;
import com.ypp.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsTitleFragment extends Fragment {
    private boolean isTwopane;
    private static final String TAG = "ssssssss";
    public NewsTitleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.news_title_fragment, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter=new NewsAdapter(getNews());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<News> getNews() {
        List<News> newsList=new ArrayList<>();
       for (int i=1;i<=50;i++){
           News news=new News();
           news.setTitle("This is news title"+i);
           news.setContext(getRandomLengthContent("This is news content"+i+"."));
           newsList.add(news);
       }
//        newsList.
        return newsList;
    }
    private String getRandomLengthContent(String content){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<length;i++){
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwopane=true;
            Toast.makeText(getContext(), "平板模式", Toast.LENGTH_SHORT).show();
        }else {
            isTwopane=false;
            Toast.makeText(getContext(), "手机模式", Toast.LENGTH_SHORT).show();
        }
    }
    //创建内部类作为adapter
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<News> mNewslist;
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView newsTitleText;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                newsTitleText=itemView.findViewById(R.id.news_title);
            }
        }
        public NewsAdapter(List<News> newsList){
            mNewslist=newsList;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_item,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news=mNewslist.get(holder.getAdapterPosition());
                    if (isTwopane){
                        NewsContentFragment newsContentFragment= (NewsContentFragment) getFragmentManager()
                                .findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(),news.getContext());
                        Log.i(TAG, "onClick: ");
                    }else {
                        NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContext());
                        Log.i(TAG, "onClick: ");
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news=mNewslist.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewslist.size();
        }


    }
}
