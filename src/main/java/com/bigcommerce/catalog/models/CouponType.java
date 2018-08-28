package com.bigcommerce.catalog.models;

public enum CouponType {
    per_item_discount("per_item_discount"), per_total_discount("per_total_discount"), shipping_discount("shipping_discount"), free_shipping(
            "free_shipping"), percentage_discount("percentage_discount");
    static final String NO_MATCHING_ENUMS_ERROR_MESSAGE = "No matching enum found for %s";
    public final String value;

    CouponType(final String value) {
        this.value = value;
    }

    public static CouponType toEnum(String value) {
        if (per_item_discount.toString().equals(value)) {
            return CouponType.per_item_discount;
        } else if (per_total_discount.toString().equals(value)) {
            return CouponType.per_total_discount;
        } else if (shipping_discount.toString().equals(value)) {
            return CouponType.shipping_discount;
        } else if (free_shipping.toString().equals(value)) {
            return CouponType.free_shipping;
        } else if (percentage_discount.toString().equals(value)) {
            return CouponType.percentage_discount;
        }
        throw new IllegalArgumentException(String.format(NO_MATCHING_ENUMS_ERROR_MESSAGE, value));
    }
}
