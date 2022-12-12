package view.Order;

import view.MainLauncher;
import view.User.UserViewLauncher;

import java.util.Scanner;



public class OrderViewLauncher {
    static Scanner scanner = new Scanner(System.in);
    static OrderView orderView = new OrderView();

    public static void runOrder() {
        int choice;
        do {
            menuOrder();
            try {
                System.out.println("Chọn chức năng");
                System.out.print("➽");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        orderView.addOrder();
                        break;
                    case 2:
//                        orderView.showTurnover();
                        break;
                    case 3:
                        MainLauncher.menuOption();
                        break;
                    case 4:
//                        UserViewLauncher.login();
                        break;
                    default:
                        System.out.println("Nhập sai ❌ Vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Nhập sai ❌ Vui lòng nhập lại!!!");
            }
        } while (true);
    }

    public static void menuOrder() {
        System.out.println("\t┌──────────────────────────────────────────────────────────┐");
        System.out.println("\t│► ░░░░░░░░░░░░░░░░░░░[QUẢN LÝ ĐƠN HÀNG]░░░░░░░░░░░░░░░░░ ◄│");
        System.out.println("\t└──────────────────────────────────────────────────────────┘");
        System.out.println("\t│                 【1】. Tạo đơn hàng                       │");
        System.out.println("\t│                 【2】. Xem doanh thu                      │");
        System.out.println("\t│                 【3】. Quay lại MAIN MENU                 │");
        System.out.println("\t│                 【4】. Thoát                              │");
        System.out.println("\t└──────────────────────────────────────────────────────────┘");
    }
}

