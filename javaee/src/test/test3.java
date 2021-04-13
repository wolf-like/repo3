package test;

import java.util.Scanner;

public class test3 {
    public static void main(String[] args) {
        System.out.println("heello world ");
        System.out.println("欢迎进入一食堂点单系统");
        System.out.println("*********************************");
        System.out.println("1.水煮鱼");
        System.out.println("2.牛肉");
        System.out.println("3.saasd");
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入你想要的菜品序号");
       int next = sc.nextInt();

        switch (next){
            case  1:
                System.out.println("你选着水...");
                break;
            case 2:
                System.out.println("你选着了牛肉");
        }
    }
}
