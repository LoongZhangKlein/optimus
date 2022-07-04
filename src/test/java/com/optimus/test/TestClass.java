package com.optimus.test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestClass {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        /*list.add("一级菜单");*/
       /* ArrayList list1 = new ArrayList();
        list1.add("二级菜单");
        list.add(list1);*/
        /*ArrayList list2 = new ArrayList();
        list2.add("三级菜单");
        list.add(list2);*/
        for (int i = 0; i < 2; i++) {
            list.add("一级菜单");
            ArrayList list1 = new ArrayList();
            list1.add("二级菜单");
            for (int j = 0; j < 2; j++) {
                ArrayList list2 = new ArrayList();
                list2.add("三级菜单");
                list1.add(list2);
                list.add(list1);
            }
        }
        System.out.println(list.toString());
    }
}
