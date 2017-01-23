package com.another1dd.balinasofttest.rest.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "yml_catalog")
public class Yml {

    @Element(name="shop", required = false)
    private
    Shop shop;


    @Attribute(name="date", required = false)
    private
    String date;



    public Shop getShop() { return this.shop; }
    public void setShop(Shop _value) { this.shop = _value; }


    public String getDate() { return this.date; }
    public void setDate(String _value) { this.date = _value; }



    /*public static class Shop {

        @ElementList(name = "categories", required = false)
        List<Category> categories;


        @ElementList(name = "offers", required = false)
        List<Offer> offers;



        public List<Category> getCategories() { return this.categories; }
        public void setCategories(List<Category> _value) { this.categories = _value; }


        public List<Offer> getOffers() { return this.offers; }
        public void setOffers(List<Offer> _value) { this.offers = _value; }


    }

    public static class Category {

        @Attribute(name="id", required = false)
        String id;



        public String getId() { return this.id; }
        public void setId(String _value) { this.id = _value; }


    }

    public static class Offer {

        @Element(name="url", required = false)
        String url;


        @Element(name="name", required = false)
        String name;


        @Element(name="price", required = false)
        String price;


        @Element(name="description", required = false)
        String description;


        @Element(name="picture", required = false)
        String picture;


        @Element(name="categoryId", required = false)
        String categoryId;


        @Attribute(name="id", required = false)
        String id;


        @ElementList(name = "param", inline = true, required = false)
        List<Param> param;



        public String getUrl() { return this.url; }
        public void setUrl(String _value) { this.url = _value; }


        public String getName() { return this.name; }
        public void setName(String _value) { this.name = _value; }


        public String getPrice() { return this.price; }
        public void setPrice(String _value) { this.price = _value; }


        public String getDescription() { return this.description; }
        public void setDescription(String _value) { this.description = _value; }


        public String getPicture() { return this.picture; }
        public void setPicture(String _value) { this.picture = _value; }


        public String getCategoryId() { return this.categoryId; }
        public void setCategoryId(String _value) { this.categoryId = _value; }


        public String getId() { return this.id; }
        public void setId(String _value) { this.id = _value; }


        public List<Param> getParam() { return this.param; }
        public void setParam(List<Param> _value) { this.param = _value; }


    }

    public static class Param {

        @Attribute(name="name", required = false)
        String name;



        public String getName() { return this.name; }
        public void setName(String _value) { this.name = _value; }


    }*/
}
