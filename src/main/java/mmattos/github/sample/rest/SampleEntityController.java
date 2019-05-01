package mmattos.github.sample.rest;

import static mmattos.github.sample.mapper.SampleEntityMapper.convertFromDto;
import static mmattos.github.sample.mapper.SampleEntityMapper.convertToDto;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import mmattos.github.sample.mapper.SampleEntityMapper;
import mmattos.github.sample.model.dto.SampleEntityDto;
import mmattos.github.sample.model.entity.SampleEntity;
import mmattos.github.sample.service.SampleEntityService;
import io.micrometer.core.annotation.Timed;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Timed(percentiles = {0.5, 0.75, 0.95, 0.99}, histogram = true)
@RestController
@RequestMapping("/sample-entity")
public class SampleEntityController {

  @Autowired
  private SampleEntityService sampleEntityService;

  @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  public SampleEntityDto addSampleEntity(
      @Valid @RequestBody SampleEntityDto sampleEntityDto) {
    log.info("Request to add new sample entity: {}", sampleEntityDto);
    SampleEntity addedSampleEntity = sampleEntityService
        .addSampleEntity(convertFromDto(sampleEntityDto));
    return convertToDto(addedSampleEntity);
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<SampleEntityDto> getAllSampleEntities() {
    log.info("Request to get all sample directories");
    return sampleEntityService.getAllSampleEntities()
        .stream()
        .map(SampleEntityMapper::convertToDto)
        .collect(toList());
  }

  @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
  public SampleEntityDto getSampleEntityById(@PathVariable("id") UUID id) {
    log.info("Request to get sample entity by id: {}", id);
    var sampleEntity = sampleEntityService.getSampleEntityById(id);
    return convertToDto(sampleEntity);
  }
}
