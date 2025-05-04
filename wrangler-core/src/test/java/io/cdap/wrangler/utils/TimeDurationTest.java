TimeDurationTest.javaimport org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimeDurationTest {
  @Test
  public void testTimeDurationParsing() {
    // Valid cases
    assertEquals(1_000_000L, new TimeDuration("1ms").getNanos());
    assertEquals(2_500_000_000L, new TimeDuration("2.5s").getNanos());
    assertEquals(60_000_000_000L, new TimeDuration("1m").getNanos());
    assertEquals(3600_000_000_000L, new TimeDuration("1h").getNanos());
    
    // Case insensitivity
    assertEquals(1_000_000L, new TimeDuration("1MS").getNanos());
    
    // Invalid units
    assertThrows(IllegalArgumentException.class, () -> new TimeDuration("10ks"));
    assertThrows(IllegalArgumentException.class, () -> new TimeDuration("invalid"));
  }
}