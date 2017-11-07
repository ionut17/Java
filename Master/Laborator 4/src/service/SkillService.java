package service;

import model.ItemDto;
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

    private List<String> skills = new ArrayList<>();

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    @ManagedProperty("#{databaseService}")
    protected DatabaseService databaseService;

    public SkillService(){
    }

    @PostConstruct
    public void init() throws SQLException {
        skills = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = databaseService.getConnection();
        String stm = "Select * from skills";

        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            while(rs.next()) {
                int x = 5;
                skills.add(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getSkills() {
        return this.skills;
    }

    public String getSkill(Integer id) throws SQLException {
        init();
        return this.skills.size() >= id ? this.skills.get(id-1) : null;
    }

    public Integer getId(String skill) throws SQLException {
        init();
        return this.skills.indexOf(skill)+1;
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
}
