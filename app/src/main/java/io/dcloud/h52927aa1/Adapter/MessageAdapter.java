package io.dcloud.h52927aa1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.dcloud.h52927aa1.Acticity.ImageDetailActivity;
import io.dcloud.h52927aa1.Bean.Friends;
import io.dcloud.h52927aa1.R;


/**
 * Created by wuxiaohui on 17/3/11.
 */

public class MessageAdapter extends BaseAdapter{
    private Context context;
    private List<Friends>list;






    public MessageAdapter(List<Friends> list, Context context) {
        this.list = list;
        this.context = context;
    }


    public void setList(List<Friends> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final List<String> imagesList = list.get(position).getContent_img();

        if (convertView == null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_friend,null);

            viewHolder=new ViewHolder();
            viewHolder.roundHead= (ImageView) convertView.findViewById(R.id.iv_head2);
            viewHolder.photo= (ImageView) convertView.findViewById(R.id.iv_images);
//            viewHolder.share= (ImageView) convertView.findViewById(R.id.iv_share);
            viewHolder.userName= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.title= (TextView) convertView.findViewById(R.id.tv_message);
            viewHolder.guanxipiao= (TextView) convertView.findViewById(R.id.tv_guanXiPiao);
            viewHolder.time= (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.gridView= (GridView) convertView.findViewById(R.id.gv);

            viewHolder.onItemClickListener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int tag = (int) viewHolder.gridView.getTag();
                    Intent intent = new Intent(context, ImageDetailActivity.class);
                    intent.putExtra("index", position);
                    intent.putStringArrayListExtra("imageList", (ArrayList<String>) list.get(tag).getContent_img());
                    context.startActivity(intent);
                }
            };
            viewHolder.gridViewForScrollAdapter = new GridViewForScrollAdapter(imagesList, context);
            viewHolder.gridView.setOnItemClickListener(viewHolder.onItemClickListener);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Friends friends = list.get(position);
        String imgUrl = list.get(position).getUser_img();
        x.image().loadDrawable(imgUrl, ImageOptions.DEFAULT, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable drawable) {
                viewHolder.roundHead.setImageDrawable(drawable);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
            }
            @Override
            public void onCancelled(CancelledException e) {
            }
            @Override
            public void onFinished() {
            }
        });

        String lvUrl = list.get(position).getUser_level();
        Log.e("--Main--",lvUrl);


        viewHolder.userName.setText(friends.getUser_cooked_name());
        viewHolder.guanxipiao.setText(friends.getUser_number());
        viewHolder.time.setText(friends.getContent_time());
        viewHolder.title.setText(friends.getTitle());

        viewHolder.gridViewForScrollAdapter.setList(imagesList);
        viewHolder.gridView.setAdapter(viewHolder.gridViewForScrollAdapter);
        viewHolder.gridView.setTag(position);

        return convertView;
    }



    class ViewHolder{
        ImageView roundHead,share,photo;//头像 等级 分享
        TextView userName,guanxipiao,time,title;//id 关系票 时间 个性标签
        GridView gridView;
        GridViewForScrollAdapter gridViewForScrollAdapter;
        AdapterView.OnItemClickListener onItemClickListener;
    }

}
