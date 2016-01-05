package com.epam.vakhidat.parser.controller;

import com.epam.vakhidat.parser.ParserManager;
import com.epam.vakhidat.parser.entity.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class Dispatcher {
    public static final String XML_PATH = "WEB-INF/xml/internet-shop.xml";
    public static final String VIEW = "result";
    public static final String DOM_PARSER_NAME = "DOM";
    public static final String STAX_PARSER_NAME = "StAX";
    public static final String SAX_PARSER_NAME = "SAX";
    public static final String PARSER_ATTRIBUTE_NAME = "parserName";
    public static final String SHOP_ATTRIBUTE_NAME = "shop";
    private HttpServletRequest request;

    @RequestMapping("/result")
    public ModelAndView parser(@RequestParam("parserType") String parserType) throws ParseException, SAXException, IOException {
        String contextPath = request.getSession().getServletContext().getRealPath("/");
        String fullPath = contextPath + XML_PATH;
        ModelAndView mav = new ModelAndView();
        Shop shop;
        if (parserType.equals(DOM_PARSER_NAME)) {
            shop = ParserManager.DOMManager(fullPath);
            mav.addObject(PARSER_ATTRIBUTE_NAME, DOM_PARSER_NAME);
        } else if (parserType.equals(STAX_PARSER_NAME)) {
            shop = ParserManager.StAXManager(fullPath);
            mav.addObject(PARSER_ATTRIBUTE_NAME, STAX_PARSER_NAME);
        } else {
            shop = ParserManager.SAXManager(fullPath);
            mav.addObject(PARSER_ATTRIBUTE_NAME, SAX_PARSER_NAME);
        }
        mav.addObject(SHOP_ATTRIBUTE_NAME, shop);
        mav.setViewName(VIEW);
        return mav;
    }


}
