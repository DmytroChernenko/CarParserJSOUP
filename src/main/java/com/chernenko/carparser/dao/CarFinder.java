package com.chernenko.carparser.dao;

import com.chernenko.carparser.entity.Car;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by Dmytro on 12.03.16.
 */
public class CarFinder {
    private Document site;
    Map<String, Car> mapCars;

    public CarFinder(Document site) {
        this.site = site;
        find(site);
    }

    private void find(Document site) {
        mapCars = new HashMap();

        try {
            Elements linksElements = site.getElementsByClass("item-link");

            for (Element link : linksElements) {

                Car car =new Car();
                String url = link.attr("href");
                car.setUrl(url);

                Element title = link.getElementsByClass("ad-title").first();
                car.setTitle(title.ownText());

                Element shortDescriptionElement = link.getElementsByClass("ad-description").first();
                car.setShortDescription(shortDescriptionElement.toString());

                Element priceElement = link.getElementsByClass("ad-price").first();
                car.setPrice(priceElement.ownText());

                String carFullLink = "http://www.autogidas.lt/" + car.getUrl();
                SiteGetter siteGetter = new SiteGetter(carFullLink);
                Document fullCarInfoDoc = siteGetter.getSite();

                Element fullDescriptionDiv = fullCarInfoDoc.getElementsByAttributeValue("class", "params-block").get(1);
                car.setDescription(fullDescriptionDiv.html());

                Element mainPhoto = fullCarInfoDoc.getElementById("big-photo").getElementsByAttribute("src").first();
                car.setMainPhoto(mainPhoto.absUrl("src"));

                ArrayList<String> photos = new ArrayList<String>();
                Elements photosEl = fullCarInfoDoc.getElementsByAttributeValue("class", "photo");
                for (Element element : photosEl) {
                    photos.add(element.absUrl("data-src"));
                    System.out.println(element.absUrl("data-src"));
                }
                car.setLinksOfPhotos(photos);

                mapCars.put(url, car);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public Map<String, Car> getMapCars() {
        return mapCars;
    }
}
