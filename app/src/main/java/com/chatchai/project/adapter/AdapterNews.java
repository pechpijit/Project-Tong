package com.chatchai.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatchai.project.R;
import com.chatchai.project.model.ModelNews;

import java.util.ArrayList;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.VersionViewHolder> {
    ArrayList<ModelNews> list;
    String url;
    Context context;
    int[] image;
    OnItemClickListener clickListener;

    public AdapterNews(Context applicationContext, ArrayList<ModelNews> list, int[] image) {
        this.context = applicationContext;
        this.list = list;
        this.image = image;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_news, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int position) {
        versionViewHolder.title.setText(list.get(position).getNewsName());
        versionViewHolder.content.setText(list.get(position).getNewsDetail().substring(0,50)+"...");
        Glide.with(context)
                .load(image[position])
                .placeholder(R.drawable.nopic)
                .into(versionViewHolder.img);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,content;
        ImageView img;


        public VersionViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_title);
            content = (TextView) itemView.findViewById(R.id.txt_content);
            img = (ImageView) itemView.findViewById(R.id.img_news);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}