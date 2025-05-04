import io.cdap.wrangler.test.TestingRig;
import io.cdap.wrangler.api.Row;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AggregateStatsTest {
  @Test
  public void testAggregationWithUnits() {
    // Sample input rows
    List<Row> rows = Arrays.asList(
      new Row().add("data_transfer_size", new ByteSize("1KB")).add("response_time", new TimeDuration("500ms")),
      new Row().add("data_transfer_size", new ByteSize("2MB")).add("response_time", new TimeDuration("1.5s"))
    );

    // Recipe: aggregate-stats :data_transfer_size :response_time total_size_mb total_time_sec
    String[] recipe = new String[] {
      "aggregate-stats :data_transfer_size :response_time total_size_mb total_time_sec"
    };

    // Execute the recipe
    List<Row> results = TestingRig.execute(recipe, rows);

    // Verify results
    assertEquals(1, results.size());
    Row result = results.get(0);
    
    // Size: 1KB + 2MB = 1024 + (2 * 1024 * 1024) = 2,099,200 bytes → 2.0 MB
    double expectedTotalSizeMB = (1024.0 + 2 * 1024 * 1024) / (1024 * 1024);
    assertEquals(expectedTotalSizeMB, result.getValue("total_size_mb"), 0.001);
    
    // Time: 500ms + 1.5s = 0.5 + 1.5 = 2.0 seconds
    double expectedTotalTimeSec = (500.0 / 1000) + 1.5;
    assertEquals(expectedTotalTimeSec, result.getValue("total_time_sec"), 0.001);
  }

  @Test
  public void testAverageTimeAggregation() {
    // Similar setup with optional "average" argument (modify directive logic)
    // ...
  }
}