package com.example.apiservices;

import android.os.Parcel;
import android.os.Parcelable;

public class Competition implements Parcelable{
    private String id;
    private String name;
    private String area;
    private String code;

    public Competition(String id, String name, String area, String code) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.code = code;
    }

    protected Competition(Parcel in) {
        id = in.readString();
        name = in.readString();
        area = in.readString();
        code = in.readString();
    }

    public static final Creator<Competition> CREATOR = new Creator<Competition>() {
        @Override
        public Competition createFromParcel(Parcel in) {
            return new Competition(in);
        }

        @Override
        public Competition[] newArray(int size) {
            return new Competition[size];
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", area='" + area + '\'' +
                "code='" + code + '\'' +
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
        dest.writeString(area);
        dest.writeString(code);
    }
}
