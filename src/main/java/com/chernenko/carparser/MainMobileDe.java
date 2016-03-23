package com.chernenko.carparser;

import com.chernenko.carparser.dao.CarFinder;
import com.chernenko.carparser.dao.MobileDeCarFinder;
import com.chernenko.carparser.dao.SiteGetter;
import com.chernenko.carparser.entity.Car;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Dmytro on 23.03.16.
 */
public class MainMobileDe {

    public static void main(String[] args) throws IOException {
        String url = getUrl();

        List<Car> cars = getCars(url);

    }

    private static List<Car> getCars(String url) {
        Document site = SiteGetter.getSite(url);
        CarFinder carFinder = new MobileDeCarFinder();
        return carFinder.find(site);
    }

    private static String getUrl() throws IOException {
        System.out.println("Please input your url");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
}
