package view.Order;

import view.AdminView;
import view.MainLauncher;
import view.SelectFunction;
import view.User.MenuUserView;
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
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        orderView.addOrder(AdminView.idOnlineUser);
                        break;
                    case 2:
                        orderView.updateOrder(AdminView.idOnlineUser);
                        break;
                    case 3:
                        orderView.showOrder(SelectFunction.SHOW);
                        break;
                    case 4 :
                        SearchOrderView searchOrderView = new SearchOrderView();
                        searchOrderView.search(AdminView.idOnlineUser);
                        break;
                    case 5:
                        Statistical.showStatistical();
                        break;
                    case 6:
                        MainLauncher.menuOption();
                        break;
                    case 7:
                        MenuUserView.login();
                        break;
                    default:
                        System.out.println("Nhập sai ❌ Vui lòng nhập lại!");
                }
            } catch (Exception e) {
//                System.out.println("Nhập sai ❌ Vui lòng nhập lại!!!");
            }
        } while (true);
    }

    public static void menuOrder() {
        System.out.println("\t┌──────────────────────────────────────────────────────────┐");
        System.out.println("\t│► ░░░░░░░░░░░░░░░░░░░[QUẢN LÝ ĐƠN HÀNG]░░░░░░░░░░░░░░░░░ ◄│");
        System.out.println("\t└──────────────────────────────────────────────────────────┘");
        System.out.println("\t│                 【1】. Tạo đơn hàng                       │");
        System.out.println("\t│                 【2】. Sửa đơn hàng                       │");
        System.out.println("\t│                 【3】. Xem đơn hàng                       │");
        System.out.println("\t│                 【4】. Tìm kiếm đơn hàng                  │");
        System.out.println("\t│                 【5】. Thống kê                           │");
        System.out.println("\t│                 【6】. Quay lại MAIN MENU                 │");
        System.out.println("\t│                 【7】. Thoát                              │");
        System.out.println("\t└──────────────────────────────────────────────────────────┘");
        System.out.println("Chọn chức năng");
        System.out.print("➱");
    }
}

