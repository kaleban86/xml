package com.app.xml;

public class EntryProducer {

    private String entryTemplate;

    public EntryProducer() {

        entryTemplate =
                "<entry> \n" +
                        "    <field>%s</field>\n" +
                        "</entry>\n";
    }

    public String produceItem(int value) {

        return String.format(entryTemplate, value);

    }
}
