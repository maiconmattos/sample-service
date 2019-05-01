package mmattos.github.sample.mapper;

import mmattos.github.sample.model.dto.SampleEntityDto;
import mmattos.github.sample.model.entity.SampleEntity;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public final class SampleEntityMapper {

  private static ModelMapper modelMapper = new ModelMapper();

  public static SampleEntity convertFromDto(SampleEntityDto sampleEntityDto) {
    return modelMapper.map(sampleEntityDto, SampleEntity.class);
  }

  public static SampleEntityDto convertToDto(SampleEntity sampleEntity) {
    return modelMapper.map(sampleEntity, SampleEntityDto.class);
  }

}
