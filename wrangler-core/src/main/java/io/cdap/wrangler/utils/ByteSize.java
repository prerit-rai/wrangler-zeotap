package io.cdap.wrangler.api.parser;

public class ByteSize extends Token {
  private final long bytes;

  public ByteSize(String value) {
    super(TokenType.BYTE_SIZE, value);
    this.bytes = parseBytes(value);
  }

  public long getBytes() { return bytes; }

  private long parseBytes(String input) {
    String unit = input.replaceAll("[^A-Za-z]", "").toUpperCase();
    double num = Double.parseDouble(input.replaceAll("[A-Za-z]", ""));

    switch (unit) {
      case "B":  return (long) num;
      case "KB": return (long) (num * 1024);
      case "MB": return (long) (num * 1024 * 1024);
      case "GB": return (long) (num * 1024 * 1024 * 1024);
      case "TB": return (long) (num * 1024 * 1024 * 1024 * 1024);
      default: throw new IllegalArgumentException("Invalid byte unit: " + unit);
    }
  }
}