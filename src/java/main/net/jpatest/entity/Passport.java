package net.jpatest.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity(name="Passport")
@Table(name="tbl_passport")
public class Passport implements Serializable {

  private int passportNumber;
  private String address1,address2,state,country;

  public Passport() {}

  @Id
  @Column(name="passport_num")
  @GeneratedValue(strategy=GenerationType.AUTO)
  public int getPassportNumber() {
    return this.passportNumber;
  }

  public void setPassportNumber(int _passportNumber) {
    this.passportNumber=_passportNumber;
  }

  public void setAddress1(String _address1) {
    this.address1=_address1;
  }

  @Column(name="address1")
  public String getAddress1() {
    return this.address1;
  }

  public void setAddress2(String _address2) {
    this.address2=_address2;
  }

  @Column(name="address2")
  public String getAddress2() {
    return this.address2;
  }

  public void setState(String _state) {
    this.state=_state;
  }

  @Column(name="state_name")
  public String getState() {
    return this.state;
  }

  public void setCountry(String _country) {
    this.country=_country;
  }

  @Column(name="country_name")
  public String getCountry() {
    return this.country;
  }

  public String toString() {
    return "Passport:[passport_num:"+getPassportNumber()+" ,address1:"+getAddress1()+" ,address2:"+getAddress2()+" ,state:"+getState()+" ,country:"+getCountry()+"]"; 
  }
 
}