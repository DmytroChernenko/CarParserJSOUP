package com.chernenko.carparser.dao;

import org.jsoup.nodes.Document;

/**
 * Created by Dmytro on 12.03.16.
 */
public class CarDetailFinder {

    Document site;

    public CarDetailFinder(Document site) {
        this.site = site;
    }
}
