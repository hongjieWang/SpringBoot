package cn.org.july.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private static List<User> userList = new ArrayList<>();

    static {
          User zhangsan = new User();
          zhangsan.setAge(12);
          zhangsan.setName("张三");

          User lisi = new User();
          lisi.setName("李四");
          lisi.setAge(14);

          User ww = new User();
          ww.setAge(20);
          ww.setName("王五");

          userList.add(zhangsan);
          userList.add(lisi);
          userList.add(ww);
    }

    @RequestMapping(value = "/users")
    @ResponseBody
    public List<User> getUsers(){
        return userList;
    }


}

class User{
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}