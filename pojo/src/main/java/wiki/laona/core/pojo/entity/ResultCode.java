package wiki.laona.core.pojo.entity;

/**
 * @description: 状态码
 * @author: laona
 * @create: 2021-02-04 13:55
 **/
public enum ResultCode {
    /**
     * 成功状态码
     */
    SUCCESS(1, "成功"),
    /**
     * 参数相关
     */
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数确实"),
    /**
     * 用户相关
     */
    USER_NOT_LOGIN(2001, "用户未登录，访问路径需要验证，请登录"),
    USER_LOGIN_ERROR(2002, "账号不存在或者密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用"),
    USER_NOT_EXIST(2004, "用户不存在"),
    USER_HAS_EXITS(2005, "用户已存在"),
    /**
     * 操作相关 curd
     */
    FAILED(3001, "失败"),
    UPDATE_FAILED(3002, "更新失败"),
    DELETE_FAILED(3003, "删除失败"),
    QUERY_FAILED(3004, "查询失败"),
    SAVE_FAILED(3005, "保存失败"),
    UPDATE_SUCCESS(3006, "更新成功"),
    DELETE_SUCCESS(3007, "删除成功"),
    QUERY_SUCCESS(3008, "查询成功"),
    SAVE_SUCCESS(3009, "保存成功");

    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 提示信息
     */
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
