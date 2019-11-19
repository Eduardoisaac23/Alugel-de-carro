package model.services;

import entities.CarRental;
import entities.Invoice;

public class RentalServices {

	private Double pricePerHour;
	private Double pricePerDay;
	private TexSrevice texService;
	
	public RentalServices() {
		
	}

	public RentalServices(Double pricePerHour, Double pricePerDay, TexSrevice texService) {

		super();
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.texService = texService;
	}


	public void processInvoice(CarRental carRental) {
		//getStart = momento em que o carro foi pego
		//getFinish = momento de devolução do veícolo
		//getTime = pega o valor em milesegundos da minha data
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		//achando a diferença em horas
		//t2-t1 vai encontrar a difeênça em milessegundos
		//para converter essa diferênça em milesegundos para seugundos divide-se por 1000
		// par apegar essa diferênça em ssegundos e converter em minutos divide -se por 60
		// par apegar essa diferênça em minutos e converter em horas divide - se por 60
		double hours = (double) (t2-t1)/1000/60/60;
		double basicPayment;
		if(hours <=12.0) {
			basicPayment = Math.ceil(hours) * pricePerHour;
		}
		else {
			//(hours / 24) Horas dividido por 24 para achar a quantidade em dias
			//depois multiplicar por preço por dia
			basicPayment = Math.ceil(hours / 24) * pricePerDay;
		}
		//Para calcular o imposto
		//Aqui calcula o valor do imposto apartir da quantidade .
		double tax =texService.tax(basicPayment);
		
		
		//  criei um novo objeto de pagamento e associei ele com o meu objeto de alugel
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
	
	
	
	
}
