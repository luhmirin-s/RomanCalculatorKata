import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RomanCalcTest {

    @Test
    public void SimpleNumbersAddition() {
        assertEquals("II", RomanCalc.add("I", "I"));
        assertEquals("III", RomanCalc.add("I", "II"));
        assertEquals("IV", RomanCalc.add("II", "II"));
        assertEquals("XV", RomanCalc.add("V", "X"));
        assertEquals("LXXIV", RomanCalc.add("XIV", "LX"));
    }

    @Test
    public void ComplexNumbersAddition() {
        assertEquals("MCCXIV", RomanCalc.add("CCCLXIX", "DCCCXLV"));
        assertEquals("CLXXXIII", RomanCalc.add("CXXII", "LXI"));
    }


}
