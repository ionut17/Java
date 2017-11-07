package model;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Project extends Item {
    private int quota;
    private List<Pair<Integer, Student>> students = new ArrayList<>();

    public Project(){}

    public Project(String name, List<String> skills){
        super(name, skills);
    }

    public Project(Integer id, String name, List<String> skills){
        super(id, name, skills);
    }

    public Project(String name, List<String> skills, int quota){
        super(name, skills);
        this.quota = quota;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public List<Pair<Integer, Student>> getStudents() {
        return students;
    }

    public void setStudents(List<Pair<Integer, Student>> students) {
        this.students = students;
    }

    public void addStudent(Integer score, Student student){
        students.add(new Pair(score, student));
        Collections.sort(students, Comparator.comparing(p -> -p.getKey()));
    }

    public void replaceStudent(Integer score, Student newStudent){
        students.remove(students.get(students.size()-1));
        students.add(new Pair(score, newStudent));
    }
}
