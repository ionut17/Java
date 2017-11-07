package service;

import model.Item;
import model.ItemDto;
import model.Student;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "studentService")
@ApplicationScoped
public class StudentService extends ItemService<Student> {

    public List<Student> getItems() {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = databaseService.getConnection();
        String stm = "Select * from students";
        List<Student> students = new ArrayList<>();
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            while(rs.next()) {
                students.add(new Student(rs.getString(2), getSkills(rs.getInt(1), "student_skills")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int x = 5;

        return new ArrayList<>();
    }

}

