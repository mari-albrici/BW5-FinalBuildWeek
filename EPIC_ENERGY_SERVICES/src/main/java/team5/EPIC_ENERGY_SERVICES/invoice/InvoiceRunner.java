package team5.EPIC_ENERGY_SERVICES.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import team5.EPIC_ENERGY_SERVICES.customers.CustomerService;
import team5.EPIC_ENERGY_SERVICES.invoice.service.InvoiceService;

import java.time.LocalDate;

@Component
public class InvoiceRunner implements CommandLineRunner {

	@Autowired
	InvoiceService inService;

	@Autowired
	CustomerService customerService;

	@Override
	public void run(String... args) throws Exception {
		
		Invoice i1 = new Invoice(1976, LocalDate.of(1976, 6, 19), 456.78, "9876543210", InvoiceType.PAID);
		
		Invoice i2 = new Invoice(2023, LocalDate.of(2023, 6, 19), 789.01, "2468135790", InvoiceType.ISSUED);

		Invoice i3 = new Invoice(2022, LocalDate.of(2022, 6, 19), 234.56, "1357924680", InvoiceType.TO_PAY);

		Invoice i4 = new Invoice(1985, LocalDate.of(1985, 6, 19), 789.01, "9876543210", InvoiceType.PAID);

		Invoice i5 = new Invoice(2023, LocalDate.of(2023, 6, 19), 123.45, "2468135790", InvoiceType.EXPIRED);

		Invoice i6 = new Invoice(2012, LocalDate.of(2012, 6, 19), 678.90, "1357924680", InvoiceType.TO_PAY);

		Invoice i7 = new Invoice(2001, LocalDate.of(2001, 6, 19), 345.67, "9876543210", InvoiceType.PAID);

		Invoice i8 = new Invoice(2000, LocalDate.of(2000, 6, 19), 901.23, "2468135790", InvoiceType.PAID);

		Invoice i9 = new Invoice(2008, LocalDate.of(2008, 6, 19), 456.78, "1357924680", InvoiceType.ISSUED);
//		
//		Invoice i10 = new Invoice(2023, LocalDate.of(2023, 6, 21), 234.56, "9876543210", InvoiceType.ISSUED);
//		
//		Invoice i11 = new Invoice(1999, LocalDate.of(1999, 6, 19), 789.01, "2468135790", InvoiceType.PAID);
//		
//		Invoice i12 = new Invoice(2020, LocalDate.of(2020, 6, 19), 123.45, "1357924680", InvoiceType.TO_PAY);
//		
//		Invoice i13 = new Invoice(1992, LocalDate.of(1992, 6, 19), 678.90, "9876543210", InvoiceType.PAID);
//		
//		Invoice i14 = new Invoice(2011, LocalDate.of(2011, 6, 19), 345.67, "2468135790", InvoiceType.ISSUED);
//		
//		Invoice i15 = new Invoice(2015, LocalDate.of(2015, 6, 19), 901.23, "1357924680", InvoiceType.TO_PAY);
//		
//		Invoice i16 = new Invoice(2004, LocalDate.of(2004, 6, 19), 456.78, "9876543210", InvoiceType.EXPIRED);
//		
//		Invoice i17 = new Invoice(2019, LocalDate.of(2019, 6, 19), 234.56, "2468135790", InvoiceType.PAID);
//		
//		Invoice i18 = new Invoice(2003, LocalDate.of(2003, 6, 19), 789.01, "1357924680", InvoiceType.ISSUED);
//		
//		Invoice i19 = new Invoice(1978, LocalDate.of(1978, 6, 19), 123.45, "9876543210", InvoiceType.PAID);
//		
//		Invoice i20 = new Invoice(2013, LocalDate.of(2013, 6, 19), 678.90, "2468135790", InvoiceType.TO_PAY);
//		
//		Invoice i21 = new Invoice(1993, LocalDate.of(1993, 6, 19), 345.67, "1357924680", InvoiceType.PAID);
//		
//		Invoice i22 = new Invoice(2023, LocalDate.of(2023, 3, 19), 901.23, "9876543210", InvoiceType.ISSUED);
//		
//		Invoice i23 = new Invoice(2002, LocalDate.of(2002, 6, 19), 456.78, "2468135790", InvoiceType.PAID);
//		
//		Invoice i24 = new Invoice(2011, LocalDate.of(2011, 6, 19), 234.56, "1357924680", InvoiceType.TO_PAY);
//		
//		Invoice i25 = new Invoice(2023, LocalDate.of(2023, 12, 19), 789.01, "9876543210", InvoiceType.ISSUED);
//		
//		Invoice i26 = new Invoice(2002, LocalDate.of(2002, 6, 19), 123.45, "2468135790", InvoiceType.EXPIRED);
//		
//		Invoice i27 = new Invoice(1983, LocalDate.of(1983, 6, 19), 678.90, "1357924680", InvoiceType.PAID);
//		
//		Invoice i28 = new Invoice(2000, LocalDate.of(2000, 6, 19), 345.67, "9876543210", InvoiceType.ISSUED);
//		
//		Invoice i29 = new Invoice(2022, LocalDate.of(2022, 6, 19), 901.23, "2468135790", InvoiceType.PAID);
//		
//		Invoice i30 = new Invoice(1970, LocalDate.of(1970, 6, 19), 456.78, "1357924680", InvoiceType.PAID);
//		
		inService.create(i1);
		inService.create(i2);
		inService.create(i3);
		inService.create(i4);
		inService.create(i5);
		inService.create(i6);
		inService.create(i7);
		inService.create(i8);
		inService.create(i9);
//		inService.create(i10);
//		inService.create(i11);

	}

}
