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
 * Profile related API controller integration tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Sql("/sql/profile-data.sql")
@Transactional
public class ProfileControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllProfilesTestV1() throws Exception {
        mvc.perform(get("/v1/profile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[0].balance", is(123456.78)))
                .andExpect(jsonPath("$[0].status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$[1].id", is(3)))
                .andExpect(jsonPath("$[1].name", is("Marcus")))
                .andExpect(jsonPath("$[1].balance", is(0.0)))
                .andExpect(jsonPath("$[1].status", is(Status.INACTIVE.name())))
                .andExpect(jsonPath("$[2].id", is(2)))
                .andExpect(jsonPath("$[2].name", is("Tessa")))
                .andExpect(jsonPath("$[2].balance", is(876543.21)))
                .andExpect(jsonPath("$[2].status", is(Status.ACTIVE.name())));
    }

    @Test
    public void getFilteredProfilesTestV1() throws Exception {
        mvc.perform(get("/v1/profile?statuses={status}", "ACTIVE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[0].balance", is(123456.78)))
                .andExpect(jsonPath("$[0].status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Tessa")))
                .andExpect(jsonPath("$[1].balance", is(876543.21)))
                .andExpect(jsonPath("$[1].status", is(Status.ACTIVE.name())));
    }

    @Test
    public void searchProfileTestV1() throws Exception {
        mvc.perform(get("/v1/profile/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.balance", is(123456.78)))
                .andExpect(jsonPath("$.status", is(Status.ACTIVE.name())));
    }

    @Test
    public void searchProfileNotFoundTestV1() throws Exception {
        mvc.perform(get("/v1/profile/{id}", 100))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("ERR_600")))
                .andExpect(jsonPath("$.message", is("Profile not found.")));
    }

    @Test
    public void searchProfileBadRequestTestV1() throws Exception {
        mvc.perform(get("/v1/profile/{id}", "123abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_006")))
                .andExpect(jsonPath("$.message",
                        is("Path parameter in wrong format. Please check the service documentation.")));
    }

    @Test
    public void createProfileTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/profile/create-profile.json"), Charset.forName("UTF-8"));

        mvc.perform(post("/v1/profile")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Peter")))
                .andExpect(jsonPath("$.balance", is(4554.45)))
                .andExpect(jsonPath("$.status", is(Status.ACTIVE.name())));
    }

    @Test
    public void createProfileBadRequestTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/profile/create-profile-bad-request.json"), Charset.forName("UTF-8"));

        mvc.perform(post("/v1/profile")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_005")))
                .andExpect(jsonPath("$.message",
                        is("Missing body parameter. Please check the service documentation.")));
    }

    @Test
    public void updateProfileTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/profile/update-profile.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/profile/{id}", 1)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Peter")))
                .andExpect(jsonPath("$.balance", is(0.0)))
                .andExpect(jsonPath("$.status", is(Status.INACTIVE.name())));
    }

    @Test
    public void updateProfileNotFoundTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/profile/update-profile.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/profile/{id}", 100)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("ERR_600")))
                .andExpect(jsonPath("$.message", is("Profile not found.")));
    }

    @Test
    public void updateProfileEmptyTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/profile/update-profile-empty.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/profile/{id}", 1)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.balance", is(123456.78)))
                .andExpect(jsonPath("$.status", is(Status.ACTIVE.name())));
    }

    @Test
    public void updateProfileBadRequestTestV1() throws Exception {
        mvc.perform(put("/v1/profile/{id}", "123abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_006")))
                .andExpect(jsonPath("$.message",
                        is("Path parameter in wrong format. Please check the service documentation.")));
    }
}
