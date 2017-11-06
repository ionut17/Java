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
}