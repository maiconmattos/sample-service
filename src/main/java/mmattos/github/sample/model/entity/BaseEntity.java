package mmattos.github.sample.model.entity;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEntity {

  @Id
  @GeneratedValue(generator = "generator_rule_id")
  @GenericGenerator(name = "generator_rule_id", strategy = "uuid2")
  @EqualsAndHashCode.Include
  private UUID id;

  @Column
  @CreationTimestamp
  private Instant created;
  @Column
  @UpdateTimestamp
  private Instant modified;
}
