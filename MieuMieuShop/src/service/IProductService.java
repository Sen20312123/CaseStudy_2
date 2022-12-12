package service;

import model.Order;
import model.Product;

import java.util.List;

public interface IProductService extends InterfaceService<Product>{


    Product findProductById(long productId);
    public void updateQuantity(long id, int quantity);
}
