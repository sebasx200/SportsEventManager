package com.ces3.project.ces3project.dto;

public class PlayerDTO {
    private Integer id;
    private String name;
    private String lastName;
    private String position;
    private Integer number;
    private Boolean isActive;

    public PlayerDTO(String name, String lastName, String position, Integer number, Boolean isActive) {
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", number=" + number +
                ", isActive=" + isActive +
                '}';
    }
}
