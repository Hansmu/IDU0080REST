package ee.ttu.spring.rest.engine;

import ee.ttu.spring.rest.domain.common.Order;
import ee.ttu.spring.rest.domain.common.OrderInfo;
import ee.ttu.spring.rest.domain.entity.Book;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {

    public static final String ORDER_MAKER = "order.maker";

    @JmsListener(destination = ORDER_MAKER)
    @SendTo(OrderHandler.ORDER_RESPONSE)
    public OrderInfo receiveOrderPrice(OrderInfo orderInfo) {
        System.out.println("Order for " + orderInfo.getBooks().size() + " books has been received.");
        Long totalPrice = 0L;

        for (Order order: orderInfo.getBooks()) {
            totalPrice += order.getBook().getBookTitle().length() * 10 * order.getAmount();
        }

        simulateCalculationWait();
        System.out.println("Book total prices: " + totalPrice);
        orderInfo.setTotalPrice(totalPrice);

        return orderInfo;
    }

    private void simulateCalculationWait() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
