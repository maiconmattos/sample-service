package mmattos.github.sample.service;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import mmattos.github.sample.model.entity.SampleEntity;
import mmattos.github.sample.repository.SampleEntityRepository;
import mmattos.github.sample.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SampleEntityService {

  @Autowired
  private SampleEntityRepository sampleEntityRepository;

  /**
   * Save a {@link SampleEntity}
   *
   * @param sampleEntity {@link SampleEntity}
   * @return persisted sampleEntity
   */
  public SampleEntity addSampleEntity(SampleEntity sampleEntity) {
    notNull(sampleEntity, "Sample entity must not be null");
    return sampleEntityRepository.save(sampleEntity);
  }

  public List<SampleEntity> getAllSampleEntities() {
    return sampleEntityRepository.findAll();
  }

  public SampleEntity getSampleEntityById(UUID id) {
    return sampleEntityRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Could not find a sample entity with the specified id"));
  }


}
