package com.atotwhite;

import com.atotwhite.service.StudentManager;
import com.atotwhite.service.impl.StudentManagerImpl;

import java.util.Scanner;
/*
* 只能在方法中new实体类 集合类
* */
public class Main {
    public static void main(String[] args) throws Exception {

        StudentManager studentManager = new StudentManagerImpl();
        Scanner input = new Scanner(System.in);

        boolean isExit = false;

        while (!isExit) {
            Menu.show();
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    studentManager.addStudent();
                    break;
                case 2:
                    studentManager.showAllStudents();
                    break;
                case 3:
                    studentManager.modifyStudent();
                    break;
                case 4:
                    studentManager.deleteStudent();
                    break;
                case 5:
                    studentManager.searchStudent();
                    break;
                case 6:
                    isExit = true;
                    break;
                default:
                    System.out.println("无效的选择，请重新输入");
            }
        }
    }
}
