package mmattos.github.sample.it;

import static mmattos.github.sample.testutils.TestUtils.readData;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import mmattos.github.sample.SampleServiceAPI;
import mmattos.github.sample.repository.SampleEntityRepository;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SampleServiceAPI.class)
@AutoConfigureMockMvc
class SampleServiceControllerIT {

  private static final String SAMPLE_ENTITY_ENDPOINT = "/sample-entity";
  private static final String SAMPLE_ENTITY_ENDPOINT_BY_ID = "/sample-entity/{id}";

  @Autowired
  private SampleEntityRepository sampleEntityRepository;

  @Autowired
  private MockMvc mockMvc;

  @AfterEach
  void tearDown() {
    sampleEntityRepository.deleteAll();
  }

  @BeforeEach
  void setUp() {
  }

  /// SampleEntity
  @Test
  void add() throws Exception {
    ResultActions resultActions = mockMvc.perform(post(SAMPLE_ENTITY_ENDPOINT).contentType(APPLICATION_JSON)
        .content(readData("/requests/sample_entity_request.json")))
        .andExpect(status().isCreated());
    check(resultActions);
  }

  @Test
  void getAll() throws Exception {
    ResultActions resultActions = mockMvc.perform(get(SAMPLE_ENTITY_ENDPOINT))
        .andExpect(status().isOk());
    checkList(resultActions);
  }

  @Test
  void getById() throws Exception {
    UUID sampleEntityId = sampleEntityRepository.findAll().iterator().next().getId();
    ResultActions resultActions = mockMvc.perform(get(SAMPLE_ENTITY_ENDPOINT_BY_ID, sampleEntityId))
        .andExpect(status().isOk());
    check(resultActions);
  }

  private void checkList(ResultActions resultActions) throws Exception {
    resultActions
        .andExpect(jsonPath("$.length()", greaterThan(1)))
        .andExpect(jsonPath("$.[*].id").exists())
        .andExpect(jsonPath("$.[*].name").value(everyItem(equalTo("TEST"))));
  }

  private void check(ResultActions resultActions) throws Exception {
    resultActions
        .andExpect(jsonPath("$..id").exists())
        .andExpect(jsonPath("$.name").value(equalTo("TEST")));
  }
}
