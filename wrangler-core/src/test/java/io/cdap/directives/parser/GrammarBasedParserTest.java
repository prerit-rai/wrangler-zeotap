import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrammarBasedParserTest {
  @Test
  public void testValidByteSizeSyntax() {
    // Valid syntax
    assertDoesNotThrow(() -> parseRecipe("set-column :total_size '10KB'"));
    assertDoesNotThrow(() -> parseRecipe("aggregate-stats :size_col :time_col total_size total_time"));
  }

  @Test
  public void testInvalidByteSizeSyntax() {
    // Invalid syntax (missing unit)
    assertThrows(SyntaxError.class, () -> parseRecipe("set-column :total_size '10'"));
    // Invalid syntax (unknown unit)
    assertThrows(SyntaxError.class, () -> parseRecipe("set-column :total_size '10XB'"));
  }

  private void parseRecipe(String recipe) {
    // Use Wrangler's TestingRig or RecipeCompiler to validate parsing
    RecipeCompiler.compile(recipe);
  }
}