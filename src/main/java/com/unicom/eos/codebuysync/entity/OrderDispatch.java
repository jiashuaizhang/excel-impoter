package com.unicom.eos.codebuysync.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @数表名称 tf_b_order_dispatch
 * @开发日期 2019-06-11
 * @开发作者 by: 薛衎
 */
@Getter
@Setter
@ToString(exclude = {"index"})
public class OrderDispatch extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = 3831055355293537472L;

    /**  (主健ID) (无默认值) */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ExcelProperty(index = 0)
    private Long orderId;

    /**  (无默认值) */
    @ExcelProperty(index = 1)
    private String orderNo;

    /**  (无默认值) */
    @ExcelProperty(index = 2)
    private String businessType;

    /** 订单中心配送意向：07上门 10自提 99码上购派单/智能调度(必填项)  (默认值为: 99) */
    @ExcelProperty(index = 3)
    private String orderDeliveryIntention;

    /**  (无默认值) */
    @ExcelProperty(index = 4)
    private Date orderCreatTime;

    /**  (无默认值) */
    @ExcelProperty(index = 5)
    private String goodsName;

    /**  (无默认值) */
    @ExcelProperty(index = 6)
    private String provinceCodePost;

    /**  (无默认值) */
    @ExcelProperty(index = 7)
    private String cityCodePost;

    /**  (无默认值) */
    @ExcelProperty(index = 8)
    private String districtCodePost;

    /**  (无默认值) */
    private String postAddr;

    /** 调度指令状态分类：0.待智能调度 1.智能调度 2.转人工处理/待人工审核/待人工调度    3.人工发起派单/后台管理人员派单/人工调度（管理人员-> 营业厅/派送员）4.它厅转派（营业厅->营业厅）5.它人转派 (派送员->派送员)    6.转第三方物流待分配(eg.等待顺丰分配）7.第三方物流派单（eg.顺丰zop->派送员）8.调度指令结束-退回审单 9.开户完成 10 预约类订单退单    11.第三方物流转派（eg.顺丰zop 派送员->派送员）(必填项) (无默认值) */
    @ExcelProperty(index = 9)
    private Integer dispatchCmdCate;

    /** 0 待派单 1.已派单 2.开户完成 3.退回审单-退回到新订单中心 (为0和1时，DISPATCH_OBJ_XX相关字段置为空；2、只有log表有，3.detail和log表存在)(必填项) (无默认值) */
    @ExcelProperty(index = 10)
    private Integer dispatchState;

    /** 调度分配对象类型：1.营业厅  2.派送员(可选项) (无默认值) */
    @ExcelProperty(index = 11)
    private Integer dispatchObjType;

    /** 调度分配对象主体：xx营业厅编码  xx派送员编码(可选项) (无默认值) */
    @ExcelProperty(index = 12)
    private String dispatchObjSubject;

    /** 调度分配对象联系方式(可选项) (无默认值) */
    @ExcelProperty(index = 13)
    private String dispatchObjTel;

    /** 调度分配对象派送时间(可选项) (无默认值) */
    @ExcelProperty(index = 14)
    private Date dispatchObjTime;

    /** 1 无匹配网格  2 网格内没有符合条件的营业厅或派送员  3 数据/服务异常  4 派单员xxx回退（原因）  5.营业厅xxx回退（原因）  6 超时自动回退 7 管理人员撤回   8.其他原因 9 第三方发起回退(可选项) (无默认值) */
    @ExcelProperty(index = 15)
    private Integer manualReasonCate;

    /** 进入人工调度的原因描述(可选项) (无默认值) */
    @ExcelProperty(index = 16)
    private String manualReasonDesc;

    /** 撤回原因(可选项) (无默认值) */
    @ExcelProperty(index = 17)
    private String dispatchRecallReason;

    /** 派送状态: 1首次派单，2  再次派单(必填项)  (默认值为: 1) */
    @ExcelProperty(index = 18)
    private Integer dispatchTag;

    /** 网格id(可选项) (无默认值) */
    @ExcelProperty(index = 19)
    private String autoDispatchGrid;

    /** 智能调度参照值: 1 订单中心指定到营业厅 2.网格关键字匹配 ; 3.网格区域匹配(可选项) (无默认值) */
    @ExcelProperty(index = 20)
    private Integer autoDispatchRefer;

    /** 智能匹配调度轨迹(可选项) (无默认值) */
    @ExcelProperty(index = 21)
    private String autoTrace;

    /** 收货地址经纬度（百度地图）(可选项) (无默认值) */
    @ExcelProperty(index = 22)
    private String mapPoint;

    /** 0:未超时 1 超时(必填项)  (默认值为: 0) */
    @ExcelProperty(index = 23)
    private Integer timeoutTag;

    /** 订单中心指定配送对象，为ORDER_DELIVERY_INTENTION =10时不为空(可选项) (无默认值) */
    @ExcelProperty(index = 24)
    private String orderAssignSubject;

    /**  变更原因(可选项) (无默认值) */
    @ExcelProperty(index = 25)
    private String changeReason;

    /**  (无默认值) */
    @ExcelProperty(index = 26)
    private String dispatchManagerTag;

    /** 变更人：可能为后台管理人员、派单员编码、营业厅编码、ETM超时、AUTO_DISPATCH(可选项) (无默认值) */
    @ExcelProperty(index = 27)
    private String updateStaff;

    /** 更新操作时间(必填项)  (默认值为: CURRENT_TIMESTAMP) */
    @ExcelProperty(index = 28)
    private Date updateTime;

    /**  (无默认值) */
    @ExcelProperty(index = 29)
    private String remark;

    /**  (无默认值) */
    @ExcelProperty(index = 30)
    private String ageTag;

    /**  (无默认值) */
    @ExcelProperty(index = 31)
    private String changeType;

    /**  (无默认值) */
    @ExcelProperty(index = 32)
    private String callerType;

    /** 宽带订单号(可选项) (无默认值) */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ExcelProperty(index = 33)
    private Long broadbandOrderId;

    /** 0表示本地订单 1表示跨地市订单 2跨省订单(必填项)  (默认值为: 0) */
    @ExcelProperty(index = 34)
    private Integer crossCityTag;

    /** 同步标记：1：默认(新订单中心同步) 2：预约类同步（张旭阳组同步预约单：如-后台电话购、投放预订单）(必填项)  (默认值为: 1) */
    @ExcelProperty(index = 35)
    private Integer rsyncTag;

    /** 订单派单锁定标识。0：未锁定；1：锁定中(必填项)  (默认值为: 0) */
    @ExcelProperty(index = 36)
    private Integer lockTag;

    /** 系统来源 SS01 自有2I/SMA ; SS13:商城后台 SELECT * from td_b_commpara where PARAM_ATTR=9118;(可选项) (无默认值) */
    @ExcelProperty(index = 37)
    private String sysChannel;

    /** 业务形态 BC06电话购 BC07预约上门 SELECT * from td_b_commpara where PARAM_ATTR=9119;(可选项) (无默认值) */
    @ExcelProperty(index = 38)
    private String busChannel;
    
    /** 锁定或释放原因(可选项) (无默认值) */
    @ExcelProperty(index = 39)
    private String lockReason;

    /** 产品ID(可选项) (无默认值) */
    @ExcelProperty(index = 40)
    private String productId;


    /** 1 一级调度池  2 二级调度池  默认为1(必填项)  (默认值为: 1) */
    @ExcelProperty(index = 41)
    private Integer poolTag;

    /** 第三方物流公司编码 SELECT * FROM TF_F_MERCHANT_COMP_NAME (可选项) (无默认值) */
    @ExcelProperty(index = 42)
    private String comCode;

    /** 预约安装时间 格式: YYYY-MM-DD HH24:MI:SS (可选项) (无默认值) */
    @ExcelProperty(index = 43)
    private Date appointDate;
    
    /** 装机时间段 0-全天；1-上午；2-下午(可选项) (无默认值) */
    @ExcelProperty(index = 44)
    private Integer appointDateSegment;
    
    /** 用户联系电话(可选项) (无默认值) */
    @ExcelProperty(index = 45)
    private String postPhone;

    /** 宽带客户姓名(可选项) (无默认值) */
    @ExcelProperty(index = 46)
    private String broadbandCustName;

    /** 宽带客户证件类别编码 (可选项) (无默认值) */
    @ExcelProperty(index = 47)
    private String broadbandPsptTypeCode;

    /** 宽带客户证件号码(可选项) (无默认值) */
    @ExcelProperty(index = 48)
    private String broadbandPsptNo;

    /** 配送方式：1自提 2上门  3上门自提均可(可选项) (无默认值) */
    @ExcelProperty(index = 49)
    private Integer secondPoolLimitTag;

    /** 详细地址，不带省市区县的地址(可选项) (无默认值) */
    private String postAddrDetail;
    
    /**  (无默认值) */
    @ExcelProperty(index = 50)
    private Date userAppointStartTime;

    /**  (无默认值) */
    @ExcelProperty(index = 51)
    private Date userAppointEndTime;

    /** 订单所属省份编码 2位(可选项) (无默认值) */
    @ExcelProperty(index = 52)
    private String orderProvinceCode;

    /** 订单所属地市编码 3位(可选项) (无默认值) */
    @ExcelProperty(index = 53)
    private String orderCityCode;

    /** 进入码上购时间(可选项) (无默认值) */
    @ExcelProperty(index = 54)
    private Date enterMsgoTime;

    /** 外呼次数(可选项) (无默认值) */
    @ExcelProperty(index = 55)
    private String calloutTimes;

    /** 外呼时间 格式: YYYY-MM-DD HH24:MI:SS(可选项) (无默认值) */
    @ExcelProperty(index = 56)
    private Date contactTime;

    /** 锁失效时间 格式: YYYY-MM-DD HH24:MI:SS(可选项) (无默认值) */
    @ExcelProperty(index = 57)
    private Date lockExpiresTime;

    /** 路由ID(必填项) (无默认值) */
    private Integer dn;

    /** 路由ID(必填项) (无默认值) */
    private Integer logDn;

    /** 年龄(可选项) (无默认值) */
    private Integer age;

    /** 0：未同步码上购， 1：已同步码上购(必填项)  (默认值为: 0) */
    private Integer syncTag;

    /** 有无装机资源:0 无 1 有(可选项) (无默认值) */
    @ExcelProperty(index = 58)
    private Integer linkInstallResTag;

    /** 装机地址(可选项) (无默认值) */
    @ExcelProperty(index = 59)
    private String linkInstallAddr;

    /**  (无默认值) */
    private String provinceName;

    /**  (无默认值) */
    private String cityName;

    /**  (无默认值) */
    private String districtName;
    
}