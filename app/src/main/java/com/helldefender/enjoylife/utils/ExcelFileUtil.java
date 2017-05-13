package com.helldefender.enjoylife.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Created by Helldefender on 2017/4/7.
 */

public class ExcelFileUtil {
    //
    private static ExcelFileUtil excelFileUtil;
    //创建工作薄
    private WritableWorkbook writableWorkbook;
    //创建新的一页
    private WritableSheet writableSheet;

    private ExcelFileUtil() {

    }

    public static ExcelFileUtil getInstance() {
        if (excelFileUtil == null) {
            synchronized (ExcelFileUtil.class) {
                if (excelFileUtil == null) {
                    excelFileUtil = new ExcelFileUtil();
                }
            }
        }
        return excelFileUtil;
    }

    public ExcelFileUtil createExcel(String path, String name) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        writableWorkbook = Workbook.createWorkbook(new File(path + File.separator + name + ".xls"));
        return this;
    }

    public ExcelFileUtil openExcel(File file) throws IOException, BiffException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = Workbook.getWorkbook(fileInputStream);
        writableWorkbook = Workbook.createWorkbook(file, workbook);
        return this;
    }

    public ExcelFileUtil createSheet(String name) {
        checkWritableWorkNull();
        writableSheet = writableWorkbook.createSheet(name, 0);
        return this;
    }

    public ExcelFileUtil openSheet(int position) {
        checkWritableWorkNull();
        writableSheet = writableWorkbook.getSheet(position);
        return this;
    }

    //创建表头起始列，起始行，终止列，终止行
    public ExcelFileUtil addHeader(int startColumn, int startRow, int endColumn, int endRow, String title, WritableCellFormat writableCellFormat) throws WriteException, RowsExceededException {
        checkWritableWorkNull();
        checkWritableSheetNull();

        writableSheet.mergeCells(startColumn, startRow, endColumn, endRow);

        if (writableCellFormat == null) {
            writableSheet.addCell(new Label(startColumn, startRow, title));
        } else {
            writableSheet.addCell(new Label(startColumn, startRow, title, writableCellFormat));
        }
        return this;
    }

    //单元格样式控制对象WritableFormat
    //支持整数数据和浮点型数据的插入
    public ExcelFileUtil addNumber(int column, int row, double number, WritableCellFormat writableCellFormat, int height) throws WriteException {
        checkWritableWorkNull();
        checkWritableSheetNull();
        if (writableCellFormat == null) {
            writableSheet.addCell(new Number(column, row, number));
        } else {
            writableSheet.addCell(new Number(column, row, number, writableCellFormat));
        }
        //设置第一行的高度
        writableSheet.setRowView(0, height, false);
        return this;
    }

    public ExcelFileUtil addLabel(int column, int row, String content, WritableCellFormat writableCellFormat) throws WriteException {
        checkWritableWorkNull();
        checkWritableSheetNull();
        if (writableCellFormat == null) {
            writableSheet.addCell(new Label(column, row, content));
        } else {
            writableSheet.addCell(new Label(column, row, content, writableCellFormat));
        }
        return this;
    }

    public ExcelFileUtil insertColumn(int position) {
        checkWritableWorkNull();
        checkWritableSheetNull();
        writableSheet.insertColumn(position);
        return this;
    }

    public ExcelFileUtil insertRow(int position) {
        checkWritableWorkNull();
        checkWritableSheetNull();
        writableSheet.insertRow(position);
        return this;
    }

    public ExcelFileUtil deleteColumn(int position) {
        checkWritableWorkNull();
        checkWritableSheetNull();
        writableSheet.removeColumn(position);
        return this;
    }

    public ExcelFileUtil deleteRow(int position) {
        checkWritableWorkNull();
        checkWritableSheetNull();
        writableSheet.removeRow(position);
        return this;
    }

    public void close() throws IOException, WriteException {
        checkWritableWorkNull();
        writableWorkbook.write();
        writableWorkbook.close();
        writableWorkbook = null;
        writableSheet = null;
    }

    private void checkWritableWorkNull() {
        if (writableWorkbook == null) {
            throw new NullPointerException("请先创建或打开Excel表格");
        }
    }

    private void checkWritableSheetNull() {
        if (writableSheet == null) {
            throw new NullPointerException("请先创建或打开Sheet");
        }
    }

}
