package team5.EPIC_ENERGY_SERVICES.invoice.controller;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import team5.EPIC_ENERGY_SERVICES.invoice.Invoice;
import team5.EPIC_ENERGY_SERVICES.invoice.InvoiceType;
import team5.EPIC_ENERGY_SERVICES.invoice.payload.InvoicePayload;
import team5.EPIC_ENERGY_SERVICES.invoice.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public Page<Invoice> InvoiceAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortedBy) {
		return invoiceService.find(page, size, sortedBy);
	};

// -------------------------------------------------------------------------------------------------------------------------------------------------

	@PostMapping("")
	@PostAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public Invoice createInvoice(@RequestBody @Validated InvoicePayload invoicePayload) {
		return invoiceService.create(invoicePayload);
	};

// ------------------------------------------------------------------------------------------------------------------------------------------------

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Invoice findInvoiceById(@PathVariable UUID id) throws Exception {
		return invoiceService.findById(id);
	};

// ------------------------------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/{:invoiceNumber}")
	@ResponseStatus(HttpStatus.OK)
	public Invoice findInvoiceId(@PathVariable String invoiceNumber) throws Exception{
		return invoiceService.findByInvoiceNumber(invoiceNumber);
	};
	
	
// ------------------------------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("find/status-{:idStatus}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<Invoice>> findByType(@PathVariable InvoiceType invoiceType, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sorted) {
		Page<Invoice> invoices = invoiceService.findByType(invoiceType, page, size, sorted);
		return ResponseEntity.ok(invoices);
	}
	
// -----------------------------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("find/year-{:idYear}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<Invoice>> findByYear(@PathVariable int year, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sorted) {
		Page<Invoice> invoices = invoiceService.findByYear(year, page, size, sorted);
		return ResponseEntity.ok(invoices);
	}
// ------------------------------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("find/date-{:idDate}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<Invoice>> findByDate(@PathVariable LocalDate date, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sorted) {
		Page<Invoice> invoices = invoiceService.findByDate(date, page, size, sorted);
		return ResponseEntity.ok(invoices);
	}
// ------------------------------------------------------------------------------------------------------------------------------------------------
	
//	@GetMapping("find/import-range-{:idInitialRange}-{:idFinalRange}")
//	@ResponseStatus(HttpStatus.OK)
//	public ResponseEntity<Page<Invoice>> findByImportRange(@PathVariable int initialRange, @PathVariable int finalRange, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sorted) {
//		Page<Invoice> invoices = invoiceService.findByImportRange(initialRange, finalRange, page, size, sorted);
//		return ResponseEntity.ok(invoices);
//	};
// ------------------------------------------------------------------------------------------------------------------------------------------------

	@PutMapping("/{id}")
	@PostAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public Invoice findAndUpdate(@PathVariable UUID id, @RequestBody InvoicePayload invoicePayload) throws Exception{
		return invoiceService.findByIdAndUpdate(id, invoicePayload);
	};

// ------------------------------------------------------------------------------------------------------------------------------------------------

	@DeleteMapping("/{id}")
	@PostAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInvoice(@PathVariable UUID id) throws Exception {
		invoiceService.findByIdAndDelete(id);
	};
}
