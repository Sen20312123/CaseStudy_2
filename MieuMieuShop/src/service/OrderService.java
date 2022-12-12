package service;

import model.Order;
import model.Product;
import utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService{
    private static final String PATH = "E:\\Module2\\CaseStudy_2\\MieuMieuShop\\data\\order.csv";

    public static OrderService orderService;

    public OrderService(){

    }

    public static OrderService orderService(){
        if (orderService == null)
            orderService = new OrderService();
        return orderService;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        List<String> records = CSVUtils.readFile(PATH);
        for (String record : records) {
            orders.add(Order.parseOrder(record));
        }
        return orders;
    }

    @Override
    public void add(Order newInstant) {
    List<Order> orders = findAll();
    orders.add(newInstant);
    CSVUtils.writeFile(PATH , orders);
    }

    @Override
    public void removeById(long id) {
        List<Order> orders = findAll();
        orders.removeIf(order -> order.getId()==id);
        CSVUtils.writeFile(PATH,orders);
    }

    @Override
    public void update(Order newInstant) {
    List<Order> orders = findAll();
        for (Order order: orders) {
            if(order.getId() == newInstant.getId()){
                order.setName(newInstant.getName());
                order.setPhone(newInstant.getPhone());
                order.setAddress(newInstant.getAddress());
                order.setGrandTotalTotal(newInstant.getGrandTotalTotal());
                order.setUserId(newInstant.getUserId());
                order.setUpdateAt(Instant.now());
                CSVUtils.writeFile(PATH , orders);
                break;
            }
        }
    }

    @Override
    public boolean existsById(long id) {
        return findById(id) != null;
    }

    @Override
    public Order findById(long id) {
        List<Order> orders = findAll();
        for (Order order : orders) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }

    @Override
    public List<Order> findUserById(long userId) {
        List<Order> orders = findAll();
        List<Order> findId = new ArrayList<>();
        for (Order order: orders){
            if (order.getUserId() == userId)
                findId.add(order);
        }
        return findId;
    }
}
