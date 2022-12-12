package service;

import model.Order;

import java.util.List;

public interface IOrderService extends InterfaceService<Order>{

    List<Order> findUserById( long userId);
}
