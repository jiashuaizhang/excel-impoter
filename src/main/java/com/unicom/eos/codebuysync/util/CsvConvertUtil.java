package com.unicom.eos.codebuysync.util;

import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 转换CSV中换行导致的错误数据
 */
@Slf4j
public class CsvConvertUtil {

    private static final int COLUMN_NO = 60;
    private static final String COLUMN_SEPARATOR = "\",\"";
    private static final String QUOTES = "\"";
    private static final String CONTENT_SEPARATOR = " ";
    private static final Charset DEFAULT_CHARSET = Charset.forName("GB2312");

    private static final Pattern ORDERID_PATTERN = Pattern.compile("^[\\d]{16}$");

    public static void main(String[] args) throws IOException {
        String sourceFilePath = "E:\\temp\\eos-order-codebuy-sync\\意向单\\export.csv";
        String targetFilePath = "E:\\temp\\eos-order-codebuy-sync\\意向单\\converted.csv";
        convert(sourceFilePath, targetFilePath);
    }

    public static void convert(String sourceFilePath, String targetFilePath) throws IOException {
        File source = new File(sourceFilePath);
        log.info("解析和转换文件开始");
        List<String> sourceLines = Files.readLines(source, DEFAULT_CHARSET);
        List<String> convertedLines = new ArrayList<>(sourceLines.size());
        StringBuilder tempLine = new StringBuilder();
        for (String line : sourceLines) {
            // 空行跳过
            if (StringUtils.isBlank(line)) {
                continue;
            }
            String[] columns = line.split(COLUMN_SEPARATOR);
            //正常结束
            if (columns.length == COLUMN_NO) {
                convertedLines.add(line);
                // 临时数据不为空，积累的临时数据应该刚好是一行完整数据
                if (tempLine.length() > 0) {
                    tempLine = flushBuffer(convertedLines, tempLine);
                }
            } else {
                if (tempLine.length() > 0) {
                    String first = columns[0].replaceFirst(QUOTES, StringUtils.EMPTY);
                    // 以order_id开始，不能直接拼接到异常行上。前次异常行缓冲清空
                    if (isOrderId(first)) {
                        tempLine = flushBuffer(convertedLines, tempLine);
                    }
                }
                log.info("遇到不完整行数据:【{}】", line);
                tempLine.append(line).append(CONTENT_SEPARATOR);
            }
        }
        sourceLines = null;
        log.info("解析和转换文件结束， 写文件开始");
        File target = new File(targetFilePath);
        try (BufferedWriter writer = Files.newWriter(target, DEFAULT_CHARSET)) {
            for (String line : convertedLines) {
                writer.append(line);
                writer.newLine();
            }
        }
        log.info("写文件结束");
    }

    private static boolean isOrderId(String str) {
        return ORDERID_PATTERN.matcher(str).matches();
    }

    private static StringBuilder flushBuffer(List<String> convertedLines, StringBuilder tempLine) {
        log.info("处理后数据:【{}】", tempLine);
        convertedLines.add(tempLine.toString());
        return tempLine = new StringBuilder();
    }

    @Test
    public void simpleTest() {
        System.out.println(isOrderId("7519070701755014"));
        System.out.println(isOrderId("7319020539191483"));
        System.out.println(isOrderId("6819032336052934"));
        System.out.println(isOrderId("8619032547309648"));
        System.out.println(isOrderId("1619051601547574"));
    }
}
