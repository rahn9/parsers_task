package com.epam.vakhidat.parser;

import com.epam.vakhidat.parser.entity.Category;
import com.epam.vakhidat.parser.entity.Product;
import com.epam.vakhidat.parser.entity.Shop;
import com.epam.vakhidat.parser.entity.Subcategory;
import com.epam.vakhidat.parser.util.DateConverter;
import com.epam.vakhidat.parser.util.ParserPropManager;
import lombok.Getter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ShopStAX {
    private static ParserPropManager manager = ParserPropManager.getParserPropManager();
    @Getter
    private Shop shop;
    private Category category;
    private Subcategory subcategory;
    private Product product;
    private List<Category> categories;
    private List<Subcategory> subcategories;
    private List<Product> products;
    private String name;

    public void parse(InputStream inputStream) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            process(reader);
        } catch (XMLStreamException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void process(XMLStreamReader reader) throws XMLStreamException, ParseException {
        int type = reader.getEventType();
        categories = new ArrayList<>();
        subcategories = new ArrayList<>();
        products = new ArrayList<>();

        while (reader.hasNext()) {
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    startElement(reader);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    endElement(reader);
                    break;
            }
            type = reader.next();
        }
        shop.setCategories(categories);
    }

    private void startElement(XMLStreamReader reader) throws XMLStreamException, ParseException {
        name = reader.getLocalName();
        switch (name) {
            case "shop":
                shop = new Shop();
                break;
            case "category":
                category = new Category();
                category.setName(reader.getAttributeValue(0));
                break;
            case "subcategory":
                subcategory = new Subcategory();
                subcategory.setName(reader.getAttributeValue(0));
                break;
            case "product":
                product = new Product();
                product.setId(UUID.fromString(reader.getAttributeValue(0)));
                break;
            case "product-name":
                product.setName(reader.getElementText());
                break;
            case "producer":
                product.setProducer(reader.getElementText());
                break;
            case "model":
                product.setModel(reader.getElementText());
                break;
            case "date-of-issue":
                product.setDateOfIssue(DateConverter.parseDate(reader.getElementText()));
                break;
            case "color":
                product.setColor(reader.getElementText());
                break;
            case "price":
                product.setPrice(Double.valueOf(reader.getElementText()));
                break;
            case "not_in_stock":
                product.setNotInStock(Boolean.valueOf(reader.getElementText()));
                break;
        }
    }

    private void endElement(XMLStreamReader reader) throws XMLStreamException {
        name = reader.getLocalName();

        switch (name) {
            case "product":
                products.add(product);
                break;
            case "subcategory":
                subcategory.setProducts(products);
                subcategories.add(subcategory);
                break;
            case "category":
                category.setSubcategories(subcategories);
                categories.add(category);
                break;
        }
    }
}
