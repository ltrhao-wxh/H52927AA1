package io.dcloud.h52927aa1.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.wxhl.core.utils.IntentUtil;
import com.wxhl.core.utils.T;

import java.util.List;

import io.dcloud.h52927aa1.Acticity.SomeOneActivity;
import io.dcloud.h52927aa1.Bean.Friends;
import io.dcloud.h52927aa1.R;
import io.dcloud.h52927aa1.Utils.LoadImageUtils;


/**
 * 2017
 */

public class YunHouAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    private List<Friends> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private RequestQueue queue;
    private final ImageLoader loader;
    private View mHeaderView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    /**
     * 新建两个私有变量用于保存用户设置的监听器及其set方法
     * @param mOnItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    public YunHouAdapter(final List<Friends> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        queue = Volley.newRequestQueue(context);
        loader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            private LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(8 * 1024 * 1024) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };

            @Override
            public Bitmap getBitmap(String url) {
                return lruCache.get(url);
            }
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url, bitmap);
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView;
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new NOtimeViewHolder(mHeaderView);
        }
        contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new NOtimeViewHolder(contentView);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {


        if (getItemViewType(position) == TYPE_NORMAL) {
            if (viewHolder instanceof NOtimeViewHolder) {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                Friends friends = list.get(position - 1);
                setData((NOtimeViewHolder) viewHolder, friends);
                return;
            }
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {

            return;
        } else {
            return;
        }


    }



    //HeaderView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null) {
            return list.size();
        } else if (mHeaderView == null) {
            return list.size() + 1;
        } else if (mHeaderView != null) {
            return list.size() + 1;
        } else {
            return list.size() + 2;
        }
    }

    /**
     * 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    *
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }


    public void setData(final NOtimeViewHolder viewHolder, Friends friends) {


        viewHolder.userName.setText(friends.getUser_cooked_name());
        viewHolder.guanxipiao.setText(friends.getUser_number());
        viewHolder.time.setText(friends.getContent_time());
        viewHolder.title.setText(friends.getTitle());
        viewHolder.content.setText(friends.getContent_content());
        viewHolder.lv.setText(friends.getUser_level());
        LoadImageUtils.loadImage(friends.getUser_img(), viewHolder.roundHead);


        viewHolder.gridViewForScrollAdapter = new GridViewForScrollAdapter(friends.getContent_img(), context);

        viewHolder.gridView.setAdapter(viewHolder.gridViewForScrollAdapter);

        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(viewHolder.itemView,position); // 2

                }
            });
        }
        if(mOnItemLongClickListener != null){
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = viewHolder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(viewHolder.itemView,position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }

    }

    public class NOtimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView roundHead, share, photo;//头像 等级 分享
        TextView userName, guanxipiao, time, title, lv, content;//id 关系票 时间 个性标签 等级 发布的文字内容
        GridView gridView;
        GridViewForScrollAdapter gridViewForScrollAdapter;
        int position;

        public NOtimeViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            }
            roundHead = (ImageView) itemView.findViewById(R.id.iv_head2);
            lv = (TextView) itemView.findViewById(R.id.tv_LV);
            photo = (ImageView) itemView.findViewById(R.id.iv_images);
//            viewHolder.share= (ImageView) convertView.findViewById(R.id.iv_share);
            userName = (TextView) itemView.findViewById(R.id.tv_name);
            title = (TextView) itemView.findViewById(R.id.tv_message);
            guanxipiao = (TextView) itemView.findViewById(R.id.tv_guanXiPiao);
            time = (TextView) itemView.findViewById(R.id.tv_time);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            gridView = (GridView) itemView.findViewById(R.id.gv);


            roundHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IntentUtil.startActivity(context, SomeOneActivity.class);
                }
            });

        }

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_head2:
                    T.show(context,"@2222");
                    break;
            }
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }


}
