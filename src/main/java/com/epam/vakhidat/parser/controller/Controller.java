package com.epam.vakhidat.parser.controller;

import com.epam.vakhidat.parser.ShopDOM;
import com.epam.vakhidat.parser.ShopSAX;
import com.epam.vakhidat.parser.ShopStAX;
import com.epam.vakhidat.parser.entity.Shop;
import kz.epam.ilya.task2.model.ShopType;
import kz.epam.ilya.task2.util.xml.parser.ProductsDOMParser;
import kz.epam.ilya.task2.util.xml.parser.ProductsSAXParser;
import kz.epam.ilya.task2.util.xml.parser.ProductsStAXParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;


public class Controller implements ServletRequestAware {
    public static final String XML_PATH = "WEB-INF/xml/shop.xml";
    public static final String STRUTS_PATH_SUCCESS = "SUCCESS";
    private Shop shop;
    private HttpServletRequest servletRequest;

    public String dom() {
        String contextPath = servletRequest.getSession().getServletContext().getRealPath("/");
        String xmlToParsePath = contextPath + XML_PATH;

        ShopDOM parser = new ShopDOM();
        try {
            shop = parser.parse(xmlToParsePath);
        } catch (SAXException | IOException e) {
            logger.error("DOM parser exception: {}", e);
        }
        return STRUTS_PATH_SUCCESS;
    }

    public String sax() {
        String contextPath = servletRequest.getSession().getServletContext().getRealPath("/");
        String xmlToParsePath = contextPath + XML_PATH;

        ShopSAX parser = new ShopSAX();
        try {
            shop = parser.parse(xmlToParsePath);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return STRUTS_PATH_SUCCESS;
    }

    public String stax() {
        String contextPath = servletRequest.getSession().getServletContext().getRealPath("/");
        String xmlToParsePath = contextPath + XML_PATH;

        ShopStAX parser = new ShopStAX();
        try {
            shop = parser.parse(xmlToParsePath);
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
        return STRUTS_PATH_SUCCESS;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void setServletRequest(HttpServletRequest arg0) {
        this.servletRequest = arg0;
    }

}
