package cn.spring.ssm.common.enums;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.common.enums
 * User: 25414
 * Date: 2019/9/18
 * Time: 8:57
 * Description:
 */
public enum ResultEnum {
    SUCCESS(0, "success"), FAILED(-1, "failed"), EXCEPTION(-2, "exception");
    private Integer code;
    private String msg;

    ResultEnum() {
    }

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
