package com.bigcommerce;

import com.bigcommerce.catalog.models.AppliesTo;
import com.bigcommerce.catalog.models.Coupon;
import com.bigcommerce.catalog.models.CouponType;
import com.bigcommerce.catalog.models.DateTimeAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BigcommerceSdkTest {

    private static final String SOME_ORDER_ID = "100";
    private static final String SOME_TRACKING_CARRIER = "123";
    private static final String SOME_COMMENTS = "This is a fulfillment from channel ape";
    private static final String SOME_TRACKING_NUMBER = "1Z0398842038";
    private static final String SOME_SHIPPING_PROVIDER = "UPS";
    private static final String SOME_BIN_PICKING_NUMBER = "12345";
    private static final int SOME_QUANTITY = 10;
    private static final String SOME_NAME = "SOME NAME";
    private static final String SOME_LAST_NAME = "Kazokas";
    private static final String SOME_FIRST_NAME = "Ryan";
    private static final String SOME_EMAIL = "rkazokas@channelape.com";
    private static final int SOME_ID = 1;
    private static final String SOME_CURRENCY_EXCHANGE = "Currency Exchange";
    private static final String SOME_CURRENCY_CODE = "USD";
    private static final String SOME_CREDIT_CARD_TYPE = "VISA";
    private static final String SOME_COUPON_DISCOUNT = "Coupon Discount";
    private static final String SOME_DATE_STRING = "Tue, 30 May 2019 17:14:57 +0000";
    private static final BigDecimal SOME_PRICE = new BigDecimal(0.0200);
    private static final String SOME_STORE_HASH = "ajxxno7ktu";
    private static final String SOME_CLIENT_ID = "mnd50eaqydvuab8my48xcqh7z1ftx9v";
    private static final String SOME_ACCESS_TOKEN = "oc7cvqsgtcnr7uadto1cb4w97wrhce2";
    private static final char FORWARD_SLASH = '/';

    public void createCoupon() {

        final String expectedPath = new StringBuilder().append(FORWARD_SLASH).append(SOME_STORE_HASH)
                .append(FORWARD_SLASH).append(BigcommerceSdk.API_VERSION_V2).append(FORWARD_SLASH)
                .append("coupons").toString();

        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(SOME_STORE_HASH)
                .withClientId(SOME_CLIENT_ID).withAccessToken(SOME_ACCESS_TOKEN).build();

        Coupon coupon = new Coupon();
        coupon.setName("Unific API2");
        coupon.setAmount(new BigDecimal(500));
        coupon.setMinPurchase(new BigDecimal(5));
        coupon.setType(CouponType.per_item_discount);
        final DateTimeFormatter formatter = DateTimeFormat.forPattern(DateTimeAdapter.RFC_822_DATE_FORMAT);
        coupon.setExpires(formatter.parseDateTime(SOME_DATE_STRING));
        AppliesTo appliesTo = new AppliesTo();
        appliesTo.setEntity("products");

        List<Integer> idsToApply = new ArrayList<>();
        idsToApply.add(new Integer(77));
        idsToApply.add(new Integer(80));
        appliesTo.setIds(idsToApply);
        coupon.setAppliesTo(appliesTo);
        coupon.setEnabled(true);
        coupon.setCode("CouponCodeApplied2");
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = mapperObj.writeValueAsString(coupon);

        } catch (Exception e) {
            e.printStackTrace();
        }


        final Coupon actualCoupon = bigcommerceSdk.createCoupon(coupon);

    }

}
