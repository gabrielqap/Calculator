package imd0412.parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;

public class Main
{
	private static final Exception DateFormatException = null;
	private static final Exception InvalidDataException = null;

	public static void main(String[] args) throws Exception
	{
		final String checkin = "1970.02.30 11:30";
		final String checkout = "2017.04.08 14:35";
		LocalDateTime checkinTime;
		LocalDateTime checkoutTime;
		try
		{
			// Transformar de String para objeto data
			 checkinTime = LocalDateTime.parse(checkin, Constants.DATE_FORMATTER);
			 checkoutTime = LocalDateTime.parse(checkout, Constants.DATE_FORMATTER);
		}
		catch (DateTimeParseException exc)
		{
			System.err.printf("%s is not parsable!%n", checkin);
			throw exc;
		}
		
			System.out.printf("Checkin %s, Checkout %s\n", checkinTime, checkoutTime);
			int year;
			int month;
			int dayOfMonth;
			int hour; 
			int minute;
			
			// Extrair dados do objeto data
			try {
				
			year = checkinTime.getYear();
			if (year < 1970 || year > 2018) {
				throw DateFormatException;
			}
			month = checkinTime.getMonth().getValue();
			if(month < 1 || month > 12) {
				throw DateFormatException;
			}
			dayOfMonth = checkinTime.getDayOfMonth();
			if(dayOfMonth < 1 || dayOfMonth > 31) {
				throw DateFormatException;
			}
			if(dayOfMonth == 30 && month == 2) {
				throw InvalidDataException;
			}
			if(dayOfMonth == 29) {
				if(year % 400 == 0) {
					System.out.println("É bissexto!\n");
				}
				else if((year % 4 == 0) && (year % 100 != 0)) {
					System.out.println("É bissexto!\n");
				}
				else {
					throw InvalidDataException;
				}
			}
			hour = checkinTime.getHour();
			minute = checkinTime.getMinute();

			System.out.printf("Data formatada com os dados extraídos: %d-%d-%d %d:%d\n", year, month, dayOfMonth, hour,
					minute);
			
			// Calcular a diferença entre dois objetos data
						Duration duration = Duration.between(checkinTime, checkoutTime);
						long days = duration.toDays();
						long hours = duration.toHours();
						long minutes = duration.toMinutes();
						System.out.printf("Permanência de: %d dias, ou %d horas, ou %d minutos.\n", days, hours, minutes);
					
			}
			
			finally {
			}
			

		// Exemplo de como identificar inputs fora do padrão
		
	}
}