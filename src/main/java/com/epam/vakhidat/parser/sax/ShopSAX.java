package com.epam.vakhidat.parser.sax;

import com.epam.vakhidat.parser.Parser;
import com.epam.vakhidat.parser.entity.Shop;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ShopSAX implements Parser<Shop> {
    private SAXHandler saxHandler;

    public ShopSAX() {
        saxHandler = new SAXHandler();
    }

    public Shop parse(String xmlPath) throws Exception {
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(saxHandler);
        xmlReader.parse(xmlPath);
        return saxHandler.getShop();
    }
}
