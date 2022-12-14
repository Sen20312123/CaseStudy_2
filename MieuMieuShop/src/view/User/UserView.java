package view.User;

import model.Role;
import model.User;
import service.UserService;
import utils.AppUtils;
import utils.InstantUtils;
import utils.ValidateUtils;
import view.MainLauncher;
import view.SelectFunction;
import view.User.MenuUserView;

import java.util.List;
import java.util.Scanner;

public class UserView {
    public static Scanner sc = new Scanner(System.in);

    private UserService userService;

    public UserView(){
        userService= UserService.getUserService();
    }

    public void addUser(){
        do {
            try {
                long idUser = System.currentTimeMillis()/1000;
                String username = inputUserName();
                String password = inputPassword();
                String fullName = inputFullName(SelectFunction.ADD);
                String mobile = inputMobile(SelectFunction.ADD);
                String address = inputAddress(SelectFunction.ADD);
                String email = inputEmail(SelectFunction.ADD);
                User user = new User(idUser, username, password, fullName, mobile, email, address, Role.USER);
                user.setRole(Role.USER);
                userService.add(user);
                System.out.println("Đã thêm người dùng thành công!");
                showUsers(SelectFunction.SHOW);

            }catch (Exception e){
                System.out.println("Nhập sai . Vui lòng nhập lại!!!");
            }
        }while (AppUtils.isRetry(SelectFunction.ADD));
    }

    public void updateUser() {
        boolean isRetry = false;
        do {
            try {
                showUsers(SelectFunction.UPDATE);
                int id = inputId(SelectFunction.UPDATE);
                System.out.println("\t┌──────────────────────────────────────────────────────────┐");
                System.out.println("\t│► ░░░░░░░░░░░░░░░░░░░░[SỬA NGƯỜI DÙNG]░░░░░░░░░░░░░░░░░░ ◄│");
                System.out.println("\t└──────────────────────────────────────────────────────────┘");
                System.out.println("\t│               【1】. Sửa tên                              │");
                System.out.println("\t│               【2】. Sửa số điện thoại                    │");
                System.out.println("\t│               【3】. Sửa địa chỉ                          │");
                System.out.println("\t│               【4】. Sửa email                            │");
                System.out.println("\t│               【5】. Quay lại                             │");
                System.out.println("\t└──────────────────────────────────────────────────────────┘");
                int option = AppUtils.retryChoose(1, 5);
                User newUser = new User();
                newUser.setIdUser(id);
                switch (option) {
                    case 1:
                        String name = inputFullName(SelectFunction.UPDATE);
                        newUser.setFullName(name);
                        userService.update(newUser);
                        System.out.println("Đã đổi tên thành công!");
                        break;
                    case 2:
                        String phone = inputMobile(SelectFunction.UPDATE);
                        newUser.setMobile(phone);
                        userService.update(newUser);
                        System.out.println("Đổi số điện thoại thành công!");
                        break;
                    case 3:
                        String address = inputAddress(SelectFunction.UPDATE);
                        newUser.setAddress(address);
                        userService.update(newUser);
                        System.out.println("Đổi địa chỉ thành công!");
                        break;
                    case 4:
                        String email = inputEmail(SelectFunction.UPDATE);
                        newUser.setEmail(email);
                        userService.update(newUser);
                        System.out.println("Đổi email thành công!");
                        break;
                }
                isRetry = option != 5 && AppUtils.isRetry(SelectFunction.UPDATE);
            } catch (Exception e) {
                System.out.println("Nhập sai !!! ");
            }
        } while (isRetry);
    }

    public void removeUser() {
//        List<User> users = userService.findAll();
        showUsers(SelectFunction.REMOVE);
        int id;
        while (!userService.existsById(id = inputId(SelectFunction.REMOVE))) {
            System.out.println("Không tồn tại người dùng !");
            System.out.println("Nhấn 'y' để tìm lại người dùng || Nhấn 'b' để quay lại || Nhấn 't' để thoát chương trình ");
            System.out.print("➠");
            String option = sc.nextLine().toLowerCase();
            switch (option) {
                case "y":
                    removeUser();
                    break;
                case "b":
                    MenuUserView.menuUser();
                    return;
                case "t":
                    AppUtils.exit();
                    break;
                default:
                    System.out.println("Nhập chức năng không đúng! Vui lòng nhập lại!");
                    break;
            }
        }
        System.out.println("\t┌──────────────────────────────────────────────────────────┐");
        System.out.println("\t│► ░░░░░░░░░░░░░░░░░░░░░[XÁC NHẬN XÓA]░░░░░░░░░░░░░░░░░░░ ◄│");
        System.out.println("\t└──────────────────────────────────────────────────────────┘");
        System.out.println("\t│               【1】. Nhấn 1 để xóa                        │");
        System.out.println("\t│               【2】. Nhấn 2 để quay lại                   │");
        System.out.println("\t└──────────────────────────────────────────────────────────┘");
        int option = AppUtils.retryChoose(1, 2);
        if (option == 1) {
            userService.removeById(id);
            System.out.println("Đã xóa user thành công!");
            showUsers(SelectFunction.REMOVE);
            AppUtils.isRetry(SelectFunction.REMOVE);
        } else if (option == 2) {
            MainLauncher.launch();
        }
    }
    private int inputId(SelectFunction choose) {
        int id;
        switch (choose) {
            case ADD:
                System.out.println("Nhập ID: ");
                break;
            case UPDATE:
                System.out.println("Nhập ID bạn muốn sửa: ");
                break;
            case REMOVE:
                System.out.println("Nhập ID muốn xóa: ");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.retryParseInt();
            boolean exist = userService.existsById(id);
            switch (choose) {
                case ADD:
                    if (exist) {
                        System.out.println("ID đã tồn tại. Vui lòng nhập lại!");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thấy ID. Vui lòng nhập lại!");
                    }
                    isRetry = !exist;
            }
        } while (isRetry);
        return id;
    }
    public String inputEmail(SelectFunction choose) {
        switch (choose) {
            case ADD:
                System.out.println("Nhập email (vd: Thanhnhan123@gmail.com)");
                break;
            case UPDATE:
                System.out.println("Nhập email mà bạn muốn đổi: ");
                break;
        }
        System.out.print("➠");
        String email;
        do {
            if (!ValidateUtils.isEmailValid(email = sc.nextLine())) {
                System.out.println("Email " + email + " không đúng định dạng Vui lòng nhập lại !" + "");
                System.out.println("Nhập email (vd: Thanhnhan123@gmail.com)");
                System.out.print("➠");
                continue;
            }
            if (userService.existByEmail(email)) {
                System.out.println("Email " + email + " của bạn đẫ tồn tại! Vui lòng nhập lại!");
                System.out.println("Nhập email (vd: Thanhnhan123@gmail.com");
                System.out.print("➠");
                continue;
            }
            break;
        } while (true);
        return email;
    }

    public String inputAddress(SelectFunction choose) {
        switch (choose) {
            case ADD:
                String address;
                System.out.println("Nhập địa chỉ (vd: Huế)");
                do {
                    address = sc.nextLine();
                    if (address.trim().isEmpty()) {
                        System.out.println("Địa chỉ không được để trống!");
                        System.out.println("Nhập lại địa chỉ");
                        System.out.print("➠");
                        address = sc.nextLine();
                    }
                } while (address.trim().isEmpty());
                return address;
            case UPDATE:
                System.out.println("Nhập địa chỉ muốn đổi (vd: Huế)");
                do {
                    address = sc.nextLine();
                    if (address.trim().isEmpty()) {
                        System.out.println("Địa chỉ không được để trống!");
                        System.out.println("Nhập lại địa chỉ");
                        System.out.print("➠");
                        address = sc.nextLine();
                    }
                } while (address.trim().isEmpty());
                return address;
        }
        return null;
    }


    public String inputMobile(SelectFunction choose) {
        switch (choose) {
            case ADD:
                System.out.println("Nhập số điện thoại (vd: 0969961123, 10 số , số thứ 2 là những số 3/5/7/9)");
                break;
            case UPDATE:
                System.out.println("Nhập số điện thoại mà bạn muốn đổi lại: ");
                break;
        }
        System.out.print("➠");
        String mobile;
        do {
            mobile = sc.nextLine();
            if (!ValidateUtils.isPhoneValid(mobile)) {
                System.out.println("Số " + mobile + " không đúng định dạng! Nhập lại!" + "Gồm 10 số và bắt đầu bằng số 0, số thứ 2 là những số 3/5/7/9!");
                System.out.println("Nhập số điện thoại (vd: 0969961123)");
                System.out.print("➠");
                continue;
            }
            if (userService.existByPhone(mobile)) {
                System.out.println("Số này đã tồn tại. Mời bạn nhập lại!");
                System.out.print("➠");
                continue;
            }
            break;
        } while (true);
        return mobile;
    }

    public String inputFullName(SelectFunction choose) {
        switch (choose) {
            case ADD:
                System.out.println("Nhập họ và tên (vd: Nguyễn Văn A)");
                break;
            case UPDATE:
                System.out.println("Nhập tên mà bạn muốn sửa: ");
                break;
        }

        System.out.print("➠");
        String fullName;
        while (!ValidateUtils.isFullNameValid(fullName = sc.nextLine())) {
            System.out.println("Tên " + fullName + " không đúng định dạng." + " Viết hoa chữ cái đầu" + "Không bao gồm số, không thêm ký tự đặc biệt" );
            System.out.println("Nhập tên (vd: Nguyễn Văn A) ");
            System.out.print("➠");
        }
        return fullName;
    }

    public String inputPassword() {
        System.out.println("Nhập mật khẩu (từ 8 đến 20 ký tự), bao gồm số, chữ cái viết hoa, ký tự đặc biệt (:;',?/*~$^+=<> !@#&–_)");
        System.out.println("vd: Thanhnhan123@");
        System.out.print("➠");
        String password;
        while (!ValidateUtils.isPasswordValid(password = sc.nextLine())) {
            System.out.println("Mật khẩu yếu (Không đúng định dạng). Nhập lại !!!");
            System.out.print("➠");
        }
        return password;
    }

    public String inputUserName() {
        System.out.println("Nhập Username (không bao gồm dấu cách, kí tự đặc biệt)");
        System.out.print("⭆");
        String username;
        do {
            if (!ValidateUtils.isUserNameValid(username = AppUtils.retryString("Username"))) {
                System.out.println("Không đúng định dạng. Vui lòng kiểm tra và nhập lại!");
                System.out.print("⭆ ");
                continue;
            }
            if (userService.existByUserName(username)) {
                System.out.println("Username này đã tồn tại. Vui lòng nhập lại!");
                System.out.print("⭆ ");
                continue;
            }
            break;
        } while (true);
        return username;
    }


    public void showUsers(SelectFunction choose) {
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬ DANH SÁCH NGƯỜI DÙNG▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.printf("%-15s %-22s %-15s %-22s %-18s %-15s %-20s %-20s\n", "Id", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Người dùng", "Ngày tạo", "Ngày cập nhật");
        System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.printf("%-15d %-22s %-15s %-22s %-18s %-15s %-20s %-20s\n",
                    user.getIdUser(),
                    user.getFullName(),
                    user.getMobile(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getRole(),
                    InstantUtils.instantToString(user.getCreateAt()),
                    user.getUpdateAt() == null ? "" : InstantUtils.instantToString(user.getUpdateAt())
            );
        }
        System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        if (choose == SelectFunction.SHOW)
            AppUtils.isRetry(SelectFunction.SHOW);
    }


}
