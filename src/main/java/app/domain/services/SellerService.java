package app.domain.services;

import app.domain.models.Bill;
import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.ports.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Service
public class SellerService {
    @Autowired
    private OrderPort orderPort;

    public Order ConsultOrder(Order order) throws Exception {
        Order  order1 = orderPort.findByOrderId(order.getOrderId());
        if(order1 == null) {
            throw new Exception("no existe una orden con este Numero");
        }
        return order1;
    }
    public void SellMedicine(Order sale) throws Exception {


    }
}
