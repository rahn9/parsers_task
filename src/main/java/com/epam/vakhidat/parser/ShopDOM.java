package com.epam.vakhidat.parser;

import com.epam.vakhidat.parser.entity.Category;
import com.epam.vakhidat.parser.entity.Product;
import com.epam.vakhidat.parser.entity.Shop;
import com.epam.vakhidat.parser.entity.Subcategory;
import com.epam.vakhidat.parser.util.DateConverter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ShopDOM {
    private DocumentBuilder builder;
    private Shop shop;

    public ShopDOM() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Shop parse(String xmlPath) throws IOException, SAXException, ParseException {
        Document document = builder.parse(xmlPath);
        Element root = document.getDocumentElement();
        root.normalize();
        Shop shop = parseShop(root);
        return shop;
    }

    private Shop parseShop(Element root) throws ParseException {
        List<Category> categoryList = new ArrayList<>();
        Shop shop = new Shop();
        NodeList categoryNodeList = root.getElementsByTagName("category");
        for (int i = 0; i < categoryNodeList.getLength(); i++) {
            Element categoryElement = (Element) categoryNodeList.item(i);
            Category category = new Category();
            category.setName(categoryElement.getAttribute("name"));
            List<Subcategory> subCategoryList = parseSubcategory(categoryElement);
            category.setSubcategories(subCategoryList);
            categoryList.add(category);
        }
        shop.setCategories(categoryList);
        return shop;
    }

    private List<Subcategory> parseSubcategory(Element categoryElement) throws ParseException {
        List<Subcategory> subcategoryList = new ArrayList<>();
        NodeList subcategoryNodeList = categoryElement.getElementsByTagName("subcategory");
        for (int i = 0; i < subcategoryNodeList.getLength(); i++) {
            Element subcategoryElement = (Element) subcategoryNodeList.item(i);
            Subcategory subcategory = new Subcategory();
            subcategory.setName(subcategoryElement.getAttribute("name"));
            List<Product> productList = parseProduct(subcategoryElement);
            subcategory.setProducts(productList);
            subcategoryList.add(subcategory);
        }
        return subcategoryList;
    }

    private List<Product> parseProduct(Element subcategoryElement) throws ParseException {
        List<Product> productList = new ArrayList<>();
        NodeList productNodeList = subcategoryElement.getElementsByTagName("product");
        for (int i = 0; i < productNodeList.getLength(); i++) {
            Element productElement = (Element) productNodeList.item(i);
            Product product = new Product();
            product.setId(UUID.fromString(productElement.getAttribute("id")));
            product.setName(productElement.getElementsByTagName("product-name").item(0).getTextContent());

            if (productElement.getElementsByTagName("price")!=null && productElement.getElementsByTagName("price").item(0)!=null)  {
                product.setPrice(Double.parseDouble(productElement.getElementsByTagName("price").item(0).getTextContent()));
            }
            if (productElement.getElementsByTagName("not_in_stock")!=null && productElement.getElementsByTagName("not_in_stock").item(0)!=null)  {
                product.setNotInStock(Boolean.valueOf(productElement.getElementsByTagName("not_in_stock").item(0).getTextContent()));
            }
            product.setModel(productElement.getElementsByTagName("model").item(0).getTextContent());
            product.setProducer(productElement.getElementsByTagName("producer").item(0).getTextContent());
            Date date = DateConverter.parseDate(productElement.getElementsByTagName("date-of-issue").item(0).getTextContent());
            product.setDateOfIssue(date);
            product.setColor(productElement.getElementsByTagName("color").item(0).getTextContent());
            productList.add(product);
        }
        return productList;
    }
}
