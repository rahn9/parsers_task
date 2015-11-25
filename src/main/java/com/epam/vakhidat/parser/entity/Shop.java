package com.epam.vakhidat.parser.entity;

import lombok.Data;

import java.util.List;

@Data
public class Shop {
    private List<Category> categories;
}
