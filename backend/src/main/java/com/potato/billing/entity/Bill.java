package com.potato.billing.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="bills")
public class Bill {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(unique=true,nullable=false) private String billNo;
 @ManyToOne(optional=false) private Customer customer;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal quantityKg=BigDecimal.ZERO;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal ratePerKg=BigDecimal.ZERO;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal total=BigDecimal.ZERO;
 private BigDecimal cgstRate=BigDecimal.ZERO; private BigDecimal sgstRate=BigDecimal.ZERO;
 private BigDecimal cgstAmount=BigDecimal.ZERO; private BigDecimal sgstAmount=BigDecimal.ZERO;
 private BigDecimal previousPending=BigDecimal.ZERO; private BigDecimal advanceAdjusted=BigDecimal.ZERO;
 private BigDecimal paidNow=BigDecimal.ZERO; private BigDecimal netPayable=BigDecimal.ZERO;
 private LocalDateTime billDate=LocalDateTime.now();
 @ElementCollection(fetch=FetchType.EAGER) @OrderColumn(name="line_no") private List<BillItem> items=new ArrayList<>();
 public Long getId(){return id;} public String getBillNo(){return billNo;} public void setBillNo(String v){billNo=v;}
 public Customer getCustomer(){return customer;} public void setCustomer(Customer v){customer=v;}
 public BigDecimal getQuantityKg(){return quantityKg;} public void setQuantityKg(BigDecimal v){quantityKg=v;}
 public BigDecimal getRatePerKg(){return ratePerKg;} public void setRatePerKg(BigDecimal v){ratePerKg=v;}
 public BigDecimal getTotal(){return total;} public void setTotal(BigDecimal v){total=v;}
 public BigDecimal getCgstRate(){return cgstRate;} public void setCgstRate(BigDecimal v){cgstRate=v;}
 public BigDecimal getSgstRate(){return sgstRate;} public void setSgstRate(BigDecimal v){sgstRate=v;}
 public BigDecimal getCgstAmount(){return cgstAmount;} public void setCgstAmount(BigDecimal v){cgstAmount=v;}
 public BigDecimal getSgstAmount(){return sgstAmount;} public void setSgstAmount(BigDecimal v){sgstAmount=v;}
 public BigDecimal getPreviousPending(){return previousPending;} public void setPreviousPending(BigDecimal v){previousPending=v;}
 public BigDecimal getAdvanceAdjusted(){return advanceAdjusted;} public void setAdvanceAdjusted(BigDecimal v){advanceAdjusted=v;}
 public BigDecimal getPaidNow(){return paidNow;} public void setPaidNow(BigDecimal v){paidNow=v;}
 public BigDecimal getNetPayable(){return netPayable;} public void setNetPayable(BigDecimal v){netPayable=v;}
 public LocalDateTime getBillDate(){return billDate;}
 public List<BillItem> getItems(){return items;} public void setItems(List<BillItem> v){items=v;}
}
