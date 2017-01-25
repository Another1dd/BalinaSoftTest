package com.another1dd.balinasofttest.rest.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class Offer extends SugarRecord implements Parcelable{

    @Element(name = "url", required = false)
    private String url;


    @Element(name = "name", required = false)
    private String name;


    @Element(name = "price", required = false)
    private String price;


    @Element(name = "description", required = false)
    private String description;


    @Element(name = "picture", required = false)
    private String picture;


    @Element(name = "categoryId", required = false)
    private String categoryId;

    @Ignore
    @ElementList(name = "param", inline = true, required = false)
    private List<Param> param;


    @Ignore
    private String weight;


    @Attribute(name = "id", required = false)
    private Long id;


    public String getUrl() {
        return this.url;
    }

    public void setUrl(String _value) {
        this.url = _value;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String _value) {
        this.name = _value;
    }


    public String getPrice() {
        return this.price;
    }

    public void setPrice(String _value) {
        this.price = _value;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String _value) {
        this.description = _value;
    }


    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String _value) {
        this.picture = _value;
    }


    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String _value) {
        this.categoryId = _value;
    }


    public List<Param> getParam() {
        return param;
    }

    public void setParam(List<Param> param) {
        this.param = param;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long _value) {
        this.id = _value;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
