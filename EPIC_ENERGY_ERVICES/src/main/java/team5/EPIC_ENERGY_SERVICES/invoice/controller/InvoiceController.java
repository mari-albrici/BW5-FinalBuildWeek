package team5.EPIC_ENERGY_SERVICES.invoice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
public class InvoiceController {
	
	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public Page<Invoice> InvoiceAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sorted){
		return invoiceService.find(page, size, sorted);
	};
	

// -------------------------------------------------------------------------------------------------------------------------------------------------
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Invoice createInvoice(@RequestBody @Validated InvoicePayload inp) {
		return invoiceService.create(inp);
	};
	
	
// ------------------------------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/{:id}")
	@ResponseStatus(HttpStatus.OK)
	public Invoice findInvoiceId(@RequestParam UUID id) throws Exception{
		return invoiceService.findById(id);
	};
	
// ------------------------------------------------------------------------------------------------------------------------------------------------
//	
//	@GetMapping("/{:invoiceNumber}")
//	@ResponseStatus(HttpStatus.OK)
//	public Invoice findInvoiceId(@RequestParam String invoiceNumber) throws Exception{
//		return invoiceService.findInvoiceNumber(invoiceNumber);
//	};
	
// ------------------------------------------------------------------------------------------------------------------------------------------------
	
	@PutMapping("/{:id}")
	@ResponseStatus(HttpStatus.OK)
	public Invoice findAndUpdate(@PathVariable UUID id, @RequestBody Invoice invoice) throws Exception{
		return invoiceService.findAndUpdate(id, invoice);
	};
	
	
// ------------------------------------------------------------------------------------------------------------------------------------------------
	
	@DeleteMapping("/{:id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInvoice(@PathVariable UUID id) throws Exception{
		invoiceService.remove(id);
	};
}
