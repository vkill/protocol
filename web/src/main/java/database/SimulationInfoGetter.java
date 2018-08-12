package database;

import po.AccountPo;
import po.SimulationPo;
import tools.InfoTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @program: protocol
 * @description: 模拟器设备信息获取类，理论上讲根本用不到这里面的插入方法，只可能用到读取设备号的方法
 * @author: gaoxiang
 * @create: 2018-08-12 22:50
 **/
public class SimulationInfoGetter {

    public InfoTools infoTools;


    public SimulationInfoGetter(){
        infoTools =InfoTools.getInstance();

    }

    public int insertOneInfo(SimulationPo simulationPo){
        //infoTools.reGetConnection();
        Connection conn = infoTools.connection;
        int i = 0;
        String sql = "insert into simulation_table(IMEINum, type, model)  value (?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,simulationPo.getIMEI_NUM());
            pstmt.setString(2,simulationPo.getSimulation_Type());
            pstmt.setString(3,simulationPo.getSimulation_Model());
            i = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("该值已经存在");
        }
        return i;
    }

    public Vector<SimulationPo> getAllSimulation(){
        //infoTools.reGetConnection();
        Connection conn = infoTools.connection;
        String sql = "select * from simulation_table ";
        PreparedStatement pstmt;
        Vector<SimulationPo> simulationPos = new Vector<SimulationPo>();
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                SimulationPo simulationPo =new SimulationPo();
                simulationPo.setIMEI_NUM(rs.getString("IMEINum"));
                simulationPo.setSimulation_Type("type");
                simulationPo.setSimulation_Model("model");
                simulationPos.add(simulationPo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //infoTools.closeConnection();
        return simulationPos;
    }
}
