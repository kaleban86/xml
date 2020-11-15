package com.app.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class ParsXml {



    public long pars(File inputFile ) {


        DataHandler dataHandler = new DataHandler();
        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            saxParser.parse(inputFile, dataHandler);
            return dataHandler.getSum();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}