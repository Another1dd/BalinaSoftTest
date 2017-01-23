package com.another1dd.balinasofttest.rest.model;


import com.orm.SugarRecord;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Param extends SugarRecord{

    @Attribute(name="name", required = false)
    private String name;

    @Text
    private String content;




    private Long offerId;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() { return this.name; }
    public void setName(String _value) { this.name = _value; }


}

