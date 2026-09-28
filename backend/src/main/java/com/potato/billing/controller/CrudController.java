package com.potato.billing.controller;
import com.potato.billing.entity.*; import com.potato.billing.repository.*; import org.springframework.web.bind.annotation.*; import java.math.BigDecimal; import java.util.*;
@RestController @RequestMapping("/api") public class CrudController {
 private final EmployeeRepository er; private final SalaryRecordRepository sr; private final RawMaterialRepository rr;
 public CrudController(EmployeeRepository er,SalaryRecordRepository sr,RawMaterialRepository rr){this.er=er;this.sr=sr;this.rr=rr;}
 @GetMapping("/employees") public List<Employee> employees(){return er.findAll();} @PostMapping("/employees") public Employee employee(@RequestBody Employee e){return er.save(e);}
 @GetMapping("/salary") public List<SalaryRecord> salary(){return sr.findAll();} @PostMapping("/salary") public SalaryRecord salary(@RequestBody SalaryRecord s){if(s.getSalary()==null)s.setSalary(s.getEmployee().getMonthlySalary()); return sr.save(s);}
 @GetMapping("/raw-materials") public List<RawMaterial> raw(){return rr.findAll();} @PostMapping("/raw-materials") public RawMaterial raw(@RequestBody RawMaterial r){return rr.save(r);}
 @PutMapping("/raw-materials/{id}/stock") public RawMaterial stock(@PathVariable Long id,@RequestParam BigDecimal quantity,@RequestParam(defaultValue="0") BigDecimal rate){RawMaterial r=rr.findById(id).orElseThrow();r.setStock(r.getStock().add(quantity));r.setLastPurchaseRate(rate);return rr.save(r);}
}
