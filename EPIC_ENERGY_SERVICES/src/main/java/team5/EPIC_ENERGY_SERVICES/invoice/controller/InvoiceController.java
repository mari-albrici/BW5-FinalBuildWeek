package team5.EPIC_ENERGY_SERVICES.invoice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
	public Invoice createInvoice(@RequestBody @Validated InvoicePayload inp) {
		return invoiceService.create(inp);
	};

// ------------------------------------------------------------------------------------------------------------------------------------------------

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Invoice findInvoiceById(@PathVariable UUID id) throws Exception {
		return invoiceService.findById(id);
	};

// ------------------------------------------------------------------------------------------------------------------------------------------------
//	
//	@GetMapping("/{:invoiceNumber}")
//	@ResponseStatus(HttpStatus.OK)
//	public Invoice findInvoiceId(@RequestParam String invoiceNumber) throws Exception{
//		return invoiceService.findByInvoiceNumber(invoiceNumber);
//	};

// ------------------------------------------------------------------------------------------------------------------------------------------------

	@PutMapping("/{id}")
	@PostAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public Invoice findAndUpdate(@PathVariable UUID id, @RequestBody InvoicePayload invoice) throws Exception {
		return invoiceService.findByIdAndUpdate(id, invoice);
	};

// ------------------------------------------------------------------------------------------------------------------------------------------------

	@DeleteMapping("/{id}")
	@PostAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInvoice(@PathVariable UUID id) throws Exception {
		invoiceService.findByIdAndDelete(id);
	};
}
