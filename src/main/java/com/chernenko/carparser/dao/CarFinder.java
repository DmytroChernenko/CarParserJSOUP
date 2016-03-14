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
    List<Car> cars;

    public CarFinder(Document site) {
        this.site = site;
        find(site);
    }

    private void find(Document site) {
        cars = new ArrayList<Car>();

        try {
            Elements linksElements = site.getElementsByClass("item-link");

            for (Element link : linksElements) {

                Car car = new Car();
                String url = link.attr("href");
                car.setUrl(url);

                Element title = link.getElementsByClass("ad-title").first();
                car.setTitle(title.ownText());

                Element shortDescriptionElement = link.getElementsByClass("ad-description").first();
                car.setShortDescription(shortDescriptionElement.toString());

                Element priceElement = link.getElementsByClass("ad-price").first();
                String price = priceElement.ownText().substring(0, priceElement.ownText().length() - 1);
                price = price.replaceAll(" ", "");
                Integer p = Integer.parseInt(price) + 1000;
                car.setPrice(p.toString());

                String carFullLink = "http://www.autogidas.lt/" + car.getUrl();
                Document fullCarInfoDoc = SiteGetter.getSite(carFullLink);

                Element fullDescriptionDiv = fullCarInfoDoc.getElementsByAttributeValue("class", "params-block").get(1);
                car.setDescription(fullDescriptionDiv.html());

                //Element mainPhoto = fullCarInfoDoc.getElementById("big-photo").getElementsByAttribute("src").first();
                Element mainPhoto = fullCarInfoDoc.getElementsByAttributeValue("class", "big-photo")
                        .first().getElementsByAttribute("src").first();
                if(mainPhoto == null)
                    continue;
                car.setMainPhoto(mainPhoto.absUrl("src"));


                ArrayList<String> photos = new ArrayList<String>();
                Elements photosEl = fullCarInfoDoc.getElementsByAttributeValue("class", "photo");
                for (Element element : photosEl) {
                    photos.add(element.getElementsByTag("img").first().toString());
                }
                car.setLinksOfPhotos(photos);


                StringBuilder sb = new StringBuilder(car.getDescription());
                for (String photo : photos) {
                    sb.append(photo);
                }

                String description = sb.toString();
                car.setDescription(description);


                cars.add(car);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public List<Car> getCars() {
        return cars;
    }
}
