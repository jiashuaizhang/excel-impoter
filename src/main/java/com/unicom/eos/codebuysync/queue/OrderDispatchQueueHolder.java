 package com.unicom.eos.codebuysync.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.unicom.eos.codebuysync.entity.OrderDispatch;

/**
 * @author zhangjiashuai
 * @date 2019/06/19
 */
public class OrderDispatchQueueHolder {
    
    public static final BlockingQueue<OrderDispatch> orderDispatchQueue = new LinkedBlockingQueue<>();
    
}
