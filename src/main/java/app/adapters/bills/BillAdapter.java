package app.adapters.bills;

import java.util.List;

import app.domain.models.Bill;
import app.ports.BillPort;

public class BillAdapter implements BillPort{

	@Override
	public Bill findByBillId(long billId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Bill bill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Bill> findByOrderId(long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> findByPetId(long petId) {
		// TODO Auto-generated method stub
		return null;
	}

}
