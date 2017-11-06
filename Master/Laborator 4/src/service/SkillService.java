package service;

import model.Item;
import model.ItemDto;
import model.Skill;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "skillService", eager = true)
@ApplicationScoped
public class SkillService {

    private List<String> skills;

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    @ManagedProperty("#{databaseService}")
    protected DatabaseService databaseService;

    public SkillService(){
    }

    @PostConstruct
    public void init() throws SQLException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = databaseService.getConnection();
        String stm = "Select * from skills";
        List<ItemDto> records = new ArrayList<>();

        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            while(rs.next()) {
                skills.add(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getSkills() {
        return this.skills;
    }

    public String getSkill(Integer id){
        return this.skills.get(id);
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
}
