package ee.ttu.spring.rest.domain.common;

import ee.ttu.spring.rest.domain.entity.Book;

import java.io.Serializable;

public class Order implements Serializable {

    private Book book;
    private int amount;

    public Order(Book book, int amount) {
        this.book = book;
        this.amount = amount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
