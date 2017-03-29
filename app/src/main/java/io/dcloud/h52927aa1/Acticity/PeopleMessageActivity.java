package io.dcloud.h52927aa1.Acticity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wxhl.core.utils.IntentUtil;
import com.wxhl.core.utils.T;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import fj.edittextcount.lib.FJEditTextCount;
import io.dcloud.h52927aa1.Base.BaseActivity;
import io.dcloud.h52927aa1.Bean.Message;
import io.dcloud.h52927aa1.Bean.Phone;
import io.dcloud.h52927aa1.Bean.User;
import io.dcloud.h52927aa1.R;
import io.dcloud.h52927aa1.Weiget.WheelView;
import io.dcloud.h52927aa1.network.RequestAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class PeopleMessageActivity extends BaseActivity {
    private static final String TAG = "-PeopleMessageActivity-";
    /*组件*/
    private RelativeLayout switchAvatar, title;
    private ImageView mImageView;
    private FJEditTextCount fjEdit;
    private TextView textView;
    private WheelView wvc, wv;
    private EditText rank;
    private Button save;
    private String[] items = new String[]{"选择本地图片", "拍照"};

    private static final String[] PLANETS = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Uranus", "Neptune", "Pluto"};

    /* 请求码*/
    private static final int CAMERA_CODE = 1;
    private static final int GALLERY_CODE = 2;
    private static final int CROP_CODE = 3;
    private Uri uri;
    private OkHttpClient client;


    @Override
    protected int getContentView() {
        return R.layout.activity_people_message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
        setIv_left(R.drawable.back_shap);
        setTv_title("用户信息");
        initOkHttp();
        //whereStar();
        init();
    }

    /**
     * 判断是新用户完善资料还是旧用户跳转修改资料
     */
    private void whereStar() {
        if (Phone.getMessage().equals("3")) {//新用户完善资料
            T.showShort(PeopleMessageActivity.this,"您是新用户,需要完善个人资料");
            postMessage();
        } else {//旧用户

        }
    }


    /**
     * 初始化okHttp
     */
    private void initOkHttp() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 向服务器请求数据接口
     * <p>
     * user_id  post  返回数据
     */
    private void postMessage() {
        int user_id = Phone.getUserid();
        RequestBody requestBodyPost = new FormBody.Builder()
                .add("user_id", String.valueOf(user_id))
                .build();
        Request requestPost = new Request.Builder()
                .url(RequestAPI.BASE_URL + RequestAPI.USERID)///url拼接
                .post(requestBodyPost)
                .build();
        client.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String requestDate = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FirstLoginGson(requestDate);
                        Log.e(TAG, requestDate);
                    }
                });
            }
        });
    }


    /**
     * 解析post获取的用户信息
     *  /**
     * user_id : 304   唯一标识账号
     * user_name : null 用户账号
     * user_pwd : null  用户密码
     * user_cooked_name :  老熟名称
     * user_img : null 用户头像
     * user_tel : 18747892334用户手机号
     * user_em : null   用户邮箱
     * user_sex : 3    性别：1男2女3保密
     * user_autograph : null 用户个性签名
     * user_rtime : null	用户注册时间
     * user_stime : null	用户登录时间
     * user_atime : null    认证为老熟的时间
     * user_type : 0000     类型：0普通用户 1认证老熟 2官方机构3工作室
     * user_checkstatus : null 审核状态 0 待审核， 1 未通过 ，2通过
     * user_state : 1   帐号状态 1正常，2冻结，3删除
     * user_province : null 省
     * user_city : null     市
     * user_area : null     自治区
     * user_voice : null    老熟个人语音信息
     * user_video : null    老熟个人视频信息
     * user_title : null    老熟职称
     * user_lvli : null     老熟履历
     * user_income : 0      关系票数->收入
     * user_number : 0      关系票数->余额
     * user_report : null   被举报次数+1
     * user_report_type : null  被举报类型
     * user_follow : null   关注人数
     * user_follow_ids : null   关注的老熟ID(使用英文半角逗号[,]分割)
     * user_openid :    微信openid
     * user_wxinfo : null   微信相关信息
     * user_th_type : 0     绑定类型：0,未绑定， 1微信
     * user_device : null   设备id，唯一标示
     * user_pay_num : 0     用户支付费用
     * user_pay_mian : 1    是否免费追问 1 免费 2收费
     * user_touxian : null  用户头衔
     * user_info_img : null 用户简介图片
     * user_changenumber : 1    用户修改名称次数
     * user_hangye : 1  关联label表 id
     * user_collection : null   悬赏收藏 1,2,3,4....
     * user_hot_friend : 3  朋友圈热门发布次数
     * user_friend_save : null  朋友圈收藏ID 1,2,3,4
     * user_level : 1   级别
     * user_total : 0   用户累计历史关系票
     * user_exp : 0 用户当前等级的经验值
     *
     *
     * @param requestDate
     */
    private void FirstLoginGson(String requestDate) {
        try {
            JSONObject jsonObject = new JSONObject(requestDate);

            User user=new User();
            String user_cooked_name=jsonObject.getString("user_cooked_name");
            String user_img=jsonObject.getString("user_img");
            String user_sex=jsonObject.getString("user_sex");
            String user_touxian=jsonObject.getString("user_touxian");
            String user_hangye=jsonObject.getString("user_hangye");//行业
            String user_lvli=jsonObject.getString("user_lvli");   //用户签名
            String user_pay_num=jsonObject.getString("user_pay_num");//提问关系票价格
            String user_pay_mian=jsonObject.getString("user_pay_mian");//是否免费追问

            user.setUser_cooked_name(user_cooked_name);
            user.setUser_img(user_img);
            user.setUser_sex(user_sex);
            user.setUser_touxian(user_touxian);
            user.setUser_hangye(user_hangye);
            user.setUser_lvli(user_lvli);
            user.setUser_pay_num(user_pay_num);
            user.setUser_pay_mian(user_pay_mian);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    /**
     * 初始化组件
     */
    private void init() {

        switchAvatar = (RelativeLayout) findViewById(R.id.rl_head);
        mImageView = (ImageView) findViewById(R.id.iv_head);
        textView = (TextView) findViewById(R.id.tv_title1);
        rank = (EditText) findViewById(R.id.et_rank);
        save = (Button) findViewById(R.id.btn_save);
//        save.setOnClickListener(this);
        rank.setEnabled(true);
        rank.setInputType(InputType.TYPE_CLASS_TEXT);
        rank.setSelection(rank.getText().length());


        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if (imm != null) {
            imm.showSoftInput(rank, 0); //第二次点击后显示软键盘
            //imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);//隐藏软键盘
        }
        wvc = (WheelView) findViewById(R.id.wv_id);
        wvc.setOffset(1);
        wvc.setItems(Arrays.asList(PLANETS));
        wvc.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.e(TAG, "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
        switchAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        title = (RelativeLayout) findViewById(R.id.rl_title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View outerView = LayoutInflater.from(PeopleMessageActivity.this).inflate(R.layout.wheel_view, null);
                wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setOffset(2);
                wv.setItems(Arrays.asList(PLANETS));
                wv.setSeletion(3);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                        textView.setText(item);
                    }
                });

                new AlertDialog.Builder(PeopleMessageActivity.this)
                        .setTitle("WheelView in Dialog")
                        .setView(outerView)
                        .setPositiveButton("OK", null)
                        .show();


            }
        });
        fjEdit = (FJEditTextCount) findViewById(R.id.et_message);
        fjEdit.setEtHint("点击编辑属于你的熟人简介")//设置提示文字
                .setEtMinHeight(20)//设置最小高度，单位px
                .setLength(150)//设置总字数
                //TextView显示类型(SINGULAR单数类型)(PERCENTAGE百分比类型)
                .setType(FJEditTextCount.SINGULAR)
                .setLineColor("#ffffff")//设置横线颜色
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 显示选择对话框
     */
    private void showDialog() {

        new AlertDialog.Builder(this)
                .setTitle("设置头像")
                .setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                //从相册选取
                                chooseFromGallery();
                                break;
                            case 1:
                                //拍照选择
                                chooseFromCamera();
                                break;
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }

    /**
     * 从相册选择图片
     */
    private void chooseFromGallery() {
        //构建一个内容选择的Intent
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //设置选择类型为图片类型
        intent.setType("image/*");
        //打开图片选择
        startActivityForResult(intent, GALLERY_CODE);
    }

    /**
     * 拍照选择图片
     */
    private void chooseFromCamera() {
        //构建隐式Intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //调用系统相机
        startActivityForResult(intent, CAMERA_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case CAMERA_CODE:
                //用户点击了取消
                if (data == null) {
                    return;
                } else {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        //获得拍的照片
                        Bitmap bm = extras.getParcelable("data");
                        //将Bitmap转化为uri
                        Uri uri = saveBitmap(bm, "temp");
                        //启动图像裁剪
                        startImageZoom(uri);
                    }
                }
                break;
            case GALLERY_CODE:
                if (data == null) {
                    return;
                } else {
                    //用户从图库选择图片后会返回所选图片的Uri
                    //获取到用户所选图片的Uri
                    uri = data.getData();
                    //返回的Uri为content类型的Uri,不能进行复制等操作,需要转换为文件Uri
                    uri = convertUri(uri);
                    startImageZoom(uri);
                }
                Message.head = mImageView.toString();
                break;
            case CROP_CODE:
                if (data == null) {
                    return;
                } else {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        //获取到裁剪后的图像
                        Bitmap bm = extras.getParcelable("data");
                        mImageView.setImageBitmap(bm);

                    }
                }
                Message.head = mImageView.toString();
                break;
            default:
                break;
        }


    }

    /**
     * 将content类型的Uri转化为文件类型的Uri
     *
     * @param uri
     * @return
     */
    private Uri convertUri(Uri uri) {
        InputStream is;
        try {
            //Uri ----> InputStream
            is = getContentResolver().openInputStream(uri);
            //InputStream ----> Bitmap
            Bitmap bm = BitmapFactory.decodeStream(is);
            //关闭流
            is.close();
            return saveBitmap(bm, "temp");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 将Bitmap写入SD卡中的一个文件中,并返回写入文件的Uri
     *
     * @param bm
     * @param dirPath
     * @return
     */
    private Uri saveBitmap(Bitmap bm, String dirPath) {
        //新建文件夹用于存放裁剪后的图片
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/" + dirPath);
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }

        //新建文件存储裁剪后的图片
        File img = new File(tmpDir.getAbsolutePath() + "/avator.png");
        try {
            //打开文件输出流
            FileOutputStream fos = new FileOutputStream(img);
            //将bitmap压缩后写入输出流(参数依次为图片格式、图片质量和输出流)
            bm.compress(Bitmap.CompressFormat.PNG, 85, fos);
            //刷新输出流
            fos.flush();
            //关闭输出流
            fos.close();
            //返回File类型的Uri
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 通过Uri传递图像信息以供裁剪
     *
     * @param uri
     */
    private void startImageZoom(Uri uri) {
        //构建隐式Intent来启动裁剪程序
        Intent intent = new Intent("com.android.camera.action.CROP");
        //设置数据uri和类型为图片类型
        intent.setDataAndType(uri, "image/*");
        //显示View为可裁剪的
        intent.putExtra("crop", true);
        //裁剪的宽高的比例为1:1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //输出图片的宽高均为150
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        //裁剪之后的数据是通过Intent返回
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_CODE);
    }


}
