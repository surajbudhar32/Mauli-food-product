package com.potato.billing.entity;
import jakarta.persistence.*; import java.math.BigDecimal;
@Entity @Table(name="raw_materials")
public class RawMaterial {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name; private String unit;
 @Column(precision=14,scale=3) private BigDecimal stock=BigDecimal.ZERO; @Column(precision=12,scale=2) private BigDecimal lastPurchaseRate=BigDecimal.ZERO;
 public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;} public String getUnit(){return unit;} public void setUnit(String v){unit=v;} public BigDecimal getStock(){return stock;} public void setStock(BigDecimal v){stock=v;} public BigDecimal getLastPurchaseRate(){return lastPurchaseRate;} public void setLastPurchaseRate(BigDecimal v){lastPurchaseRate=v;}
}
