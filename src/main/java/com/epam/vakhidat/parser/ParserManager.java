package com.epam.vakhidat.parser;

import com.epam.vakhidat.parser.entity.Shop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ParserManager {

    /*public static List<Category> DOMManager(String xmlName, String xsdName) {

        List<Category> categoryList = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            dbf.setNamespaceAware(true);
            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance("http://www.w3.org/2001/XMLSchema");
            dbf.setSchema(schemaFactory
                    .newSchema(new Source[]{new StreamSource(xsdName)}));
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(xmlName);
            Element root = document.getDocumentElement();
            categoryList = new ArrayList<Category>();
            categoryList.add(ShopDOM.listBuilder(root));
        } catch (ParserConfigurationException e) {
            log.error("Parser Configuration Exception");
        } catch (SAXException e) {
            log.error("SAXHandler Exception");
        } catch (IOException e) {
            log.error("IO Exception");
        }
        log.info("XML document parse with com.epam.vakhidat.parser.ShopDOM parser");
        return categoryList;
    }*/

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
