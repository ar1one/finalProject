package model;

import java.util.Comparator;
import java.util.Objects;

public class Person implements Comparable<Person>{
    private final Integer id;
    private final String name;
    private final Integer age;
    private final Boolean isStudent;

    private Person(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.isStudent = builder.isStudent;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getStudent() {
        return isStudent;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String name;
        private Integer age = 0;
        private Boolean isStudent = false;

        private Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder isStudent(Boolean isStudent) {
            this.isStudent = isStudent;
            return this;
        }

        public Person build() {
            if (age == null) {
                throw new IllegalStateException("Возраст не может быть равным нулю");
            }
            if (id == null) {
                throw new IllegalStateException("ID не может быть равным нулю");
            }
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalStateException("Имя не может быть пустым");
            }
            return new Person(this);
        }


    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isStudent=" + isStudent +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return Comparator.comparing(Person::getAge)
                .thenComparing(Person::getName)
                .thenComparing(Person::getId)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(age, person.age) && Objects.equals(isStudent, person.isStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, isStudent);
    }
}
