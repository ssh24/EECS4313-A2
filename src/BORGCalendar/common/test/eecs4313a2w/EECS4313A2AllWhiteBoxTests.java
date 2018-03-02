package eecs4313a2w;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Date;

import net.sf.borg.common.*;
public class EECS4313A2AllWhiteBoxTests {
	@Test
	public void testMinuteString() {
	
	//NINE Weak Normal Equivalence classes
	//Class 1: Minute/60 = 1 and Minute%60 = 0  (valid input) -- Testing 1 hour
	assertEquals("1 Hour",DateUtil.minuteString(60));
	//Class 2: Minute/60 > 1 and Minute%60 = 0 (valid input) --Testing hours more than one (2,3,4....)
	assertEquals("3 Hours",DateUtil.minuteString(180));
	//-------------------------------------------------
	//Class 3: Minute/60 = 1 and Minute%60 > 0 (valid input) --Testing 1 hour with some minutes
	assertEquals("1 Hour 15 Minutes",DateUtil.minuteString(75));
	//Class 4: Minute/60 = 1 and Minute%60 = 1 (valid input) --Testing 1 hour with 1 minute
	assertEquals("1 Hour 1 Minute",DateUtil.minuteString(61));
	//Class 5: Minute/60 > 1 and Minute%60 > 1 (valid input) --Testing hours more than 1 hour with some minutes
	assertEquals("2 Hours 25 Minutes",DateUtil.minuteString(145));
	//Class 6: Minute/60 > 1 and Minute%60 = 1 (valid input) --Testing hours more than 1 hour with some minutes 
	assertEquals("2 Hours 1 Minute",DateUtil.minuteString(121));
	//----------------------------------------------------------
	//Class 7: Minute/60 = 0 and Minute%60 = 0 (valid input) --Testing 0 minutes
	assertEquals("0 Minutes",DateUtil.minuteString(0));
	//Class 8: Minute/60 = 0 and Minute%60 = 1 (valid input) --Testing 1 minute
	assertEquals("1 Minute",DateUtil.minuteString(1));
	//Class 9: Minute/60 = 0 and Minute%60 > 0 (valid input) -- Testing minutes that are less than 1 hour : (0 -59 minutes)
	assertEquals("50 Minutes",DateUtil.minuteString(50));
	System.out.println(DateUtil.minuteString(1440));
	}
	
}
