package com.supplyflow.dto;

import com.supplyflow.model.Product;

public class ProductResponse {

    private final Long id;

    private final String name;

    private final Double price;

    private final Integer stockQuantity;

    private final Integer minimumStockLevel;

    private final boolean stockCritical;

    private final Long supplierId;

    private final String supplierName;


    public ProductResponse(
            Long id,
            String name,
            Double price,
            Integer stockQuantity,
            Integer minimumStockLevel,
            boolean stockCritical,
            Long supplierId,
            String supplierName
    ) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.minimumStockLevel = minimumStockLevel;
        this.stockCritical = stockCritical;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }


    public static ProductResponse from(
            Product product
    ) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getMinimumStockLevel(),
                product.isStockCritical(),
                product.getSupplier().getId(),
                product.getSupplier().getName()
        );
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Double getPrice() {
        return price;
    }


    public Integer getStockQuantity() {
        return stockQuantity;
    }


    public Integer getMinimumStockLevel() {
        return minimumStockLevel;
    }


    public boolean isStockCritical() {
        return stockCritical;
    }


    public Long getSupplierId() {
        return supplierId;
    }


    public String getSupplierName() {
        return supplierName;
    }
}