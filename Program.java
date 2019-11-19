package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.CarRental;
import entities.Vehicle;
import model.services.BrazilTexService;
import model.services.RentalServices;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String carModel = sc.nextLine();
		
		System.out.println("Pickup (dd/MM/yyyy hh:mm):");
		Date start = sdf.parse(sc.nextLine());
		
		System.out.println("Return (dd/MM/yyyy hh:mm):");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.println("Enter price per hour:");
		double pricePerHour = sc.nextDouble();
		
		System.out.println("Enter price per day:");
		double pricePerDay = sc.nextDouble();
		
		RentalServices rentalServices = new RentalServices(pricePerHour, pricePerDay, new BrazilTexService());
		
		rentalServices .processInvoice(cr);
		
		System.out.println("INVOICE");
		
		System.out.println("Basic Payment " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax" + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total Payment " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		sc.close();
	}

}
