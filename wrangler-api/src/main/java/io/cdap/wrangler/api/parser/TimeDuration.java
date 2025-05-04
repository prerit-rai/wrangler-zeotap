package io.cdap.wrangler.api.parser;

public class TimeDuration extends Token {
  private final long nanos;

  public TimeDuration(String value) {
    super(TokenType.TIME_DURATION, value);
    this.nanos = parseNanos(value);
  }

  public long getNanos() { return nanos; }

  private long parseNanos(String input) {
    String unit = input.replaceAll("[^A-Za-z]", "").toLowerCase();
    double num = Double.parseDouble(input.replaceAll("[A-Za-z]", ""));

    switch (unit) {
      case "ms": return (long) (num * 1_000_000);
      case "s":  return (long) (num * 1_000_000_000);
      case "m":  return (long) (num * 60 * 1_000_000_000L);
      case "h":  return (long) (num * 3600 * 1_000_000_000L);
      case "d":  return (long) (num * 86400 * 1_000_000_000L);
      default: throw new IllegalArgumentException("Invalid time unit: " + unit);
    }
  }
}