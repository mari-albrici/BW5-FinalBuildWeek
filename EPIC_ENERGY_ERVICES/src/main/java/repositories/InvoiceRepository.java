package repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import invoice.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID>{

}
