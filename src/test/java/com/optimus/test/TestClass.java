package com.optimus.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
class User {
    private String name;
    private Integer age;
    private Integer level;
    private List<User> userList;
}

public class TestClass {
    public static void main(String[] args) {
        List<User> firstUserList = new ArrayList();
        User first = new User();
        first.setName("张三");
        first.setAge(18);
        first.setLevel(1);

        // 封装二级菜单
        List<User> secondUserList = new ArrayList();
        User second = new User();
        second.setName("李四");
        second.setLevel(2);
        // 封装三级菜单
        List<User> thirdUserList = new ArrayList();
        User third = new User();
        third.setName("王五");
        third.setLevel(3);
        // 菜单拼装
        thirdUserList.add(third);
        second.setUserList(thirdUserList);
        secondUserList.add(second);
        first.setUserList(secondUserList);
        firstUserList.add(first);

        System.out.println(firstUserList.toString());

    }
}
