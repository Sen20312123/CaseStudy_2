package view.Order;

import model.Order;
import model.OrderItem;
import model.Product;
import service.*;
import utils.AppUtils;
import utils.InstantUtils;
import utils.ValidateUtils;
import view.Product.ProductView;
import view.SelectFunction;
import view.User.UserView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    public static Scanner scanner = new Scanner(System.in);
    public final IProductService productService;
    public final IOrderService orderService;
    public final IOrderItemService orderItemService ;

    public final IUserService userService;

    public final UserView userView = new UserView();

    public OrderView(){
        productService = ProductService.getProductService();
        orderService = OrderService.orderService();
        orderItemService = OrderItemService.orderItemService();
        userService = UserService.getUserService();
    }

    public void addOrder(){
//        long id , long userId, String name, String phone, String address ,
//        double grandTotal  , Instant creatAt , java.time.Instant updateAt
        try {
            long orderId = System.currentTimeMillis() %10000;
            ProductView productView = new ProductView();
            productView.showProduct(SelectFunction.ADD);
            String name = inputName(SelectFunction.ADD);
            String phone = inputPhone(SelectFunction.ADD);
            String address = inputAddress(SelectFunction.ADD);
            Instant creatAt = Instant.now();
            Instant updateAt = Instant.now();
            Order order = new Order(orderId , name ,phone ,address , creatAt , updateAt);
            order.setUserId(order.getUserId());
            OrderItemView orderItemView = new OrderItemView();
            OrderItem orderItem = orderItemView.addOrderItem(orderId);
            orderItemService.add(orderItem);
            orderService.add(order);
            showOrder();
            System.out.println("Tạo đơn thành công!");
        }catch (Exception e){
            System.out.println("Nhập sai!!! Vui lòng nhập lại!");
            e.printStackTrace();
        }
    }

    private String inputAddress(SelectFunction choose) {
        String address;
        do {
            System.out.println("Nhập địa chỉ: ");
            System.out.print("➤ ");
            address = scanner.nextLine();
            if (address.trim().isEmpty()) {
                System.out.println("Địa chỉ không được để trống!!");
                System.out.println("Nhập lại địa chỉ: ");
                System.out.print("➤ ");
            }
        } while (address.trim().isEmpty());
        return address;

    }

    private String inputPhone(SelectFunction choose) {
        System.out.println("Nhập số điện thoại (vd:0987654321)");
        System.out.print("➤ ");
        String phone = scanner.nextLine();
        while (!ValidateUtils.isPhoneValid(phone)) {
            System.out.println("Số điện thoại: (vd:0987654321) gồm 10 số ,số đầu là 0 và số thứ 2 là những số 3/5/7/9");
            System.out.print("➤ ");
            phone = scanner.nextLine();
        }
        return phone;
    }

    private String inputName(SelectFunction choose) {
        System.out.println("Nhập chức năng:");
        System.out.println("Nhập họ và tên: (vd: Nguyễn Văn A) " + "Tên phải viết hoa chữ cái đầu)");
        System.out.print("➤ ");
        String name = scanner.nextLine();
        while (!ValidateUtils.isFullNameValid(name)) {
            System.out.println("Tên " + name + " không hợp lệ!" + "Vui lòng nhập lại" + "Tên phải viết hoa chữ cái đầu, có dấu");
            System.out.println("Nhập tên: (vd: Nguyễn Văn A)");
            System.out.print("➤ ");
            name = scanner.nextLine();
        }
        return name;
    }

    public void showOrder(){
        List<Order> orders = orderService.findAll();
//        id + "," + userId+ "," + phone + "," + address+ "," + grandTotal+ "," + name + "," + creatAt+ ","+ updateAt;
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════ DANH SÁCH ĐƠN HÀNG ═════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s%-9s | %-8s%-18s | %-6s%-10s | %-11s%-24s  | %-7s%-15s | %-11s%-24s | %-4s%-18s | %-2s%-20s |\n",
                "", "ID",
                "", "NHÂN VIÊN (ID)",
                "", "SĐT",
                "", "ĐỊA CHỈ",
                "", "TỔNG TIỀN",
                "", "KHÁCH HÀNG ",
                "", "THỜI GIAN TẠO",
                "", "THỜI GIAN CHỈNH SỬA"
        );
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Order order : orders) {
            System.out.printf("| %-2s%-12s | %-3s%-23s | %-3s%-13s | %-11s%-24s  | %-4s%-18s | %-2s%-33s | %-2s%-20s | %-2s%-20s |\n",
                    "", order.getId(),
                    "", userView.findNameById(order.getUserId()) + " (" + order.getUserId()+ ")",
                    "", order.getPhone(),
                    "", order.getAddress(),
                    "", AppUtils.doubleToVND(order.getGrandTotalTotal()),
                    "",order.getName()  ,
                    "", InstantUtils.instantToString(order.getCreatAt()),
                    "", order.getUpdateAt() == null ? "" : InstantUtils.instantToString(order.getUpdateAt())
            );
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
