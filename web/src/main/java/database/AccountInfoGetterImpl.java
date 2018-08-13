package database;

import po.AccountPo;
import tools.InfoTools;

import java.sql.*;
import java.util.Vector;

/**
 * @program: web
 * @description: 账户数据库操作类
 * @author: gaoxiang
 * @create: 2018-06-04 00:28
 **/
public class AccountInfoGetterImpl {

    public InfoTools infoTools;


    public AccountInfoGetterImpl(){
        infoTools =InfoTools.getInstance();

    }

    public int insertOneInfo(AccountPo accountPo){
        //infoTools.reGetConnection();
        Connection conn = infoTools.connection;
        int i = 0;
        String sql = "insert into account_table(name,phoneNum,phoneArea,password) value (?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,accountPo.getName());
            pstmt.setString(2,accountPo.getPhone_Num());
            pstmt.setString(3,accountPo.getPhone_Area());
            pstmt.setString(4,accountPo.getPhone_PassWord());

            i = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("该值已经存在");
        }
        return i;
    }

    public Vector<AccountPo> getAllAccount(){
        //infoTools.reGetConnection();
        Connection conn = infoTools.connection;
        String sql = "select * from account_table ";
        PreparedStatement pstmt;
        Vector<AccountPo> accountPos = new Vector<AccountPo>();
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                AccountPo accountPo = new AccountPo();
                accountPo.setName(rs.getString("name"));
                accountPo.setPhone_Num(rs.getString("phoneNum"));
                accountPo.setPhone_Area(rs.getString("phoneArea"));
                accountPo.setPhone_PassWord(rs.getString("password"));
                accountPos.add(accountPo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //infoTools.closeConnection();
        return accountPos;
    }


}
