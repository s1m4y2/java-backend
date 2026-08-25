package com.supplyflow.dto;

import com.supplyflow.model.Supplier;

public class SupplierResponse {

    private final Long id;

    private final String name;

    private final String email;

    private final String phone;


    public SupplierResponse(
            Long id,
            String name,
            String email,
            String phone
    ) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    public static SupplierResponse from(
            Supplier supplier
    ) {

        return new SupplierResponse(
                supplier.getId(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getPhone()
        );
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    public String getPhone() {
        return phone;
    }
}