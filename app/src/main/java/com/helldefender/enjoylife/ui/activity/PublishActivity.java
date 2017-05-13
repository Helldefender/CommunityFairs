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
import android.widget.Button;
import android.widget.Toast;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.ui.adapter.PublishRVAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Helldefender on 2017/2/8.
 */

public class PublishActivity extends BaseActivity {

    public static final int CHOOSE_PHOTO = 0;

    public static final int REQUEST_WES_PERMISSION = 1;

    //private static boolean isEnable = false;

    private boolean isEmpty = false;

    @BindView(R.id.rv_publish)
    RecyclerView imgRecyclerView;

    @BindView(R.id.btn_publish_add)
    Button addBtn;
//
//    @BindView(R.id.img_publish_back)
//    ImageView imgBack;
//
//    @BindView(R.id.tv_publish_num)
//    TextView tvNum;
//
//    @BindView(R.id.tv_publish_publishActivity)
//    TextView tvPublish;
//
//    @BindView(R.id.et_publish_title)
//    EditText etTitle;
//
//    @BindView(R.id.et_publish_theText)
//    EditText etTheText;
//
//    @BindView(R.id.img_publish_addImage)
//    ImageView imgAddImage;
//
//    private TextWatcher textWatcher = new TextWatcher() {
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            boolean isEnable = etTitle.getText().length() > 0
//                    && etTheText.getText().length() > 0;
//
//            updateReleaseBtn(isEnable);
//
//            tvNum.setText(etTitle.getText().length() + etTheText.getText().length() + "字");
//        }
//    };

    //private SubscriberOnNextListener imageUpListener;


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

        setUpStatusBar(R.color.statusColor);

        List<String> data = new ArrayList<String>();
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        imgRecyclerView.setLayoutManager(linearLayoutManager);

        imgRecyclerView.setAdapter(new PublishRVAdapter(this, R.layout.item_publish_rv_img, data));

        //initView();

        //initSubscriberListener();
    }

    @OnClick({R.id.btn_publish_add})
    public void onAddBtnClick() {
        //点击添加图片 更改文字
        addBtn.setText("+ 继续添加");
    }

    //点击删除按钮 ,根据RecyclerView数据源数量判断文字个数



//    private void initView() {
//        etTitle.addTextChangedListener(textWatcher);
//        etTheText.addTextChangedListener(textWatcher);
//
//        tvPublish.setEnabled(false);
//    }

//    @OnClick({R.id.img_publish_back, R.id.tv_publish_publishActivity, R.id.img_publish_addImage})
//    public void onClickView(View view) {
//        switch (view.getId()) {
//            case R.id.img_publish_back:
//                finish();
//                break;
//            case R.id.tv_publish_publishActivity:
//                //ThemeDialogFragment themeDialogFragment = new ThemeDialogFragment();
//                //themeDialogFragment.show(getSupportFragmentManager(), "theme_choose");
//                break;
//            case R.id.img_publish_addImage:
//                if (ContextCompat.checkSelfPermission(PublishActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(PublishActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WES_PERMISSION);
//                } else {
//                    openAlbum();
//                }
//                break;
//        }
//    }
//
//    private void updateReleaseBtn(boolean isEnable) {
//        tvPublish.setEnabled(isEnable);
//    }

//    private void initSubscriberListener() {
//        imageUpListener = new SubscriberOnNextListener<ImageBean>() {
//            @Override
//            public void onNext(final ImageBean imageBean) {
//                Glide.with(getApplicationContext())
//                        .load(HttpApiConfig.BASE_URL + imageBean.getUrl())
//                        .asBitmap()
//                        .into(new SimpleTarget<Bitmap>() {
//                            @Override
//                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//
//                                float scaleWidth = ((float) JUtils.getScreenWidth() - JUtils.dip2px(30)) / resource.getWidth();
//                                Matrix matrix = new Matrix();
//                                matrix.postScale(scaleWidth, scaleWidth);
//                                Bitmap newBitmap = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
//
//                                ImageSpan imageSpan = new ImageSpan(PublishActivity.this, newBitmap);
//                                String url = HttpApiConfig.BASE_URL + imageBean.getUrl();
//
//                                String tempUrl = "<img src=\"" + url + "\" />";
//                                SpannableString spannableString = new SpannableString(tempUrl);
//
//                                spannableString.setSpan(imageSpan, 0, tempUrl.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//                                int index = etTheText.getSelectionStart();
//                                Editable editText = etTheText.getEditableText();
//                                if (index < 0 || index >= editText.length()) {
//                                    editText.append(spannableString);
//                                } else {
//                                    editText.insert(index, spannableString);
//                                }
//                                editText.insert(index + spannableString.length(), "\n");
//                            }
//                        });
//            }
//        };
//    }

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
}
