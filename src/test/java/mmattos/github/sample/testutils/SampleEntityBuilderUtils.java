package mmattos.github.sample.testutils;

import mmattos.github.sample.model.dto.SampleEntityDto;
import mmattos.github.sample.model.entity.SampleEntity;
import mmattos.github.sample.model.entity.Status;
import java.time.Instant;
import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class SampleEntityBuilderUtils {

  private static final Instant INSTANT = Instant.now();
  private static final UUID ID = UUID.randomUUID();
  private static final String NAME = "NAME";

  public static SampleEntityDto createSampleEntityDTO() {
    SampleEntityDto sampleEntityDto = new SampleEntityDto();
    sampleEntityDto.setId(ID);
    sampleEntityDto.setName(NAME);
    sampleEntityDto.setStatus(Status.ENABLED.toString());
    sampleEntityDto.setBlocked(true);
    return sampleEntityDto;
  }

  public static SampleEntity createSampleEntity(String name) {
    SampleEntity sampleEntity = new SampleEntity();
    sampleEntity.setId(ID);
    sampleEntity.setName(name);
    sampleEntity.setStatus(Status.ENABLED);
    sampleEntity.setBlocked(true);
    sampleEntity.setCreated(INSTANT);
    sampleEntity.setModified(INSTANT);
    return sampleEntity;
  }

  public static SampleEntity createSampleEntity() {
    return createSampleEntity(NAME);
  }

}
