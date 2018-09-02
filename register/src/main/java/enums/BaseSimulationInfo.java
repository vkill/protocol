package enums;

/**
 * @program: protocol
 * @description: 模拟器信息参数类型枚举类
 * @author: gaoxiang
 * @create: 2018-08-26 16:47
 **/
public enum     BaseSimulationInfo {
    //与模拟器有关的枚举类型变量

    uuid("uuid"),device_type("device_type"),device_brand("device_brand");

    private String vaule;

    private BaseSimulationInfo(String vaule){
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
