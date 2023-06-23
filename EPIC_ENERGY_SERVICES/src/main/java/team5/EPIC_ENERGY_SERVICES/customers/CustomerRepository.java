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

    @Query(value = "SELECT * FROM customers WHERE annual_turnover >= :annualTurnover", nativeQuery = true)
    Page<Customer> findCustomerByAnnualTurnover(BigDecimal annualTurnover, Pageable pageable);

    @Query(value = "SELECT * FROM customers WHERE added_name ILIKE %:added%", nativeQuery = true)
    Page<Customer> findCustomerByAdded(LocalDate added, Pageable pageable);

    @Query(value = "SELECT * FROM customers WHERE last_contact ILIKE %:lastContact%", nativeQuery = true)
    Page<Customer> findCustomerByLastContact(LocalDate lastContact, Pageable pageable);

    @Query(value = "SELECT * FROM customers WHERE business_name ILIKE %:businessName%", nativeQuery = true)
    Page<Customer> findCustomerByBusinessName(@Param("businessName") String businessName, Pageable pageable);
}
