package imd0412.parkinglot.calculator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import imd0412.parkinglot.Constants;
import imd0412.parkinglot.ParkingLotType;

public class Calculator {
	private static final Exception DateFormatException = null;
	private static final Exception InvalidDataException = null;
	Boolean bissexto;			/* Boolean para checar se os dois anos são bissextos. */
	Boolean bissexto2;
	/**
	 * Calculates the staying cost in the parking lot.
	 * 
	 * @param checkin
	 *            String representing check-in date. String follows the format
	 *            "yyyy.MM.dd HH:mm".
	 * @param checkout
	 *            String representing check-out date. String follows the format
	 *            "yyyy.MM.dd HH:mm".
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	Float calculateParkingCost(String checkin, String checkout,
			ParkingLotType type) throws Exception {
		
		LocalDateTime checkinTime;
		LocalDateTime checkoutTime;
		this.bissexto = false;
		this.bissexto2 = false;
		try
		{
			if ((checkin.contains("02.30")) || (checkout.contains("02.30")) || (checkin.contains("02.31")) || (checkout.contains("02.31"))) {
				throw InvalidDataException;
			}
			if(checkin.contains("02.29")) {
				this.bissexto = true;
			}
			if(checkout.contains("02.29")) {
				this.bissexto2 = true;
			}
			// Transformar de String para objeto data
			 checkinTime = LocalDateTime.parse(checkin, Constants.DATE_FORMATTER);
			 checkoutTime = LocalDateTime.parse(checkout, Constants.DATE_FORMATTER);
		}
		catch (DateTimeParseException exc)
		{
		//	System.err.printf("%s is not parsable!%n", checkin);
			throw exc;
		}
		
		//	System.out.printf("Checkin %s, Checkout %s\n", checkinTime, checkoutTime);
			
			int year;
			int year2;
			int month;
			int dayOfMonth;
			int hour; 
			int minute;
			
			// Extrair dados do objeto data
			try {
				
			year = checkinTime.getYear();
			year2 = checkoutTime.getYear();
			if (year < 1970 || year > 2018 || year2 < 1970 || year2 > 2018) {
				throw DateFormatException;
			}
			
			if(checkoutTime.isBefore(checkinTime)) {
				throw InvalidDataException;    
			}
			
			month = checkinTime.getMonth().getValue();
			dayOfMonth = checkinTime.getDayOfMonth();
			
			if(bissexto) {
				if(year % 400 == 0) {
				}
				else if((year % 4 == 0) && (year % 100 != 0)) {
				}
				else {
					throw InvalidDataException;
				}
			}
			
			if(bissexto2) {
				if(year2 % 400 == 0) {
				}
				else if((year2 % 4 == 0) && (year2 % 100 != 0)) {
				}
				else {
					throw InvalidDataException;
				}
			}
			hour = checkinTime.getHour();
			minute = checkinTime.getMinute();

			// Calcular a diferença entre dois objetos data
			Duration duration = Duration.between(checkinTime, checkoutTime);
			long days = duration.toDays();
			long hours = duration.toHours();
			long minutes = duration.toMinutes();
		//	System.out.printf("Permanência de: %d dias, ou %d horas, ou %d minutos.\n", days, hours, minutes);
			
			minutes = minutes % 60;
			hours = hours  % 24;
						
		//	System.out.printf("Data formatada com os dados extraídos: %d-%d-%d %d:%d\n", year, month, dayOfMonth, hour,
		//						minute);
			if(type.equals(ParkingLotType.ShortTerm)) {
				
				return calculateShortTerm(days,hours,minutes);
			}
			if (type.equals(ParkingLotType.LongTerm)) {
				
				return calculateLongTerm(days,hours,minutes);
			}
			if(type.equals(ParkingLotType.VIP)) {
				
				return calculateVIP(days,hours,minutes);
			}
			
	}
			finally {
			}
			
			
		return null;
	}
	
	Float calculateShortTerm(long days, long hours, long minutes) {
		if(days == 0 && hours == 0 && minutes == 0) {
			return (float) 8.0;
		}
		if(minutes == 0) {
			if(hours == 1 && days ==0) {
				return (float) 8.0;
			}
			if(hours <= 23 && days == 0) {
				return (float) 8.0 + ((hours - 1)*2); 
			}
			
			if(days == 1 && hours == 0) {
				return (float) 8.0 + (23*2);
			}

			hours = hours + (days * 24);
			
			if(days <= 7) {
				return (float) 8.0 + ((hours - 1)*2) + (50*days);
			}
			else {
				return (float) 8.0 + ((hours - 1)*2) + (50*7) + (30*(days-7));
			}
		}
		
		else {
			if(hours < 24 && days == 0) {
				return (float) 8.0 + (hours*2);
			}
			hours = hours + (days*24);
			
			if(days <= 7) {
				return (float) 8.0 + (hours*2) + (50*days);
			}
			
			else {
				return (float) 8.0 + (hours*2) + (50*7) + (30*(days-7));
			}
			
		}
	}
	
	Float calculateLongTerm(long days, long hours, long minutes) {
		if(days == 0 && hours == 0 && minutes == 0) {
			return (float) 70.0;
		}
		if(hours == 0 && minutes == 0) {
			if (days == 1) {
				return (float) 70.0;
			}
			if(days <= 7) {
				return (float) 70.0 + ((days-1)*50);
			}
			if(days < 30) {
				return (float) 70.0 + (6*50) + ((days-7)*30);
			}
			else {
				long mes = days/30;
				return (float) 70.0 + (6*50) + ((days-7)*30) + (500*mes);
			}
		}
		
		else {
			if(days == 0) {
				return (float) 70.0;
			}
			if(days < 7) {
					return (float) 70.0 + (days*50);
			}
			if(days < 30) {
					return (float) 70.0 + (6*50) + ((days-6)*30);
			}
			else {
				long mes = days/30;
				return (float) 70.0 + (6*50) + ((days-6)*30) + (500*mes);
			}
			
		}
	}
	
	Float calculateVIP(long days, long hours, long minutes) {
		if(days == 0 && hours == 0 && minutes == 0) {
			return (float) 500.0;
		}
		if(minutes == 0 && hours == 0) {
			if(days <= 7) {
				return (float) 500.0;
			}
			if(days <= 14) {
				return (float) 500.0 + (100 * (days-7));
			}
			else {
				return (float) 500.0 + (100*7) + (80*(days-14));
			}
		}
		
		else {
			if(days < 7) {
				return (float) 500.0;
			}
			if(days < 14) {
				return (float) 500.0 + (100* (days-6));
			}
			else {
				return (float) 500.0 + (100*7) + (80*(days-13));
			}
			
		}
	}
	
}
