package com.bigcommerce.catalog.models;


import java.util.List;

public class AppliesTo {

    private String entity;
    private List<Integer> ids;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
