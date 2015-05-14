/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maine
 */
@XmlRootElement
public class MBR implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Double batchSize;
    private String batchNo;
    private String unit;
    private Date mfgDate;
    private Date expDate;
    private String poNo;
    private Product productId;
    private PackagingSize packagingSizeId;
    private ProductWithPackSize productWithPackSizeId;

    public MBR(Product productId, PackagingSize packagingSizeId, Double batchSize, String batchNo, String unit, Date mfgDate, Date expDate,
            String poNo) {
        this.productId = productId;
        this.batchSize = batchSize;
        this.batchNo = batchNo;
        this.unit = unit;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
        this.poNo = poNo;
        this.packagingSizeId = packagingSizeId;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Double batchSize) {
        this.batchSize = batchSize;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(Date mfgDate) {
        this.mfgDate = mfgDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public PackagingSize getPackagingSizeId() {
        return packagingSizeId;
    }

    public void setPackagingSizeId(PackagingSize packagingSizeId) {
        this.packagingSizeId = packagingSizeId;
    }

    public ProductWithPackSize getProductWithPackSizeId() {
        return productWithPackSizeId;
    }

    public void setProductWithPackSizeId(ProductWithPackSize productWithPackSizeId) {
        this.productWithPackSizeId = productWithPackSizeId;
    }

    @Override
    public String toString() {
        return "server.mbr.entity.MBR[ id=" + id + " ]";
    }

}
