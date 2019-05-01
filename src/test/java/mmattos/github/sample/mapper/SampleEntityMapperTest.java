package mmattos.github.sample.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;
import mmattos.github.sample.testutils.SampleEntityBuilderUtils;
import org.junit.jupiter.api.Test;

class SampleEntityMapperTest {

  @Test
  void convertFromDto() {
    var sampleEntityDTO = SampleEntityBuilderUtils.createSampleEntityDTO();
    sampleEntityDTO.setId(UUID.randomUUID());
    sampleEntityDTO.setName("NAME");

    var sampleEntity = SampleEntityMapper.convertFromDto(sampleEntityDTO);

    assertEquals(sampleEntityDTO.getId(), sampleEntity.getId());
    assertEquals(sampleEntityDTO.getBlocked(), sampleEntity.getBlocked());
    assertEquals("NAME", sampleEntity.getName());
    assertEquals(sampleEntityDTO.getStatus(), sampleEntity.getStatus().toString());
  }

  @Test
  void convertToDto() {
    var sampleEntity = SampleEntityBuilderUtils.createSampleEntity("NAME");
    sampleEntity.setId(UUID.randomUUID());

    var sampleEntityDto = SampleEntityMapper.convertToDto(sampleEntity);

    assertEquals(sampleEntity.getId(), sampleEntityDto.getId());
    assertEquals(sampleEntity.getBlocked(), sampleEntityDto.getBlocked());
    assertEquals(sampleEntity.getName(), sampleEntityDto.getName());
    assertEquals(sampleEntity.getStatus().toString(), sampleEntityDto.getStatus());
    assertEquals(sampleEntity.getCreated(), sampleEntityDto.getCreated());
    assertEquals(sampleEntity.getModified(), sampleEntityDto.getModified());
  }
}