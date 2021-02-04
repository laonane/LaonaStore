package wiki.laona.core.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 返回结果
 * @author: laona
 * @create: 2021-02-04 13:51
 **/
@Data
public class Result implements Serializable {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据，没有数据则为 null
     */
    private Object data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result() {
    }

    public Result(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 返回成功
     *
     * @return {@linkplain Result} result 实体
     */
    public static Result success() {
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 返回成功
     *
     * @param data 返回值
     * @return {@linkplain Result} result 实体
     */
    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS, data);
    }

    /**
     * 返回失败
     *
     * @param resultCode 失败信息
     * @return {@linkplain Result} result
     */
    public static Result failure(ResultCode resultCode) {
        return new Result(resultCode);
    }

    /**
     * 返回失败
     *
     * @param resultCode resultCode
     * @param data       返回值
     * @return {@linkplain Result} result
     */
    public static Result failure(ResultCode resultCode, Object data) {
        return new Result(resultCode, data);
    }
}
