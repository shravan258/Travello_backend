package com.project.travello_backend.RequestEntity;

public class FilterRangeResponse {
    private Integer MaxPrice;
    private Integer MinPrice;


    public FilterRangeResponse() {
    }

    public FilterRangeResponse(Integer maxPrice, Integer minPrice) {
        MaxPrice = maxPrice;
        MinPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return MaxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        MaxPrice = maxPrice;
    }

    public Integer getMinPrice() {
        return MinPrice;
    }

    public void setMinPrice(Integer minPrice) {
        MinPrice = minPrice;
    }

    @Override
    public String toString() {
        return "FilterRangeResponse{" +
                "MaxPrice=" + MaxPrice +
                ", MinPrice=" + MinPrice +
                '}';
    }
}
