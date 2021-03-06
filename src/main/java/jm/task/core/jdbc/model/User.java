package jm.task.core.jdbc.model;

import javax.persistence.*;
import java.util.Objects;

@Entity // анатация сущность
@Table (name = "User")

public class User {
    @Id //анатация первичного ключа PRIMARY KEY(id)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // значение id генерируется автоматически

    private Long id;

    private String name;

    private String lastName;

    private byte age;

    public User() {

    }

    public User(String name, String lastName, byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id && age == that.age && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, age);
    }

    @Override
    public String toString() {

        return "User{" +
                "id = " + id +
                ", name = " + name + '\'' +
                ", lastName = " + lastName + '\'' +
                ", age = " + age +
                '}';
    }

}