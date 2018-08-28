package com.bigcommerce.catalog.models;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CouponResponse {

    private Coupon data;

    public Coupon getData() {
        return data;
    }

    public void setData(final Coupon data) {
        this.data = data;
    }
}
