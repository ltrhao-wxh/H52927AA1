package io.dcloud.h52927aa1.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wxhl.core.utils.T;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.dcloud.h52927aa1.Adapter.YunHouAdapter;
import io.dcloud.h52927aa1.Bean.Friends;
import io.dcloud.h52927aa1.R;
import io.dcloud.h52927aa1.Utils.HttpUtil;
import io.dcloud.h52927aa1.Weiget.LoopPicture;
import io.dcloud.h52927aa1.network.RequestAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class HomeFragment extends Fragment {

    private LoopPicture loop;
    private List<Friends> list;
    private RecyclerView recyclerView;
//    private MessageAdapter messageAdapter;
    private YunHouAdapter yunHouAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

    }

    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.myList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String url= RequestAPI.getAbsoluteUrl(RequestAPI.REMEN);

        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String requestDate = response.body().string();

                list = parserJsonWhitGson(requestDate);
                yunHouAdapter=new YunHouAdapter(list,getContext());

                yunHouAdapter.setOnItemClickListener(new YunHouAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (view.getId()){
                            case R.id.iv_head2:
                                T.showShort(getActivity(),"aaaaa");
                                break;
                        }
                    }
                });
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(yunHouAdapter);

                        setHeaderView(recyclerView);
                    }
                });

            }
        });

    }



    private void setHeaderView(RecyclerView view){
        View head=LayoutInflater.from(getActivity()).inflate(R.layout.header, view, false);
//        loop= (LoopPicture) head.findViewById(R.id.looper);
//        int[] imagesRes = new int[]{
//                R.drawable.pic1, R.drawable.pic3, R.drawable.pic4
//        };
//        loop.setImageRes(imagesRes, false);
        yunHouAdapter.setHeaderView(head);
    }



    private List<Friends> parserJsonWhitGson(String responseDate) {

        List<Friends> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(responseDate);
            for (int i = 0; i < jsonArray.length(); i++) {
                Friends friend = new Friends();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("content_id");
                String contentUserId = jsonObject.getString("content_user_id");
                String content = jsonObject.getString("content_content");
                String time = jsonObject.getString("content_time");
                String good = jsonObject.getString("content_good");
                String report = jsonObject.getString("content_report");
                String reportType = jsonObject.getString("content_report_type");
                String hot = jsonObject.getString("content_hot");
                String follow = jsonObject.getString("content_follow");
                String address = jsonObject.getString("content_address");
                String isHot = jsonObject.getString("is_hot");
                String hotSort = jsonObject.getString("hot_sort");
                String conState = jsonObject.getString("con_state");
                String userId = jsonObject.getString("user_id");
                String userName = jsonObject.getString("user_cooked_name");
                String userNumber = jsonObject.getString("user_number");
                String userImage = jsonObject.getString("user_img");
                String userType = jsonObject.getString("user_type");
                String userLevel = jsonObject.getString("user_level");
                String title = jsonObject.getString("title");
                String content_video = jsonObject.getString("content_video");


                // TODO: 17/3/11 vocieList语音解析
                List<String> voiceList = new ArrayList<>();
                JSONArray jsonArray1 = jsonObject.getJSONArray("content_voice");
                for (int j = 0; j < jsonArray1.length(); j++) {
                    voiceList.add(jsonArray1.getString(j));
                }
                friend.setContent_voice(voiceList);



                // TODO: 17/3/11  imageList图片解析
                List<String> imageList = new ArrayList<>();
                JSONArray jsonArray2 = jsonObject.getJSONArray("content_img");
                for (int j = 0; j < jsonArray2.length(); j++) {
                    JSONObject object = jsonArray2.getJSONObject(j);
                    imageList.add(object.getString("b"));
//                    imageList.add(object.getString("b"));
                }
                friend.setContent_img(imageList);
                if (imageList!=null){
                    friend.setVideo(false);
                }else {
                    friend.setVideo(true);
                }




                // TODO: 17/3/11 pp解析
                List<String> ppList = new ArrayList<>();
                JSONArray jsonArray4 = jsonObject.getJSONArray("pp");
                for (int j = 0; j < jsonArray4.length(); j++) {
                    ppList.add(jsonArray4.getString(j));
                }
                friend.setPp(ppList);

                // TODO: 17/3/11 dianzan解析
                List<String> dzList = new ArrayList<>();
                JSONArray jsonArray5 = jsonObject.getJSONArray("dianzan");
                for (int j = 0; j < jsonArray5.length(); j++) {
                    dzList.add(jsonArray5.getString(j));
                }
                friend.setDianzan(dzList);

                // TODO: 17/3/11 其他控件
                friend.setContent_id(id);
                friend.setContent_user_id(contentUserId);
                friend.setContent_content(content);
                friend.setContent_time(time);
                friend.setContent_good(good);
                friend.setContent_report(report);
                friend.setContent_report_type(reportType);
                friend.setContent_hot(hot);
                friend.setContent_follow(follow);
                friend.setContent_address(address);
                friend.setIs_hot(isHot);
                friend.setHot_sort(hotSort);
                friend.setCon_state(conState);
                friend.setUser_id(userId);
                friend.setUser_cooked_name(userName);
                friend.setUser_number(userNumber);
                friend.setUser_img(userImage);
                friend.setUser_type(userType);
                friend.setUser_level(userLevel);
                friend.setTitle(title);
                friend.setContent_video(content_video);

                list.add(friend);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;

    }





    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
