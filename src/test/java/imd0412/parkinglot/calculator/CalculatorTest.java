package imd0412.parkinglot.calculator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import imd0412.parkinglot.ParkingLotType;

@RunWith(Parameterized.class)
public class CalculatorTest {
	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object [][]
				{
				{"1970.01.01 00:00","1970.01.01 00:00", ParkingLotType.ShortTerm, 8.0F},	//RS1
				{"1970.01.01 00:00","1970.01.01 00:01", ParkingLotType.ShortTerm, 8.0F},	//RS1
				{"1970.01.01 00:00", "1970.01.01 00:59", ParkingLotType.ShortTerm, 8.0F},	//RS1
				{"1970.01.01 00:00", "1970.01.01 01:00", ParkingLotType.ShortTerm, 8.0F}, 	//RS1
				{"1970.01.01 00:00", "1970.01.01 01:01", ParkingLotType.ShortTerm, 10.0F},	//RS2
				{"1970.01.01 00:00", "1970.01.01 23:59", ParkingLotType.ShortTerm, 54.0F},	//RS2
				{"1970.01.01 00:00", "1970.01.02 00:00", ParkingLotType.ShortTerm, 54.0F},	//RS2
				{"1970.01.01 00:00", "1970.01.02 00:01", ParkingLotType.ShortTerm, 106.0F},	//RS3
				{"1970.01.01 00:00", "1970.01.07 23:59", ParkingLotType.ShortTerm, 642.0F},
				{"1970.01.01 00:00", "1970.01.08 00:00", ParkingLotType.ShortTerm, 692.0F},
				{"1970.01.01 00:00", "1970.01.08 00:01", ParkingLotType.ShortTerm, 694.0F},
				{"1970.01.01 00:00", "1970.01.08 23:59", ParkingLotType.ShortTerm, 740.0F},
				{"1970.01.01 00:00", "1970.01.09 00:00", ParkingLotType.ShortTerm, 770.0F},
				{"1970.01.01 00:00", "1970.01.09 00:01", ParkingLotType.ShortTerm, 772.0F}, 
				{"1970.01.01 00:00", "1970.01.01 00:00", ParkingLotType.LongTerm, 70.0F},
				{"1970.01.01 00:00", "1970.01.01 23:59", ParkingLotType.LongTerm, 70.0F},
				{"1970.01.01 00:00", "1970.01.02 00:00", ParkingLotType.LongTerm, 70.0F},
				{"1970.01.01 00:00", "1970.01.02 00:01", ParkingLotType.LongTerm, 120.0F},
				{"1970.01.01 00:00", "1970.01.02 00:02", ParkingLotType.LongTerm, 120.0F},
				{"1970.01.01 00:00", "1970.01.07 23:59", ParkingLotType.LongTerm, 370.0F},
				{"1970.01.01 00:00", "1970.01.08 00:00", ParkingLotType.LongTerm, 370.0F},
				{"1970.01.01 00:00", "1970.01.08 00:01", ParkingLotType.LongTerm, 400.0F},
				{"1970.01.01 00:00", "1970.01.30 23:59", ParkingLotType.LongTerm, 1060.0F},
				{"1970.01.01 00:00", "1970.01.31 00:00", ParkingLotType.LongTerm, 1560.0F},
				{"1970.01.01 00:00", "1970.01.31 00:01", ParkingLotType.LongTerm, 1590.0F},
				{"1970.01.01 00:00", "1970.01.01 00:00", ParkingLotType.VIP, 500.0F},
				{"1970.01.01 00:00", "1970.01.01 00:01", ParkingLotType.VIP, 500.0F},
				{"1970.01.01 00:00", "1970.01.07 23:59", ParkingLotType.VIP, 500.0F},
				{"1970.01.01 00:00", "1970.01.08 00:00", ParkingLotType.VIP, 500.0F},
				{"1970.01.01 00:00", "1970.01.08 00:01", ParkingLotType.VIP, 600.0F},
				{"1970.01.01 00:00", "1970.01.14 23:49", ParkingLotType.VIP, 1200.0F},
				{"1970.01.01 00:00", "1970.01.15 00:00", ParkingLotType.VIP, 1200.0F},
				{"1970.01.01 00:00", "1970.01.15 00:01", ParkingLotType.VIP, 1280.0F},
				});
		}
	@Parameter(0)
	public String checkin;
	@Parameter(1)
	public String checkout;
	@Parameter(2)
	public ParkingLotType type;
	@Parameter(3)
	public Float ExpectedValue;
	
	@Test
	public void testCalculateParkingCost() throws Exception {
		//testCalculateShortTerm();
	}

	@Test
	public void testCalculateShortTerm() throws Exception {
		Calculator calc = new Calculator();
		Float ReturnedValue = calc.calculateParkingCost(checkin, checkout, type);
		assertEquals(ExpectedValue, ReturnedValue);
	}

	@Test
	public void testCalculateLongTerm() {
//		fail("Not yet implemented");
	}

	@Test
	public void testCalculateVIP() {
	//	fail("Not yet implemented");
	}

}
