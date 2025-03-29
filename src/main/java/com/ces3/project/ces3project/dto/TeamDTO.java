package com.ces3.project.ces3project.dto;

public class TeamDTO {
    private String name;

    public TeamDTO(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
