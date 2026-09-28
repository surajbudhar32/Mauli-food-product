package com.potato.billing.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="employees")
public class Employee {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name; private String mobile; private String role;
 @Column(precision=12,scale=2) private BigDecimal monthlySalary=BigDecimal.ZERO;
 public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;} public String getMobile(){return mobile;} public void setMobile(String v){mobile=v;} public String getRole(){return role;} public void setRole(String v){role=v;} public BigDecimal getMonthlySalary(){return monthlySalary;} public void setMonthlySalary(BigDecimal v){monthlySalary=v;}
}
