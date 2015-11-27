package com.epam.vakhidat.parser;

import com.epam.vakhidat.parser.entity.Shop;
import lombok.Getter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ShopSAX {
    @Getter
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
