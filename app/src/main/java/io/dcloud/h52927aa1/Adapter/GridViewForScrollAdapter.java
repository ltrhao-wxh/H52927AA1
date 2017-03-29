package io.dcloud.h52927aa1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import io.dcloud.h52927aa1.Bean.Friends;
import io.dcloud.h52927aa1.R;
import io.dcloud.h52927aa1.Utils.LoadImageUtils;


/**
 * Created by Administrator on 2017/03/11.
 */
public class GridViewForScrollAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public GridViewForScrollAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
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
        Friends friends=new Friends();
        final ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_images,null);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.iv_images);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        String str=list.get(position);
        LoadImageUtils.loadImage("http://app.shourenapp.com/"+str,viewHolder.imageView);
        return convertView;
    }

    class ViewHolder{

        ImageView imageView;
    }
}
