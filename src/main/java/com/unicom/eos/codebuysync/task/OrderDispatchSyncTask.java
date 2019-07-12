package com.unicom.eos.codebuysync.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.unicom.eos.codebuysync.entity.OrderDispatch;
import com.unicom.eos.codebuysync.excel.ExcelListener;
import com.unicom.eos.codebuysync.service.OrderDispatchSyncService;

import lombok.extern.slf4j.Slf4j;

import static com.unicom.eos.codebuysync.queue.OrderDispatchQueueHolder.orderDispatchQueue;

/**
 * @author zhangjiashuai
 * @date 2019/06/18
 */
@Slf4j
@Component
public class OrderDispatchSyncTask {

    @Value("${import.excelPath}")
    private String excelPath;
    @Autowired
    private ExcelListener excelListener;
    @Autowired
    private OrderDispatchSyncService orderDispatchSyncService;

    private static final ExecutorService pollExecutor = Executors.newSingleThreadExecutor();
    private static final ExecutorService saveExecutor = Executors.newFixedThreadPool(16);

    private final AtomicInteger counter = new AtomicInteger();

    public void execute() {

        Runnable pollTask = () -> {
            while (true) {
                int excelTotal = excelListener.getCount();
                if (excelTotal > 0 && excelTotal == counter.intValue()) {
                    log.info("入库任务结束，成功数量:【{}】", orderDispatchSyncService.getSuccessCount());
                    saveExecutor.shutdown();
                    break;
                }
                try {
                    OrderDispatch orderDispatch = orderDispatchQueue.poll(1, TimeUnit.SECONDS);
                    if (orderDispatch != null) {
                        saveExecutor.submit(() -> {
                            orderDispatchSyncService.completeAndSave(orderDispatch);
                            counter.incrementAndGet();
                        });
                    }
                } catch (InterruptedException e) {
                    log.error(e.getClass().getName(), e);
                }
            }
        };
        pollExecutor.submit(pollTask);

        log.info("import excel begin, file path: 【{}】", excelPath);
        File file = new File(excelPath);
        try (InputStream inputStream = new FileInputStream(file)) {
            EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, OrderDispatch.class), excelListener);
        } catch (Exception e) {
            log.error(e.getClass().getName(), e);
        }
        pollExecutor.shutdown();
    }

}
