package com.epam.vakhidat.parser;

import com.epam.vakhidat.parser.entity.Shop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ParserManager {

    public static Shop SAXManager(String xmlName) {
        Shop shop = new Shop();
        try {
            ShopSAX shopSAX = new ShopSAX();
            shopSAX.parse(xmlName);
            shop = shopSAX.getSaxHandler().getShop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shop;
    }

    public static Shop StAXManager(String xmlName) throws FileNotFoundException {
        Shop shop;
        ShopStAX stAXParser = new ShopStAX();
        InputStream inputStream = new FileInputStream(xmlName);
        stAXParser.parse(inputStream);
        shop = stAXParser.getShop();
        return shop;
    }
}
