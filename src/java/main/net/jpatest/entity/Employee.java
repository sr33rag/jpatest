package net.jpatest.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity(name="Employee")
@Table(name="tbl_employee")
public class Employee implements Serializable {
 
  private int employeeId;
  private String name;
  private Passport passportDetails;

  public Employee() {}

  public void setEmployeeId(int _employeeId) {
    this.employeeId=_employeeId;
  }

  @Id
  @Column(name="emp_id")
  @GeneratedValue(strategy=GenerationType.AUTO)
  public int getEmployeeId() {
    return this.employeeId;
  }

  public void setName(String _name) {
    this.name=_name;
  }

  @Column(name="emp_name")
  public String getName() {
    return this.name; 
  }

  public void setPassportDetails(Passport _passportDetails) {
    this.passportDetails=_passportDetails;
  }

  @OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
  @JoinColumn(name="passport_num")
  public Passport getPassportDetails() {
    return this.passportDetails;
  }

  public String toString() {
    return "Employee[empid:"+getEmployeeId()+" ,name:"+getName()+" ,passportDetails:\n\t"+getPassportDetails()+"\n\t]";
  }
}