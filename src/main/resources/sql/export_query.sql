SELECT
	ORDER_ID,
	ORDER_NO,
	BUSINESS_TYPE,
	ORDER_DELIVERY_INTENTION,
	ORDER_CREAT_TIME,
	GOODS_NAME,
	PROVINCE_CODE,
	CITY_CODE,
	DISTRICT_CODE,
	DISPATCH_CMD_CATE,
	DISPATCH_STATE,
	DISPATCH_OBJ_TYPE,
	DISPATCH_OBJ_SUBJECT,
	DISPATCH_OBJ_TEL,
	DISPATCH_OBJ_TIME,
	MANUAL_REASON_CATE,
	MANUAL_REASON_DESC,
	DISPATCH_RECALL_REASON,
	DISPATCH_TAG,
	AUTO_DISPATCH_GRID,
	AUTO_DISPATCH_REFER,
	AUTO_TRACE,
	MAP_POINT,
	TIMEOUT_TAG,
	ORDER_ASSIGN_SUBJECT,
	CHANGE_REASON,
	DISPATCH_MANAGER_TAG,
	UPDATE_STAFF,
	UPDATE_TIME,
	REMARK,
	AGE_TAG,
	CHANGE_TYPE,
	CALLER_TYPE,
	BROADBAND_ORDER_ID,
	CROSS_CITY_TAG,
	RSYNC_TAG,
	LOCK_TAG,
	SYS_CHANNEL,
	BUS_CHANNEL,
	LOCK_REASON,
	PRODUCT_ID,
	POOL_TAG,
	COM_CODE,
	APPOINT_DATE,
	APPOINT_DATE_SEGMENT,
	POST_PHONE,
	BROADBAND_CUST_NAME,
	BROADBAND_PSPT_TYPE_CODE,
	BROADBAND_PSPT_NO,
	SECOND_POOL_LIMIT_TAG,
	USER_APPOINT_START_TIME,
	USER_APPOINT_END_TIME,
	ORDER_PROVINCE_CODE,
	ORDER_CITY_CODE,
	ENTER_MSGO_TIME,
	CALLOUT_TIMES,
	CONTACT_TIME,
	LOCK_EXPIRES_TIME,
	LINK_INSTALL_RES_TAG,
	LINK_INSTALL_ADDR
from ORDER_DISPATCH
WHERE PROVINCE_CODE = '110000'