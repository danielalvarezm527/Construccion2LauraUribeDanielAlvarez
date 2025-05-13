package app.adapters.rest;

import app.adapters.rest.dto.CancelOrderRequest;
import app.adapters.rest.dto.OrderRequest;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.OrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderPort orderPort;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            Pet pet = new Pet();
            pet.setPetId(request.getPetId());
            Person owner = new Person();
            owner.setDocument(request.getOwnerDocument());
            User veterinarian = new User();
            veterinarian.setDocument(request.getVeterinarianDocument());

            Order order = new Order();
            order.setPet(pet);
            order.setOwner(owner);
            order.setVeterinarian(veterinarian);
            order.setMedicine(request.getMedicine());
            order.setDose(request.getDose());
            order.setDate(new Timestamp(System.currentTimeMillis()));
            order.setCancelled(false);

            orderPort.save(order);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable long id) {
        Order order = orderPort.findByOrderId(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable long id, @RequestBody CancelOrderRequest request) {
        try {
            Order order = orderPort.findByOrderId(id);
            if (order == null) {
                return ResponseEntity.notFound().build();
            }
            User veterinarian = new User();
            veterinarian.setDocument(request.getVeterinarianDocument());
            order.setVeterinarian(veterinarian);
            order.setCancelled(true);
            orderPort.save(order);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
