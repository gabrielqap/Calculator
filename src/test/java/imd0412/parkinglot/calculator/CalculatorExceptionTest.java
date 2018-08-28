package imd0412.parkinglot.calculator;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import imd0412.parkinglot.ParkingLotType;
import imd0412.parkinglot.exception.DateFormatException;
@RunWith(Parameterized.class)
public class CalculatorExceptionTest {
	@Rule 
	public ExpectedException thrown = ExpectedException.none();
	
	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(
				new Object[][] {
					{"1970.04.08 14:30min", "1970.04.08 15:35min", ParkingLotType.ShortTerm, DateTimeParseException.class},
					{"1970.13.08 14:30", "1970.13.08 15:35", ParkingLotType.ShortTerm, DateTimeParseException.class},
					{"1970.04.32 14:30", "1970.04.32 15:35", ParkingLotType.ShortTerm, DateTimeParseException.class},
					{"1970.04.00 14:30", "1970.04.00 15:35", ParkingLotType.ShortTerm, DateTimeParseException.class},
					{"1970.02.30 14:30", "1970.02.30 15:35", ParkingLotType.ShortTerm, NullPointerException.class},
					{"1971.02.29 14:30", "1971.02.29 15:35", ParkingLotType.ShortTerm, NullPointerException.class},
					{"2000.02.29 14:30", "2001.02.29 15:35", ParkingLotType.ShortTerm, NullPointerException.class},
					{"2000.02.31 14:30", "2001.02.31 15:35", ParkingLotType.ShortTerm, NullPointerException.class},
					{"2001.02.10 10:50", "2000.02.10 10:50", ParkingLotType.ShortTerm, NullPointerException.class},
								
				}
				);
	}
	
	@Parameter(0)
	public String checkinTime;

	@Parameter(1)
	public String checkoutTime;
	
	@Parameter(2)
	public ParkingLotType type;
	
	@Parameter(3)
	public Class <? extends Exception> expectedException;
	
	@Test
	public void TestExceptions() throws Exception {
		thrown.expect(expectedException);
		new Calculator().calculateParkingCost(checkinTime, checkoutTime, type);
	}
	

}

	