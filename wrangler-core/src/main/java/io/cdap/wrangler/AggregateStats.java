@Plugin(type = Directive.Type.AGGREGATE)
public class AggregateStats implements Directive {
  private String sizeCol, timeCol, totalSizeCol, totalTimeCol;
  private long totalBytes;
  private long totalNanos;

  @Override
  public void initialize(String... args) {
    this.sizeCol = args[0];
    this.timeCol = args[1];
    this.totalSizeCol = args[2];
    this.totalTimeCol = args[3];
  }

  @Override
  public void execute(Row row, ExecutorContext context) {
    ByteSize size = row.getValue(sizeCol);
    TimeDuration time = row.getValue(timeCol);
    totalBytes += size.getBytes();
    totalNanos += time.getNanos();
  }

  @Override
  public List<Row> finalize() {
    Row result = new Row();
    result.add(totalSizeCol, totalBytes);
    result.add(totalTimeCol, totalNanos / 1_000_000_000.0);
    return Collections.singletonList(result);
  }
}