package com.optimus.test;


import org.springframework.util.CollectionUtils;
import java.io.Serializable;
import java.util.*;

public class MenuDemo implements Serializable {
    /**
     *
     */
    static class Menu implements Serializable {
        public Long id;

        public Long parentId;

        public String name;

        public List<Menu> menuList;

        public Menu(Long id, Long parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Menu{" +
                    "id=" + id +
                    ", parentId=" + parentId +
                    ", name='" + name + '\'' +
                    ", menuList=" + menuList +
                    '}';
        }
    }


    public static void main(String[] args) {

        // 造数据
        Menu menu1 = new Menu(1L, null, "一级菜单1");

        Menu menu2 = new Menu(2L, null, "一级菜单2");

        Menu menu3 = new Menu(3L, 1L, "二级菜单1");

        Menu menu4 = new Menu(4L, 2L, "二级菜单2");

        Menu menu5 = new Menu(5L, 3L, "三级菜单1");

        Menu menu6 = new Menu(6L, 4L, "三级菜单2");
        Menu menu7 = new Menu(7L, 5L, "四级菜单2");
        Menu menu8 = new Menu(8L, 6L, "四级菜单2");

        List<Menu> menuList = new ArrayList<>();
        menuList.add(menu1);
        menuList.add(menu2);
        menuList.add(menu3);
        menuList.add(menu4);
        menuList.add(menu5);
        menuList.add(menu6);
        menuList.add(menu7);
        menuList.add(menu8);


        /* 整理结构 */
        Map<Long, Menu> menuMap = new HashMap<>();
        for (Menu menu : menuList) {
            menuMap.put(menu.id, menu);
        }
        // 寻找自己的父节点
        for (Menu menu : menuList) {
            Long parentId = menu.parentId;
            if (parentId == null) {
                continue;
            }
            // 取map中当前菜单的上级菜单
            Menu parentMenu = menuMap.get(parentId);

            // 上级菜单中的子菜单
            List<Menu> childMenuList = parentMenu.menuList;
            // 子菜单为空
            if (CollectionUtils.isEmpty(childMenuList)) {
                childMenuList = new ArrayList<>();
                /**
                 * 这一步是这个方法的灵魂  使用引用传值将 childMenuList的地址传给parentMenu
                 * 下边想要添加只需要继续操作childMenuList即可
                 */
                parentMenu.menuList = childMenuList;
            }
            childMenuList.add(menu);
        }
        // 找到根节点输出
        List<Menu> result = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.parentId == null) {
                result.add(menu);
            }
        }

        for (Menu menu:result) {
            System.out.println(menu.toString());
        }
        /*System.out.println(JSONUtils.write(result));*/
    }
}
