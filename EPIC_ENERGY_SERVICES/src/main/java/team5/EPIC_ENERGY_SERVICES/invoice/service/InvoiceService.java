package team5.EPIC_ENERGY_SERVICES.invoice.service;

import org.springframework.data.domain.Pageable;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import team5.EPIC_ENERGY_SERVICES.invoice.Invoice;
import team5.EPIC_ENERGY_SERVICES.invoice.repositories.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public Invoice create(Invoice in) {

		return invoiceRepository.save(in);
	};
	
	
// ---------------------------------------------------------------------------
	public Invoice findById(UUID id) throws Exception{
		return invoiceRepository.findById(id).orElseThrow(() -> new Exception("invoice not found"));
	};
	

// -----------------------------------------------------------------------------
	public Page<Invoice> find(int page, int size, String sortedBy){
		if (size < 0) {
			size = 10;
		}
		if (size > 50) {
			size = 50;
		}
		Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by(sortedBy));
		
		return invoiceRepository.findAll(pageable);
	}
	
// ---------------------------------------------------------------------------
	public Invoice findByInvoiceNumber(String invoiceNumber) throws Exception{
		return invoiceRepository.findByInvoiceNumber(invoiceNumber).orElseThrow(() -> new Exception("Invoice number not found"));
	};
	
// ---------------------------------------------------------------------------
	public Invoice findAndUpdate(UUID id, Invoice invoice) throws Exception{
		Invoice i = this.findById(id);
		
		i.setYear(invoice.getYear());
		i.setDate(invoice.getDate());
		i.setAmount(invoice.getAmount());
		i.setInvoiceNumber(invoice.getInvoiceNumber());
		i.setType(invoice.getType());
		
		return invoiceRepository.save(i);
	};
	
	
	
// -----------------------------------------------------------------------------
	public void remove(UUID id) throws Exception{
		Invoice i = this.findById(id);
		
		invoiceRepository.delete(i);
	};
}
