package view;

import model.Item;
import service.ItemService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

public abstract class ItemView<T extends Item> implements Serializable {

    private List<T> items;

    @ManagedProperty("#{itemService}")
    private ItemService<T> service;

    @PostConstruct
    public void init() {
        items = service.getItems();
    }

    public List<T> getItems() {
        return items;
    }

    public void setService(ItemService<T> service) {
        this.service = service;
    }
}

