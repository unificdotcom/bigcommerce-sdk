package com.bigcommerce.catalog.models;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CouponTypeAdapter extends XmlAdapter<String, CouponType> {


    @Override
    public CouponType unmarshal(String type) throws Exception {
        return CouponType.toEnum(type);
    }

    @Override
    public String marshal(CouponType type) throws Exception {
        return type.toString();
    }

}
