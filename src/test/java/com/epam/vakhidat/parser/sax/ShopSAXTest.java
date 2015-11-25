package com.epam.vakhidat.parser.sax;

import com.epam.vakhidat.parser.entity.Shop;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ShopSAXTest {
    @Test
    public void parseTest() throws Exception {
        ShopSAX shopSAX = new ShopSAX();
        Shop shop = shopSAX.parse("src/main/resources/internet-shop.xml");
        assertNotNull(shop);
        System.out.println(shop);
    }
}