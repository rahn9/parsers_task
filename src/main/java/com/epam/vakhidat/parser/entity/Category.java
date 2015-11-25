package com.epam.vakhidat.parser.entity;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    private String name;
    private List<Subcategory> subcategories;
}
