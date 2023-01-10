package view.Product;

import model.Product;
import service.ProductService;
import utils.ValidateUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class SearchProductView {
    public static Scanner sc = new Scanner(System.in);
    public static ProductView productView = new ProductView();
    public static ProductService productService = new ProductService();
    static DecimalFormat decimalFormat = new DecimalFormat("###,###,###" + "đ");
    public List<Product> products;

    public SearchProductView(){
        products = productService.findAll();
    }

    public static void search() {

        productView.show(productService.findAll());
        boolean flag = true;
        int choice = -1;
        do {
            System.out.println("\t┌──────────────────────────────────────────────────────────┐");
            System.out.println("\t│► ░░░░░░░░░░░░░░░░░░[ TÌM KIẾM SẢN PHẨM]░░░░░░░░░░░░░░░░ ◄│");
            System.out.println("\t└──────────────────────────────────────────────────────────┘");
            System.out.println("\t│               【1】. Tìm kiếm theo tên sản phẩm           │");
            System.out.println("\t│               【2】. Tìm kiếm theo tên thương hiệu        │");
            System.out.println("\t│               【0】. Quay lại                             │");
            System.out.println("\t└──────────────────────────────────────────────────────────┘");
            System.out.println("Chọn chức năng: ");
            System.out.print("➽ ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            }catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 1:
                    searchByName();
                    break;
                case 2:
                    searchByTrademark();
                    break;
                case 0:
                    MenuProductView.runProduct();
                    flag = false;
                    break;
                default:
                    System.out.println("Nhập sai !!! Vui lòng nhập lại !!!");
                    break;
            }
        }while (flag);
    }

    public static void searchByName() {
        List<Product> products = productService.findAll();
        int count = 0;
        System.out.println("Nhập tên cần tìm kiếm (Tên có dấu): ");
        try {
            String name = sc.nextLine();
            System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            System.out.printf("%-15s %-20s %-18s %-10s %-18s", "Id", "Tên Sản Phẩm", "Giá", "Số lượng" ,"Thương hiệu\n");
            System.out.println("");
            System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
            for (Product product : products) {
                if (ValidateUtils.removeAccent(product.getNameProduct().toLowerCase()).contains(ValidateUtils.removeAccent(name.toLowerCase()))) {
                    count++;
                    System.out.printf("%-20s %-20s %-18s %-10s %-18s\n", product.getIdProduct(), product.getNameProduct(), decimalFormat.format(product.getPrice()),
                            product.getQuantity(), product.getTrademark());
                }
            }
            System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
            showReturnSearch(count);

        } catch (Exception e) {
            System.out.println("Chưa hợp lệ!Mời nhập lại");
        }
    }
    public static void searchByTrademark() {
        List<Product> products = productService.findAll();
        int count = 0;
        System.out.println("Nhập tên thương hiệu cần tìm kiếm (tên có dấu): ");
        try {
            String trademark = sc.nextLine();
            System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            System.out.printf("%-15s %-20s %-18s %-10s %-18s", "Id", "Tên Sản Phẩm", "Giá", "Số lượng" ,"Thương hiệu\n");
            System.out.println("");
            System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
            for (Product product : products) {
                if (ValidateUtils.removeAccent(product.getTrademark().toLowerCase()).contains(ValidateUtils.removeAccent(trademark.toLowerCase()))) {
                    count++;
                    System.out.printf("%-20s %-20s %-18s %-10s %-18s\n", product.getIdProduct(), product.getNameProduct(), decimalFormat.format(product.getPrice()),
                            product.getQuantity(), product.getTrademark());
                }
            }
            System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
            showReturnSearch(count);

        } catch (Exception e) {
            System.out.println("Chưa hợp lệ!Mời nhập lại");
        }
    }

    public static void showReturnSearch(int count) {
        char choice = ' ';
        boolean flag;
        System.out.println();
        do {
            System.out.println("Nhấn 'q' để quay lại.");
            System.out.print("➤ ");
            try {
                choice = sc.nextLine().charAt(0);
            } catch (Exception e) {
                choice = ' ';
            }
            switch (choice) {
                case 'q':
                    SearchProductView.search();
                    flag = false;
                    break;
                default:
                    flag = true;
            }
        } while (flag);
    }
}
