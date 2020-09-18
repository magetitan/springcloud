package com.my.springcloud.common.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * excel导入导出工具类
 */
public class EasyExcel {
    protected static FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyyMMddHH:mm:ss");
    private final static Logger LOGGER = LoggerFactory.getLogger(EasyExcel.class);

    /**
     * @param fileName             文件名
     * @param cameraReportStopList 普通vo类
     * @param entityModel          继承BaseRowModel的模型class
     * @param response
     * @param <T>
     */
    public static <T extends BaseRowModel> void baseExportExcel(String fileName, List cameraReportStopList, Class<T> entityModel, HttpServletResponse response) {
        List<BaseRowModel> modelList = new ArrayList<>();
        if (StringUtils.isEmpty(cameraReportStopList)) return;
        cameraReportStopList.forEach(cameraReportStopVo -> {
            try {
                BaseRowModel cameraReportStopModel = entityModel.newInstance();
                BeanUtils.copyProperties(cameraReportStopVo, cameraReportStopModel);
                modelList.add(cameraReportStopModel);
            } catch (Exception e) {

            }
        });
        try {
            writeExcel(response, modelList, fileName + fastDateFormat.format(new Date()), "第一页");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     */
    public static void writeExcel(HttpServletResponse response, List<? extends BaseRowModel> list,
                                  String fileName, String sheetName) throws Exception {
        ExcelWriter writer = new ExcelWriter(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        Class clazz = null;
        if (list.size() > 0) {
            clazz = list.get(0).getClass();
        } else {
            clazz = BaseRowModel.class;
        }
        Sheet sheet = new Sheet(1, 0, clazz);
        sheet.setSheetName(sheetName);
        writer.write(list, sheet);
        writer.finish();
    }

    /**
     * 导出文件时为Writer生成OutputStream
     */
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }

    /**
     * 判断扩展名是xlsx
     *
     * @param fileName
     * @return
     */
    public static boolean judgeExtIsXlsx(String fileName) {
        String[] arr = fileName.split(".");
        if (arr.length > 1) {
            String extName = arr[arr.length - 1];
            return "xlsx".equals(extName.toLowerCase());
        }
        return false;
    }

    /**
     * 解析Excel返回信息
     *
     * @param multipartFile
     * @return
     */
    public static List<T> processExcel(MultipartFile multipartFile, Class<? extends BaseRowModel> clazz) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            CommonListener commonListener = new CommonListener<>();//new CommonListener<类名>();
            EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, clazz), commonListener);
            return commonListener.getData();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 动态List下载
     *
     * @param response 响应
     * @param title    标题
     * @param content  内容
     * @param fileName Excel名称
     */
    public static void dynamicExcel(HttpServletResponse response, List<String> title, List<List<String>> content, String fileName) throws UnsupportedEncodingException {
        //创建HSSFWorkbook对象(excel的文档对象)

        OutputStream output = null;
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        HSSFSheet sheet = wb.createSheet(fileName);
//            //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
//            //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
//            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setFontName("宋体");
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        fontStyle.setFontHeightInPoints((short) 20);
        fontStyle.setBold(true);
        cellStyle.setFont(fontStyle);
        HSSFRow row2 = null;
        if (title.size() > 0) {
            //在sheet里创建第二行
            row2 = sheet.createRow(0);
            //创建单元格并设置单元格内容
            for (int i = 0; i < title.size(); i++) {
                row2.createCell(i).setCellValue(title.get(i));
            }
        }
        if (!CollectionUtils.isEmpty(content)) {
            for (int i = 0; i < content.get(0).size(); i++) {
                row2 = sheet.createRow(i + 1);
                //创建单元格并设置单元格内容
                for (int t = 0; t < title.size(); t++) {
                    row2.createCell(t).setCellValue(content.get(t).get(i));
                }
            }
        }
        String name = fileName+df1.format(new Date())+ ".xls";
        //输出Excel文件
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",  "attachment; filename="  +new String(name.getBytes(), "iso8859-1"));
        try {
            output = response.getOutputStream();
            wb.write(output);
            if (output != null) {
                output.close();
            }
        } catch (IOException e) {
            LOGGER.error("context", e);
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                LOGGER.error("context", e);
            }
        }
        }
    /**
     *  动态表头
     *  与上面的方法不同的是list是数据行集合(list.(0).(1),list.(0).(2),list.(0).(3))，上面是数据列集合(list.(0).(1),list.(2).(1),list.(3).(1))
     *   gch
     */
    public static void export(HttpServletResponse response,List<List<String>> headList,List<List<Object>> list,String fileName ) throws IOException {
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
        ExcelWriter writer = null;
        try {
            writer = EasyExcelFactory.getWriter(getOutputStream(fileName + df1.format(new Date()),response));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("exceptionContext", e);
        }
        // 表单
        Sheet sheet = new Sheet(1,0);
        sheet.setSheetName(fileName);
        // 创建一个表格
        Table table = new Table(1);
        table.setHead(headList);
        writer.write1(list,sheet,table);
        // 记得 释放资源
        writer.finish();
        System.out.println("ok");
    }

}
