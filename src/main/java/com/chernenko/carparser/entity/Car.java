package com.chernenko.carparser.entity;

import java.util.List;

/**
 * Created by Dmytro on 11.03.16.
 */
public class Car {

    private String url;
    private String title;
    private String shortDescription;
    private String description;

    private String pathToPhotoDir;
    private List<String> linksOfPhotos;

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    private String mainPhoto;

    private String price;

    public String getPathToPhotoDir() {
        return pathToPhotoDir;
    }

    public void setPathToPhotoDir(String pathToPhotoDir) {
        this.pathToPhotoDir = pathToPhotoDir;
    }

    public List<String> getLinksOfPhotos() {
        return linksOfPhotos;
    }

    public void setLinksOfPhotos(List<String> linksOfPhotos) {
        this.linksOfPhotos = linksOfPhotos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("url='").append(url).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", shortDescription='").append(shortDescription).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", pathToPhotoDir='").append(pathToPhotoDir).append('\'');
        sb.append(", linksOfPhotos=").append(linksOfPhotos);
        sb.append(", price='").append(price).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
