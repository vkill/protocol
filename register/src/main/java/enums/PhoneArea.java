package enums;

import java.util.HashMap;

public class PhoneArea {

    public static HashMap<String,String> phoneFromAddress;

    static{
        phoneFromAddress = new HashMap<>();
        phoneFromAddress.put("泰国","+66");
    }
}
