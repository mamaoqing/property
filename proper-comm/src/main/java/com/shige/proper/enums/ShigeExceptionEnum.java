package com.shige.proper.enums;

public enum ShigeExceptionEnum {
    /**
     * 系统同意错误吗。
     * 5xxxxx系统异常
     * 4xxxxx参数异常
     * 3xxxxx设置异常
     * 1xxxxx登录问题
     */
    SYSTEM_INSERT_ERROR ("添加数据系统异常",50011),
    SYSTEM_UPDATE_ERROR ("修改数据系统异常",50012),
    SYSTEM_DELETE_ERROR ("删除数据系统异常",50013),
    SYSTEM_SELECT_ERROR ("查询数据系统异常",50014),
    PARAMS_MISS_ERROR ("参数错误",40011),
    PAGE_NO_MISS_ERROR ("参数错误,页码必传",40012),
    USER_EXIST_ERROR ("用户名已经存在",40021),
    SET_USER_ROLE_ERROR ("设置用户权限异常",30011),
    SET_USER_COMM_ERROR ("设置用户数据权限异常",30012),
    SET_ROLE_MENU_ERROR ("设置角色菜单异常",30013),
    RESET_PASSWORD_ERROR ("旧密码输入错误，重设密码失败",30014),
    RESET_PASSWORD_ERROR_SYSTEM ("重设密码失败",30010),
    LOGIN_TIME_OUT ("登录失效",10010),
    ;
    private Integer code;

    private String msg;

    ShigeExceptionEnum(String msg,Integer code) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
