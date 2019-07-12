package com.unicom.eos.codebuysync.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author:xuekan
 * @Date:2018/11/19 13:31
 * @Description: 分布式事务的手动管理
 */

@Component
public class EosTransactionManager {


    //1.获取事务控制管理器
    @Autowired
    private DataSourceTransactionManager transactionManager;



     public TransactionStatus getNewEosTransaction(){
         //2.获取事务定义

         DefaultTransactionDefinition def = new DefaultTransactionDefinition();

         //3.设置事务隔离级别，开启新事务

         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

         def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

         //4.获得事务状态

         TransactionStatus status = transactionManager.getTransaction(def);
         return  status;
    }


    public void commit(TransactionStatus status) {
        transactionManager.commit(status);
    }

    public void rollback(TransactionStatus status) {
        transactionManager.rollback(status);
    }
}
