import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ControlFlowTests {

	String GetMonth(int month) {
        String monthString;
        
        switch (month) {
            case 1:  monthString = "January";
                     break;
            case 2:  monthString = "February";
                     break;
            case 3:  monthString = "March";
                     break;
            case 4:  monthString = "April";
                     break;
            case 5:  monthString = "May";
                     break;
            case 6:  monthString = "June";
                     break;
            case 7:  monthString = "July";
                     break;
            case 8:  monthString = "August";
                     break;
            case 9:  monthString = "September";
                     break;
            case 10: monthString = "October";
                     break;
            case 11: monthString = "November";
                     break;
            case 12: monthString = "December";
                     break;
            default: monthString = "Invalid month";
                     break;
        }
        return monthString;
	}
	
	int GetDayOfWeek(String day) {
		int result;
		switch(day) {
		case "Saturday": 
			result = 1;
			break;	
		case "Sunday": 
			result = 2;
			break;		
		case "Monday": 
			result = 3;
			break;		
		case "Tuesday": 
			result = 4;
			break;		
		case "Wednesday": 
			result = 5;
			break;		
		case "Thursday": 
			result = 6;
			break;		
		case "Friday": 
			result = 7;
			break;	
		default:
			result = 0;
		}
		
		return result;
	}
	
	@Test
	void testGetMonth() {
		assertEquals("March", GetMonth(3));
		assertEquals("Invalid month", GetMonth(0));
		assertEquals("November", GetMonth(11));
	}
	
	@Test
	void testGetDayOfWeek() {
		assertEquals(1, GetDayOfWeek("Saturday"));
		assertEquals(5, GetDayOfWeek("Wednesday"));
		assertEquals(7, GetDayOfWeek("Friday"));
		assertEquals(0, GetDayOfWeek("PresidentsDay"));
	}
	
	@Test
	void testFor() {
		int[] numbers = {1,2,3,4,5,6,7,8,9,10};
		int sum = 0;
		for(int number : numbers) {
			sum += number;
		}
		assertEquals(55, sum);
	}

}
