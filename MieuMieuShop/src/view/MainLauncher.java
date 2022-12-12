package view;

import service.UserService;

import java.util.Scanner;

public class MenuUserView {
    public static Scanner sc = new Scanner(System.in);

    public static UserView userView;
    public static UserService userService;

    static AdminView adminView = new AdminView();

    public static void launch() {
        int choice = -1;
        do {
            UserViewLauncher.menuUser();
            try {
                do {
                    System.out.println("Chọn chức năng");
                    System.out.print("➠ ");
                    choice = Integer.parseInt(sc.nextLine());
                    if (choice > 6 || choice < 1)
                        System.out.println("Chọn chức năng không đúng !!!");
                } while (choice > 6 || choice < 1);
                switch (choice) {
                    case 1:
                        userView.addUser();
                        break;
                    case 2:
                        userView.updateUser();
                        break;
                    case 3:
                        userView.removeUser();
                        break;
                    case 4:
                        userView.showUsers(SelectFunction.SHOW);
                        break;
                    case 5:
                        UserViewLauncher.menuOption();
                        break;
                    case 6:
                        UserViewLauncher.login();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng !!!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai !!!");
            }
        } while (choice != 6);
    }


    public static void runOderUser() {
    }
}
