package com.example.apiservices;

import android.os.Parcel;
import android.os.Parcelable;

public class Teams implements Parcelable{
    private String id;
    private String name;
    private String web;
    private String fundado;

    public Teams(String id, String name, String web, String fundado) {
        this.id = id;
        this.name = name;
        this.web = web;
        this.fundado = fundado;
    }

    protected Teams(Parcel in) {
        id = in.readString();
        name = in.readString();
        web = in.readString();
        fundado = in.readString();
    }

    public static final Creator<Teams> CREATOR = new Creator<Teams>() {
        @Override
        public Teams createFromParcel(Parcel in) {
            return new Teams(in);
        }

        @Override
        public Teams[] newArray(int size) {
            return new Teams[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getFundado() {
        return fundado;
    }

    public void setFundado(String fundado) {
        this.fundado = fundado;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", web='" + web + '\'' +
                "fundado='" + fundado + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(web);
        dest.writeString(fundado);
    }
}
