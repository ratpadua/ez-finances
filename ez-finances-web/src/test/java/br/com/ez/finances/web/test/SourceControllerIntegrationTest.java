package br.com.ez.finances.web.test;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import br.com.ez.finances.domain.enums.Status;

/**
 * Source related API controller integration tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Sql({"/sql/profile-data.sql", "/sql/source-data.sql"})
@Transactional
public class SourceControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getSourcesTestV1() throws Exception {
        mvc.perform(get("/v1/source?profileId={profileId}&statuses={statuses}",
                1, Status.ACTIVE.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].name", is("Gas")))
                .andExpect(jsonPath("$[0].status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].name", is("Groceries")))
                .andExpect(jsonPath("$[1].status", is(Status.ACTIVE.name())));
    }

    @Test
    public void getSourcesTestBadRequestV1() throws Exception {
        mvc.perform(get("/v1/source"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_004")))
                .andExpect(jsonPath("$.message",
                        is("Missing request parameter. Please check the service documentation.")));
    }

    @Test
    public void searchSourceTestV1() throws Exception {
        mvc.perform(get("/v1/source/{id}", 4))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.name", is("Groceries")))
                .andExpect(jsonPath("$.status", is(Status.INACTIVE.name())));
    }

    @Test
    public void searchSourceNotFoundTestV1() throws Exception {
        mvc.perform(get("/v1/source/{id}", 100))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("ERR_700")))
                .andExpect(jsonPath("$.message", is("Source not found.")));
    }

    @Test
    public void searchSourceBadRequestTestV1() throws Exception {
        mvc.perform(get("/v1/source/{id}", "123abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_006")))
                .andExpect(jsonPath("$.message",
                        is("Path parameter in wrong format. Please check the service documentation.")));
    }

    @Test
    public void createSourceTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/source/create-source.json"), Charset.forName("UTF-8"));

        mvc.perform(post("/v1/source")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Others")))
                .andExpect(jsonPath("$.status", is(Status.ACTIVE.name())));
    }

    @Test
    public void createSourceBadRequestTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/source/create-source-bad-request.json"), Charset.forName("UTF-8"));

        mvc.perform(post("/v1/source")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_005")))
                .andExpect(jsonPath("$.message",
                        is("Missing body parameter. Please check the service documentation.")));
    }

    @Test
    public void updateSourceTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/source/update-source.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/source/{id}", 1)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Other Purchases")))
                .andExpect(jsonPath("$.status", is(Status.INACTIVE.name())));
    }

    @Test
    public void updateSourceNotFoundTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/source/update-source.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/source/{id}", 100)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("ERR_700")))
                .andExpect(jsonPath("$.message", is("Source not found.")));
    }

    @Test
    public void updateSourceEmptyTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/source/update-source-empty.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/source/{id}", 7)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(7)))
                .andExpect(jsonPath("$.name", is("Cleaning Supplies")))
                .andExpect(jsonPath("$.status", is(Status.ACTIVE.name())));
    }

    @Test
    public void updateSourceBadRequestTestV1() throws Exception {
        mvc.perform(put("/v1/source/{id}", "123abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_006")))
                .andExpect(jsonPath("$.message",
                        is("Path parameter in wrong format. Please check the service documentation.")));
    }
}
