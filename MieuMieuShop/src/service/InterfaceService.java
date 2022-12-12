package service;

import model.Product;

import java.util.List;

public interface InterfaceService<T> {

    List<T> findAll();

    void add (T newInstance);
    void remove (long id);
    void update(T newInstance);
    boolean exists(long id);
    T findById(long id);
}
