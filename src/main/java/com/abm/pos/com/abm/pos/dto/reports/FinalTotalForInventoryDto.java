package com.abm.pos.com.abm.pos.dto.reports;

/**
 * Created by asp5045 on 9/21/16.
 */
public class FinalTotalForInventoryDto {

    private int totalQuantity;
    private double totalCost;
    private double totalRetail;
    private double avgMargin;


    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalRetail() {
        return totalRetail;
    }

    public void setTotalRetail(double totalRetail) {
        this.totalRetail = totalRetail;
    }

    public double getAvgMargin() {
        return avgMargin;
    }

    public void setAvgMargin(double avgMargin) {
        this.avgMargin = avgMargin;
    }
}
