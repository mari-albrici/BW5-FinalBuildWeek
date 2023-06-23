package team5.EPIC_ENERGY_SERVICES.invoice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import team5.EPIC_ENERGY_SERVICES.customers.CustomerRepository;
import team5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import team5.EPIC_ENERGY_SERVICES.invoice.Invoice;
import team5.EPIC_ENERGY_SERVICES.invoice.payload.InvoicePayload;
import team5.EPIC_ENERGY_SERVICES.invoice.repositories.InvoiceRepository;

@Log
@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Invoice create(InvoicePayload in) {

		Invoice newInvoice = new Invoice();
		newInvoice.setYear(in.getYear());
		newInvoice.setDate(in.getDate());
		newInvoice.setAmount(in.getAmount());
		newInvoice.setInvoiceNumber(in.getInvoiceNumber());
		newInvoice.setType(in.getType());
		newInvoice.setCustomerId(customerRepository.findById(in.getCustomerId())
				.orElseThrow(() -> new NotFoundException("customer not found")));

		return invoiceRepository.save(newInvoice);
	};

// ---------------------------------------------------------------------------
	public Invoice findById(UUID id) throws NotFoundException {
		return invoiceRepository.findById(id).orElseThrow(() -> new NotFoundException("invoice not found"));
	};

// -----------------------------------------------------------------------------
	public Page<Invoice> find(int page, int size, String sortedBy) {
		if (size < 0) {
			size = 10;
		}
		if (size > 50) {
			size = 50;
		}
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));

		return invoiceRepository.findAll(pageable);
	}

// ---------------------------------------------------------------------------
	public Invoice findByInvoiceNumber(String invoiceNumber) throws NotFoundException {
		return invoiceRepository.findByInvoiceNumber(invoiceNumber)
				.orElseThrow(() -> new NotFoundException("Invoice number not found"));
	};

// ---------------------------------------------------------------------------
	public Invoice findByIdAndUpdate(UUID id, InvoicePayload invoicePayload) throws NotFoundException {
		Invoice i = this.findById(id);

		i.setYear(invoicePayload.getYear());
		i.setDate(invoicePayload.getDate());
		i.setAmount(invoicePayload.getAmount());
		i.setInvoiceNumber(invoicePayload.getInvoiceNumber());
		i.setType(invoicePayload.getType());
		// i.setCustomerId(in.getCustomerId());

		return invoiceRepository.save(i);
	};

// -----------------------------------------------------------------------------
//	
//	public Page<Invoice> findByType(InvoiceType invoiceType, int page, int size, String sorted){
//		if(invoiceType != null) {
//			Pageable pageable = PageRequest.of(page, size, Sort.by(sorted));
//			return invoiceRepository.findByType(invoiceType, pageable);
//		}else {
//			log.info("put a value that is not null");
//			return Page.empty();
//		}
//	};
//	
//// ------------------------------------------------------------------------------
//	
//	public Page<Invoice> findByDate(LocalDate date, int page, int size, String sorted){
//		
//			Pageable pageable = PageRequest.of(page, size, Sort.by(sorted));
//			return invoiceRepository.findByDate(date, pageable);
//	};
//	
//// -----------------------------------------------------------------------------
//	public Page<Invoice> findByYear(int year, int page, int size, String sorted){
//		if(year > 1900) {
//			Pageable pageable = PageRequest.of(page, size, Sort.by(sorted));
//			return invoiceRepository.findByYear(year, pageable);
//		}else {
//			log.info("put a year greater than 1900");
//			return Page.empty();
//		}
//	};
//	
//	
//// -----------------------------------------------------------------------------
//	public Page<Invoice> findByImportRange(int initialRange, int finalRange, int page, int size, String sorted){
//		if(initialRange >= 0 & finalRange >= 0) {
//			Pageable pageable = PageRequest.of(page, size, Sort.by(sorted));
//			return invoiceRepository.findByImportRange(initialRange, finalRange, pageable);
//		}else {
//			log.info("put an initialRange and a finalRange greater than or equal to 0");
//			return Page.empty();
//		}
//	};	
//	
// -----------------------------------------------------------------------------
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Invoice i = this.findById(id);
		invoiceRepository.delete(i);
	};
}
