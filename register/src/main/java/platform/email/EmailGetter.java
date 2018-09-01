package platform.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import po.PhonePo;

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
    //api_qianbaiwan_rrgr,qianbaiwan
    private String userName ="api_qianbaiwan_fjxr";
    private String password ="qianbaiwan";
    private String projectID ="1019";
    private String projectPasswordID = "1024";
    private String Login_url ;
    private String Usertoken ="BF276684B59345D28D937E83B95F1F63";

    /**
     * 登陆平台初始化方法
     */
    public void loginIT(){
        loginIT("api_qianbaiwan_fjxr","qianbaiwan");
    }

    public void loginIT(String user,String password){
        this.userName = user;
        this.password = password;
        String tag = "ERR";
        int errTime =0;
        String[] buffers = null;
        Login_url = "http://new.wmisms.com/yhapi.ashx?Action=userLogin&userName="+userName+"&userPassword="+this.password;

        try {

            while (tag.equals("ERR")&&errTime<3){
                Document document = Jsoup.connect(Login_url).get();
                // System.out.println(document.body().text());
                String buff=document.body().text();
                buffers = buff.split("\\|");
                tag = buffers[0];
                if(tag.equals("OK")){
                    System.out.println("登录成功");
                    break;
                }
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
     * 返回包含手机号码信息的po类型
     */
    public PhonePo getPhoneNumber(){
        return getPhoneNumber("随机");
    }
    public PhonePo getPhoneNumber(String phoneNum){
            String tag ="ERR";
        String phone_url;
        if(phoneNum.equals("随机")){
            phone_url="http://new.wmisms.com/yhapi.ashx?Action=getPhone&token="+Usertoken+"&i_id="+projectID+"&d_id=&p_operator=&p_qcellcore=&mobile=";

        }else{
            phone_url="http://new.wmisms.com/yhapi.ashx?Action=getPhone&token="+Usertoken+"&i_id="+projectPasswordID+"&d_id=&p_operator=&p_qcellcore=&mobile="+phoneNum;
        }
        Document document = null;
        String[] buffers = null;
        int worryTime =0;
        while(tag.equals("ERR")&&worryTime<2){
            try {
                document = Jsoup.connect(phone_url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String buff=document.body().text();
            buffers = buff.split("\\|");
            tag =buffers[0];
            if(tag.equals("OK")){
                System.out.println("成功获取手机号: "+buffers[4]+buffers[1]);
                break;
            }else{
                System.out.print("发生错误，错误信息为: "+buff);
            }
            worryTime++;
        }
        if(worryTime==2){
            System.out.println("获取手机号码失败，将返回空值");
            return null;
        }else{
            if(buffers.length==7){
                PhonePo phonePo = new PhonePo(buffers[1],buffers[2],buffers[3],buffers[4],buffers[6]);
                return phonePo;
            }else{
                System.out.println("格式出现错误，请进行更正");
            }

            return null;
        }

    }
    /**
     *
     * @param P_ID 注意检查参数不能为控
     * @return
     */
    public String getIdentCode(String P_ID){
        String infoUrl ="http://new.wmisms.com/yhapi.ashx?Action=getPhoneMessage&token="+Usertoken+"&p_id="+P_ID;
        Document document =null;
        String buff = null;
        String[] buffers;
        String tag ="ERR";
        int buffer_Num =0;
        while(tag.equals("ERR")&buffer_Num<10){
            try {
                Thread.sleep(5000);
                document =Jsoup.connect(infoUrl).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            buff = document.body().text();
            buffers = buff.split("\\|");
            tag = buffers[0];
            if(tag.equals("OK")){
                return buffers[2].substring(3,7);
            }
            else{
                System.out.println("出现错误："+buffers[1]);
            }
            buffer_Num++;
        }
        System.out.print("获取验证码失败");
        return "请求超时";
    }
    public static void main(String[] args) {

        EmailGetter emailGetter =new EmailGetter();
        emailGetter.loginIT();
        while(true){
            PhonePo phonePo =emailGetter.getPhoneNumber();
            System.out.print(emailGetter.getIdentCode(phonePo.getP_ID()));
        }

    }
}
