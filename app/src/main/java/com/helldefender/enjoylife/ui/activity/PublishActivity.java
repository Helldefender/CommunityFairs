package com.helldefender.enjoylife.ui.activity;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.ui.adapter.PublishRVAdapter;
import com.helldefender.enjoylife.utils.file.FileInfo;
import com.helldefender.enjoylife.utils.file.UploadService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.internal.Platform;


/**
 * Created by Helldefender on 2017/2/8.
 */

public class PublishActivity extends BaseActivity {

    public static final int CHOOSE_PHOTO = 0;

    public static final int REQUEST_WES_PERMISSION = 1;

    //private static boolean isEnable = false;

    private boolean isEmpty = false;

    private Platform platform;

    @BindView(R.id.tb_publish)
    Toolbar mToolBar;

    @BindView(R.id.rv_publish)
    RecyclerView imgRecyclerView;

    @BindView(R.id.btn_publish_add)
    Button addBtn;

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
        return R.layout.activity_test;
    }

    @Override
    public int getEmptyLayoutId() {
        return 0;
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(mToolBar);

        setUpStatusBar(R.color.statusColor);

        platform = Platform.get();

        List<String> data = new ArrayList<String>();
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        imgRecyclerView.setLayoutManager(linearLayoutManager);

        imgRecyclerView.setAdapter(new PublishRVAdapter(this, R.layout.item_publish_rv_img, data));
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
