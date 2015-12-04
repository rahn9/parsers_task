package com.epam.vakhidat.parser.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParserProperty {
    private ParserProperty parserProperty;
    private static final Properties PROPERTIES = new Properties();

    private ParserProperty(String filename) {

        InputStream stream = ParserProperty.class.getClassLoader().getResourceAsStream(filename);
        try {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ParserProperty getParserProperty(String filename) {
        if (parserProperty == null) {
            synchronized (ParserProperty.class) {
                if (parserProperty == null) {
                    parserProperty = new ParserProperty(filename);
                }
            }
        }
        return parserProperty;
    }

    public String getPattern(String key) {
        return PROPERTIES.getProperty(key);
    }
}
