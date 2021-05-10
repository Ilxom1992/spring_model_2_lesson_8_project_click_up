package com.example.demo.entity.enums;

public enum Permission {
CAN_ADD_OR_REMOVE_MEMBER("Add/Remove Members",
        "Gives user permission to add or remove members"),
    CAN_MANAGER_STATUS("Edit status","Gives permission...");
    public String name;
    public String description;

    Permission(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
