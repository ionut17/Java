package view;

import model.Item;
import service.ItemService;
import service.SkillService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

public abstract class ItemView<T extends Item> implements Serializable {

    protected List<T> items;

    @ManagedProperty("#{itemService}")
    protected ItemService<T> itemService;

    @ManagedProperty("#{skillService}")
    protected SkillService skillService;

    @PostConstruct
    public void init() {
        items = itemService.getItems();
    }

    public List<T> getItems() {
        return items;
    }

    public void addItem(T item){
        itemService.add(item);
    }

    public void setItemService(ItemService<T> service) {
        this.itemService = service;
    }

    public void setSkillService(SkillService service) {
        this.skillService = service;
    }

}

