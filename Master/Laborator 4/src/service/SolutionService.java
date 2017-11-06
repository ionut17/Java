package service;

import model.Project;
import model.Student;
import java.util.*;

public class SolutionService {

    private List<Project> projects;
    private Map<String, Project> projectsMap;
    private List<Student> unengagedStudents;
    private Map<Student, Set<String>> preferredProjectsDictionary;

    private Map<Student, Project> solution = new HashMap<>();

    public SolutionService(List<Project> projects, List<Student> students){
        this.projects = projects;
        this.unengagedStudents = students;

        projects.forEach(project -> projectsMap.put(project.getName(), project));
    }

    public Map<Student, Project> solve(){
        initializePreferredProjects();

        while(unengagedStudents.size() > 0){
            unengagedStudents.stream().forEach(student -> propose(student));
        }
        return null;
    }

    private void initializePreferredProjects(){
        for(Student student : unengagedStudents){
            Map<String, Integer> preferredProjects = new HashMap<>();
            for(Project project : projects){
                int projectScore = 0;
                int projectSkillsNo = project.getSkills().size();
                for(String skill : project.getSkills()){
                    if(student.getSkills().contains(skill)){
                        projectScore += (projectSkillsNo+1) - project.getSkills().indexOf(skill);
                    }
                }
                int maxProjectScore = projectSkillsNo*(projectSkillsNo+1)/2;
                int projectPercentageScore = projectScore/maxProjectScore *100;

                preferredProjects.put(project.getName(), projectPercentageScore);
            }

            Comparator<Map.Entry<String, Integer>> byMapValues = new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> left, Map.Entry<String, Integer> right) {
                    return right.getValue().compareTo(left.getValue());
                }
            };

            List<Map.Entry<String, Integer>> preferredProjectsList = new ArrayList<>();
            preferredProjectsList.addAll(preferredProjects.entrySet());

            Collections.sort(preferredProjectsList, byMapValues);

            preferredProjectsDictionary.put(student, preferredProjects.keySet());
        }
    }

    private void propose(Student student){
        Project project = student.getProjects().get(0);

        int projectScore = 0;
        int projectSkillsNo = project.getSkills().size();
        for(String skill : project.getSkills()){
            if(student.getSkills().contains(skill)){
                projectScore += (projectSkillsNo+1) - project.getSkills().indexOf(skill);
            }
        }
        int maxProjectScore = projectSkillsNo*(projectSkillsNo+1)/2;
        int projectPercentageScore = projectScore/maxProjectScore *100;

        if(project.getStudents().size() < project.getQuota()){
            project.addStudent(projectPercentageScore, student);
        }
        else{
            int smallestScore = project.getStudents().get(project.getStudents().size() - 1).getKey();
            if(smallestScore < projectPercentageScore){
                project.replaceStudent(projectPercentageScore, student);
                solution.put(student, project);
                unengagedStudents.remove(student);
            }
            else{
                if(student.getProjects().size()>0) {
                    student.getProjects().remove(0);
                    propose(student);
                }
                else{
                    unengagedStudents.remove(student);
                    unengagedStudents.add(student);
                }
            }
        }
    }
}
