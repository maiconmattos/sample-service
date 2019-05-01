package mmattos.github.sample.model.dto;

import java.time.Instant;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SampleEntityDto {

  private UUID id;
  @NotNull
  private String name;
  @NotNull
  private String status;
  private Boolean blocked;
  private Instant created;
  private Instant modified;
}