package br.ifmg.edu.bsi.progmovel.photojar1.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "photos")
public class PhotoRecord {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private String date;
    private String photoUri;
    private String local;


    public PhotoRecord(String description, String date, String photoUri, String local) {
        this.description = description;
        this.date = date;
        this.photoUri = photoUri;
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getLocal(){return local;}

    public void setLocal(String local){this.local = local;}
}
