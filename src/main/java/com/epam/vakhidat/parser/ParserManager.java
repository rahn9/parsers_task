package com.epam.vakhidat.parser;

import com.epam.vakhidat.parser.entity.Shop;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public class ParserManager {

    public static Shop DOMManager(String xmlName) throws IOException, SAXException, ParseException {
        ShopDOM shopDOM = new ShopDOM();
        Shop shop = shopDOM.parse(xmlName);
        return shop;
    }

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

    public static Shop StAXManager(String xmlName) throws IOException {
        Shop shop;
        ShopStAX stAXParser = new ShopStAX();
        InputStream inputStream = new FileInputStream(xmlName);
        stAXParser.parse(inputStream);
        shop = stAXParser.getShop();
        inputStream.close();
        return shop;
    }
}
