package view.Order;

import model.Order;
import service.OrderService;
import utils.AppUtils;
import utils.InstantUtils;
import utils.ValidateUtils;
import view.MainLauncher;
import view.Product.MenuProductView;
import view.User.MenuUserView;
import view.User.UserViewLauncher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Statistical {
    public static Scanner scanner = new Scanner(System.in);
   public  final OrderService orderService ;

public Statistical(){
    orderService = OrderService.orderService();

}

    public static void showStatistical() {
        boolean isTrue = true;
        do {
           menuStatistical();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        Statistical statistical = new Statistical();
                        statistical.statisticalByDay();
                        break;
                    case 2:
                        Statistical statistical1 = new Statistical();
                        statistical1.statisticalByMonth();
                        break;
                    case 3:
                        Statistical statistical2 = new Statistical();
                        statistical2.statisticalByYear();
                        break;
                    case 4:
                        isTrue = false;
                        break;
                    case 5:
                        MenuUserView.login();
                        break;
                    default:
                        System.out.println("Nhập sai ⚔️Vui lòng nhập lại! ");
                        break;
                }
            }catch (Exception e) {
                System.out.println("Error!  ̿’̿’\\̵͇̿̿\\");
            }
        }while (isTrue);
    }
    public void statisticalByDay() {
        System.out.println("──────────────────────── THỐNG KÊ THEO NGÀY ────────────────────────");
        String day = inputDay();
        List<Order> ordersFind = new ArrayList<>();
        List<Order> orders = orderService.findAllPrintedOrder();
        for (Order order : orders) {
            String createdDate = InstantUtils.instantToStringDay(order.getCreatAt());
            if (day.equals(createdDate)) {
                ordersFind.add(order);
            }
        }
        System.out.printf("─────────────────────────────────────────────── DOANH THU NGÀY %s ────────────────────────────────────────\n", day);
        System.out.println("│                                                                                                             │");
        System.out.println("│-------------------------------------------------------------------------------------------------------------│");
        System.out.printf("│ %-2s%-5s | %-8s%-16s | %-5s%-9s | %-6s%-25s | %-5s%-17s │\n",
                "", "STT",
                "", "KHÁCH HÀNG",
                "", "SĐT",
                "", "ĐỊA CHỈ",
                "", "THÀNH TIỀN"
        );
        System.out.println("│-------------------------------------------------------------------------------------------------------------│");
        double revenueTotal = 0;
        for (int i = 0; i < ordersFind.size(); i++) {
            Order order = ordersFind.get(i);
            revenueTotal += order.getGrandTotal();
            System.out.printf("│ %-3s%-4s | %-2s%-22s | %-2s%-12s | %-6s%-25s | %-2s%-20s │\n",
                    "", i + 1,
                    "", order.getName(),
                    "", order.getPhone(),
                    "", order.getAddress(),
                    "", AppUtils.doubleToVND(order.getGrandTotal())
            );
        }
        System.out.println("│-------------------------------------------------------------------------------------------------------------│");
        System.out.println("│                                                                                                             │");
        System.out.println("│-------------------------------------------------------------------------------------------------------------│");
        System.out.printf("│                                                           TỔNG DOANH THU: %-20s%6s  │\n", AppUtils.doubleToVND(revenueTotal), "");
        System.out.println("│-------------------------------------------------------------------------------------------------------------│");
        System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────");
    }

    public void statisticalByMonth() {
        System.out.println("──────────────────────── THỐNG KÊ THEO THÁNG ────────────────────────");
        String month = inputMonth();
        List<Order> ordersFind = new ArrayList<>();
        List<Order> orders = orderService.findAllPrintedOrder();
        for (Order order : orders) {
            String createdDate = InstantUtils.instantToStringMonth(order.getCreatAt());
            if (month.equals(createdDate)) {
                ordersFind.add(order);
            }
        }
        System.out.printf("───────────────────────────────────────── DOANH THU THÁNG %s ──────────────────────────────────────\n", month);
        System.out.println("│                                                                                                     │");
        System.out.println("│-----------------------------------------------------------------------------------------------------│");
        System.out.printf("│ %-2s%-5s | %-8s%-16s | %-5s%-9s |%-6s%-25s | %-5s%-17s │\n",
                "", "STT",
                "", "KHÁCH HÀNG",
                "", "SĐT",
                "", "ĐỊA CHỈ",
                "", "THÀNH TIỀN"
        );
        System.out.println("│-----------------------------------------------------------------------------------------------------│");
        double revenueTotal = 0;
        for (int i = 0; i < ordersFind.size(); i++) {
            Order order = ordersFind.get(i);
            revenueTotal += order.getGrandTotal();
            System.out.printf("│ %-3s%-4s | %-2s%-22s | %-2s%-12s |%-6s%-25s | %-2s%-20s │\n",
                    "", i + 1,
                    "", order.getName(),
                    "", order.getPhone(),
                    "", order.getAddress(),
                    "", AppUtils.doubleToVND(order.getGrandTotal())
            );
        }
        System.out.println("│-----------------------------------------------------------------------------------------------------│");
        System.out.println("│                                                                                                     │");
        System.out.println("│-----------------------------------------------------------------------------------------------------│");
        System.out.printf("│                                                         TỔNG DOANH THU: %-20s%6s  │\n", AppUtils.doubleToVND(revenueTotal), "");
        System.out.println("│-----------------------------------------------------------------------------------------------------│");
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────");
    }

    public void statisticalByYear() {
        System.out.println("──────────────────────── THỐNG KÊ THEO NĂM ────────────────────────");
        String year = inputYear();
        List<Order> ordersFind = new ArrayList<>();
        List<Order> orders = orderService.findAllPrintedOrder();
        for (Order order : orders) {
            String createdDate = InstantUtils.instantToStringYear(order.getCreatAt());
            if (year.equals(createdDate)) {
                ordersFind.add(order);
            }
        }
        System.out.printf("──────────────────────────────────────── DOANH THU NĂM %s ─────────────────────────────────────\n", year);
        System.out.println("│                                                                                                     │");
        System.out.println("│-----------------------------------------------------------------------------------------------------│");
        System.out.printf("│ %-2s%-5s | %-8s%-16s | %-5s%-9s | %-6s%-25s | %-5s%-17s │\n",
                "", "STT",
                "", "KHÁCH HÀNG",
                "", "SĐT",
                "", "ĐỊA CHỈ",
                "", "THÀNH TIỀN"
        );
        System.out.println("│-----------------------------------------------------------------------------------------------------│");
        double revenueTotal = 0;
        for (int i = 0; i < ordersFind.size(); i++) {
            Order order = ordersFind.get(i);
            revenueTotal += order.getGrandTotal();
            System.out.printf("│%-3s%-4s | %-2s%-22s | %-2s%-12s | %-6s%-25s | %-2s%-20s │\n",
                    "", i + 1,
                    "", order.getName(),
                    "", order.getPhone(),
                    "", order.getAddress(),
                    "", AppUtils.doubleToVND(order.getGrandTotal())
            );
        }
        System.out.println("│-----------------------------------------------------------------------------------------------------│");
        System.out.println("│                                                                                                     │");
        System.out.println("│-----------------------------------------------------------------------------------------------------│");
        System.out.printf("│                                                         TỔNG DOANH THU: %-20s%6s  │\n", AppUtils.doubleToVND(revenueTotal), "");
        System.out.println("│-----------------------------------------------------------------------------------------------------│");
        System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────");
    }


    private String inputDay() {
        System.out.println("Nhập ngày (VD: 13-11-2022): ");
        System.out.print(" => ");
        String day;
        while (!ValidateUtils.isDayValid(day = scanner.nextLine().trim())) {
            System.out.println("Ngày, tháng, năm được phân tách bởi dấu '-' (VD: 28-12-2022)");
            System.out.print(" => ");
        }
        return day;
    }

    private String inputMonth() {
        System.out.println("Nhập tháng (Ví Dụ: 12-2022): ");
        System.out.print(" => ");
        String month;
        while (!ValidateUtils.isMonthValid(month = scanner.nextLine().trim())) {
            System.out.println("Tháng, năm được phân tách bởi dấu '-' (Ví Dụ: 11-2022).");
            System.out.print(" => ");
        }
        return month;
    }

    private String inputYear() {
        System.out.println("Nhập năm (Ví Dụ: 2022): ");
        System.out.print(" => ");
        String year;
        while (!ValidateUtils.isYearValid(year = scanner.nextLine().trim())) {
            System.out.println("Năm gồm 4 chữ số (Ví Dụ: 2022).");
            System.out.print(" => ");
        }
        return year;
    }


    public static void menuStatistical(){
        System.out.println("\t┌──────────────────────────────────────────────────────────┐");
        System.out.println("\t│► ░░░░░░░░░░░░░░░░░░░░░░░░[THÔNG KÊ]░░░░░░░░░░░░░░░░░░░░ ◄│");
        System.out.println("\t└──────────────────────────────────────────────────────────┘");
        System.out.println("\t│               【1】. Thông kê theo ngày                   │");
        System.out.println("\t│               【2】. Thông kê theo tháng                  │");
        System.out.println("\t│               【3】. Thông kê theo năm                    │");
        System.out.println("\t│               【4】. Quay lại Menu quản lý                │");
        System.out.println("\t│               【5】. Thoát                                │");
        System.out.println("\t└──────────────────────────────────────────────────────────┘");
        System.out.println("Chọn chức năng: ");
        System.out.print("➱ ");
    }
}
