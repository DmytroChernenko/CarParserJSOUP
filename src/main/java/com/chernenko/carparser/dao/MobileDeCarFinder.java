package com.chernenko.carparser.dao;

import com.chernenko.carparser.entity.Car;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmytro on 23.03.16.
 */
public class MobileDeCarFinder implements CarFinder {


    public List<Car> find(Document doc) {
        List<Car>  cars = new ArrayList();

        Elements items = getElements(doc);


        for (Element item : items) {

            System.out.println(item);
            Car car = new Car();
//
            cars.add(car);
//            car.setUrl(getUrl(item));
//            car.setTitle(getTitle(item));

        }

        return cars;
    }

    private Elements getElements(Document doc) {
        return doc.getElementsByClass("list-entry");
    }

    private String getUrl(Element item) {
        return null;
    }

    private String getTitle(Element item) {
        return null;
    }
}
