package model;


import java.util.Comparator;
import java.util.Objects;

public class Book implements Comparable<Book> {
    private final String title;
    private final Integer age;
    private final Integer numberOfPages;

    private Book(Builder builder) {
        this.title = builder.title;
        this.age = builder.age;
        this.numberOfPages = builder.numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private Integer age;
        private Integer numberOfPages;

        private Builder() {
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder numberOfPages(Integer numberOfPages) {
            this.numberOfPages = numberOfPages;
            return this;
        }

        public Book build() {
            if (age == null) {
                throw new IllegalStateException("Год выпуска не может быть пустым");
            }
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalStateException("Название книги не может быть пустым");
            }
            return new Book(this);

        }

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(age, book.age) && Objects.equals(numberOfPages, book.numberOfPages);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", age=" + age +
                ", numberOfPages=" + numberOfPages +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, age, numberOfPages);
    }

    @Override
    public int compareTo(Book o) {
        return Comparator.comparing(Book::getAge)
                .thenComparing(Book::getTitle)
                .thenComparing(Book::getNumberOfPages)
                .compare(this, o);
    }

}
