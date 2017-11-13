package com.augwit.myapp.web.rest.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author carl.wang
 *
 * @apiNote 把用户传入的Excel 存入数据库
 *
 * @datetime 2017/11/13
 *
 *
 * 这个Util现在完成用户上传Excel文件（文件格式要与我的数据库字段都有对应，而且其sheet只有第一个会被处理）到数据库。
 */
public class ExcelTranslateDateUtil {

    private List<HSSFSheet> sheets;

    public ExcelTranslateDateUtil(){

    }

    public ExcelTranslateDateUtil(List<HSSFSheet> sheets){
        this.sheets=sheets;
    }

    /*
    * 获取Sheets
    * */
    public List<HSSFSheet> getSheets(MultipartFile file)  {
        InputStream is = null;
        try {
            is = file.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(is);
            List<HSSFSheet> sheets = new ArrayList<>();
            for(int i = 0; i < wb.getActiveSheetIndex(); i++){
                HSSFSheet sheet = wb.getSheetAt(i);
                sheets.add(sheet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        * 这里获取了Excel中多个sheet，但是现在只对第一个操作，其他现在不做任何处理。
        * * */
        return sheets;
    }

    /*
    * 获取首行标题
    * */
    public Map<String, Integer> getFirstRowTitle(HSSFSheet sheet){

        int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
        Map<String, Integer>titles = new HashMap<>();
        for (int i = 0; i < cellNum; i ++){
            Object cellValue = getCellValue(sheet.getRow(0), i);
            titles.put((String) cellValue,i);
        }
        return titles;
    }
    /*
    * 获取除首行后的其他行
    * */
    public List<HSSFRow> getRows(HSSFSheet sheet){

        int rowNum = sheet.getPhysicalNumberOfRows();
        List<HSSFRow> rows = new ArrayList<>();
        for(int j = 1; j < rowNum; j++){
            rows.set(j, sheet.getRow(j));
        }
        return rows;
    }

    /*
    * 获取每列的值
    * */
    public Object getCellValue(HSSFRow row, int colNum){
        Object cellValue = null;
        HSSFCell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
            cell.setCellValue("null value");
        }
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case BLANK:
                cellValue = "null blank";
                break;
            case _NONE:
                cellValue = "none";
                break;
            case ERROR:
                cellValue = "error value";
                break;
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "未填写";
                break;
        }
        return cellValue;
    }

   /* private Object getCellValue(HSSFSheet sheet, int rowNum,int colNum) {

        HSSFRow row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        HSSFCell cell = row.getCell(colNum);

        if (cell == null) {
            cell = row.createCell(colNum);
            cell.setCellValue("null value");
        }

        Object cellValue = null;
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case BLANK:
                cellValue = "null blank";
                break;
            case _NONE:
                cellValue = "none";
                break;
            case ERROR:
                cellValue = "error value";
                break;
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "未填写";
                break;
        }
        return cellValue;
    }*/


}
