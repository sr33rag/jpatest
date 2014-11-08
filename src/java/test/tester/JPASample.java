package tester;

import java.util.List;
import java.util.Iterator;

import javax.persistence.Query;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManagerFactory;

import net.jpatest.entity.Student;
import net.jpatest.entity.Employee;
import net.jpatest.entity.Passport;

public class JPASample {

  private EntityManager em;

  public JPASample() {}

  public static void main(String[] args) {
    JPASample sample=new JPASample();
    sample.getEntityManager();
    sample.doInsert();
    sample.doRead();
	sample.doUpdate();
	sample.doRead();
    System.out.println("Before delete");
    sample.doRead();
    sample.doDelete();
    System.out.println("After delete");
    sample.doRead();
	sample.doUpdateUQ();
	System.out.println("\n Before deleting..");
	sample.doRead();
    sample.doDeleteUQ();
	System.out.println("\nAfter deleting using query..");
	sample.doRead();
	System.out.println("\nOneToOne Example");
	sample.insertRecord();
	System.out.println("After insert:");
	sample.doReadEmployees();
  }

  public void getEntityManager() {
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPATest23PU");
    em=emf.createEntityManager();
  }

  public void doInsert() {
    if(null!=em) {
      EntityTransaction et=em.getTransaction();
      Student stud=new Student();
      stud.setName("Sree");
      stud.setLevel("H");
      et.begin();
      em.persist(stud);
      et.commit();
      System.out.println("The object is persisted...");
    }
  }

  public void doRead() {
    if(null!=em) {
      EntityTransaction readTrans=em.getTransaction();
      readTrans.begin();
      Query query=em.createQuery("SELECT s FROM Student s");
      List<Student> list=query.getResultList();
      Iterator<Student> iterator=list.iterator();
      while(iterator.hasNext()) {
        Student stud=(Student) iterator.next();
        System.out.println(stud);
      }
      readTrans.commit();
    }
  }

  public void retrieveObjects() {
    if(null!=em) {
       Student stud=em.find(Student.class, 1);
       if(null!=stud)
         System.out.println(stud);
       else
          System.out.println("No object found.");
    }
  }

  public void doDelete() {
    if(null!=em) {
      Student stud=em.find(Student.class, 201);
      if(null!=stud) {
        EntityTransaction delTrans=em.getTransaction();
        delTrans.begin();
        em.remove(stud);
        delTrans.commit();
      }
    }
  }

  public void doUpdate() {
    if(null!=em) {
	  EntityTransaction updateTrans=em.getTransaction();	  
	  Student stud=em.find(Student.class, 251);
	  if(stud!=null) {
	    updateTrans.begin();
		stud.setLevel("K");
	    updateTrans.commit();
	  }
	}
  }

  public void doUpdateUQ() {
    if(null!=em) {
	  EntityTransaction updateUQTrans=em.getTransaction();
	  updateUQTrans.begin();
	  Query query=em.createQuery("UPDATE Student s SET s.level='J' WHERE s.id=:id");
	  query.setParameter("id",251);
	  int updateCount=query.executeUpdate();
	  if(updateCount>0)
		System.out.println("Done..");
	  else
		System.out.println("Updation unsucessfull..");
	  updateUQTrans.commit();
	}
  }

  public void doDeleteUQ() {
	int id=1;
    if(null!=em) {
	  EntityTransaction deleteUQTrans=em.getTransaction();
      deleteUQTrans.begin();
	  Query delQry=em.createQuery("DELETE FROM Student s WHERE s.id=:id");
	  delQry.setParameter("id",id);
	  int delCount=delQry.executeUpdate();
	  if(delCount>0)
		System.out.println("Id:"+id+" deleted..");
	  else
		System.out.println("Delete using query unsuccessfull.");
	  deleteUQTrans.commit();
	}
  }

  public void doReadEmployees() {
	if(null!=em) {
	  EntityTransaction readTrans=em.getTransaction();
	  readTrans.begin();
	  Query readQuery=em.createQuery("SELECT e FROM Employee e");
	  List<Employee> employeeList=readQuery.getResultList();
	  Iterator<Employee> empIter=employeeList.iterator();
	  while(empIter.hasNext()) {
	    Employee emp=empIter.next();
		System.out.println(emp);
	  }
	  readTrans.commit();
	}
  }

  public void insertRecord() {
	if(null!=em) {
	  Employee emp=new Employee();
	  emp.setName("Sreeddharan");
	  Passport passportDetails=new Passport();
	  passportDetails.setAddress1("Pombra");
	  passportDetails.setAddress2("Palakkad");
	  passportDetails.setState("Kerala");
	  passportDetails.setCountry("India");
	  emp.setPassportDetails(passportDetails);
	  EntityTransaction saveTrans=em.getTransaction();
	  saveTrans.begin();
      em.persist(emp);
	  saveTrans.commit();
	  System.out.println("Employee details saved..\n");
	}
  }
}
