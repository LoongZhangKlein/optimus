package com.optimus.commons;



import com.alibaba.fastjson.JSON;
import com.optimus.enums.GlobalEnum;
import com.optimus.exception.GlobalException;
import lombok.Data;

/**
 * 封装全局结果集
 * @author DragonZhang
 */
@Data
public class R<T> {
    private String code;

    private String msg;

    private T data;



    public T checkAndGet(){
        if (!GlobalEnum.SUCCESS.getCode().equals(this.code)){
            throw new GlobalException(this.code,this.msg);
        }
        return this.data;
    }

    public static R ok(){
        R r=new R();
        r.code= GlobalEnum.SUCCESS.getCode();
        r.msg= GlobalEnum.SUCCESS.getMsg();
        return r;
    }

    public static <T> R creatR(GlobalEnum errorEnum){
        R r=new R();
        r.code=errorEnum.getCode();
        r.msg=errorEnum.getMsg();
        return r;
    }
    public static <T> R creatR(T o, GlobalEnum errorEnum){
        R r=new R();
        r.code=errorEnum.getCode();
        r.msg=errorEnum.getMsg();
        r.data=o;
        return r;
    }
    public static <T> R creatR(JSON o, GlobalEnum errorEnum){
        R r=new R();
        r.code=errorEnum.getCode();
        r.msg=errorEnum.getMsg();
        r.data=o;
        return r;
    }
    public static <T> R ok(T o){
        R r=new R();
        r.code= GlobalEnum.SUCCESS.getCode();
        r.msg= GlobalEnum.SUCCESS.getMsg();
        r.data=o;
        return r;
    }

    public static <T> R fail(){
        R r=new R();
        r.code= GlobalEnum.ERROR.getCode();
        r.msg= GlobalEnum.ERROR.getMsg();
        return r;
    }
    public static <T> R fail(GlobalEnum globalEnum){
        R r=new R();
        r.code=globalEnum.getCode();
        r.msg=globalEnum.getMsg();
        return r;
    }
    public static R fail(String code, String msg) {
        R r=new R();
        r.code=code;
        r.msg=msg;
        return r;
    }

}
