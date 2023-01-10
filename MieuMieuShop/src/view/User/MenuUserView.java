package view.User;

import view.AdminView;
import view.Order.OrderView;
import view.Product.ProductView;
import view.SelectFunction;

import java.util.Scanner;

import static view.User.UserViewLauncher.adminView;

public class MenuUserView {

    private static Scanner sc = new Scanner(System.in);
    static ProductView productView = new ProductView();

    public static void menuOderUser() {
        System.out.println("⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷");
        System.out.println("⋇      CHÀO MỪNG BẠN ĐẾN VỚI CỬA HÀNG MỸ PHẨM       ⋇");
        System.out.println("⋇                   MIEU MIEU SHOP                  ⋇");
        System.out.println("⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷");
        System.out.println("                       MAIN MENU                     ");
        System.out.println("⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷");
        System.out.println("⋇                                                   ⋇");
        System.out.println("⋇                1. Tạo đơn hàng                    ⋇");
        System.out.println("⋇                2. Hiện thị các sản phẩm           ⋇");
        System.out.println("⋇                3. Chỉnh sửa đơn hàng              ⋇");
        System.out.println("⋇                4. Xem đơn hàng                    ⋇");
        System.out.println("⋇                0. Đăng xuất                       ⋇");
        System.out.println("⋇                                                   ⋇");
        System.out.println("⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷⊷");
        System.out.println("Chọn chức năng:");
        System.out.print("➽ ");
    }
    public static void menuUser() {
        System.out.println("\t┌──────────────────────────────────────────────────────────┐");
        System.out.println("\t│► ░░░░░░░░░░░░░░░░░░[QUẢN LÝ NGƯỜI DÙNG]░░░░░░░░░░░░░░░░ ◄│");
        System.out.println("\t└──────────────────────────────────────────────────────────┘");
        System.out.println("\t│               【1】. Thêm người dùng                      │");
        System.out.println("\t│               【2】. Sửa thông tin người dùng             │");
        System.out.println("\t│               【3】. Xóa người dùng                       │");
        System.out.println("\t│               【4】. Hiện thông tin người dùng            │");
        System.out.println("\t│               【5】. Quay lại MAIN MENU                   │");
        System.out.println("\t│               【6】. Thoát                                │");
        System.out.println("\t└──────────────────────────────────────────────────────────┘");
    }
    public static void login() {
        System.out.println("\t┌───────────────────────────────────────────┐");
        System.out.println("\t│► ░░░░░░░░░░░░░[MieuMieuShop]░░░░░░░░░░░░ ◄│");
        System.out.println("\t└───────────────────────────────────────────┘");
        System.out.println("\t│                1.Đăng nhập                │");
        System.out.println("\t│                0.Thoát                    │");
        System.out.println("\t└───────────────────────────────────────────┘");
        System.out.println("Chọn chức năng (chọn số) :");
        System.out.print("➠ ");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                adminView.adminLogin();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Nhập sai !!! Vui lòng nhập lại !!!");
        }
    }
    public static void runOderUser() {
        do {
            menuOderUser();
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    OrderView orderView = new OrderView();
                    orderView.addOrder(AdminView.idOnlineUser);
                    break;
                case 2:
                    productView.showProduct(SelectFunction.SHOW);
                    runOderUser();
                    break;
                case 3:
                    OrderView orderView1 = new OrderView();
                    orderView1.updateOrder(AdminView.idOnlineUser);
                    break;
                case 4:
                    OrderView orderView2 = new OrderView();
                    orderView2.showOrder(SelectFunction.SHOW);
                    break;
                case 0:
                    login();
                    break;
                default:
                    System.out.println("Nhập chức năng sai ❌ Vui lòng nhập lại !!!");
            }
        }while (true);
    }
}
