package app.adapters.orders;

import org.springframework.stereotype.Service;

import app.domain.models.Order;
import app.ports.OrderPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class OrderAdapter implements OrderPort {

	@Override
	public Order findByOrderId(long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Order order) {
		// TODO Auto-generated method stub
		
	}

}
