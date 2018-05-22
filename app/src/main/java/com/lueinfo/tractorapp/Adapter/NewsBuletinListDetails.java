package com.lueinfo.tractorapp.Adapter;


import org.json.JSONException;
import org.json.JSONObject;

public class NewsBuletinListDetails {

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {

        return title;
    }

    public String getImage() {
        return image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    String title="";
String image="";
String created_at="";
    String description="";

    public NewsBuletinListDetails(JSONObject jsonObject){
        try {
            this.title=jsonObject.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.image=jsonObject.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.created_at=jsonObject.getString("created_at");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.description=jsonObject.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
