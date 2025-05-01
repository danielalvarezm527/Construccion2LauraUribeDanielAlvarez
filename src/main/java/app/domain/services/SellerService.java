package app.domain.services;

import app.domain.models.Bill;
import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.ports.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Service
public class SellerService {
    @Autowired
    private OrderPort orderPort;
    @Autowired
    private BillPort billPort;
    @Autowired
    private PetPort petPort;

    public void SellMedicine(Order order, Bill bill) throws Exception {
        Order existingOrder = orderPort.findByOrderId(order.getOrderId());
        if (existingOrder == null) {
            throw new Exception("La orden especificada no existe");
        }
        
        if (existingOrder.isCancelled()) {
            throw new Exception("No se puede vender medicamentos con una orden cancelada");
        }
        
        Pet pet = petPort.findByPetId(bill.getPetId().getPetId());
        if (pet == null) {
            throw new Exception("La mascota no existe en el sistema");
        }
        
        bill.setOrderId(existingOrder);
        
        bill.setProductName(existingOrder.getMedicine());
        
        bill.setDate(new Timestamp(System.currentTimeMillis()));
        
        
        billPort.save(bill);
    }

    public void sellOtherProduct(Bill bill) throws Exception {
        if (bill.getProductName() == null || bill.getProductName().isEmpty()) {
            throw new Exception("El nombre del producto es obligatorio");
        }
        
        if (bill.getValue() <= 0) {
            throw new Exception("El valor del producto debe ser mayor que cero");
        }
        
        if (bill.getAmount() <= 0) {
            throw new Exception("La cantidad debe ser mayor que cero");
        }
        
        if (bill.getPetId() != null) {
            Pet pet = petPort.findByPetId(bill.getPetId().getPetId());
            if (pet == null) {
                throw new Exception("La mascota no existe en el sistema");
            }
        }
        
        bill.setOrderId(null);
        
        bill.setDate(new Timestamp(System.currentTimeMillis()));
        
        
        billPort.save(bill);
    }
    
    public Bill generateBill(Bill bill) throws Exception {
        
        if (bill.getDate() == null) {
        	bill.setDate(new Timestamp(System.currentTimeMillis()));
        }
        
        billPort.save(bill);
        
        return bill;
    }

    public Order ConsultOrder(Order order) throws Exception {
        Order  order1 = orderPort.findByOrderId(order.getOrderId());
        if(order1 == null) {
            throw new Exception("no existe una orden con este Numero");
        }
        return order1;
    }
}
