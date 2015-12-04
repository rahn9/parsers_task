package com.epam.vakhidat.parser.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ParserPropManager {
    private static ParserPropManager parserPropManager;
    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTY_FILE_NAME = "parser.properties";

    private ParserPropManager() {
        InputStream stream = ParserPropManager.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
        try {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ParserPropManager getParserPropManager() {
        if (parserPropManager == null) {
            synchronized (ParserPropManager.class) {
                if (parserPropManager == null) {
                    parserPropManager = new ParserPropManager();
                }
            }
        }
        return parserPropManager;
    }

    public String getPattern(String key) {
        return PROPERTIES.getProperty(key);
    }
}
