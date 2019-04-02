package org.baoyingwang.springboottips.sample.helloworldproj.enums;

//TODO 到底是否严格设定restful/http返回码是有争论/讨论的 https://www.zhihu.com/question/58686782
//譬如
// option1 - 全部返回200（ok），然后使用程序内部的错误码（作为http response content）来检查。 除非是那种程序中没有捕捉到的，被外部捕捉到返回的才不是200。
// option2 - 严格按照http/RESTful定义来返回相应的error code。其实即使这样，内部的业务码也是有必要的。
// 我个人倾向于option1， option2虽然看起来美好但是实际使用的时候可能很少有人能够掌握那么多错误码。
import lombok.Getter;

@Getter
public enum RestBusinessErrorCode {

    SUCCESS(0, "ok"),
    FAIL_INSERT(1001, "fail to insert"),

    INTERNAL_ERROR(9999, "internal error");

    private int code;
    private String msg;
    RestBusinessErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }



}
