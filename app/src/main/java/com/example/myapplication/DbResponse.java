package com.example.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DbResponse {
    int page;
    int per_page;

    public ArrayList<DataResponse> getData() {
        return data;
    }

    @SerializedName("data")
    @Expose
    public ArrayList<DataResponse> data = null;

/*    public DataResponse.data[] getData() {
        return data;
    }*/

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    int total;

    public int getTotal_pages() {
        return total_pages;
    }

    int total_pages;

    public DbResponse.ad getAd() {
        return ad;
    }

    ad ad;
    public class ad{
        public String getCompany() {
            return company;
        }

        public String getUrl() {
            return url;
        }

        public String getText() {
            return text;
        }

        String company,url,text;
    }
}
