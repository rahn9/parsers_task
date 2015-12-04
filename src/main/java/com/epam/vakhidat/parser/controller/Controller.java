package com.epam.vakhidat.parser.controller;

import com.epam.vakhidat.parser.ParserManager;
import com.epam.vakhidat.parser.entity.Shop;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;


public class Controller implements ServletRequestAware {
    public static final String XML_PATH = "WEB-INF/xml/internet-shop.xml";
    public static final String STRUTS_PATH_SUCCESS = "success";
    private Shop shop;
    private HttpServletRequest servletRequest;

    public String dom() {
        String contextPath = servletRequest.getSession().getServletContext().getRealPath("/");
        String xmlToParsePath = contextPath + XML_PATH;

        try {
            shop = ParserManager.DOMManager(xmlToParsePath);
        } catch (IOException | SAXException | ParseException e) {
            e.printStackTrace();
        }

        return STRUTS_PATH_SUCCESS;
    }

    public String sax() {
        String contextPath = servletRequest.getSession().getServletContext().getRealPath("/");
        String xmlToParsePath = contextPath + XML_PATH;
        shop = ParserManager.SAXManager(xmlToParsePath);
        return STRUTS_PATH_SUCCESS;
    }

    public String stax() {
        String contextPath = servletRequest.getSession().getServletContext().getRealPath("/");
        String xmlToParsePath = contextPath + XML_PATH;
        try {
            shop = ParserManager.StAXManager(xmlToParsePath);
        } catch (IOException e) {
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
