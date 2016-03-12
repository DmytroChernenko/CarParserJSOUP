package com.chernenko.carparser.dao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by dmytro on 11.01.15.
 */
public class SiteGetter {
    private String url;
    private String userAgent;


    public SiteGetter(String url) {
        this.url = url;
    }

    public Document getSite(){
        Document doc;

        try{
            doc = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .post();
        }catch (IOException e){
            System.out.println("Скорее всего нас забанили((( нету соединения");
            return null;
        }
        return doc;

    }
}
