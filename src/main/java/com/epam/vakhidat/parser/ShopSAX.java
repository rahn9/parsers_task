package com.epam.vakhidat.parser;

import com.epam.vakhidat.parser.entity.Shop;
import lombok.Getter;
import lombok.Setter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ShopSAX implements Parser<Shop> {
    @Getter
    @Setter
    private SAXHandler handler;

    public ShopSAX() {
        handler = new SAXHandler();
    }

    public Shop parse(String xmlPath) throws Exception {
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(xmlPath);
        return handler.getShop();
    }
}
