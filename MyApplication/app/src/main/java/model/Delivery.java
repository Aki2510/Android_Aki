package model;

/**
 * Created by Aki on 6/14/2017.
 */

public class Delivery {
    private String tvName;
    private String tvAddress;
    private String tvCountry;
    private String ivImage;
    private String tvPriceRange;


    public Delivery(String tvName, String tvAddress, String tvCountry, String ivImage) {
        this.tvName = tvName;
        this.tvAddress = tvAddress;
        this.tvCountry = tvCountry;
        this.ivImage = ivImage;
    }

    public Delivery(String tvPriceRange) {
        this.tvPriceRange = tvPriceRange;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getTvAddress() {
        return tvAddress;
    }

    public void setTvAddress(String tvAddress) {
        this.tvAddress = tvAddress;
    }

    public String gettvCountry() {
        return tvCountry;
    }

    public void settvCountry(String tvCountry) {
        this.tvCountry = tvCountry;
    }

    public String getIvImage() {
        return ivImage;
    }

    public void setIvImage(String ivImage) {
        this.ivImage = ivImage;
    }

    public String getTvCountry() {
        return tvCountry;
    }

    public void setTvCountry(String tvCountry) {
        this.tvCountry = tvCountry;
    }

    public String getTvPriceRange() {
        return tvPriceRange;
    }

    public void setTvPriceRange(String tvPriceRange) {
        this.tvPriceRange = tvPriceRange;
    }
}
