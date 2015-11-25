package com.epam.vakhidat.parser.entity;

import lombok.Data;

import java.util.List;

@Data
public class Subcategory {
    private String name;
    private List<Product> products;
}
