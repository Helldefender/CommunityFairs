package com.helldefender.enjoylife.widget;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

/**
 * Created by Helldefender on 2017/4/7.
 */

public class ExcelFileFormatUtil {

    private WritableFont writableFont;

    private WritableCellFormat writableCellFormat;

    private static ExcelFileFormatUtil excelFileFormatUtil;

    private ExcelFileFormatUtil() {

    }

    public static ExcelFileFormatUtil getInstance() {
        if (excelFileFormatUtil == null) {
            synchronized (ExcelFileFormatUtil.class) {
                if (excelFileFormatUtil == null) {
                    excelFileFormatUtil = new ExcelFileFormatUtil();
                }
            }
        }
        return excelFileFormatUtil;
    }

    public ExcelFileFormatUtil createCellFont(WritableFont.FontName fontName) throws WriteException {
        //设置字体类型，字号大小，是否采用黑体显示（构造函数）
        writableFont = new WritableFont(fontName);
        writableFont.setPointSize(12);
        writableFont.setColour(Colour.BLACK);
        writableCellFormat = new WritableCellFormat();
        writableCellFormat.setFont(writableFont);
        //单元格内容水平方向居中
        writableCellFormat.setAlignment(Alignment.CENTRE);
        //单元格内容垂直反向居中
        writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        return this;
    }

    public ExcelFileFormatUtil setFontSize(int fontSize) throws WriteException {
        checkNull();
        writableFont.setPointSize(fontSize);
        return this;
    }

    public ExcelFileFormatUtil setFontColor(Colour color) throws WriteException {
        checkNull();
        writableFont.setColour(color);
        return this;
    }

    //设置单元格内容对齐格式
    public ExcelFileFormatUtil setAlignment(Alignment align, VerticalAlignment verticalAlign) throws WriteException {
        checkNull();
        writableCellFormat.setAlignment(align);
        writableCellFormat.setVerticalAlignment(verticalAlign);
        return this;
    }

    public ExcelFileFormatUtil setBackgroundColor(Colour color) throws WriteException {
        checkNull();
        writableCellFormat.setBackground(color);
        return this;
    }


    public WritableCellFormat getCellFormat() {
        return writableCellFormat;
    }

    private void checkNull() {
        if (writableFont == null) {
            throw new NullPointerException("WritableFont为空");
        }
        if (writableCellFormat == null) {
            throw new NullPointerException("WritableCellFormat为空");
        }
    }

}
