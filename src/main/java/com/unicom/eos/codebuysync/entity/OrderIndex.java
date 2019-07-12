 package com.unicom.eos.codebuysync.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

 /**
 * @author zhangjiashuai
 * @date 2019/06/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderIndex  implements Serializable {

    private static final long serialVersionUID = 8217774531916588741L;
    
    private String orderId;
    private Integer dn;
    private Integer logDn;
    private String postAddr;
    private String provinceCode;
    private String cityCode;

    public OrderIndex(Integer dn, Integer logDn){
        this.dn = dn;
        this.logDn = logDn;
    }
}
