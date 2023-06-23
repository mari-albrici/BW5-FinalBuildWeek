package team5.EPIC_ENERGY_SERVICES.invoice.repositories;

import java.util.UUID;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import team5.EPIC_ENERGY_SERVICES.invoice.Invoice;
import team5.EPIC_ENERGY_SERVICES.invoice.InvoiceType;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID>{

	Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
	
// -------------------------------------------------------------------
	@Query(value = "SELECT * FROM invoice WHERE type = :invoiceType", nativeQuery = true)
	Page<Invoice> findByType(InvoiceType invoiceType, Pageable pageable);
	
	
/// --------------------------------------------------------------------
	@Query(value = "SELECT * FROM invoice WHERE date = :date", nativeQuery = true)
	Page<Invoice> findByDate(LocalDate date, Pageable pageable);
//	
/// -------------------------------------------------------------------
	@Query(value = "SELECT * FROM invoice WHERE year = :year", nativeQuery = true)
	Page<Invoice> findByYear(int year, Pageable pageable);
	
// -------------------------------------------------------------------
//	@Query(value = "SELECT * FROM invoice WHERE amount BETWEEN = %:initalRange% AND %:finalRange%", nativeQuery = true)
//	Page<Invoice> findByImportRange(double initialRange, double finalRange, Pageable pageable);

}
