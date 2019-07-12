package com.unicom.eos.codebuysync.util;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.unicom.eos.codebuysync.config.ErrorFileConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FileUtil {

    public static void append(String filePath, String lineContent) {
        try {
            File file = new File(filePath);
            Files.asCharSink(file, StandardCharsets.UTF_8, FileWriteMode.APPEND).write(lineContent + "\n");
        } catch (Exception e) {
            log.error("错误日志写入失败,数据: " + lineContent, e);
        }
    }
}
