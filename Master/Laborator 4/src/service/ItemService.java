package service;

import model.Item;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "itemService")
@ApplicationScoped
public class ItemService<T extends Item> {

    @ManagedProperty("#{databaseService}")
    protected DatabaseService databaseService;

    @ManagedProperty("#{skillServiceS}")
    protected SkillService skillService;

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    private List<T> items;

    public List<T> getItems() {
        return this.items;
    }

    public T add(T item){
        int x = 5;
        int y = 4;
        return null;
    }

    protected List<String> getSkills(Integer id, String table){
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = databaseService.getConnection();
        String stm = "Select * from "+table;

        List<String> records = new ArrayList<>();
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            while(rs.next()) {
                if (rs.getInt(1) == id){
                    records.add(skillService.getSkill(rs.getInt(2)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}
