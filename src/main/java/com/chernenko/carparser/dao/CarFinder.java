package com.chernenko.carparser.dao;

import com.chernenko.carparser.entity.Car;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by Dmytro on 23.03.16.
 */
public interface CarFinder {
    List<Car> find(Document doc);
}
