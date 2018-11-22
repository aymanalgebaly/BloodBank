package com.example.android.fa3el5eer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestModel implements Parcelable{

    String name,hospital,city,blood,address_hospital,details,age,bags,phone_number;


    public RequestModel() {
    }

    public RequestModel(String name, String hospital, String city,
                        String blood, String address_hospital, String details, String age, String bags, String phone_number) {
        this.name = name;
        this.hospital = hospital;
        this.city = city;
        this.blood = blood;
        this.address_hospital = address_hospital;
        this.details = details;
        this.age = age;
        this.bags = bags;
        this.phone_number = phone_number;
    }


    protected RequestModel(Parcel in) {
        name = in.readString();
        hospital = in.readString();
        city = in.readString();
        blood = in.readString();
        address_hospital = in.readString();
        details = in.readString();
        age = in.readString();
        bags = in.readString();
        phone_number = in.readString();
    }

    public static final Creator<RequestModel> CREATOR = new Creator<RequestModel>() {
        @Override
        public RequestModel createFromParcel(Parcel in) {
            return new RequestModel(in);
        }

        @Override
        public RequestModel[] newArray(int size) {
            return new RequestModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getAddress_hospital() {
        return address_hospital;
    }

    public void setAddress_hospital(String address_hospital) {
        this.address_hospital = address_hospital;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBags() {
        return bags;
    }

    public void setBags(String bags) {
        this.bags = bags;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(hospital);
        dest.writeString(city);
        dest.writeString(blood);
        dest.writeString(address_hospital);
        dest.writeString(details);
        dest.writeString(age);
        dest.writeString(bags);
        dest.writeString(phone_number);
    }
}
