 package com.unicom.eos.codebuysync.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangjiashuai
 * @date 2019/06/17
 */
public class StringUtil {
    
    private StringUtil() {}
    
    private static final String UNDER_LINE = "_";
    
    /**
     * 下划线分隔形式的字符串转为驼峰形式
     * @param str 下划线分隔形式的字符串
     * @return 驼峰形式的字符串
     */
    public static String underLine2Camel(String str) {
        if(StringUtils.isEmpty(str) || !str.contains(UNDER_LINE)) {
            return str;
        }
        String[] array = str.split(UNDER_LINE);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            String item = array[i];
            if(i == 0) {
                builder.append(item);
            } else {
                builder.append(StringUtils.capitalize(item));
            }
        }
        return builder.toString();
    }
    
    public static void main(String[] args) {
        String source = "order_id,order_no,business_type,"
            + "order_delivery_intention,order_creat_time,goods_name,province_code_post,city_code_post,district_code_post,post_addr,dispatch_cmd_cate,dispatch_state,dispatch_obj_type,dispatch_obj_subject,dispatch_obj_tel,dispatch_obj_time,"
            + "manual_reason_cate,manual_reason_desc,dispatch_recall_reason,dispatch_tag,auto_dispatch_grid,auto_dispatch_refer,auto_trace,map_point,timeout_tag,order_assign_subject,change_reason,dispatch_manager_tag,update_staff,update_time,"
            + "remark,age_tag,change_type,caller_type,broadband_order_id,cross_city_tag,rsync_tag,lock_tag,sys_channel,bus_channel,product_id,lock_reason,pool_tag,com_code,post_phone,broadband_cust_name,broadband_pspt_type_code,broadband_pspt_no,"
            + "appoint_date,appoint_date_segment,second_pool_limit_tag,post_addr_detail,user_appoint_end_time,user_appoint_start_time,order_province_code,order_city_code,enter_msgo_time,callout_times,contact_time,lock_expires_time,dn,log_dn,age,"
            + "sync_tag,link_install_res_tag,link_install_addr,province_name,city_name,district_name";
        StringBuilder sb = new StringBuilder();
        String[] array = source.split(",");
        for (String str : array) {
            sb.append("#{").append(underLine2Camel(str)).append("},");
        }
        System.out.println(sb);
    }

}
