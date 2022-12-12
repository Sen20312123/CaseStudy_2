package service;

import model.Order;
import model.OrderItem;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOrderItemService{
    private static final String PATH = "E:\\Module2\\CaseStudy_2\\MieuMieuShop\\data\\orderItem.csv";


    public static OrderItemService orderItemService;

    public OrderItemService(){

    }

    public static OrderItemService orderItemService(){
        if (orderItemService == null)
            orderItemService = new OrderItemService();
        return orderItemService;
    }
    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        List<String> records = CSVUtils.readFile(PATH);
        for (String record : records) {
            orderItems.add(OrderItem.parseOrderItem(record));
        }
        return orderItems;
    }

    @Override
    public void add(OrderItem newInstant) {
    List<OrderItem> orderItems = findAll();
        orderItems.add(newInstant);
        CSVUtils.writeFile(PATH,orderItems);
        }


    @Override
    public void removeById(long id) {
        List<OrderItem> orderItems = findAll();
        orderItems.removeIf(orderItem -> orderItem.getId()==id);
        CSVUtils.writeFile(PATH,orderItems);
    }

    @Override
    public void update(OrderItem newInstant) {
        List<OrderItem> orderItems = findAll();
        for (OrderItem orderItem: orderItems) {
            if (orderItem.getId() == newInstant.getId()){
                orderItem.setProductId(newInstant.getProductId());
                orderItem.setProductName(newInstant.getProductName());
                orderItem.setPrice(newInstant.getPrice());
                orderItem.setQuantity(newInstant.getQuantity());
                orderItem.setOrderId(newInstant.getOrderId());
                CSVUtils.writeFile(PATH,orderItems);
                break;
            }
        }
    }

    @Override
    public boolean existsById(long id) {
        return findById(id) != null;
    }

    @Override
    public OrderItem findById(long id) {
        List<OrderItem> orderItems = findAll();
        for (OrderItem orderItem : orderItems){
            if (orderItem.getId()== id)
                return orderItem;
        }
        return null;
    }

    @Override
    public List<OrderItem> findByOrderId(long orderId) {
        List<OrderItem> orderItems = findAll();
        List<OrderItem> orderItemsFind = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getOrderId() == orderId) {
                orderItemsFind.add(orderItem);
            }
        }
        if (orderItemsFind.isEmpty()) {
            return null;
        }
        return orderItemsFind;
    }
}
