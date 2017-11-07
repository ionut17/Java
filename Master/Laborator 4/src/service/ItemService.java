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

public class ItemService<T extends Item> {

    @ManagedProperty("#{databaseService}")
    protected DatabaseService databaseService;

    @ManagedProperty("#{skillService}")
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
                    String skill = skillService.getSkill(rs.getInt(2));
                    records.add(skill);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}
