package com.potato.billing.service;

import com.potato.billing.dto.BillRequest;
import com.potato.billing.entity.*;
import com.potato.billing.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.*;
import java.util.*;

@Service public class BillService {
 private final BillRepository bills; private final CustomerRepository customers;
 public BillService(BillRepository b,CustomerRepository c){bills=b;customers=c;}
 private BigDecimal n(BigDecimal x){return x==null?BigDecimal.ZERO:x;}
 @Transactional public Bill create(BillRequest r){
  if(r.customerName()==null||r.customerName().isBlank()) throw new IllegalArgumentException("Customer name is required");
  List<BillRequest.ItemRequest> src=r.items()==null||r.items().isEmpty()?List.of(new BillRequest.ItemRequest(r.description(),r.hsn(),r.quantityKg(),r.unit(),r.ratePerKg())):r.items();
  Customer c=customers.findFirstByMobileOrderByIdDesc(r.mobile()).orElseGet(Customer::new);
  c.setName(r.customerName()); c.setMobile(r.mobile()); c.setAddress(r.address()); c.setGstin(r.gstin()); customers.save(c);
  BigDecimal previous=bills.findByCustomerOrderByBillDateAsc(c).stream().reduce(BigDecimal.ZERO,(sum,b)->sum.add(n(b.getNetPayable())),BigDecimal::add);
  Bill b=new Bill(); b.setCustomer(c); b.setPreviousPending(previous.max(BigDecimal.ZERO));
  BigDecimal subtotal=BigDecimal.ZERO;
  List<BillItem> items=new ArrayList<>();
  for(BillRequest.ItemRequest x:src){
   if(x.description()==null||x.description().isBlank()||n(x.quantityKg()).signum()<=0||n(x.ratePerKg()).signum()<0) continue;
   BillItem i=new BillItem(); i.setDescription(x.description()); i.setHsn(x.hsn()); i.setQuantityKg(x.quantityKg()); i.setUnit(x.unit()==null?"KG":x.unit()); i.setRatePerKg(x.ratePerKg()); i.setTotal(x.quantityKg().multiply(x.ratePerKg()).setScale(2,RoundingMode.HALF_UP)); subtotal=subtotal.add(i.getTotal()); items.add(i);
  }
  if(items.isEmpty()) throw new IllegalArgumentException("Select at least one product with quantity and rate");
  b.setItems(items); b.setQuantityKg(items.stream().map(BillItem::getQuantityKg).reduce(BigDecimal.ZERO,BigDecimal::add)); b.setRatePerKg(items.size()==1?items.get(0).getRatePerKg():BigDecimal.ZERO); b.setTotal(subtotal.setScale(2,RoundingMode.HALF_UP));
  b.setCgstRate(n(r.cgstRate())); b.setSgstRate(n(r.sgstRate()));
  b.setCgstAmount(subtotal.multiply(b.getCgstRate()).divide(BigDecimal.valueOf(100),2,RoundingMode.HALF_UP)); b.setSgstAmount(subtotal.multiply(b.getSgstRate()).divide(BigDecimal.valueOf(100),2,RoundingMode.HALF_UP));
  BigDecimal gross=b.getTotal().add(b.getCgstAmount()).add(b.getSgstAmount()); b.setAdvanceAdjusted(n(r.advanceAdjusted())); b.setPaidNow(n(r.paidNow()));
  b.setNetPayable(previous.add(gross).subtract(b.getAdvanceAdjusted()).subtract(b.getPaidNow()).setScale(2,RoundingMode.HALF_UP));
  b.setBillNo("PC-"+String.format("%06d",(bills.count()+1)));
  return bills.save(b);
 }
 public List<Bill> all(){return bills.findTop50ByOrderByBillDateDesc();}
 public Bill one(Long id){return bills.findById(id).orElseThrow();}
 public CustomerSummary summary(String mobile){
  Customer c=customers.findFirstByMobileOrderByIdDesc(mobile).orElse(null); if(c==null)return new CustomerSummary(null,null,BigDecimal.ZERO,BigDecimal.ZERO);
  BigDecimal balance=bills.findByCustomerOrderByBillDateAsc(c).stream().reduce(BigDecimal.ZERO,(sum,b)->sum.add(n(b.getNetPayable())),BigDecimal::add);
  return new CustomerSummary(c.getName(),c.getAddress(),balance.max(BigDecimal.ZERO),balance.min(BigDecimal.ZERO).abs());
 }
 public record CustomerSummary(String name,String address,BigDecimal pending,BigDecimal advance){}
}
