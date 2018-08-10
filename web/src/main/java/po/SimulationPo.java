package po;

/**
 * @program: protocol
 * @description: 模拟器参数存储码
 * @author: gaoxiang
 * @create: 2018-08-10 15:03
 **/
public class SimulationPo {

    /**
     * IMEI码、手机品牌、手机型号
     */
    private String IMEI_NUM;
    private String Simulation_Type;
    private String Simulation_Model;

    public String getIMEI_NUM() {
        return IMEI_NUM;
    }

    public void setIMEI_NUM(String IMEI_NUM) {
        this.IMEI_NUM = IMEI_NUM;
    }

    public String getSimulation_Type() {
        return Simulation_Type;
    }

    public void setSimulation_Type(String simulation_Type) {
        Simulation_Type = simulation_Type;
    }

    public String getSimulation_Model() {
        return Simulation_Model;
    }

    public void setSimulation_Model(String simulation_Model) {
        Simulation_Model = simulation_Model;
    }

}
