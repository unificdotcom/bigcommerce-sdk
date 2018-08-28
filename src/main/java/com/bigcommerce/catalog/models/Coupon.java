package com.bigcommerce.catalog.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;


/**
 * reference https://developer.bigcommerce.com/api/v2/#coupon-object-properties
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coupon {

    private Integer id;
    private String name;

    @XmlJavaTypeAdapter(CouponTypeAdapter.class)
    @XmlElement(name = "type")
    private CouponType type;

    @XmlElement(name = "amount")
    private BigDecimal amount;

    @XmlElement(name = "min_purchase")
    private BigDecimal minPurchase;

    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlElement(name = "expires")
    private DateTime expires;

    @XmlElement(name = "enabled")
    private boolean enabled;

    @XmlElement(name = "code")
    private String code;

    @XmlElement(name = "applies_to")
    private AppliesTo appliesTo;

    @XmlElement(name = "num_uses")
    private Integer numUses;

    @XmlElement(name = "max_uses")
    private Integer maxUses;

    @XmlElement(name = "max_uses_per_customer")
    private Integer maxUsesPerCustomer;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CouponType getType() {
        return type;
    }

    public void setType(CouponType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getMinPurchase() {
        return minPurchase;
    }

    public void setMinPurchase(BigDecimal minPurchase) {
        this.minPurchase = minPurchase;
    }

    public DateTime getExpires() {
        return expires;
    }

    public void setExpires(DateTime expires) {
        this.expires = expires;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AppliesTo getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(AppliesTo appliesTo) {
        this.appliesTo = appliesTo;
    }

    public Integer getNumUses() {
        return numUses;
    }

    public void setNumUses(Integer numUses) {
        this.numUses = numUses;
    }

    public Integer getMaxUses() {
        return maxUses;
    }

    public void setMaxUses(Integer maxUses) {
        this.maxUses = maxUses;
    }

    public Integer getMaxUsesPerCustomer() {
        return maxUsesPerCustomer;
    }

    public void setMaxUsesPerCustomer(Integer maxUsesPerCustomer) {
        this.maxUsesPerCustomer = maxUsesPerCustomer;
    }
}
