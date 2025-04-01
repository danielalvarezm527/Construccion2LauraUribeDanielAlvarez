package app.adapters.orders;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.orders.entity.OrderEntity;
import app.adapters.orders.repository.OrderRepository;
import app.adapters.person.entity.PersonEntity;
import app.adapters.person.repository.PersonRepository;
import app.adapters.pets.entity.PetEntity;
import app.adapters.pets.repository.PetRepository;
import app.adapters.user.entity.UserEntity;
import app.adapters.user.repository.UserRepository;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.OrderPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class OrderAdapter implements OrderPort {

	@Autowired
	private OrderRepository orderRepository;
    
    @Autowired
    private PetRepository petRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Order findByOrderId(long orderId) {
        Optional<OrderEntity> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            return mapToOrder(orderOptional.get());
        }
        return null;
    }

    @Override
    public void save(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(order.getOrderId());
        entity.setMedicine(order.getMedicine());
        entity.setDose(order.getDose());
        entity.setDate(order.getDate());
        entity.setCancelled(order.isCancelled());
        
        // Mapear relaciones
        if (order.getPet() != null) {
            Optional<PetEntity> petOptional = petRepository.findById(order.getPet().getPetId());
            if (petOptional.isPresent()) {
                entity.setPet(petOptional.get());
            }
        }
        
        if (order.getOwner() != null) {
            Optional<PersonEntity> ownerOptional = personRepository.findById(order.getOwner().getDocument());
            if (ownerOptional.isPresent()) {
                entity.setOwner(ownerOptional.get());
            }
        }
        
        if (order.getVeterinarian() != null) {
            Optional<UserEntity> vetOptional = userRepository.findById(order.getVeterinarian().getDocument());
            if (vetOptional.isPresent()) {
                entity.setVeterinarian(vetOptional.get());
            }
        }
        
        orderRepository.save(entity);
        
        order.setOrderId(entity.getOrderId());
    }
    
    private Order mapToOrder(OrderEntity entity) {
        Order order = new Order();
        order.setOrderId(entity.getOrderId());
        order.setMedicine(entity.getMedicine());
        order.setDose(entity.getDose());
        order.setDate(entity.getDate());
        order.setCancelled(entity.isCancelled());
        
        if (entity.getPet() != null) {
            Pet pet = new Pet();
            pet.setPetId(entity.getPet().getPetId());
            pet.setName(entity.getPet().getName());
            pet.setAge(entity.getPet().getAge());
            pet.setSpecies(entity.getPet().getSpecies());
            pet.setRace(entity.getPet().getRace());
            pet.setWeight(entity.getPet().getWeight());
            pet.setCaracteristicas(entity.getPet().getCaracteristicas());
            
            if (entity.getPet().getOwner() != null) {
                Person owner = new Person();
                owner.setDocument(entity.getPet().getOwner().getDocument());
                owner.setName(entity.getPet().getOwner().getName());
                owner.setAge(entity.getPet().getOwner().getAge());
                owner.setRole(entity.getPet().getOwner().getRole());
                pet.setOwner(owner);
            }
            order.setPet(pet);
        }
        
        if (entity.getOwner() != null) {
            Person owner = new Person();
            owner.setDocument(entity.getOwner().getDocument());
            owner.setName(entity.getOwner().getName());
            owner.setAge(entity.getOwner().getAge());
            owner.setRole(entity.getOwner().getRole());
            order.setOwner(owner);
        }
        
        if (entity.getVeterinarian() != null) {
            User vet = new User();
            vet.setDocument(entity.getVeterinarian().getDocument());
            vet.setName(entity.getVeterinarian().getName());
            vet.setAge(entity.getVeterinarian().getAge());
            vet.setRole(entity.getVeterinarian().getRole());
            vet.setUserName(entity.getVeterinarian().getUserName());
            vet.setPassword(entity.getVeterinarian().getPassword());
            order.setVeterinarian(vet);
        }
        
        return order;
    }

}
