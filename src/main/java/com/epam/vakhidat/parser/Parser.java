package com.epam.vakhidat.parser;

public interface Parser<T> {
    T parse(String xmlPath) throws Exception;
}
