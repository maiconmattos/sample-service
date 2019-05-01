package mmattos.github.sample.service;

import static java.util.Collections.emptyList;
import static java.util.Collections.nCopies;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import mmattos.github.sample.exception.NotFoundException;
import mmattos.github.sample.repository.SampleEntityRepository;
import java.util.Optional;
import java.util.UUID;
import mmattos.github.sample.testutils.SampleEntityBuilderUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SampleEntityServiceTest {

  private static final String NAME = "NAME";

  @InjectMocks
  private SampleEntityService sampleEntityService;

  @Mock
  private SampleEntityRepository sampleEntityRepository;

  @Test
  void add() {
    var expectedSampleEntity = SampleEntityBuilderUtils.createSampleEntity(NAME);
    when(sampleEntityRepository.save(expectedSampleEntity)).thenReturn(expectedSampleEntity);
    var actualSampleEntity = sampleEntityService.addSampleEntity(expectedSampleEntity);
    Assertions.assertEquals(expectedSampleEntity, actualSampleEntity);
  }

  @Test
  void addNull() {
    assertThrows(IllegalArgumentException.class, () -> sampleEntityService.addSampleEntity(null),
        "Sample entity must not be null");
  }

  @Test
  void getAll() {
    var sampleEntities = nCopies(2, SampleEntityBuilderUtils.createSampleEntity(NAME));
    when(sampleEntityRepository.findAll()).thenReturn(sampleEntities);

    var allSampleEntities = sampleEntityService.getAllSampleEntities();
    assertEquals(sampleEntities, allSampleEntities);
  }

  @Test
  void getAllNotFound() {
    when(sampleEntityRepository.findAll()).thenReturn(emptyList());
    var allSampleEntities = sampleEntityService.getAllSampleEntities();
    assertTrue(isEmpty(allSampleEntities));
  }

  @Test
  void getById() {
    var expectedSampleEntity = SampleEntityBuilderUtils.createSampleEntity(NAME);
    UUID id = UUID.randomUUID();
    expectedSampleEntity.setId(id);
    when(sampleEntityRepository.findById(id)).thenReturn(Optional.of(expectedSampleEntity));

    var actualSampleEntity = sampleEntityService.getSampleEntityById(id);
    Assertions.assertEquals(expectedSampleEntity, actualSampleEntity);
  }

  @Test
  void getByIdNotFound() {
    UUID id = UUID.randomUUID();
    when(sampleEntityRepository.findById(id)).thenThrow(NotFoundException.class);
    assertThrows(NotFoundException.class, () -> sampleEntityService.getSampleEntityById(id),
        "Could not find a sampleEntity with the specified id");
  }

}
