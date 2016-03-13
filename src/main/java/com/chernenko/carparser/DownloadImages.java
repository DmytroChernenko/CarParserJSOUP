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

    //The path of the folder that you want to save the images to
    private static final String mainFolderPath = "src/main/resources/data/";


    public static void getImage(String imageSrc) throws IOException {

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

        OutputStream out = new BufferedOutputStream(new FileOutputStream(mainFolderPath+ name));

        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        out.close();
        in.close();

    }
}