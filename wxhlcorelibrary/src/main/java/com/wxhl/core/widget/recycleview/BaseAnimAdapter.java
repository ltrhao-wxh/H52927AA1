package com.wxhl.core.widget.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by qiao yuandong.
 * 2015年11月4日,0004.
 */
public abstract class BaseAnimAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private int lastPosition = -1;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setAnimation(holder.itemView, position);

    }

    private void setAnimation(View viewToAnimate, int position) {
//        if (position > lastPosition) {
////            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.item_slide_bottom_up);
//            AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f,1.0f);
//            alphaAnimation.setDuration(1000);
//            viewToAnimate.startAnimation(alphaAnimation);
//            lastPosition = position;
//        }
    }
}