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
import br.com.ez.finances.domain.enums.TransactionType;

/**
 * Transaction related API controller integration tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Sql({"/sql/profile-data.sql", "/sql/source-data.sql", "/sql/translation-data.sql", "/sql/transaction-data.sql"})
@Transactional
public class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllTransactionsTestV1() throws Exception {
        mvc.perform(get("/v1/transaction?sort={sort}", "inputDate,DESC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(2)))
                .andExpect(jsonPath("$.content[0].source.id", is(3)))
                .andExpect(jsonPath("$.content[0].source.name", is("Gas")))
                .andExpect(jsonPath("$.content[0].source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.content[0].translation.id", is(5)))
                .andExpect(jsonPath("$.content[0].translation.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$.content[0].translation.description", is("PAT GSTAT")))
                .andExpect(jsonPath("$.content[0].translation.toDescription", is("Patrick Gas Station")))
                .andExpect(jsonPath("$.content[0].translation.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.content[0].type", is(TransactionType.FIXED.name())))
                .andExpect(jsonPath("$.content[0].description", is("PAT GSTAT")))
                .andExpect(jsonPath("$.content[0].balance", is(109.88)))
                .andExpect(jsonPath("$.content[0].inputDate", is("2018-09-20T08:12:33.123")))
                .andExpect(jsonPath("$.content[1].id", is(1)))
                .andExpect(jsonPath("$.content[1].source.id", is(1)))
                .andExpect(jsonPath("$.content[1].source.name", is("Groceries")))
                .andExpect(jsonPath("$.content[1].source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.content[1].translation.id", is(1)))
                .andExpect(jsonPath("$.content[1].translation.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$.content[1].translation.description", is("ULTMKT LMT")))
                .andExpect(jsonPath("$.content[1].translation.toDescription", is("Ultra Market")))
                .andExpect(jsonPath("$.content[1].translation.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.content[1].type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$.content[1].description", is("ULTMKT LMT")))
                .andExpect(jsonPath("$.content[1].balance", is(54.78)))
                .andExpect(jsonPath("$.content[1].inputDate", is("2018-09-19T18:55:34.534")));
    }

    /*@Test
    public void getTranslationsTestBadRequestV1() throws Exception {
        mvc.perform(get("/v1/translation"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_004")))
                .andExpect(jsonPath("$.message",
                        is("Missing request parameter. Please check the service documentation.")));
    }

    @Test
    public void searchTranslationTestV1() throws Exception {
        mvc.perform(get("/v1/translation/search?description={description}", "ELT CMP"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(6)))
                .andExpect(jsonPath("$.source.id", is(5)))
                .andExpect(jsonPath("$.source.name", is("Bills")))
                .andExpect(jsonPath("$.source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.type", is(TransactionType.FIXED.name())))
                .andExpect(jsonPath("$.description", is("ELT CMP")))
                .andExpect(jsonPath("$.toDescription", is("Electric Bill")))
                .andExpect(jsonPath("$.status", is(Status.ACTIVE.name())));
    }

    @Test
    public void searchTranslationBadRequestTestV1() throws Exception {
        mvc.perform(get("/v1/translation/search"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_004")))
                .andExpect(jsonPath("$.message",
                        is("Missing request parameter. Please check the service documentation.")));
    }

    @Test
    public void createTranslationTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/translation/create-translation.json"), Charset.forName("UTF-8"));

        mvc.perform(post("/v1/translation")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.source.id", is(7)))
                .andExpect(jsonPath("$.source.name", is("Cleaning Supplies")))
                .andExpect(jsonPath("$.source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.type", is(TransactionType.FIXED.name())))
                .andExpect(jsonPath("$.description", is("ACEMARKT")))
                .andExpect(jsonPath("$.toDescription", is("Ace Market")))
                .andExpect(jsonPath("$.status", is(Status.ACTIVE.name())));
    }

    @Test
    public void createTranslationBadRequestTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/translation/create-translation-bad-request.json"), Charset.forName("UTF-8"));

        mvc.perform(post("/v1/translation")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_005")))
                .andExpect(jsonPath("$.message",
                        is("Missing body parameter. Please check the service documentation.")));
    }

    @Test
    public void updateTranslationTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/translation/update-translation.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/translation/{id}", 4)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.source.id", is(3)))
                .andExpect(jsonPath("$.source.name", is("Gas")))
                .andExpect(jsonPath("$.source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$.description", is("GARGSTAT")))
                .andExpect(jsonPath("$.toDescription", is("Gary Gas and Food Station")))
                .andExpect(jsonPath("$.status", is(Status.ACTIVE.name())));
    }

    @Test
    public void updateTranslationNotFoundTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/translation/update-translation.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/translation/{id}", 100)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("ERR_800")))
                .andExpect(jsonPath("$.message", is("Translation not found.")));
    }

    @Test
    public void updateTranslationEmptyTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/translation/update-translation-empty.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/translation/{id}", 6)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(6)))
                .andExpect(jsonPath("$.source.id", is(5)))
                .andExpect(jsonPath("$.source.name", is("Bills")))
                .andExpect(jsonPath("$.source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.type", is(TransactionType.FIXED.name())))
                .andExpect(jsonPath("$.description", is("ELT CMP")))
                .andExpect(jsonPath("$.toDescription", is("Electric Bill")))
                .andExpect(jsonPath("$.status", is(Status.ACTIVE.name())));
    }

    @Test
    public void updateTranslationBadRequestTestV1() throws Exception {
        mvc.perform(put("/v1/translation/{id}", "123abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_006")))
                .andExpect(jsonPath("$.message",
                        is("Path parameter in wrong format. Please check the service documentation.")));
    }*/
}