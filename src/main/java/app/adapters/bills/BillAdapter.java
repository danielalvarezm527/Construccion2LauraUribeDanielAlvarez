package app.adapters.bills;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.bills.entity.BillEntity;
import app.adapters.bills.repository.BillRepository;
import app.adapters.orders.entity.OrderEntity;
import app.adapters.orders.repository.OrderRepository;
import app.adapters.pets.entity.PetEntity;
import app.adapters.pets.repository.PetRepository;
import app.domain.models.Bill;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.ports.BillPort;

@Service
public class BillAdapter implements BillPort{

	@Autowired
	private BillRepository billRepository;
    
    @Autowired
    private PetRepository petRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Bill findByBillId(long billId) {
        Optional<BillEntity> billOptional = billRepository.findById(billId);
        if (billOptional.isPresent()) {
            return mapToBill(billOptional.get());
        }
        return null;
    }

    @Override
    public void save(Bill bill) {
        BillEntity entity = new BillEntity();

        if (bill.getBillId() > 0) {
            entity.setBillId(bill.getBillId());
        }
        
        entity.setBillId(bill.getBillId());
        entity.setProductName(bill.getProductName());
        entity.setValue(bill.getValue());
        entity.setAmount(bill.getAmount());
        entity.setDate(bill.getDate());
        
        // Mapear relaciones
        if (bill.getPetId() != null) {
            Optional<PetEntity> petOptional = petRepository.findById(bill.getPetId().getPetId());
            if (petOptional.isPresent()) {
                entity.setPet(petOptional.get());
            }
        }
        
        if (bill.getOrderId() != null) {
            Optional<OrderEntity> orderOptional = orderRepository.findById(bill.getOrderId().getOrderId());
            if (orderOptional.isPresent()) {
                entity.setOrder(orderOptional.get());
            }
        }
        
        billRepository.save(entity);
        
        bill.setBillId(entity.getBillId());
    }

    @Override
    public List<Bill> findByOrderId(long orderId) {
        List<BillEntity> entities = billRepository.findByOrderOrderId(orderId);
        List<Bill> bills = new ArrayList<>();
        
        for (BillEntity entity : entities) {
            bills.add(mapToBill(entity));
        }
        
        return bills;
    }

    @Override
    public List<Bill> findByPetId(long petId) {
        List<BillEntity> entities = billRepository.findByPetPetId(petId);
        List<Bill> bills = new ArrayList<>();
        
        for (BillEntity entity : entities) {
            bills.add(mapToBill(entity));
        }
        
        return bills;
    }
    
    private Bill mapToBill(BillEntity entity) {
        Bill bill = new Bill();
        bill.setBillId(entity.getBillId());
        bill.setProductName(entity.getProductName());
        bill.setValue(entity.getValue());
        bill.setAmount(entity.getAmount());
        bill.setDate(entity.getDate());
        
        //Aca no estoy seguro si puedan faltar mas cambios
        if (entity.getPet() != null) {
            Pet pet = new Pet();
            pet.setPetId(entity.getPet().getPetId());
            pet.setName(entity.getPet().getName());
            bill.setPetId(pet);
        }
        
        //Aca no estoy seguro si puedan faltar mas cambios
        if (entity.getOrder() != null) {
            Order order = new Order();
            order.setOrderId(entity.getOrder().getOrderId());
            bill.setOrderId(order);
        }
        
        return bill;
    }

}
