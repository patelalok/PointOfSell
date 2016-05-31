package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/17/16.
 */
public class VendorDto {

    private int vendorId;
    private String vendorName;
    private String description;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
