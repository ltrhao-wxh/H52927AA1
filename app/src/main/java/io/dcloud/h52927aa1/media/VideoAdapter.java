package io.dcloud.h52927aa1.media;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import io.dcloud.h52927aa1.R;
import io.dcloud.h52927aa1.VideoItemData;


/**
 * Author  wangchenchen
 * Description video列表adapter
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<VideoItemData> list;
    public VideoAdapter(Context context){
        list=new ArrayList<>();
    }

    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        VideoAdapter.VideoViewHolder holder = new VideoAdapter.VideoViewHolder(view);
        view.setTag(holder);
        return new VideoAdapter.VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoAdapter.VideoViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refresh(List<VideoItemData> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout videoLayout;
        private int position;
        private RelativeLayout showView;


        public VideoViewHolder(View itemView) {
            super(itemView);
            videoLayout = (FrameLayout) itemView.findViewById(R.id.layout_video);
            showView= (RelativeLayout) itemView.findViewById(R.id.showview);
        }

        public void update(final int position) {
            this.position = position;
            showView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showView.setVisibility(View.GONE);
                    if (click != null)
                        click.onclick(position);
                }
            });
        }
    }

    private onClick click;

    public void setClick(onClick click){
        this.click=click;
    }

    public static interface onClick{
        void onclick(int position);
    }
}
