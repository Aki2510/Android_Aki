package model;

/**
 * Created by Aki on 6/19/2017.
 */

public class HotDeal {
    private String dealImage;
    private String dealDate;
    private String dealDiscount;
    private String dealDiscountDescribe;

    public HotDeal(String dealImage, String dealDate, String dealDiscount, String dealDiscountDescribe) {
        this.dealImage = dealImage;
        this.dealDate = dealDate;
        this.dealDiscount = dealDiscount;
        this.dealDiscountDescribe = dealDiscountDescribe;
    }

    public String getDealImage() {
        return dealImage;
    }

    public void setDealImage(String dealImage) {
        this.dealImage = dealImage;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getDealDiscount() {
        return dealDiscount;
    }

    public void setDealDiscount(String dealDiscount) {
        this.dealDiscount = dealDiscount;
    }

    public String getDealDiscountDescribe() {
        return dealDiscountDescribe;
    }

    public void setDealDiscountDescribe(String dealDiscountDescribe) {
        this.dealDiscountDescribe = dealDiscountDescribe;
    }
}
