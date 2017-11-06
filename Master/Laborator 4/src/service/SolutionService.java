package service;

import model.Project;
import model.Student;

import java.util.*;
import java.util.stream.Collectors;

public class SolutionService {

    private List<Project> projects;
    private Map<String, Project> projectsMap;
    private List<Student> unengagedStudents;
    private Map<Student, Set<String>> preferredProjectsDictionary;

    public SolutionService(List<Project> projects, List<Student> students){
        this.projects = projects;
        this.unengagedStudents = students;

        projects.forEach(project -> projectsMap.put(project.getName(), project));
    }

    public Dictionary<Student, Project> solve(){
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
        Set<String> preferredProjects = preferredProjectsDictionary.get(student);
        for(String projectName: preferredProjects){

        }
    }
}
