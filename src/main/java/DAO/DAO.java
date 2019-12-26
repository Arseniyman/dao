package DAO;

import java.util.ArrayList;
import java.util.List;

public interface DAO<T> {
    T getOne(int id);
    ArrayList<T> getAll();
    void insert(T t);
    void update (T t);
    void delete(int id);
}
