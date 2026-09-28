package com.potato.billing.dto;
import java.math.BigDecimal;
import java.util.List;

public record BillRequest(
 String customerName, String mobile, String address, String gstin,
 String hsn, String description, String unit, BigDecimal quantityKg, BigDecimal ratePerKg,
 BigDecimal cgstRate, BigDecimal sgstRate, BigDecimal advanceAdjusted, BigDecimal paidNow,
 List<ItemRequest> items
) {
 public record ItemRequest(String description,String hsn,BigDecimal quantityKg,String unit,BigDecimal ratePerKg) {}
}
