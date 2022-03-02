//package jm;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Entity
//@Table(name = "user") //,schema = "test", catalog = "test@localhost")
//public class UserEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "id")
//    private long id;
//    @Basic
//    @Column(name = "name")
//    private String name;
//    @Basic
//    @Column(name = "lastName")
//    private String lastName;
//    @Basic
//    @Column(name = "age")
//    private int age;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserEntity that = (UserEntity) o;
//        return id == that.id && age == that.age && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, lastName, age);
//    }
//}
