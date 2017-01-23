package com.another1dd.balinasofttest.rest.model;


import org.simpleframework.xml.ElementList;

import java.util.List;

public class Shop {

    @ElementList(name = "categories", required = false)
    private
    List<Category> categories;


    @ElementList(name = "offers", required = false)
    private
    List<Offer> offers;



    public List<Category> getCategories() { return this.categories; }
    public void setCategories(List<Category> _value) { this.categories = _value; }


    public List<Offer> getOffers() { return this.offers; }
    public void setOffers(List<Offer> _value) { this.offers = _value; }
}
