package com.supplyflow.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class OrderSuggestion {

    private final Long productId;
    private final Long supplierId;
    private final int recommendedQuantity;

    @JsonCreator
    public OrderSuggestion(
            @JsonProperty("productId") Long productId,
            @JsonProperty("supplierId") Long supplierId,
            @JsonProperty("recommendedQuantity") int recommendedQuantity
    ) {
        this.productId = productId;
        this.supplierId = supplierId;
        this.recommendedQuantity = recommendedQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public int getRecommendedQuantity() {
        return recommendedQuantity;
    }

    @Override
    public String toString() {
        return "OrderSuggestion{" +
                "productId=" + productId +
                ", supplierId=" + supplierId +
                ", recommendedQuantity=" + recommendedQuantity +
                '}';
    }
}