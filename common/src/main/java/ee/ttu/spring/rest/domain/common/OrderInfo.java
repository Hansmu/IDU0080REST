package ee.ttu.spring.rest.domain.common;

import ee.ttu.spring.rest.domain.entity.Book;

import java.io.Serializable;
import java.util.List;

public class OrderInfo implements Serializable {
    private boolean isOk;
    private Long totalPrice;
    private String message;
    private List<Order> books;

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Order> getBooks() {
        return books;
    }

    public void setBooks(List<Order> books) {
        this.books = books;
    }
}
