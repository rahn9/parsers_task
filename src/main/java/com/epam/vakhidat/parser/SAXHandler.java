package com.epam.vakhidat.parser;

import com.epam.vakhidat.parser.entity.Category;
import com.epam.vakhidat.parser.entity.Product;
import com.epam.vakhidat.parser.entity.Shop;
import com.epam.vakhidat.parser.entity.Subcategory;
import com.epam.vakhidat.parser.util.DateConverter;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

public class SAXHandler extends DefaultHandler {
    @Getter
    private Shop shop = null;
    private List<Category> categories = new ArrayList<>();
    private List<Subcategory> subcategories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private Stack<String> elementStack = new Stack<>();
    private Stack<Object> objectStack = new Stack<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        this.elementStack.push(qName);

        if ("shop".equals(qName)) {
            shop = new Shop();
            this.objectStack.push(shop);
        }
        if ("category".equals(qName)) {
            Category category = new Category();
            category.setName(atts.getValue(0));
            this.objectStack.push(category);
            this.categories.add(category);
        }
        if ("subcategory".equals(qName)) {
            Subcategory subcategory = new Subcategory();
            subcategory.setName(atts.getValue(0));
            this.objectStack.push(subcategory);
            this.subcategories.add(subcategory);
        }
        if ("product".equals(qName)) {
            Product product = new Product();
            product.setId(UUID.fromString(atts.getValue(0)));
            this.objectStack.push(product);
            this.products.add(product);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.elementStack.pop();

        if ("shop".equals(qName) || "category".equals(qName) || "subcategory".equals(qName) || "product".equals(qName)) {
            Object object = this.objectStack.pop();
            if ("subcategory".equals(qName)) {
                Subcategory subcategory = (Subcategory) object;
                subcategory.setProducts(this.products);
            }
            if ("category".equals(qName)) {
                Category category = (Category) object;
                category.setSubcategories(this.subcategories);
            }
            if ("shop".equals(qName)) {
                Shop shop = (Shop) object;
                shop.setCategories(this.categories);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (value.length() == 0) return; // ignore white space

        if ("product-name".equals(currentElement())) {
            Product product = (Product) this.objectStack.peek();
            product.setName(value);
        } else if ("producer".equals(currentElement())) {
            Product product = (Product) this.objectStack.peek();
            product.setProducer(value);
        } else if ("model".equals(currentElement())) {
            Product product = (Product) this.objectStack.peek();
            product.setModel(value);
        } else if ("date-of-issue".equals(currentElement())) {
            Product product = (Product) this.objectStack.peek();
            try {
                product.setDateOfIssue(DateConverter.parseDate(value));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if ("color".equals(currentElement())) {
            Product product = (Product) this.objectStack.peek();
            product.setColor(value);
        } else if ("price".equals(currentElement()) || "not_in_stock".equals(currentElement())) {
            if ("price".equals(currentElement())) {
                Product product = (Product) this.objectStack.peek();
                product.setPrice(Double.valueOf(value));
            } else {
                Product product = (Product) this.objectStack.peek();
                product.setNotInStock(Boolean.valueOf(value));
            }
        }
        if ("category-name".equals(currentElement())) {
            Category category = (Category) this.objectStack.peek();
            category.setName(value);
        }
        if ("subcategory-name".equals(currentElement())) {
            Subcategory subcategory = (Subcategory) this.objectStack.peek();
            subcategory.setName(value);
        }
    }

    private String currentElement() {
        return this.elementStack.peek();
    }
}
