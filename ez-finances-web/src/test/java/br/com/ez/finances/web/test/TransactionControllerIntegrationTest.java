package br.com.ez.finances.web.test;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    public void searchTransactionsTestV1() throws Exception {
        mvc.perform(get("/v1/transaction?sort={sort}", "inputDate,DESC")
                .header("Profile-Id", 1))
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
                .andExpect(jsonPath("$.content[0].balance", is(-109.88)))
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
                .andExpect(jsonPath("$.content[1].balance", is(-54.78)))
                .andExpect(jsonPath("$.content[1].inputDate", is("2018-09-19T18:55:34.534")));
    }

    @Test
    public void searchTransactionsTestBadRequestV1() throws Exception {
        mvc.perform(get("/v1/transaction"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_007")))
                .andExpect(jsonPath("$.message",
                        is("Missing header. Please check the service documentation.")));
    }

    @Test
    public void searchTransactionsProfileNotFoundTestV1() throws Exception {
        mvc.perform(get("/v1/transaction?statuses={statuses}", Status.ACTIVE.name())
                .header("Profile-Id", 100))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("ERR_600")))
                .andExpect(jsonPath("$.message", is("Profile not found.")));
    }

    @Test
    public void getTransactionTestV1() throws Exception {
        mvc.perform(get("/v1/transaction/{id}", 1)
                .header("Profile-Id", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.source.id", is(1)))
                .andExpect(jsonPath("$.source.name", is("Groceries")))
                .andExpect(jsonPath("$.source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.translation.id", is(1)))
                .andExpect(jsonPath("$.translation.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$.translation.description", is("ULTMKT LMT")))
                .andExpect(jsonPath("$.translation.toDescription", is("Ultra Market")))
                .andExpect(jsonPath("$.translation.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$.description", is("ULTMKT LMT")))
                .andExpect(jsonPath("$.balance", is(-54.78)))
                .andExpect(jsonPath("$.inputDate", is("2018-09-19T18:55:34.534")));
    }

    @Test
    public void getTransactionNotFoundTestV1() throws Exception {
        mvc.perform(get("/v1/transaction/{id}", 100)
                .header("Profile-Id", 1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("ERR_900")))
                .andExpect(jsonPath("$.message", is("Transaction not found.")));
    }

    @Test
    public void getTransactionBadRequestTestV1() throws Exception {
        mvc.perform(get("/v1/transaction/{id}", "123abc")
                .header("Profile-Id", 1))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_006")))
                .andExpect(jsonPath("$.message",
                        is("Path parameter in wrong format. Please check the service documentation.")));
    }

    @Test
    public void getTransactionInvalidProfileTestV1() throws Exception {
        mvc.perform(get("/v1/transaction/{id}", 1)
                .header("Profile-Id", 2))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_910")))
                .andExpect(jsonPath("$.message",
                        is("This transaction does not belong to the provided profile.")));
    }

    @Test
    public void createTransactionTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/transaction/create-transaction.json"), Charset.forName("UTF-8"));

        mvc.perform(post("/v1/transaction")
                .header("Profile-Id", 1)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.source.id", is(1)))
                .andExpect(jsonPath("$.source.name", is("Groceries")))
                .andExpect(jsonPath("$.source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.translation.id", is(1)))
                .andExpect(jsonPath("$.translation.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$.translation.description", is("ULTMKT LMT")))
                .andExpect(jsonPath("$.translation.toDescription", is("Ultra Market")))
                .andExpect(jsonPath("$.translation.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.type", is(TransactionType.FIXED.name())))
                .andExpect(jsonPath("$.description", is("Ultra Market")))
                .andExpect(jsonPath("$.balance", is(-31.50)))
                .andExpect(jsonPath("$.inputDate", is("2018-09-19T18:55:34.534")));
    }

    @Test
    public void createTransactionBadRequestTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/transaction/create-transaction-bad-request.json"), Charset.forName("UTF-8"));

        mvc.perform(post("/v1/transaction")
                .header("Profile-Id", 1)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_005")))
                .andExpect(jsonPath("$.message",
                        is("Missing body parameter. Please check the service documentation.")));
    }

    @Test
    public void updateTransactionTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/transaction/update-transaction.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/transaction/{id}", 3)
                .header("Profile-Id", 2)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.source.id", is(5)))
                .andExpect(jsonPath("$.source.name", is("Bills")))
                .andExpect(jsonPath("$.source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.translation.id", is(6)))
                .andExpect(jsonPath("$.translation.type", is(TransactionType.FIXED.name())))
                .andExpect(jsonPath("$.translation.description", is("ELT CMP")))
                .andExpect(jsonPath("$.translation.toDescription", is("Electric Bill")))
                .andExpect(jsonPath("$.translation.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.type", is(TransactionType.FIXED.name())))
                .andExpect(jsonPath("$.description", is("Electric Bill")))
                .andExpect(jsonPath("$.balance", is(-43.12)))
                .andExpect(jsonPath("$.inputDate", is("2018-09-21T18:55:34.534")));
    }

    @Test
    public void updateTransactionNotFoundTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/transaction/update-transaction.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/transaction/{id}", 100)
                .header("Profile-Id", 1)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("ERR_900")))
                .andExpect(jsonPath("$.message", is("Transaction not found.")));
    }

    @Test
    public void updateTransactionEmptyTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/transaction/update-transaction-empty.json"), Charset.forName("UTF-8"));

        mvc.perform(put("/v1/transaction/{id}", 1)
                .header("Profile-Id", 1)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.source.id", is(1)))
                .andExpect(jsonPath("$.source.name", is("Groceries")))
                .andExpect(jsonPath("$.source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.translation.id", is(1)))
                .andExpect(jsonPath("$.translation.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$.translation.description", is("ULTMKT LMT")))
                .andExpect(jsonPath("$.translation.toDescription", is("Ultra Market")))
                .andExpect(jsonPath("$.translation.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$.description", is("ULTMKT LMT")))
                .andExpect(jsonPath("$.balance", is(-54.78)))
                .andExpect(jsonPath("$.inputDate", is("2018-09-19T18:55:34.534")));
    }

    @Test
    public void updateTransactionBadRequestTestV1() throws Exception {
        mvc.perform(put("/v1/transaction/{id}", "123abc")
                .header("Profile-Id", 1))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_006")))
                .andExpect(jsonPath("$.message",
                        is("Path parameter in wrong format. Please check the service documentation.")));
    }

    @Test
    public void deleteTransactionTestV1() throws Exception {
        mvc.perform(delete("/v1/transaction/{id}", 1)
                .header("Profile-Id", 1))
                .andExpect(status().isOk());

        mvc.perform(get("/v1/profile/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(123511.56)));
    }

    @Test
    public void deleteTransactionNotFoundTestV1() throws Exception {
        mvc.perform(delete("/v1/transaction/{id}", 100)
                .header("Profile-Id", 1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("ERR_900")))
                .andExpect(jsonPath("$.message", is("Transaction not found.")));
    }

    @Test
    public void deleteTransactionBadRequestTestV1() throws Exception {
        mvc.perform(delete("/v1/transaction/{id}", "123abc")
                .header("Profile-Id", 1))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_006")))
                .andExpect(jsonPath("$.message",
                        is("Path parameter in wrong format. Please check the service documentation.")));
    }

    @Test
    public void deleteTransactionInvalidProfileTestV1() throws Exception {
        mvc.perform(delete("/v1/transaction/{id}", 1)
                .header("Profile-Id", 2))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_910")))
                .andExpect(jsonPath("$.message",
                        is("This transaction does not belong to the provided profile.")));
    }

    @Test
    public void uploadFileTestV1() throws Exception {
        String path = getClass().getResource("/payload/transaction/upload-file.ofx").getPath();

        mvc.perform(get("/v1/transaction/upload?filePath={filePath}", path)
                .header("Profile-Id", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].translation.id", is(1)))
                .andExpect(jsonPath("$[0].translation.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$[0].translation.description", is("ULTMKT LMT")))
                .andExpect(jsonPath("$[0].translation.toDescription", is("Ultra Market")))
                .andExpect(jsonPath("$[0].translation.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$[0].description", is("ULTMKT LMT")))
                .andExpect(jsonPath("$[0].balance", is(-17.90)))
                .andExpect(jsonPath("$[0].inputDate", is("2018-08-02T00:00:00")))
                .andExpect(jsonPath("$[1].description", is("SMP HJK")))
                .andExpect(jsonPath("$[1].balance", is(-16.00)))
                .andExpect(jsonPath("$[1].inputDate", is("2018-08-03T00:00:00")))
                .andExpect(jsonPath("$[2].translation.id", is(5)))
                .andExpect(jsonPath("$[2].translation.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$[2].translation.description", is("PAT GSTAT")))
                .andExpect(jsonPath("$[2].translation.toDescription", is("Patrick Gas Station")))
                .andExpect(jsonPath("$[2].translation.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$[2].description", is("PAT GSTAT")))
                .andExpect(jsonPath("$[2].balance", is(-800.00)))
                .andExpect(jsonPath("$[2].inputDate", is("2018-08-04T00:00:00")));
    }

    @Test
    public void uploadFileNotFoundTestV1() throws Exception {
        String path = "/payload/transaction/upload-file-not-found.ofx";

        mvc.perform(get("/v1/transaction/upload?filePath={filePath}", path)
                .header("Profile-Id", 1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("ERR_920")))
                .andExpect(jsonPath("$.message", is("File not found.")));
    }

    @Test
    public void uploadFileReadExceptionTestV1() throws Exception {
        String path = getClass().getResource("/payload/transaction/upload-file-invalid.ofx").getPath();

        mvc.perform(get("/v1/transaction/upload?filePath={filePath}", path)
                .header("Profile-Id", 1))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("ERR_930")))
                .andExpect(jsonPath("$.message", is("Error while reading the OFX file.")));
    }

    @Test
    public void uploadTransactionsTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/transaction/upload-transactions.json"), Charset.forName("UTF-8"));

        mvc.perform(post("/v1/transaction/upload")
                .header("Profile-Id", 1)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].source.id", is(1)))
                .andExpect(jsonPath("$[0].source.name", is("Groceries")))
                .andExpect(jsonPath("$[0].source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$[0].translation.id", is(1)))
                .andExpect(jsonPath("$[0].translation.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$[0].translation.description", is("ULTMKT LMT")))
                .andExpect(jsonPath("$[0].translation.toDescription", is("Ultra Market")))
                .andExpect(jsonPath("$[0].translation.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$[0].type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$[0].description", is("Ultra Market")))
                .andExpect(jsonPath("$[0].balance", is(-31.50)))
                .andExpect(jsonPath("$[0].inputDate", is("2018-09-19T18:55:34.534")))
                .andExpect(jsonPath("$[1].source.id", is(1)))
                .andExpect(jsonPath("$[1].source.name", is("Groceries")))
                .andExpect(jsonPath("$[1].source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$[1].translation.id", is(2)))
                .andExpect(jsonPath("$[1].translation.type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$[1].translation.description", is("TIDE 24HRS")))
                .andExpect(jsonPath("$[1].translation.toDescription", is("Tide Store")))
                .andExpect(jsonPath("$[1].translation.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$[1].type", is(TransactionType.VARIABLE.name())))
                .andExpect(jsonPath("$[1].description", is("Tide Store")))
                .andExpect(jsonPath("$[1].balance", is(-56.33)))
                .andExpect(jsonPath("$[1].inputDate", is("2018-09-19T19:55:34.534")))
                .andExpect(jsonPath("$[2].source.id", is(3)))
                .andExpect(jsonPath("$[2].source.name", is("Gas")))
                .andExpect(jsonPath("$[2].source.status", is(Status.ACTIVE.name())))
                .andExpect(jsonPath("$[2].type", is(TransactionType.FIXED.name())))
                .andExpect(jsonPath("$[2].description", is("GAS PETROL MARTIN")))
                .andExpect(jsonPath("$[2].balance", is(-100.77)))
                .andExpect(jsonPath("$[2].inputDate", is("2018-09-19T20:55:34.534")));
    }

    @Test
    public void uploadTransactionsBadRequestTestV1() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().
                getResourceAsStream("payload/transaction/upload-transactions-bad-request.json"),
                Charset.forName("UTF-8"));

        mvc.perform(post("/v1/transaction/upload")
                .header("Profile-Id", 1)
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.code", is("ERR_003")))
                .andExpect(jsonPath("$.message",
                        is("Could not serialize a field, wrong format. Please check the service documentation.")));
    }
}
