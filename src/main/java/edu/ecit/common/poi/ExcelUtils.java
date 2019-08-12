package edu.ecit.common.poi;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import edu.ecit.bean.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 此工具类实现Excel导入、导出
 */
public class ExcelUtils {

    /**
     * 以下常量配置只是部分配置，根据需要自行添加
     * 这些配置信息可以放在配置文件中，供修改
     */

    // 文档详细信息常量
    private static final String DOCUMENT_CATEGORY="员工信息";
    private static final String DOCUMENT_MANAGER="邵烽烽";
    private static final String DOCUMENT_COMPANY="XXX集团";

    //文档摘要信息常量
    private static final String DOCUMENT_SUBJECT="员工信息表";
    private static final String DOCUMENT_TITLE="员工信息";
    private static final String DOCUMENT_AUTHOR="邵烽烽";
    private static final String DOCUMENT_COMMENTS="无";

    //excel表单名
    private static final String SHEET_NAME="XXX集团员工信息表";
    /**
     * 将对象列表导入到Excel
     * @param dataSet 待转换的对象列表
     * @return
     */
    public static ResponseEntity<byte[]> exportObject2Excel(Collection<?> dataSet,Class<?> clazz){

        HttpHeaders headers=null;
        ByteArrayOutputStream baos=null;

        //1.创建excel文档对象
        HSSFWorkbook workbook=new HSSFWorkbook();
        //2.创建文档摘要
        workbook.createInformationProperties();
        //3.文档详细/摘要信息配置
        setSumaryInformation(workbook);
        //3.创建Excel表单
        HSSFSheet sheet=workbook.createSheet(SHEET_NAME);
        //创建日期显示格式
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //5定义所有列的宽
        setAllColumnWidth(sheet);
        //6设置表头
        setHeader(sheet,headerStyle);
        //7装载数据
        loadData2Excel(dataSet,sheet,clazz);


    }

    private static void setSumaryInformation(HSSFWorkbook workbook){
        //3.获取文档详细信息
        DocumentSummaryInformation dsi=workbook.getDocumentSummaryInformation();
        //4.获取摘要信息
        SummaryInformation si = workbook.getSummaryInformation();
        //3.1 文档类别
        dsi.setCategory(DOCUMENT_CATEGORY);
        //3.2设置文档管理员
        dsi.setManager(DOCUMENT_MANAGER);
        //3.3设置组织机构
        dsi.setCompany(DOCUMENT_COMPANY);

        //4.1设置文档主题
        si.setSubject(DOCUMENT_SUBJECT);
        //4.2设置文档作者
        si.setTitle(DOCUMENT_TITLE);
        //4.3设置文档作者
        si.setAuthor(DOCUMENT_AUTHOR);
        //4.4设置文档备注
        si.setComments(DOCUMENT_COMMENTS);
    }

    //格式一般是固定的，提前设置好就行
    private static void setAllColumnWidth(HSSFSheet sheet){

        //定义列的宽度
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 5 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 16 * 256);
        sheet.setColumnWidth(9, 12 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        sheet.setColumnWidth(12, 16 * 256);
        sheet.setColumnWidth(13, 14 * 256);
        sheet.setColumnWidth(14, 14 * 256);
        sheet.setColumnWidth(15, 12 * 256);
        sheet.setColumnWidth(16, 8 * 256);
        sheet.setColumnWidth(17, 16 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        sheet.setColumnWidth(19, 12 * 256);
        sheet.setColumnWidth(20, 8 * 256);
        sheet.setColumnWidth(21, 25 * 256);
        sheet.setColumnWidth(22, 14 * 256);
        sheet.setColumnWidth(23, 12 * 256);
        sheet.setColumnWidth(24, 12 * 256);
    }

    /**
     * 设置表头
     */
    private static void setHeader(HSSFSheet sheet,HSSFCellStyle headerStyle){

        HSSFRow headerRow = sheet.createRow(0);
        HSSFCell cell0 = headerRow.createCell(0);
        cell0.setCellValue("编号");
        cell0.setCellStyle(headerStyle);
        HSSFCell cell1 = headerRow.createCell(1);
        cell1.setCellValue("姓名");
        cell1.setCellStyle(headerStyle);
        HSSFCell cell2 = headerRow.createCell(2);
        cell2.setCellValue("工号");
        cell2.setCellStyle(headerStyle);
        HSSFCell cell3 = headerRow.createCell(3);
        cell3.setCellValue("性别");
        cell3.setCellStyle(headerStyle);
        HSSFCell cell4 = headerRow.createCell(4);
        cell4.setCellValue("出生日期");
        cell4.setCellStyle(headerStyle);
        HSSFCell cell5 = headerRow.createCell(5);
        cell5.setCellValue("身份证号码");
        cell5.setCellStyle(headerStyle);
        HSSFCell cell6 = headerRow.createCell(6);
        cell6.setCellValue("婚姻状况");
        cell6.setCellStyle(headerStyle);
        HSSFCell cell7 = headerRow.createCell(7);
        cell7.setCellValue("民族");
        cell7.setCellStyle(headerStyle);
        HSSFCell cell8 = headerRow.createCell(8);
        cell8.setCellValue("籍贯");
        cell8.setCellStyle(headerStyle);
        HSSFCell cell9 = headerRow.createCell(9);
        cell9.setCellValue("政治面貌");
        cell9.setCellStyle(headerStyle);
        HSSFCell cell10 = headerRow.createCell(10);
        cell10.setCellValue("电话号码");
        cell10.setCellStyle(headerStyle);
        HSSFCell cell11 = headerRow.createCell(11);
        cell11.setCellValue("联系地址");
        cell11.setCellStyle(headerStyle);
        HSSFCell cell12 = headerRow.createCell(12);
        cell12.setCellValue("所属部门");
        cell12.setCellStyle(headerStyle);
        HSSFCell cell13 = headerRow.createCell(13);
        cell13.setCellValue("职称");
        cell13.setCellStyle(headerStyle);
        HSSFCell cell14 = headerRow.createCell(14);
        cell14.setCellValue("职位");
        cell14.setCellStyle(headerStyle);
        HSSFCell cell15 = headerRow.createCell(15);
        cell15.setCellValue("聘用形式");
        cell15.setCellStyle(headerStyle);
        HSSFCell cell16 = headerRow.createCell(16);
        cell16.setCellValue("最高学历");
        cell16.setCellStyle(headerStyle);
        HSSFCell cell17 = headerRow.createCell(17);
        cell17.setCellValue("专业");
        cell17.setCellStyle(headerStyle);
        HSSFCell cell18 = headerRow.createCell(18);
        cell18.setCellValue("毕业院校");
        cell18.setCellStyle(headerStyle);
        HSSFCell cell19 = headerRow.createCell(19);
        cell19.setCellValue("入职日期");
        cell19.setCellStyle(headerStyle);
        HSSFCell cell20 = headerRow.createCell(20);
        cell20.setCellValue("在职状态");
        cell20.setCellStyle(headerStyle);
        HSSFCell cell21 = headerRow.createCell(21);
        cell21.setCellValue("邮箱");
        cell21.setCellStyle(headerStyle);
        HSSFCell cell22 = headerRow.createCell(22);
        cell22.setCellValue("合同期限(年)");
        cell22.setCellStyle(headerStyle);
        HSSFCell cell23 = headerRow.createCell(23);
        cell23.setCellValue("合同起始日期");
        cell23.setCellStyle(headerStyle);
        HSSFCell cell24 = headerRow.createCell(24);
        cell24.setCellValue("合同终止日期");
        cell24.setCellStyle(headerStyle);
    }

    private static void loadData2Excel(Collection<?> dataSet,HSSFSheet sheet,Class clazz){
        Iterator iterator=dataSet.iterator();
        int index=0; //下标
        while(iterator.hasNext()){
            HSSFRow row=sheet.createRow(index+1);
            //clazz.newInstance();
        }
    }

}
