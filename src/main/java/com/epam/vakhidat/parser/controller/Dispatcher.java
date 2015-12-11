package com.epam.vakhidat.parser.controller;

import com.epam.vakhidat.parser.ParserManager;
import com.epam.vakhidat.parser.entity.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class Dispatcher {
    public static final String XML_PATH = "WEB-INF/xml/internet-shop.xml";
    private Shop shop;
    private HttpServletRequest request;

    @RequestMapping("/dom")
    public String dom(Model model) throws ParseException, SAXException, IOException {
        String contextPath = request.getSession().getServletContext().getRealPath("/");
        String fullPath = contextPath + XML_PATH;
        shop = ParserManager.DOMManager(fullPath);
        model.addAttribute("shop", shop);
        return "result";
    }

    @RequestMapping("/stax")
    public String stax(Model model) throws ParseException, SAXException, IOException {
        String contextPath = request.getSession().getServletContext().getRealPath("/");
        String fullPath = contextPath + XML_PATH;
        shop = ParserManager.StAXManager(fullPath);
        model.addAttribute("shop", shop);
        return "result";
    }

    @RequestMapping("/sax")
    public String sax(Model model) throws ParseException, SAXException, IOException {
        String contextPath = request.getSession().getServletContext().getRealPath("/");
        String fullPath = contextPath + XML_PATH;
        shop = ParserManager.SAXManager(fullPath);
        model.addAttribute("shop", shop);
        return "result";
    }
}
