package team5.EPIC_ENERGY_SERVICES.invoice;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import team5.EPIC_ENERGY_SERVICES.customers.CustomerService;
import team5.EPIC_ENERGY_SERVICES.invoice.payload.InvoicePayload;
import team5.EPIC_ENERGY_SERVICES.invoice.service.InvoiceService;

@Component
public class InvoiceRunner implements CommandLineRunner {

	@Autowired
	InvoiceService inService;

	@Autowired
	CustomerService customerService;

	@Override
	public void run(String... args) throws Exception {

		InvoicePayload i1 = new InvoicePayload(1976, LocalDate.of(1976, 6, 19), 456.78, "9876543210", InvoiceType.PAID);
		
		InvoicePayload i2 = new InvoicePayload(2023, LocalDate.of(2023, 6, 19), 789.01, "2468135790", InvoiceType.ISSUED);
//
//		InvoicePayload i3 = new InvoicePayload(2022, LocalDate.of(2022, 6, 19), 234.56, "1357924680", InvoiceType.TO_PAY);
//
//		InvoicePayload i4 = new InvoicePayload(1985, LocalDate.of(1985, 6, 19), 789.01, "9876543210", InvoiceType.PAID);
//
//		InvoicePayload i5 = new InvoicePayload(2023, LocalDate.of(2023, 6, 19), 123.45, "2468135790", InvoiceType.EXPIRED);
//
//		InvoicePayload i6 = new InvoicePayload(2012, LocalDate.of(2012, 6, 19), 678.90, "1357924680", InvoiceType.TO_PAY);
//
//		InvoicePayload i7 = new InvoicePayload(2001, LocalDate.of(2001, 6, 19), 345.67, "9876543210", InvoiceType.PAID);
//
//		InvoicePayload i8 = new InvoicePayload(2000, LocalDate.of(2000, 6, 19), 901.23, "2468135790", InvoiceType.PAID);
//
//		InvoicePayload i9 = new InvoicePayload(2008, LocalDate.of(2008, 6, 19), 456.78, "1357924680", InvoiceType.ISSUED);
//		
//		InvoicePayload i10 = new InvoicePayload(2023, LocalDate.of(2023, 6, 21), 234.56, "9876543210", InvoiceType.ISSUED);
//		
//		InvoicePayload i11 = new InvoicePayload(1999, LocalDate.of(1999, 6, 19), 789.01, "2468135790", InvoiceType.PAID);
//		
//		InvoicePayload i12 = new InvoicePayload(2020, LocalDate.of(2020, 6, 19), 123.45, "1357924680", InvoiceType.TO_PAY);
//		
//		InvoicePayload i13 = new InvoicePayload(1992, LocalDate.of(1992, 6, 19), 678.90, "9876543210", InvoiceType.PAID);
//		
//		InvoicePayload i14 = new InvoicePayload(2011, LocalDate.of(2011, 6, 19), 345.67, "2468135790", InvoiceType.ISSUED);
//		
//		InvoicePayload i15 = new InvoicePayload(2015, LocalDate.of(2015, 6, 19), 901.23, "1357924680", InvoiceType.TO_PAY);
//		
//		InvoicePayload i16 = new InvoicePayload(2004, LocalDate.of(2004, 6, 19), 456.78, "9876543210", InvoiceType.EXPIRED);
//		
//		InvoicePayload i17 = new InvoicePayload(2019, LocalDate.of(2019, 6, 19), 234.56, "2468135790", InvoiceType.PAID);
//		
//		InvoicePayload i18 = new InvoicePayload(2003, LocalDate.of(2003, 6, 19), 789.01, "1357924680", InvoiceType.ISSUED);
//		
//		InvoicePayload i19 = new InvoicePayload(1978, LocalDate.of(1978, 6, 19), 123.45, "9876543210", InvoiceType.PAID);
//		
//		InvoicePayload i20 = new InvoicePayload(2013, LocalDate.of(2013, 6, 19), 678.90, "2468135790", InvoiceType.TO_PAY);
//		
//		InvoicePayload i21 = new InvoicePayload(1993, LocalDate.of(1993, 6, 19), 345.67, "1357924680", InvoiceType.PAID);
//		
//		InvoicePayload i22 = new InvoicePayload(2023, LocalDate.of(2023, 3, 19), 901.23, "9876543210", InvoiceType.ISSUED);
//		
//		InvoicePayload i23 = new InvoicePayload(2002, LocalDate.of(2002, 6, 19), 456.78, "2468135790", InvoiceType.PAID);
//		
//		InvoicePayload i24 = new InvoicePayload(2011, LocalDate.of(2011, 6, 19), 234.56, "1357924680", InvoiceType.TO_PAY);
//		
//		InvoicePayload i25 = new InvoicePayload(2023, LocalDate.of(2023, 12, 19), 789.01, "9876543210", InvoiceType.ISSUED);
//		
//		InvoicePayload i26 = new InvoicePayload(2002, LocalDate.of(2002, 6, 19), 123.45, "2468135790", InvoiceType.EXPIRED);
//		
//		InvoicePayload i27 = new InvoicePayload(1983, LocalDate.of(1983, 6, 19), 678.90, "1357924680", InvoiceType.PAID);
//		
//		InvoicePayload i28 = new InvoicePayload(2000, LocalDate.of(2000, 6, 19), 345.67, "9876543210", InvoiceType.ISSUED);
//		
//		InvoicePayload i29 = new InvoicePayload(2022, LocalDate.of(2022, 6, 19), 901.23, "2468135790", InvoiceType.PAID);
//		
//		InvoicePayload i30 = new InvoicePayload(1970, LocalDate.of(1970, 6, 19), 456.78, "1357924680", InvoiceType.PAID);
//		
		inService.create(i1);
		inService.create(i2);
//		inService.create(i3);
//		inService.create(i4);
//		inService.create(i5);
//		inService.create(i6);
//		inService.create(i7);
//		inService.create(i8);
//		inService.create(i9);
//		inService.create(i10);
//		inService.create(i11);

	}

}
