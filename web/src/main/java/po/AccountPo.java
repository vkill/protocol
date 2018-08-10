package po;

/**
 * @program: protocol
 * @description: 账户信息存储类型
 * @author: gaoxiang
 * @create: 2018-08-10 14:53
 **/
public class AccountPo {

    /**
     * 属性内容依次为用户姓名、对应手机号、对应手机归属地区、用户密码、账户状态
     */
    private String name;
    private String phone_Num;
    private String phone_Area;
    private String phone_PassWord;
    private String Account_state;

    public String getName() {   
        return name;
    }

    public String getPhone_Area() {
        return phone_Area;
    }

    public void setPhone_Area(String phone_Area) {
        this.phone_Area = phone_Area;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_Num() {
        return phone_Num;
    }

    public void setPhone_Num(String phone_Num) {
        this.phone_Num = phone_Num;
    }

    public String getPhone_PassWord() {
        return phone_PassWord;
    }

    public void setPhone_PassWord(String phone_PassWord) {
        this.phone_PassWord = phone_PassWord;
    }

    public String getAccount_state() {
        return Account_state;
    }

    public void setAccount_state(String account_state) {
        Account_state = account_state;
    }

}
