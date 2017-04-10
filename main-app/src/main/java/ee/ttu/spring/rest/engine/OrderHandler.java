package ee.ttu.spring.rest.engine;

import ee.ttu.spring.rest.domain.common.Order;
import ee.ttu.spring.rest.domain.common.OrderInfo;
import ee.ttu.spring.rest.domain.entity.Book;
import ee.ttu.spring.rest.exception.exceptions.InvalidDataException;
import ee.ttu.spring.rest.utils.Utils;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OrderHandler {

    public static final String ORDER_CHECKER = "order.checker";
    public static final String ORDER_RESPONSE = "order.response";
    public static final String ORDER_RECEIVER = "order.receiver";

    @JmsListener(destination = ORDER_CHECKER)
    @SendTo(OrderFactory.ORDER_MAKER)
    public OrderInfo validateOrder(List<Order> orderedBooks) {
        System.out.println("Order for " + orderedBooks.size() + " books has been received.");

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setBooks(orderedBooks);

        try {
            orderedBooks.forEach(order -> Utils.validateBook(order.getBook()));
            orderInfo.setOk(true);
        } catch (InvalidDataException e) {
            orderInfo.setMessage(e.getMessage());
            orderInfo.setOk(false);
        }

        return orderInfo;
    }

    @JmsListener(destination = ORDER_RESPONSE)
    @SendTo(ORDER_RECEIVER)
    public OrderInfo saveOrder(OrderInfo orderInfo) {
        if (orderInfo.isOk()) {
            System.out.println("Successful order made.");
            orderInfo.setMessage("Order successfully made.");
        } else {
            System.out.println("Order has failed.");
        }

        return orderInfo;
    }
}
