package team5.EPIC_ENERGY_SERVICES.customers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);
    Page<Customer> findCustomerByAnnualTurnover(BigDecimal annualTurnover, Pageable pageable);

    Page<Customer> findCustomerByAdded(LocalDate added, Pageable pageable);

    Page<Customer> findCustomerByLastContact(LocalDate lastContact, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.businessName LIKE %:businessName%")
    Page<Customer> findCustomerByBusinessName(@Param("businessName") String businessName, Pageable pageable);
}
