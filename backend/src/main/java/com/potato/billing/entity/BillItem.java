package com.potato.billing.entity;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class BillItem {
    private String description;
    private String hsn;
    private BigDecimal quantityKg;
    private String unit;
    private BigDecimal ratePerKg;
    private BigDecimal total;

    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getHsn(){return hsn;} public void setHsn(String v){hsn=v;}
    public BigDecimal getQuantityKg(){return quantityKg;} public void setQuantityKg(BigDecimal v){quantityKg=v;}
    public String getUnit(){return unit;} public void setUnit(String v){unit=v;}
    public BigDecimal getRatePerKg(){return ratePerKg;} public void setRatePerKg(BigDecimal v){ratePerKg=v;}
    public BigDecimal getTotal(){return total;} public void setTotal(BigDecimal v){total=v;}
}
