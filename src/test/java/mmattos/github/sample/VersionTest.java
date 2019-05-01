package mmattos.github.sample;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class VersionTest {

  @Test
  public void myTest(){
    List<String> versions = new ArrayList<>();

    versions.add("0");
    versions.add("1");
    versions.add("1.1");
    versions.add("1.2");
    versions.add("1.12");
    versions.add("1.12.1");
    versions.add("1.13");
    versions.add("1.13.4");
    versions.add("2");
    versions.add("3");

    for (int i=0; i< versions.size(); i++){
      for (int j=0; j< versions.size(); j++){
        compareVersions(versions.get(i), versions.get(j));
      }
    }

  }

  private int compareVersions(String v1, String v2){

    int output = 0;
    if (v1 == null || v2 == null) {
      throw new RuntimeException("Versions cant be null");
    }

    String[] v1Levels = v1.split("\\.");
    String[] v2Levels = v2.split("\\.");

    int maxLevels = Math.max(v1Levels.length, v2Levels.length);
    for (int i=0; i<maxLevels; i++) {
      Integer v1Level = i < v1Levels.length ? Integer.parseInt(v1Levels[i]) : 0;
      Integer v2Level = i < v2Levels.length ? Integer.parseInt(v2Levels[i]) : 0;
      if ((output = v1Level.compareTo(v2Level)) != 0) {
        break;
      }
    }
    System.out.println(String.format("Comparing version %s and %s. Output = %s", v1 , v2, output));
    return output;
  }
}
