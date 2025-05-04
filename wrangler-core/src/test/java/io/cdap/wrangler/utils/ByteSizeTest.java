import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ByteSizeTest {
  @Test
  public void testByteSizeParsing() {
    // Valid cases
    assertEquals(1024, new ByteSize("1KB").getBytes());
    assertEquals(1536, new ByteSize("1.5KB").getBytes());
    assertEquals(1024 * 1024, new ByteSize("1MB").getBytes());
    assertEquals(1024L * 1024 * 1024, new ByteSize("1GB").getBytes());
    
    // Case insensitivity
    assertEquals(1024, new ByteSize("1kb").getBytes());
    
    // Invalid units
    assertThrows(IllegalArgumentException.class, () -> new ByteSize("10XB"));
    assertThrows(IllegalArgumentException.class, () -> new ByteSize("5.5invalid"));
  }
}