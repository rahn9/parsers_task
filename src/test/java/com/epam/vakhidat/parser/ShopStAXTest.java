package com.epam.vakhidat.parser;

import com.epam.vakhidat.parser.entity.Shop;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShopStAXTest {
    private static String XML_PATH = "src/main/resources/internet-shop.xml";

    @Test
    public void testParse() throws Exception {
        Shop shop = ParserManager.StAXManager(XML_PATH);
        assertNotNull(shop);
        System.out.println(shop);
    }
}