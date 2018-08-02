package com.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.validation.constraints.Email;
import java.io.IOException;

/**
 * @program: protocol
 * @description: 获取短信验证码操作类
 * @author: gaoxiang
 * @create: 2018-08-02 18:04
 **/

public class EmailGetter {
    /**
     * 变量依次为：账号、密码、项目类型
     */
    private static final String userName ="api_spacespace_fjxr";
    private static final String password ="spacespace";
    private static final String projectID ="1019";
    private static final String Login_url = "http://new.wmisms.com/yhapi.ashx?Action=userLogin&userName="+userName+"&userPassword="+password;
    private static String Usertoken ="BF276684B59345D28D937E83B95F1F63";

    /**
     * 登陆平台初始化方法
     */
    public void loginIT(){
        String tag = "ERR";
        int errTime =0;
        String[] buffers = null;
        try {

            while (tag.equals("ERR")&&errTime<3){
                Document document = Jsoup.connect(Login_url).get();
                // System.out.println(document.body().text());
                String buff=document.body().text();
                buffers = buff.split("\\|");
                tag = buffers[0];
                System.out.println("尝试登陆错误"+errTime+"次");
                errTime++;
                //因为感觉这方面错误只有网络延迟的可能，所以就120秒吧
                Thread.sleep(120000);
            }
            Usertoken =buffers[1];
            if(errTime==3){
                System.out.println("账号可能存在问题，还是别进行下一步操作了");
            }else{
                System.out.println("用户标识Token为："+Usertoken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提取手机号方法实现,注意提取之前要去主动发送短信
     */
    public String getPhoneNumber(){
        String tag ="ERR";
        String phone_url="http://new.wmisms.com/yhapi.ashx?Action=getPhone&token="+Usertoken+"&i_id="+projectID+"&d_id=&p_operator=&p_qcellcore=&mobile=";
        Document document = null;
        String[] buffers = null;
        while(tag.equals("ERR")){
            try {
                document = Jsoup.connect(phone_url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String buff=document.body().text();
            buffers = buff.split("\\|");
            tag =buffers[0];
        }
        String phoneNumber =buffers[1];
        System.out.println("获取的手机号为："+phoneNumber);
        String infoUrl ="http://new.wmisms.com/yhapi.ashx?Action=getPhoneMessage&token="+Usertoken+"&p_id="+phoneNumber;
        System.out.println(infoUrl);
        try {
            Thread.sleep(10000);
            document =Jsoup.connect(infoUrl).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
        buff =document.body().text();
        System.out.println(buff);
        String oh = buff.split("\\|")[0];
        while (oh.equals("ERR")){
            try {
                Thread.sleep(10000);
                document =Jsoup.connect(infoUrl).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            buff =document.body().text();
            System.out.println(buff);
            oh = buff.split("\\|")[0];
        }
        System.out.println(buff);
         **/
        return phoneNumber;
    }
    public static void main(String[] args) {
        EmailGetter emailGetter =new EmailGetter();
        emailGetter.getPhoneNumber();
    }
}
