package po;

/**
 * @program: protocol
 * @description: 短信平台手机号码信息存储类
 * @author: gaoxiang
 * @create: 2018-08-04 13:21
 **/
public class PhonePo {

    private String P_ID;
    private String time;
    private String com_Num;
    private String phone_Num;
    private String Area_Num;

    public PhonePo(String P_ID,String time,String com_Num,String phone_Num,String Area_Num){
        this.P_ID = P_ID;
        this.time = time;
        this.com_Num = com_Num;
        this.phone_Num = phone_Num;
        this.Area_Num = Area_Num;
    }
    public String getP_ID() {
        return P_ID;
    }

    public void setP_ID(String p_ID) {
        P_ID = p_ID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCom_Num() {
        return com_Num;
    }

    public void setCom_Num(String com_Num) {
        this.com_Num = com_Num;
    }

    public String getPhone_Num() {
        return phone_Num;
    }

    public void setPhone_Num(String phone_Num) {
        this.phone_Num = phone_Num;
    }

    public String getArea_Num() {
        return Area_Num;
    }

    public void setArea_Num(String area_Num) {
        this.Area_Num = area_Num;
    }
}
