package enums;

/**
 * @program: protocol
 * @description: 需要手动生成的属性的枚举类
 * @author: gaoxiang
 * @create: 2018-08-26 17:03
 **/
public enum ParamsInfoList {
    //与手动生成的属性类型有关的枚举类型变量

    _rticket("_rticket"),ts("ts"),mobile("mobile");

    private String vaule;

    private ParamsInfoList(String vaule){
        this.vaule = vaule;
    }
    public String getVaule(){
        return vaule;
    }

    public Boolean isEquals(String num){
        if (num.equals(vaule)){
            return true;
        }else{
            return false;
        }
    }

    public Boolean isEmpty(){
        return vaule.isEmpty();
    }
}
