package service;

import model.Product;
import model.Role;
import model.User;
import utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    private static final String PATH = "E:\\Module2\\CaseStudy_2\\MieuMieuShop\\data\\user.csv";
    private static UserService userService;

    private UserService() {
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        List<String> records = CSVUtils.readFile(PATH);
        for (String record : records){
            users.add(User.parseUser(record));
        }
        return users;
    }

    @Override
    public void add(User newUser) {
        List<User>users = findAll();
        users.add(newUser);
        newUser.setCreateAt(Instant.now());
        CSVUtils.writeFile(PATH,users);
    }

    @Override
    public void removeById(long id) {
        List<User> users = findAll();
        users.removeIf(user -> user.getIdUser()==id);
        CSVUtils.writeFile(PATH,users);
    }

    @Override
    public void update(User newUser) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getIdUser() == newUser.getIdUser()) {
                String fullName = newUser.getFullName();
                if (fullName != null && !fullName.isEmpty())
                    user.setFullName(fullName);
                String phone = newUser.getMobile();
                if (phone != null && !phone.isEmpty())
                    user.setMobile(newUser.getMobile());
                String address = newUser.getAddress();
                if (address != null && !address.isEmpty())
                    user.setAddress(newUser.getAddress());
                user.setUpdateAt(Instant.now());
                CSVUtils.writeFile(PATH, users);
                break;
            }
        }
    }

    @Override
    public boolean existsById(long id) {
        return findById(id) != null;
    }

    @Override
    public User findById(long id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getIdUser() == id)
                return user;
        }
        return null;
    }

    @Override
    public User adminLogin(String userName, String passWord) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassWord().equals(passWord)) {
                return user;
            }
            if (user.getUserName().equals(userName) && user.getPassWord().equals(passWord)
                    && user.getRole().equals(Role.ADMIN)) {
                return user;
            }
            if (user.getUserName().equals(userName) && user.getPassWord().equals(passWord)
                    && user.getRole().equals(Role.USER)) {
                return user;
            }
        }
        return null;
    }

    public String findNameById(long id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getIdUser() == id) {
                return user.getFullName();
            }
        }
        return null;
    }

    public boolean existByEmail(String email) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public boolean existByPhone(String phone) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getMobile().equals(phone))
                return true;
        }
        return false;
    }

    public boolean existByUserName(String username) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUserName().equals(username))
                return true;
        }
        return false;
    }
}
