package mmattos.github.sample.repository;

import mmattos.github.sample.model.entity.SampleEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleEntityRepository extends JpaRepository<SampleEntity, UUID> {
  Optional<SampleEntity> findByName(String name);
}