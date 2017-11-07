package view;

import model.Item;
import service.DatabaseService;
import service.ItemService;
import service.SkillService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public abstract class ItemView<T extends Item> implements Serializable {

    protected List<T> items;

    @ManagedProperty("#{skillService}")
    protected SkillService skillService;

    public List<T> getItems() {
        return items;
    }

    public void setSkillService(SkillService service) {
        this.skillService = service;
    }

}

