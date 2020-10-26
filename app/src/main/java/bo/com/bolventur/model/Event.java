package bo.com.bolventur.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Map;

@Entity(tableName = "event_table")
public class Event {
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "uid")
    private String uid;

    @ColumnInfo(name = "category")
    private int category;

    @ColumnInfo(name = "photo")
    private String photo;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "date")
    private long date;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "host")
    private String host;

    @ColumnInfo(name = "description")
    private String description;

    //@Ignore
    @ColumnInfo(name = "ticket")
    private Map<String, String> ticket;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getTicket() {
        return ticket;
    }

    public void setTicket(Map<String, String> ticket) {
        this.ticket = ticket;
    }
}
