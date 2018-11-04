package com.space.dyrev.apisupport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.show.api.ShowApiRequest;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @program: hehedada
 * @description: 验证码识别api实现类
 * @author: Mr.gao
 * @create: 2018-10-29 19:12
 **/
public class CodeDistinguishApi {
    // 用户的 apiID与密码
    private String my_appId = "";
    private String my_appSecret = "";
    //验证码长度，如果知道会提高精确度,我觉得为了复用性还是用10比较好
    private int codeLength = 0;
    public int codeType = 30;
    private int isPrecise = 0;
    private static CodeDistinguishApi codeDistinguishApi = new CodeDistinguishApi();
    //但不需要更改密码时，直接调用单粒方法，节省内存

    public static CodeDistinguishApi getInstrance(){
        return codeDistinguishApi;
    }
    private CodeDistinguishApi(){

    }
    public CodeDistinguishApi(String my_appId,String my_appSecret){
        this.my_appId = my_appId;
        this.my_appSecret = my_appSecret;
    }

    /**
     * 是否开启精准模式以提高识别率（以效率为代价）
     * @param booleans true 开启false 关闭
     */
    public void openPrecise(boolean booleans){
        if(booleans){
            isPrecise = 1;
        }else{
            isPrecise =0;
        }
    }
    public void setCodeLength(int codeLength){
        this.codeLength =codeLength;
    }

    //业务方法

    /**
     * 识别英文和数字组成的图片的方法
     * @param img_hase64 需要识别的base64位存储的图片
     * @return
     * @throws CodeException
     */
    public String parsingEnglishAndNumCode(String img_hase64) throws CodeException {
        return parsingCode(" http://route.showapi.com/184-5",img_hase64);
    }

    /**
     * 识别英文、数字、中文组成的验证码的方法
     * @param img_hase64 需要识别的base 64 位存储的图片
     * @return
     * @throws CodeException
     */
    public String parsingEnglishAndNumAndChinaCode(String img_hase64) throws CodeException {
        return parsingCode("http://route.showapi.com/184-2",img_hase64);
    }


    /**
     *
     * @param host
     * @param img_base64
     * @return 当返回”解析失败“时，应该做错误处理，理应抛出异常的到时候做一下异常处理吧
     */
    private String parsingCode(String host,String img_base64) throws CodeException {
        String res=new ShowApiRequest(host,my_appId,my_appSecret)
                .addTextPara("img_base64",img_base64)
                .addTextPara("typeId",codeType+codeLength+"")
                .addTextPara("convert_to_jpg","0")
                .addTextPara("needMorePrecise",isPrecise+"")
                .post();
        JSONObject json = JSON.parseObject(res);
        String result = (String) json.get("showapi_res_code");
        if(result.equals("0")){
            return (String) json.get("showapi_res_body");
        }else{
            System.out.println("解析验证码失败，错误代码为："+result+" 错误描述为："+ json.get("showapi_res_error"));
        }
        throw  new CodeException("验证码解析失败，推荐重新换验证码解析");
    }
}

class CodeException extends Exception{

    public CodeException() {
        super();
    }

    public CodeException(String message, Throwable cause,
                       boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeException(String message) {
        super(message);
    }

    public CodeException(Throwable cause) {
        super(cause);
    }

    /**
     * 重写父类的方法
     */

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return super.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        // TODO Auto-generated method stub
        return super.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        // TODO Auto-generated method stub
        return super.getCause();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        // TODO Auto-generated method stub
        return super.initCause(cause);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    @Override
    public void printStackTrace() {
        // TODO Auto-generated method stub
        super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        // TODO Auto-generated method stub
        super.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        // TODO Auto-generated method stub
        super.printStackTrace(s);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        // TODO Auto-generated method stub
        return super.fillInStackTrace();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        // TODO Auto-generated method stub
        return super.getStackTrace();
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        // TODO Auto-generated method stub
        super.setStackTrace(stackTrace);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }



}