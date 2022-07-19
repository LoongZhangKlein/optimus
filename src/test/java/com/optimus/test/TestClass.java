package com.optimus.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.CollectionUtils;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor

class Menu implements Serializable {
    private Integer id;
    private String name;
    private Integer parentId;
    private List<Menu> menuList;

    public Menu(Integer id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", menuList=" + menuList +
                '}';
    }
}

@Data
public class TestClass {
    public static List<Menu> buildMenuList() {
        List<Menu> menuList = new ArrayList();
        Menu menu1 = new Menu(1, "一级菜单", null);
        Menu menu2 = new Menu(2, "一级菜单", null);
        Menu menu3 = new Menu(3, "二级菜单", 1);
        Menu menu4 = new Menu(4, "二级菜单", 2);
        Menu menu5 = new Menu(5, "三级菜单", 3);
        Menu menu6 = new Menu(6, "四级菜单", 5);
        Menu menu7= new Menu(7, "四级菜单", 5);
        Menu menu8 = new Menu(8, "三级菜单", 4);
        menuList.add(menu1);
        menuList.add(menu2);
        menuList.add(menu3);
        menuList.add(menu4);
        menuList.add(menu5);
        menuList.add(menu6);
        menuList.add(menu7);
        menuList.add(menu8);
        return menuList;
    }

    public static void main(String[] args) {
        // 组装map
        Map<Integer, Menu> menuMap = new HashMap(16);
        List<Menu> menusList = buildMenuList();
        for (Menu menu : menusList) {
            menuMap.put(menu.getId(), menu);
        }
        for (Menu menu : menusList) {
            Integer parentId = menu.getParentId();
            if (parentId == null) {
                continue;
            }
            Menu parentMenu = menuMap.get(parentId);
            List<Menu> childMenuList = parentMenu.getMenuList();
            if (CollectionUtils.isEmpty(childMenuList)) {
                childMenuList = new ArrayList<>();
                parentMenu.setMenuList(childMenuList);

            }
                childMenuList.add(menu);


        }
        List<Menu> resultList = new ArrayList();
        for (Menu menu:menusList) {
            if (menu.getParentId()==null){
                resultList.add(menu);
            }
        }
        for (Menu res:resultList) {
            System.out.println(res.toString());
        }

    }
}
