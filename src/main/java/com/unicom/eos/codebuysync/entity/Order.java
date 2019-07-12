package com.unicom.eos.codebuysync.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 8217774531916588742L;

    private Long orderId;
    private Integer dn;
    private String orderState;

}
