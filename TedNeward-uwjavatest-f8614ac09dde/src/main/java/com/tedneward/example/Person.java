 

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
  }

  public int getAge() {
    return age;
  }
  
  /**
   * Ensure that Person.setAge() throws an IllegalArgumentException when passed a value less than zero
   * Done
   */
  public void setAge(int age) {
      if (age < 0) {
          throw new IllegalArgumentException();
      }
      this.age = age;     
  }
  
  
  public String getName() {
    return name;
  }  
  
  /** 
   * Ensure that Person.setName() throws an IllegalArgumentException when passed a null String
   * Done
   */ 
  public void setName(String name) {
      if (name == null) {
          throw new IllegalArgumentException();
      }
      this.name = name;
  }
  
  public double getSalary() {
    return salary;
  }  
  public void setSalary(double salary) {
      this.salary = salary;
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }
  
  /**
   * Create a static method "getNewardFamily" that returns an ArrayList<Person> consisting of four Person objects: Ted, age 41, salary 250000; Charlotte, age 43, salary 150000; Michael, age 22, salary 10000; and Matthew, age 15, salary 0.
   */
  public static List<Person> getNewardFamily() {
      List<Person> family = new ArrayList<Person>();
      family.add(new Person("Ted", 41, 250000));
      family.add(new Person("Charlotte", 43, 150000));
      family.add(new Person("Michael", 22, 10000));
      family.add(new Person("Matthew", 15, 0));
      return family;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  /**
   * Ensure that Person.equals() returns true if two Person instances have the same name and age (salary doesn't factor into equality comparison). Make sure no exceptions are thrown from this method--anything "weird" should just return false.
   * Done
   */  
  @Override
  public boolean equals(Object o) {
      if (o instanceof Person) {
          Person p = (Person) o;
          return (this.name.equals(p.name) && this.age == p.age);
      }
      return false;
  }

  /**
   * Fix toString() so that it returns a String with the Person's name, age, and salary
   * Done
   */
  public String toString() {
    return "[Person name:" + this.name +" age:" + this.age + " salary:" + this.salary + "]";
  }
   
  /**
   * Make Person be Comparable, such that when I compare two Persons, they arrange themselves by salary in reverse order (salary 150000 is less than salary 10000). (Rich people to the front!)
   * Done
   */  
  @Override
  public int compareTo(Person person) {      
      if (this.salary > person.salary) {
          return -1;
      } else if (this.salary < person.salary) {
          return 1;
      }
      return 0;
  }
  
  /**
   * Create an AgeComparator class that compares two Persons and arranges them by age (age 15 is less than age 25). This Comparator MUST BE a nested class inside of Person; Person's fields must remain private.
   * Done
   */
  public static class AgeComparator implements Comparator<Person> {
      public AgeComparator() {
      }
      public int compare(Person a, Person b) {
          return a.age - b.age;
      }
  }
  
  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
