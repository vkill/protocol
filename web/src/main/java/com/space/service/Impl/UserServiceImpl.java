package com.space.service.Impl;

import com.space.dao.UserRepository;
import com.space.entity.User;
import com.space.service.UserService;
import com.util.MD5Code;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Resource
    private UserRepository userRepository;

    @Override
    public User login(String account, String password) {

        User result = userRepository.findByAccountAndPwd(account,new MD5Code().getMD5ofStr(password));
        return result;
    }

    public List<User> getAll() {
        List<User> result = userRepository.findAllAccountAndPwd();
        return result;
    }

    public User getOne(int id) {
        User result = userRepository.getOne(id);
        return result;
    }

    public Map regist(String account, String pwd, String email){

        int num = new Random().nextInt(999999);
        String temp = String.valueOf(num);
        int size = temp.length();
        for(int i = 0;i < 6 - size;i++){
            temp = "0" + temp;
        }
        String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString().split("-");
        Integer year = Integer.parseInt(strNow[0]);
        Integer month = Integer.parseInt(strNow[1]);
        Integer day = Integer.parseInt(strNow[2]);

        account = "abcd";
        pwd = "efg";
        String regist_id = String.valueOf(strNow[0]) + String.valueOf(strNow[1]) + String.valueOf(strNow[2]) + temp;
        Double balance = 0.0;

        List<User> list = getAll();
        int sum = list.size();
        int tag = 0;
        Map result = new HashMap();
        for(int i = 0;i < list.size();i++){
            if(list.get(i).getAccount().equals(account)){
                result.put("success",false);
                result.put("message","this accout has exits!");
                tag = 1;
                break;
            }
        }
        if(tag == 0){
            //这里先用固定的数据，等确定了输入参数再进行修改
            User user = new User();
            user.setAccount(account);
            user.setBalance(balance);
            user.setPwd(new MD5Code().getMD5ofStr(pwd));
            user.setRegistId(regist_id);
            user.setEmail(email);
            userRepository.save(user);
            if(getAll().size() - sum == 1){
                result.put("success",true);
                result.put("message","regist success!");
            }
            else{
                System.out.println("wrong");
            }
        }
        return result;
    }

    public Map recharge(String regist_id, Double balance){
        Map result = new HashMap();

        User data1 = userRepository.getOne(regist_id);
//        System.out.println(data1.getBalance());
        Double a = data1.getBalance();
        User user = new User();
        user.setRegistId(regist_id);
        user.setBalance(balance + a);
        user.setPwd(data1.getPwd());
        user.setAccount(data1.getAccount());
        user.setId(data1.getId());
        user.setEmail(data1.getEmail());
        userRepository.save(user);
        User data2 = userRepository.getOne(regist_id);
        Double b = data2.getBalance();
        if((b - 100.0)== a){
            result.put("success",true);
            result.put("message","recharge success!");
        }else {
            result.put("1:",a);
            result.put("2:",b);
            result.put("success", false);
            result.put("message", "recharge failure!");
        }

        return result;

    }

    public Map consume(String regist_id, Double balance){
        Map result = new HashMap();

        User data1 = userRepository.getOne(regist_id);
        Double a = data1.getBalance();
        User user = new User();
        user.setRegistId(regist_id);
        user.setBalance(a - balance);
        user.setPwd(data1.getPwd());
        user.setAccount(data1.getAccount());
        user.setId(data1.getId());
        user.setEmail(data1.getEmail());
        userRepository.save(user);
        User data2 = userRepository.getOne(regist_id);
        Double b = data2.getBalance();
        if((b + 100.0)== a){
            result.put("success",true);
            result.put("message","cosume success!");
        }else {
            result.put("1:",a);
            result.put("2:",b);
            result.put("success", false);
            result.put("message", "cosume failure!");
        }

        return result;
    }
}


