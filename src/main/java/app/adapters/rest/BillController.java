package app.adapters.rest;

import app.adapters.rest.dto.BillRequest;
import app.domain.models.Bill;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.ports.BillPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillPort billPort;

    @PostMapping
    public ResponseEntity<?> createBill(@RequestBody BillRequest request) {
        try {
            Pet pet = null;
            if (request.getPetId() != null) {
                pet = new Pet();
                pet.setPetId(request.getPetId());
            }
            Order order = null;
            if (request.getOrderId() != null) {
                order = new Order();
                order.setOrderId(request.getOrderId());
            }
            Bill bill = new Bill();
            bill.setPetId(pet);
            bill.setOrderId(order);
            bill.setProductName(request.getProductName());
            bill.setValue(request.getValue());
            bill.setAmount(request.getAmount());
            bill.setDate(new Timestamp(System.currentTimeMillis()));

            billPort.save(bill);
            return ResponseEntity.ok(bill);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBill(@PathVariable long id) {
        Bill bill = billPort.findByBillId(id);
        if (bill == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<?> getBillsByPet(@PathVariable long petId) {
        List<Bill> bills = billPort.findByPetId(petId);
        if (bills == null || bills.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getBillsByOrder(@PathVariable long orderId) {
        List<Bill> bills = billPort.findByOrderId(orderId);
        if (bills == null || bills.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bills);
    }
}
