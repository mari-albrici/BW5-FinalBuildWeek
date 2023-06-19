package service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import invoice.Invoice;
import payload.InvoicePayload;
import repositories.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public Invoice Create(InvoicePayload i) {
		Invoice in = new Invoice(i.getYear(), i.getDate(), i.getAmount() ,i.getInvoiceNumber(), i.getType());
		return invoiceRepository.save(in);
	};
	
	
// ---------------------------------------------------------------------------
	public Invoice findById(UUID id) throws Exception{
		return invoiceRepository.findById(id).orElseThrow(() -> new Exception("invoice not found"));
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
