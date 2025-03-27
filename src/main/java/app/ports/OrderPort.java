package app.ports;

import app.domain.models.Order;

public interface OrderPort {

    Order findByOrderId(long orderId);
    void save(Order order);
}
