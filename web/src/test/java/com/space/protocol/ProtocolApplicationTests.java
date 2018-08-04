package com.space.protocol;

import com.space.entity.User;
import com.space.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProtocolApplicationTests {

    @Test
    public void contextLoads() {

    }
    @Autowired
    private UserService userService;
    private User user;

    @Before
    public void before() throws Exception{
        user = new User();
        user.setAccount("123456");
        user.setPwd("123456");
    }
    @After
    public void after() throws Exception {
    }

    @Test
    public void login_test() throws Exception {
        User result = userService.login(user.getAccount(),user.getPwd());
        System.out.println(result);
    }
    @Test
    public void regist_test() throws Exception {
        List<User> result = userService.getAll();
        String regist_account = "test";
        boolean a = false;
        int tag = 0;
        for(int i = 0;i < result.size();i++){
            if(result.get(i).getAccount().equals(regist_account)){
                System.out.println("exits!");
                tag = 1;
                break;
            }
        }
        if(tag == 0){
            //userService.regist(321,"test","test");
        }

        System.out.println(result);
        System.out.println(userService.getAll().size());
    }
    @Test
    public void recharge_test() throws Exception{
        int recharge_id = 123;
        Double recharge_balance = 1000.0;
        userService.recharge(recharge_id,recharge_balance);
    }
    @Test
    public void consume_test() throws Exception{

        int recharge_id = 123456;
        Double recharge_balance = 100.0;
        User data1 = userService.getOne(recharge_id);
        System.out.println(data1.getBalance());
        userService.recharge(recharge_id,data1.getBalance() + recharge_balance);
        User data2 = userService.getOne(recharge_id);
        System.out.println(data2.getBalance());

    }
}
