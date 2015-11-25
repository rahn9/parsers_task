package com.epam.vakhidat.parser.entity;


import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Product {
    private UUID id;
    private String name;
    private String model;
    private Date dateOfIssue;
    private String color;
    private String producer;
    private double price;
    private boolean notInStock;
}
