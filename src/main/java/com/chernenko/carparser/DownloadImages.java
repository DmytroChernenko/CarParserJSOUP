package com.chernenko.carparser;

import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;

public class DownloadImages {

    //The url of the website. This is just an example
    private static final String webSiteURL = "http://www.supercars.net/gallery/119513/2841/5.html";

    //The path of the folder that you want to save the images to
    private static final String mainFolderPath = "src/main/resources/data/";

    public static void main(String[] args) {

        try {

            //Connect to the website and get the html
            Document doc = Jsoup.connect(webSiteURL)
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .get();

            //Get all elements with img tag ,
            Elements img = doc.getElementsByTag("img");

            for (Element el : img) {

                //for each element get the srs url
                String src = el.absUrl("src");

                System.out.println("Image Found!");
                System.out.println("src attribute is : "+src);

                getImages(src, "1/");

            }

        } catch (IOException ex) {
            System.err.println("There was an error");
            Logger.getLogger(DownloadImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void getImages(String imageSrc, String folderPath) throws IOException {

        //Exctract the name of the image from the src attribute
        int indexname = imageSrc.lastIndexOf("/");

        if (indexname == imageSrc.length()) {
            imageSrc = imageSrc.substring(1, indexname);
        }

        indexname = imageSrc.lastIndexOf("/");
        String name = imageSrc.substring(indexname, imageSrc.length());

        System.out.println(name);

        //Open a URL Stream
        URL url = new URL(imageSrc);
        InputStream in = url.openStream();

        OutputStream out = new BufferedOutputStream(new FileOutputStream(mainFolderPath + folderPath+ name));

        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        out.close();
        in.close();

    }
}