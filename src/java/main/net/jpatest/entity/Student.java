package net.jpatest.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity(name="Student")
@Table(name="Student")
public class Student implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;
  private String name;
  private String level;

  public Student() {}

  public int getId() { return this.id; }

  public void setId(int _id) { this.id=_id; }

  public String getName() { return this.name; }

  @Column(name="name")
  public void setName(String _name) { this.name=_name; }

  public String getLevel() { return this.level; }

  @Column(name="level")
  public void setLevel(String _level) { this.level=_level; }

  public String toString() {
    return "Student["+"Id:"+getId()+", name:"+getName()+", level:"+getLevel()+"]";
  }
}