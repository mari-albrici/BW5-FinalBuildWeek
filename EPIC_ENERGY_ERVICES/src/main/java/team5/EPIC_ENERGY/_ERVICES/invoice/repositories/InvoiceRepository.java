package team5.EPIC_ENERGY._ERVICES.invoice.repositories;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team5.EPIC_ENERGY._ERVICES.invoice.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID>{

	//Optional<Invoice> findInvoiceNumber(String invoiceNumber);
	
	

}
