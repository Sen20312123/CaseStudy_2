package view.Order;

import model.Order;
import model.OrderItem;
import model.Product;
import service.*;
import utils.AppUtils;
import view.SelectFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderItemView {
    private static Scanner scanner = new Scanner(System.in);
    private final IProductService productService;
    private final IOrderService orderService;
    private final IOrderItemService orderItemService;

    private final Order order = new Order();


    public OrderItemView(){
        productService = ProductService.getProductService();
        orderService = OrderService.orderService();
        orderItemService = OrderItemService.orderItemService();
    }

    public  OrderItem addOrderItem(long orderId) {
//        long id, long productId, String productName, double price, int quantity, double total, long orderId
        List<OrderItem> orderItems = new ArrayList<>();
        long id = System.currentTimeMillis() % 1000;
        System.out.println("Nhập ID sản phẩm muốn mua");
        System.out.print("┌► ");
        int idPro = Integer.parseInt(scanner.nextLine());
        while (!productService.existsById(idPro)) {
            System.out.println("ID không tồn tại!");
            System.out.println("Nhập ID sản phẩm");
            System.out.print("➤ ");
            idPro = scanner.nextInt();
        }
        Product product = productService.findById(idPro);
        String name = product.getNameProduct();
        double price = product.getPrice();
        int oldQuantity = product.getQuantity();
        System.out.println("Nhập số lượng muốn mua");
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity < 0)
                System.out.println("(Số lượng phải lớn hơn 0)");
        } while (quantity < 0);
        while (!checkQuantity(product, quantity)) {
            System.out.println("Nhập sai số lượng!!! Vui lòng nhập lại!");
            System.out.println("Nhập số lượng");
            System.out.print("➤ ");
            quantity = scanner.nextInt();
        }
        double total = quantity * product.getPrice();
        int currentQuantity = oldQuantity - quantity;
        product.setQuantity(currentQuantity);
        OrderItem orderItem = new OrderItem(id ,idPro, name, price, quantity, total, orderId);
        productService.updateQuantity(idPro, quantity);
        return orderItem;
    }

    public boolean checkQuantity(Product product, int quantity) {
        if (quantity <= product.getQuantity())
            return true;
        else
            return false;
    }

    public void showOrderItem(List<OrderItem> orderItems) {
        System.out.println("═══════════════════════════════════════════════════ GIỎ HÀNG ═══════════════════════════════════════════════════");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s%-9s | %-11s%-19s | %-7s%-11s | %-2s%-10s | %-5s%-17s |\n",
                "", "ID",
                "", "SẢN PHẨM",
                "", "GIÁ",
                "", "SỐ LƯỢNG",
                "", "THÀNH TIỀN"
        );
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getOrderId() == order.getId())
            System.out.printf("| %-2s%-12s | %-2s%-28s | %-2s%-16s | %-4s%-8s | %-2s%-20s |\n",
                    "", orderItem.getId(),
                    "", productService.findProductById(orderItem.getProductId()).getNameProduct(),
                    "", AppUtils.doubleToVND(orderItem.getPrice()),
                    "", orderItem.getQuantity(),
                    "", AppUtils.doubleToVND(orderItem.getTotal())
            );
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");

    }
}
