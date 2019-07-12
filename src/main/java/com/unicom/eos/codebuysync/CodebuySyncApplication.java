package com.unicom.eos.codebuysync;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.unicom.eos.codebuysync.task.OrderDispatchSyncTask;

/**
 * @author zhangjiashuai
 * @create 2019/6/18
 */
@SpringBootApplication
@MapperScan("com.unicom.eos.codebuysync.mapper")
public class CodebuySyncApplication {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CodebuySyncApplication.class);
        OrderDispatchSyncTask orderDispatchSyncTask = context.getBean(OrderDispatchSyncTask.class);
        orderDispatchSyncTask.execute();
    }
}
