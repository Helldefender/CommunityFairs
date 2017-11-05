package com.helldefender.enjoylife.ui.activity;

import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.modules.FormActivity;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.ui.adapter.EventReleaseRvAdapter;
import com.helldefender.enjoylife.utils.file.FileInfo;
import com.helldefender.enjoylife.utils.file.UploadService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.internal.platform.Platform;


/**
 * Created by Helldefender on 2017/2/8.
 */

public class PublishActivity extends BaseActivity {

    public static final int CHOOSE_PHOTO = 0;

    public static final int REQUEST_WES_PERMISSION = 1;

    @BindView(R.id.tv_user_info_edit)
    TextView tvUserInfoEdit;
    @BindView(R.id.et_publish_name)
    EditText etPublishName;
    @BindView(R.id.et_sign_up_num)
    EditText etSignUpNum;
    @BindView(R.id.ll_share_num)
    LinearLayout llShareNum;
    @BindView(R.id.ll_share_num_male)
    LinearLayout llShareNumMale;
    @BindView(R.id.tv_publish_level)
    TextView tvPublishLevel;
    @BindView(R.id.tv_publish_type)
    TextView tvPublishType;
    @BindView(R.id.tv_event_sign_start)
    TextView tvEventSignStart;
    @BindView(R.id.ll_event_sign_start)
    LinearLayout llEventSignStart;
    @BindView(R.id.tv_event_sing_end)
    TextView tvEventSingEnd;
    @BindView(R.id.ll_event_sign_end)
    LinearLayout llEventSignEnd;
    @BindView(R.id.tv_event_start)
    TextView tvEventStart;
    @BindView(R.id.ll_event_start)
    LinearLayout llEventStart;
    @BindView(R.id.et_feedback_content)
    EditText etFeedbackContent;
    @BindView(R.id.tv_event_title)
    TextView tvEventTitle;

    //private static boolean isEnable = false;

    private boolean isEmpty = false;

    private Platform platform;

    @BindView(R.id.rv_publish)
    RecyclerView imgRecyclerView;

    @BindView(R.id.btn_publish_add)
    Button addBtn;

    private ArrayList<String> mSexOptionsList = new ArrayList<>();

    private ArrayList<String> mIdentityOptionsList = new ArrayList<>();

    public static void start(Context context) {
        start(context, null);
    }


    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, PublishActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_publish;
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setUpStatusBar(R.color.statusColor);

        handleStatusBar();

        platform = Platform.get();

        List<String> data = new ArrayList<String>();
//        data.add("测试");
//        data.add("测试");
//        data.add("测试");
//        data.add("测试");

        mSexOptionsList.add("国家级");
        mSexOptionsList.add("省级");
        mSexOptionsList.add("市级");
        mSexOptionsList.add("校级");
        mSexOptionsList.add("院级");
        mSexOptionsList.add("班级");

        mIdentityOptionsList.add("学术科技与创新创业类活动");
        mIdentityOptionsList.add("文化艺术与身心发展类活动");
        mIdentityOptionsList.add("社会实践与志愿服务活动");
        mIdentityOptionsList.add("社团活动与技能培训类活动");
        mIdentityOptionsList.add("思想政治与道德素养类活动");
        mIdentityOptionsList.add("其他");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        imgRecyclerView.setLayoutManager(linearLayoutManager);

        imgRecyclerView.setAdapter(new EventReleaseRvAdapter(this, R.layout.item_publish_rv_img, data));

        tvEventTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvUserInfoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PublishActivity.this).setTitle("发布活动")
                        .setMessage("是否需要生成报名表")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                startActivity(FormActivity.class);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        })
                        .show();


            }
        });

        tvPublishLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OptionsPickerView sexOptions = new OptionsPickerView.Builder(PublishActivity.this, new OptionsPickerView.OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String textSelect = mSexOptionsList.get(options1);
                        tvPublishLevel.setText(textSelect);
                    }
                })
                        .setTitleText("选择活动级别")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                sexOptions.setPicker(mSexOptionsList);
                sexOptions.show();
            }
        });

        tvPublishType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OptionsPickerView sexOptions = new OptionsPickerView.Builder(PublishActivity.this, new OptionsPickerView.OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String textSelect = mIdentityOptionsList.get(options1);
                        tvPublishType.setText(textSelect);
                    }
                })
                        .setTitleText("选择活动类型")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                sexOptions.setPicker(mIdentityOptionsList);
                sexOptions.show();
            }
        });

        llEventSignStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(PublishActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        tvEventSignStart.setText(getTime(date));
                    }
                })
                        .setTitleText("选择报名开始时间")
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .setType(new boolean[]{true, true, true, true, true, false})
                        .build();
                pvTime.setDate(Calendar.getInstance());
                pvTime.show();
            }
        });

        llEventSignEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(PublishActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        tvEventSingEnd.setText(getTime(date));
                    }
                })
                        .setTitleText("选择报名截止时间")
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .setType(new boolean[]{true, true, true, true, true, false})
                        .build();
                pvTime.setDate(Calendar.getInstance());
                pvTime.show();
            }
        });

        llEventStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(PublishActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        tvEventStart.setText(getTime(date));
                    }
                })
                        .setTitleText("选择活动开始时间")
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .setType(new boolean[]{true, true, true, true, true, false})
                        .build();
                pvTime.setDate(Calendar.getInstance());
                pvTime.show();
            }
        });


    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return format.format(date);
    }

    @OnClick({R.id.btn_publish_add})
    public void onAddBtnClick() {
        //点击添加图片 更改文字
        addBtn.setText("+ 继续添加");
        //打开相册 选择并上传 视频或者图片
        //openAlbum();
    }

    private void openVideio() {
        Intent intent = new Intent();
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WES_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "您已取消授予权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
        }
    }

    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(final String imagePath) {
        if (imagePath != null) {

            //HttpMethods.getInstance().postImage(imagePath, new ProgressSubscriber<ImageBean>(imageUpListener, this));
        } else {
            Toast.makeText(this, "获取图片失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadFile(String path) {
        //String path = tv_fileName.getText().toString();
        File file = new File(path);

        if (path.equals("")) {
            Toast.makeText(this, "请选择文件", Toast.LENGTH_LONG).show();
        } else if (!file.exists()) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
        } else {
            Intent upIntent = new Intent(this, UploadService.class);
            upIntent.setAction("START_UPLOAD");

            FileInfo fileInfo = new FileInfo();
            fileInfo.setFilePath(path);
            fileInfo.setFileLength(file.length());
            fileInfo.setIsChunk(true);
            upIntent.putExtra("fileInfo", fileInfo);
            this.startService(upIntent);
        }
    }

    //实现大文件的分块上传
    //从文件的指定位置读取指定大小的数据，对文件进行分块，并计算分块数量，计算每一次上传的块的起始位置
//    private void uploadFile(String FILE_NAME,String FILE_PATH) {
//        String filepath = Environment.getExternalStorageDirectory() + File.separator + FILE_PATH + File.separator + FILE_NAME;
//        File file = new File(filepath);
//        if (file.exists()) {
//            OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build();
//
//
//            final MediaType mediaType = MediaType.parse("");
//            RequestBody fileBody = RequestBody.create(mediaType, file);
//            RequestBody requestBody = new MultipartBody.Builder().addFormDataPart("file", FILE_NAME, fileBody).build();
//            Request request = new Request.Builder().post(requestBody).url().build();
//            okhttp3.Call call = okHttpClient.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(okhttp3.Call call, IOException e) {
//                    Toast.makeText(PublishActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onResponse(okhttp3.Call call, Response response) throws IOException {
//                    ResponseBody responseBody = null;
//                    responseBody = response.body();
//                    final String info = responseBody.string();
//                }
//            });
//        }
//    }
}
