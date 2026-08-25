package com.supplyflow.model;

public class OrderSuggestion {

    private final Long productId;
    private final Long supplierId;
    private final int recommendedQuantity;

    public OrderSuggestion(
            Long productId,
            Long supplierId,
            int recommendedQuantity
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