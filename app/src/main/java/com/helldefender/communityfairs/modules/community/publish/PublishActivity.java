//package com.helldefender.communityfairs.modules.community.publish;
//
//import android.content.ContentUris;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.DocumentsContract;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.helldefender.communityfairs.R;
//import com.helldefender.communityfairs.app.BaseActivity;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import butterknife.BindView;
//import butterknife.OnClick;
//import okhttp3.internal.platform.Platform;
//
//
///**
// * Created by Helldefender on 2017/2/8.
// */
//
//public class PublishActivity extends BaseActivity {
//
//    public static final int CHOOSE_PHOTO = 0;
//
//    public static final int REQUEST_WES_PERMISSION = 1;
//
//    //private static boolean isEnable = false;
//
//    private boolean isEmpty = false;
//
//    private Platform platform;
//
//    @BindView(R.id.rv_publish)
//    RecyclerView imgRecyclerView;
//
//    @BindView(R.id.btn_publish_add)
//    Button addBtn;
//
//    private ArrayList<String> mSexOptionsList = new ArrayList<>();
//
//    private ArrayList<String> mIdentityOptionsList = new ArrayList<>();
//
//    public static void start(Context context) {
//        start(context, null);
//    }
//
//
//    public static void start(Context context, Intent extras) {
//        Intent intent = new Intent();
//        intent.setClass(context, PublishActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        if (extras != null) {
//            intent.putExtras(extras);
//        }
//        context.startActivity(intent);
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_event_publish;
//    }
//
//    @Override
//    public void initInject() {
//
//    }
//
//    @Override
//    public void initPresenter() {
//
//    }
//
//    @Override
//    protected void widgetClick(View view) {
//
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //setUpStatusBar(R.color.statusColor);
//
//        handleStatusBar();
//
//        platform = Platform.get();
//
//        List<String> data = new ArrayList<String>();
////        data.add("测试");
////        data.add("测试");
////        data.add("测试");
////        data.add("测试");
//
//        mSexOptionsList.add("国家级");
//        mSexOptionsList.add("省级");
//        mSexOptionsList.add("市级");
//        mSexOptionsList.add("校级");
//        mSexOptionsList.add("院级");
//        mSexOptionsList.add("班级");
//
//        mIdentityOptionsList.add("学术科技与创新创业类活动");
//        mIdentityOptionsList.add("文化艺术与身心发展类活动");
//        mIdentityOptionsList.add("社会实践与志愿服务活动");
//        mIdentityOptionsList.add("社团活动与技能培训类活动");
//        mIdentityOptionsList.add("思想政治与道德素养类活动");
//        mIdentityOptionsList.add("其他");
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        imgRecyclerView.setLayoutManager(linearLayoutManager);
//
//        imgRecyclerView.setAdapter(new EventReleaseRvAdapter(this, R.layout.item_publish_rv_img, data));
//
//        tvEventTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    private String getTime(Date date) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        return format.format(date);
//    }
//
//    @OnClick({R.id.btn_publish_add})
//    public void onAddBtnClick() {
//        //点击添加图片 更改文字
//        addBtn.setText("+ 继续添加");
//        //打开相册 选择并上传 视频或者图片
//        //openAlbum();
//    }
//
//    private void openVideio() {
//        Intent intent = new Intent();
//    }
//
//    private void openAlbum() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent, CHOOSE_PHOTO);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_WES_PERMISSION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    openAlbum();
//                } else {
//                    Toast.makeText(this, "您已取消授予权限", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case CHOOSE_PHOTO:
//                if (resultCode == RESULT_OK) {
//                    if (Build.VERSION.SDK_INT >= 19) {
//                        handleImageOnKitKat(data);
//                    } else {
//                        handleImageBeforeKitKat(data);
//                    }
//                }
//                break;
//            default:
//        }
//    }
//
//    private void handleImageOnKitKat(Intent data) {
//        String imagePath = null;
//        Uri uri = data.getData();
//        if (DocumentsContract.isDocumentUri(this, uri)) {
//            String docId = DocumentsContract.getDocumentId(uri);
//            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
//                String id = docId.split(":")[1];
//                String selection = MediaStore.Images.Media._ID + "=" + id;
//                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
//            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
//                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
//                imagePath = getImagePath(contentUri, null);
//            }
//        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
//            imagePath = getImagePath(uri, null);
//        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
//            imagePath = uri.getPath();
//        }
//        displayImage(imagePath);
//    }
//
//    private void handleImageBeforeKitKat(Intent data) {
//        Uri uri = data.getData();
//        String imagePath = getImagePath(uri, null);
//        displayImage(imagePath);
//    }
//
//    private String getImagePath(Uri uri, String selection) {
//        String path = null;
//        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            }
//            cursor.close();
//        }
//        return path;
//    }
//
//    private void displayImage(final String imagePath) {
//        if (imagePath != null) {
//
//            //HttpMethods.getInstance().postImage(imagePath, new ProgressSubscriber<ImageBean>(imageUpListener, this));
//        } else {
//            Toast.makeText(this, "获取图片失败", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void uploadFile(String path) {
//        //String path = tv_fileName.getText().toString();
//        File file = new File(path);
//
//        if (path.equals("")) {
//            Toast.makeText(this, "请选择文件", Toast.LENGTH_LONG).show();
//        } else if (!file.exists()) {
//            Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
//        } else {}
//    }
//
//}
