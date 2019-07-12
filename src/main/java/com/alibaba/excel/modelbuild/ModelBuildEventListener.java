package com.alibaba.excel.modelbuild;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.ExcelHeadProperty;
import com.alibaba.excel.util.TypeUtil;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.unicom.eos.codebuysync.config.ErrorFileConfig;
import com.unicom.eos.codebuysync.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanMap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author jipengfei
 * @author zhangjiashuai
 *  跳过错误行，记录日志 ,  而不是终止解析进程
 */
@Slf4j
@SuppressWarnings({"rawtypes","unchecked"})
public class ModelBuildEventListener extends AnalysisEventListener {

    @Override
    public void invoke(Object object, AnalysisContext context) {
        if (context.getExcelHeadProperty() != null && context.getExcelHeadProperty().getHeadClazz() != null) {
            try {
                Object resultModel = buildUserModel(context, (List<String>) object);
                context.setCurrentRowAnalysisResult(resultModel);
            } catch (Exception e) {
                log.error("解析数据异常,行号: " + context.getCurrentRowNum(), e);
                context.setCurrentRowAnalysisResult(null);
    //            throw new ExcelGenerateException(e);
                if (ErrorFileConfig.enabled_) {
                    String path = ErrorFileConfig.analysisErrorFile_;
                    String line = new StringBuilder("行号:【").append(context.getCurrentRowNum()).append("】数据:【").append(String.valueOf(object)).append("】").toString();
                    FileUtil.append(path, line);
                }
            }
        }
    }

    private Object buildUserModel(AnalysisContext context, List<String> stringList) throws IllegalAccessException, InstantiationException {
        ExcelHeadProperty excelHeadProperty = context.getExcelHeadProperty();
        if (excelHeadProperty == null) {
            return null;
        }
        Object resultModel = excelHeadProperty.getHeadClazz().newInstance();
        BeanMap.create(resultModel).putAll(
            TypeUtil.getFieldValues(stringList, excelHeadProperty, context.use1904WindowDate()));
        return resultModel;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
