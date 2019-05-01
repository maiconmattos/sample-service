package mmattos.github.sample.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class SampleEntity extends BaseEntity {

  @NotNull
  @Column(unique = true)
  private String name;
  @Enumerated(EnumType.STRING)
  @NotNull
  private Status status;
  private Boolean blocked;
}
