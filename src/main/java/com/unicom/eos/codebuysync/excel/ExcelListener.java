package com.unicom.eos.codebuysync.excel;

import static com.unicom.eos.codebuysync.queue.OrderDispatchQueueHolder.orderDispatchQueue;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.unicom.eos.codebuysync.entity.OrderDispatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExcelListener extends AnalysisEventListener<OrderDispatch> {
    
    private final AtomicInteger counter = new AtomicInteger();
    
    private volatile int count;

    @Override
    public void invoke(OrderDispatch orderDispatch, AnalysisContext context) {
        if(orderDispatch != null) {
            log.info("行号: 【{}】，解析数据：【{}】",context.getCurrentRowNum(), orderDispatch.getOrderId());
            orderDispatchQueue.add(orderDispatch);
            counter.incrementAndGet();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        count = counter.intValue();
        log.info("excel解析任务结束,行数:【{}】", count);
    }
    
    public int getCount() {
        return count;
    }
    
}
