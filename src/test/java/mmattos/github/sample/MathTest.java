package mmattos.github.sample;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.junit.Test;

public class MathTest {

  @Test
  public void myTest(){
    List<Double> numbers = new ArrayList<>();

    //for( int i =0; i < 60000000; i++) {
      numbers.add(0.0);
      numbers.add(0.1);
      numbers.add(0.1);
      numbers.add(0.2);
      numbers.add(0.3);
      numbers.add(1.2);
      numbers.add(1.3);
      numbers.add(2.2);
      numbers.add(12.1);
      numbers.add(13.4);
    //}

    System.out.println(percentile(numbers, 5));
    System.out.println(mean(numbers));

    System.out.println(numbers.size());
    System.out.println(avg2(numbers)); // 30.9
    System.out.println(avg(numbers).orElse(0)); // 30.9

    System.out.println(sum(Double.MAX_VALUE, 0.0));
    System.out.println(sum2(Double.MAX_VALUE, Double.MAX_VALUE));
  }

  private OptionalDouble avg(List<Double> numbers) {
    return numbers.stream().mapToDouble(i -> i).average();
  }

  private BigDecimal sum(Double d1, Double d2) {
    return new BigDecimal(d1).add(new BigDecimal(d2));
  }

  private Double sum2(Double d1, Double d2) {
    return d1+ (d2 * 10) ;
  }

  private double avg2(List<Double> numbers) {
    double sum = 0;
    for( int i =0; i < numbers.size(); i++) {
      sum+=numbers.get(i);
    }
    return sum/numbers.size();
  }

  private double median(List<Double> numbers) {
    Median median = new Median();
    double[] array = numbers.stream().mapToDouble(d -> d).toArray();
    return median.evaluate(array);
  }

  private int percentile(List<Double> numbers, int position) {
    return (int) Math.ceil(numbers.get(5) * numbers.size()) - 1;
  }
}
