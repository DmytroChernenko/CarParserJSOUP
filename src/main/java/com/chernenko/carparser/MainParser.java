package com.chernenko.carparser;


import com.chernenko.carparser.dao.CarFinder;
import com.chernenko.carparser.dao.SiteGetter;
import com.chernenko.carparser.database.PlainCarDao;
import com.chernenko.carparser.entity.Car;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainParser {

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
            url = "http://ru.autogidas.lt/automobiliai/1-psl/?f_1=Audi&f_215=1000&f_50=kaina_asc";



        Document site = SiteGetter.getSite(url);

        int numberOfPages;
        Element number = site.getElementsByAttributeValue("class", "page").last();
        numberOfPages = Integer.parseInt(number.html());

        List<Car> allCars = new ArrayList<Car>();


        for (int i = 1; i <2; i++) {
            url = "http://ru.autogidas.lt/automobiliai/" + i  + "-psl/?f_1=Audi&f_215=1000&f_50=kaina_asc";
            site = SiteGetter.getSite(url);
            CarFinder carFinder = new CarFinder(site);
            List<Car> cars = carFinder.getCars();
            allCars.addAll(cars);
        }


        System.out.println(allCars.size());


        int i = 0;
        try {
            for (Car car : allCars) {
                DownloadImages.getImage(car);
                saveToDatabase(car);
                System.out.println(++i);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void saveToDatabase(Car car) {

        PlainCarDao dao = new PlainCarDao();
        dao.insert(car);
    }


}
