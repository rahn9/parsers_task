package com.epam.vakhidat.parser;

import com.epam.vakhidat.parser.entity.Shop;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ShopParsersTest {
    private static String XML_PATH = "src/main/resources/internet-shop.xml";

    @Test
    public void parseSAXTest() throws Exception {
        Shop shop = ParserManager.SAXManager(XML_PATH);
        assertNotNull(shop);
        System.out.println(shop);
    }

    @Test
    public void parseStAXTest() throws Exception {
        Shop shop = ParserManager.StAXManager(XML_PATH);
        assertNotNull(shop);
        System.out.println(shop);
    }
}