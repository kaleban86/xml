package com.app.processor;

import com.app.dao.Dao;
import com.app.xml.CreateXmlFIle;
import com.app.xml.ParsXml;

import java.io.File;

public class DataProcessor {

    private Dao dao;

    public DataProcessor(Dao dao) {
        this.dao = dao;
    }

    public long process() {

        dao.clear();
        dao.create();
        dao.find();
        CreateXmlFIle.createXml();
        ParsXml parsXml = new ParsXml();
        File inputFile = new File("2.xml");
        return parsXml.pars(inputFile);
    }
}
