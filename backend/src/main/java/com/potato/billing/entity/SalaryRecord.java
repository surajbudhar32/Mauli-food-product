package com.potato.billing.entity;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDate;
@Entity @Table(name="salary_records")
public class SalaryRecord {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private Employee employee; private String month;
 @Column(precision=12,scale=2) private BigDecimal salary; @Column(precision=12,scale=2) private BigDecimal advance=BigDecimal.ZERO; @Column(precision=12,scale=2) private BigDecimal deduction=BigDecimal.ZERO; @Column(precision=12,scale=2) private BigDecimal paid=BigDecimal.ZERO; private LocalDate paidDate;
 public Long getId(){return id;} public Employee getEmployee(){return employee;} public void setEmployee(Employee v){employee=v;} public String getMonth(){return month;} public void setMonth(String v){month=v;} public BigDecimal getSalary(){return salary;} public void setSalary(BigDecimal v){salary=v;} public BigDecimal getAdvance(){return advance;} public void setAdvance(BigDecimal v){advance=v;} public BigDecimal getDeduction(){return deduction;} public void setDeduction(BigDecimal v){deduction=v;} public BigDecimal getPaid(){return paid;} public void setPaid(BigDecimal v){paid=v;} public LocalDate getPaidDate(){return paidDate;} public void setPaidDate(LocalDate v){paidDate=v;}
}
