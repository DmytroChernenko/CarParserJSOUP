package com.chernenko.carparser;


import com.chernenko.carparser.dao.CarFinder;
import com.chernenko.carparser.dao.HtmlCarDao;
import com.chernenko.carparser.dao.SiteGetter;
import com.chernenko.carparser.entity.Car;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.Map;

public class Main {

    static String url;

    static {
        System.out.println("Введите адрес сайта");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            url = bufferedReader.readLine();
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) throws Exception {
        // write your code here

        if (url.equals(""))
            url = "http://ru.autogidas.lt/automobiliai/?f_1=&f_model_14=&f_215=1000&f_216=10000&f_41=&f_42=&f_3=&f_2=&f_376=";

        SiteGetter siteGetter = new SiteGetter(url);
        Document site = siteGetter.getSite();


        CarFinder carFinder = new CarFinder(site);
        Map<String, Car> cars = carFinder.getMapCars();

        //FileOutputStream fileResult = new FileOutputStream();       // файл куда пишем данные

        BufferedWriter writer = new BufferedWriter(new FileWriter("1.txt"));  // файл куда пишем данные


        System.out.println("Список ссылок");
        for (String s : cars.keySet()) {
            System.out.println(s);
        }


        System.out.println("Cars:");
        HtmlCarDao carDao = new HtmlCarDao();


        for (Car car : cars.values()) {
            carDao.saveCarToXml(car);
        }


    }
}
