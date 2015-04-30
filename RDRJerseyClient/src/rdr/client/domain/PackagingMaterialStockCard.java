/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.domain;

/**
 *
 * @author Admin
 */
public class PackagingMaterialStockCard {

    private String itemCode;
    private String rrNo;
    private String itemName;
    private double inventory2;
    private double issuance;
    private double inventory1;
    private double unitCost;
    private double totalCost;
    
    public PackagingMaterialStockCard(){
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getRrNo() {
        return rrNo;
    }

    public void setRrNo(String rrNo) {
        this.rrNo = rrNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getInventory2() {
        return inventory2;
    }

    public void setInventory2(double inventory2) {
        this.inventory2 = inventory2;
    }

    public double getIssuance() {
        return issuance;
    }

    public void setIssuance(double issuance) {
        this.issuance = issuance;
    }

    public double getInventory1() {
        return inventory1;
    }

    public void setInventory1(double inventory1) {
        this.inventory1 = inventory1;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
     public double getBalance(){
        return inventory2 - issuance;
    }
    
    public double getTotalInventory(){
        return inventory1 + getBalance();
    }
    
    
    
}
