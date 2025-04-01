package app.adapters.bills.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.bills.entity.BillEntity;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    List<BillEntity> findByOrderOrderId(long orderId);
    List<BillEntity> findByPetPetId(long petId);
}
