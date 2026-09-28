package com.potato.billing.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="customers")
public class Customer {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name;
 private String mobile;
 private String address;
 private String gstin;
 private LocalDateTime createdAt=LocalDateTime.now();
 public Long getId(){return id;}
 public String getName(){return name;}
 public void setName(String v){name=v;}
 public String getMobile(){return mobile;}
 public void setMobile(String v){mobile=v;}
 public String getAddress(){return address;}
 public void setAddress(String v){address=v;}
 public String getGstin(){return gstin;}
 public void setGstin(String v){gstin=v;}
 public LocalDateTime getCreatedAt(){return createdAt;}
}
