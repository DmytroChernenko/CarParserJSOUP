package com.chernenko.carparser.dao;

import com.chernenko.carparser.entity.Car;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dmytro on 12.03.16.
 */

@XmlRootElement
@XmlType(name = "car")
public class CarXMLAdapter implements Serializable{
    private String url;
    private String title;
    private String shortDescription;
    private String description;

    private String pathToPhotoDir;
    private List<String> linksOfPhotos;


    public CarXMLAdapter() {
    }

    public CarXMLAdapter(Car car) {
        this.url = car.getUrl();
        this.title = car.getTitle();
        this.shortDescription = car.getShortDescription();
        this.description = car.getDescription();
        this.pathToPhotoDir = car.getPathToPhotoDir();
        this.linksOfPhotos = car.getLinksOfPhotos();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarXMLAdapter that = (CarXMLAdapter) o;

        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (shortDescription != null ? !shortDescription.equals(that.shortDescription) : that.shortDescription != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (pathToPhotoDir != null ? !pathToPhotoDir.equals(that.pathToPhotoDir) : that.pathToPhotoDir != null)
            return false;
        return !(linksOfPhotos != null ? !linksOfPhotos.equals(that.linksOfPhotos) : that.linksOfPhotos != null);

    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pathToPhotoDir != null ? pathToPhotoDir.hashCode() : 0);
        result = 31 * result + (linksOfPhotos != null ? linksOfPhotos.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CarXMLAdapter{");
        sb.append("url='").append(url).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", shortDescription='").append(shortDescription).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", pathToPhotoDir='").append(pathToPhotoDir).append('\'');
        sb.append(", linksOfPhotos=").append(linksOfPhotos);
        sb.append('}');
        return sb.toString();
    }
}
