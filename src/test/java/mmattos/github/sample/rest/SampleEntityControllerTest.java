package mmattos.github.sample.rest;

import static mmattos.github.sample.testutils.SampleEntityBuilderUtils.createSampleEntityDTO;
import static java.util.Collections.emptyList;
import static java.util.Collections.nCopies;
import static java.util.UUID.randomUUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import mmattos.github.sample.exception.NotFoundException;
import mmattos.github.sample.model.entity.SampleEntity;
import mmattos.github.sample.service.SampleEntityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import mmattos.github.sample.testutils.SampleEntityBuilderUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SampleEntityController.class)
class SampleEntityControllerTest {

  private static final String ENDPOINT = "/sample-entities";
  private static final String ENDPOINT_BY_ID = "/sample-entities/{id}";
  private static final UUID VALID_SAMPLE_ENTITY_ID = UUID.randomUUID();

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private SampleEntityService sampleEntityService;

  @Test
  @DisplayName("Return 201(Created) when successfully adding")
  void addRespondsWith201Code() throws Exception {
    String requestPayload = objectMapper.writeValueAsString(createSampleEntityDTO());
    when(sampleEntityService.addSampleEntity(any())).thenReturn(new SampleEntity());

    mockMvc.perform(post(ENDPOINT).contentType(APPLICATION_JSON)
        .content(requestPayload))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Return 200(OK) when successfully getting all")
  void getAll() throws Exception {
    var sampleEntities = nCopies(2, SampleEntityBuilderUtils.createSampleEntity("NAME"));
    when(sampleEntityService.getAllSampleEntities()).thenReturn(sampleEntities);

    mockMvc.perform(get(ENDPOINT))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Return 200(OK) when no sample entities are found")
  void getAllNotFound() throws Exception {
    when(sampleEntityService.getAllSampleEntities()).thenReturn(emptyList());

    mockMvc.perform(get(ENDPOINT))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Return 200(OK) when successfully getting by ID")
  void getByIdRespondsWith200Code() throws Exception {
    UUID id = randomUUID();
    when(sampleEntityService.getSampleEntityById(id)).thenReturn(new SampleEntity());

    mockMvc.perform(get(ENDPOINT_BY_ID, id))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Return 404(Not Found) when getting by Id and Id supplied was not found.")
  void getByIdRespondsWith404Code() throws Exception {
    UUID id = randomUUID();
    when(sampleEntityService.getSampleEntityById(id)).thenThrow(new NotFoundException(""));

    mockMvc.perform(get(ENDPOINT_BY_ID, id))
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Return 500(Internal Server error) when getting by ID and an unexpected exception happens")
  void getIdRespondsWith500Code() throws Exception {
    UUID id = randomUUID();
    when(sampleEntityService.getSampleEntityById(id)).thenThrow(new RuntimeException());

    mockMvc.perform(get(ENDPOINT_BY_ID, id))
        .andExpect(status().isInternalServerError());
  }

}
