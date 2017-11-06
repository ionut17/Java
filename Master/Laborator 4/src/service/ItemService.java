package service;

import model.Item;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "itemService")
@ApplicationScoped
public class ItemService<T extends Item> {

    private List<T> items;

    public List<T> getItems() {
        return this.items;
    }

    public T add(T item){
        int x = 5;
        int y = 4;
        return null;
    }
}
