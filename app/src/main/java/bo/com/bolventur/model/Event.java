package bo.com.bolventur.model;

import java.util.Date;

import bo.com.bolventur.model.users.HostUser;

public class Event {
    private String uid;
    private String category;
    private String photo;
    private String title;
    private Date date;
    private String location;
    private HostUser host;
    private String getTickets;
    private String description;
    private double price;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HostUser getHost() {
        return host;
    }

    public String getGetTickets() {
        return getTickets;
    }

    public void setGetTickets(String getTickets) {
        this.getTickets = getTickets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
