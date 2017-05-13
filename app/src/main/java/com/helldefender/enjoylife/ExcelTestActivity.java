package com.helldefender.enjoylife;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.helldefender.enjoylife.utils.ExcelFileUtil;
import com.helldefender.enjoylife.widget.ExcelFileFormatUtil;

import java.io.File;
import java.io.IOException;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

/**
 * Created by Helldefender on 2017/4/7.
 */

public class ExcelTestActivity extends AppCompatActivity {
    private static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ZzExcelCreator/";

    private EditText etFileName;
    private EditText etSheetName;
    private Button btnCreate;
    private EditText etRow;
    private EditText etCol;
    private EditText etString;
    private Button btnAddString;
    private EditText etNumber;
    private Button btnAddNumber;
    private EditText etStartRow;
    private EditText etStartCol;
    private EditText etEndRow;
    private EditText etEndCol;
    private Button btnMerge;
    private EditText etRow1;
    private EditText etCol1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.excel_sheet);

        assignViews();

    }

    private void assignViews() {
        etFileName = (EditText) findViewById(R.id.et_file_name);
        etSheetName = (EditText) findViewById(R.id.et_sheet_name);
        btnCreate = (Button) findViewById(R.id.btn_create);
        etRow = (EditText) findViewById(R.id.et_row);
        etCol = (EditText) findViewById(R.id.et_col);
        etRow1 = (EditText) findViewById(R.id.et_row1);
        etCol1 = (EditText) findViewById(R.id.et_col1);
        etString = (EditText) findViewById(R.id.et_string);
        btnAddString = (Button) findViewById(R.id.btn_add_string);
        etNumber = (EditText) findViewById(R.id.et_number);
        btnAddNumber = (Button) findViewById(R.id.btn_add_number);
        etStartRow = (EditText) findViewById(R.id.et_start_row);
        etStartCol = (EditText) findViewById(R.id.et_start_col);
        etEndRow = (EditText) findViewById(R.id.et_end_row);
        etEndCol = (EditText) findViewById(R.id.et_end_col);
        btnMerge = (Button) findViewById(R.id.btn_merge);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x01);
                    } else {
                        createExcel();
                    }
                } else {
                    createExcel();
                }

            }
        });

        btnMerge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fileName = etFileName.getText().toString().trim();

                final String co1l = etStartCol.getText().toString().trim();
                final String row1 = etStartRow.getText().toString().trim();
                final String co12 = etEndCol.getText().toString().trim();
                final String row2 = etEndRow.getText().toString().trim();

                new AsyncTask<String, Void, Integer>() {

                    @Override
                    protected Integer doInBackground(String... params) {
                        try {
                            ExcelFileUtil
                                    .getInstance()
                                    .openExcel(new File(PATH + fileName + ".xls"))
                                    .openSheet(0)
                                    //.merge(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]), Integer.parseInt(params[3]))
                                    .close();
                            return 1;
                        } catch (IOException | WriteException | BiffException e) {
                            e.printStackTrace();
                            return 0;
                        }
                    }

                    @Override
                    protected void onPostExecute(Integer aVoid) {
                        super.onPostExecute(aVoid);
                        if (aVoid == 1) {
                            Toast.makeText(ExcelTestActivity.this, "合并成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ExcelTestActivity.this, "合并失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute(co1l, row1, co12, row2);
            }
        });

        btnAddNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fileName = etFileName.getText().toString().trim();

                final String col = etCol1.getText().toString().trim();
                final String row = etRow1.getText().toString().trim();
                final String str = etNumber.getText().toString().trim();

                new AsyncTask<String, Void, Integer>() {

                    @Override
                    protected Integer doInBackground(String... params) {

                        try {

                            WritableCellFormat format = ExcelFileFormatUtil
                                    .getInstance()
                                    .createCellFont(WritableFont.ARIAL)
                                    .setAlignment(Alignment.CENTRE, VerticalAlignment.CENTRE)
                                    .setFontSize(14)
                                    .setFontColor(Colour.ROSE)
                                    .getCellFormat();

                            ExcelFileUtil
                                    .getInstance()
                                    .openExcel(new File(PATH + fileName + ".xls"))
                                    .openSheet(0)
                                    .addNumber(Integer.parseInt(col), Integer.parseInt(row), Double.parseDouble(str), format, 600)
                                    .close();
                            return 1;
                        } catch (IOException | WriteException | BiffException e) {
                            e.printStackTrace();
                            return 0;
                        }
                    }

                    @Override
                    protected void onPostExecute(Integer aVoid) {
                        super.onPostExecute(aVoid);
                        if (aVoid == 1) {
                            Toast.makeText(ExcelTestActivity.this, "插入成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ExcelTestActivity.this, "插入失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute(col, row, str);
            }
        });


        btnAddString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fileName = etFileName.getText().toString().trim();

                final String col = etCol.getText().toString().trim();
                final String row = etRow.getText().toString().trim();
                final String str = etString.getText().toString().trim();

                new AsyncTask<String, Void, Integer>() {

                    @Override
                    protected Integer doInBackground(String... params) {
                        try {
                            WritableCellFormat format = ExcelFileFormatUtil
                                    .getInstance()
                                    .createCellFont(WritableFont.ARIAL)
                                    .setAlignment(Alignment.CENTRE, VerticalAlignment.CENTRE)
                                    .setFontSize(14)
                                    .setFontColor(Colour.DARK_GREEN)
                                    .getCellFormat();
                            ExcelFileUtil
                                    .getInstance()
                                    .openExcel(new File(PATH + fileName + ".xls"))
                                    .openSheet(0)
                                    //.setColumnWidth(Integer.parseInt(col), 25)
                                    // .setRowHeight(Integer.parseInt(row), 400)
                                    .addLabel(Integer.parseInt(col), Integer.parseInt(row), str, format)
                                    .close();
                            return 1;
                        } catch (IOException | WriteException | BiffException e) {
                            e.printStackTrace();
                            return 0;
                        }
                    }

                    @Override
                    protected void onPostExecute(Integer aVoid) {
                        super.onPostExecute(aVoid);
                        if (aVoid == 1) {
                            Toast.makeText(ExcelTestActivity.this, "插入成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ExcelTestActivity.this, "插入失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute(col, row, str);
            }
        });


    }


    private void createExcel() {
        String fileName = etFileName.getText().toString().trim();
        String sheetName = etSheetName.getText().toString().trim();

        new AsyncTask<String, Void, Integer>() {

            @Override
            protected Integer doInBackground(String... params) {
                try {
                    ExcelFileUtil
                            .getInstance()
                            .createExcel(PATH, params[0])
                            .createSheet(params[1])
                            .close();
                    return 1;
                } catch (IOException | WriteException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override
            protected void onPostExecute(Integer aVoid) {
                super.onPostExecute(aVoid);
                if (aVoid == 1) {
                    Toast.makeText(ExcelTestActivity.this, "表格创建成功！请到" + PATH + "路径下查看~", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ExcelTestActivity.this, "表格创建失败！", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(fileName, sheetName);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0x01:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    createExcel();
                }
                break;
        }
    }
}
