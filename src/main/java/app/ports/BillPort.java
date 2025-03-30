package app.ports;

import app.domain.models.Bill;

import java.util.List;

public interface BillPort {
    Bill findByBillId(long billId);
    void save(Bill bill);
    List<Bill> findByOrderId(long orderId);
    List<Bill> findByPetId(long petId);
}