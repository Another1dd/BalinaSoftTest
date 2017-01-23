package com.another1dd.balinasofttest.rest.model;


import org.simpleframework.xml.Attribute;

class Category {

    @Attribute(name="id", required = false)
    private String id;



    public String getId() { return this.id; }
    public void setId(String _value) { this.id = _value; }
}
