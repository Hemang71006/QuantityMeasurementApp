
package com.quantitymeasurement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * Project Name : Quantity Measurement App
 * Module : UC17 - Spring Boot Integration
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Integration tests for quantity measurement REST endpoints.
 */

@SpringBootTest
@AutoConfigureMockMvc
class QuantityMeasurementRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Verifies that the Spring Boot application health endpoint is accessible.
    @Test
    void givenApplication_whenHealthChecked_thenReturnUp() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    // Verifies comparison of two equal quantities through the REST API.
    @Test
    void givenEqualQuantities_whenCompared_thenReturnTrue() throws Exception {
        String request = """
                {
                  "first": {
                    "value": 1,
                    "measurementType": "LENGTH",
                    "unit": "FEET"
                  },
                  "second": {
                    "value": 1,
                    "measurementType": "LENGTH",
                    "unit": "FEET"
                  }
                }
                """;

        mockMvc.perform(post("/api/v1/quantities/compare")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }

    // Verifies addition of two quantities through the REST API.
    @Test
    void givenTwoLengths_whenAdded_thenReturnSum() throws Exception {
        String request = """
                {
                  "first": {
                    "value": 1,
                    "measurementType": "LENGTH",
                    "unit": "FEET"
                  },
                  "second": {
                    "value": 2,
                    "measurementType": "LENGTH",
                    "unit": "FEET"
                  }
                }
                """;

        mockMvc.perform(post("/api/v1/quantities/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(3.0));
    }

    // Verifies conversion of a quantity into the requested target unit.
    @Test
    void givenLength_whenConverted_thenReturnConvertedValue() throws Exception {
        String request = """
                {
                  "quantity": {
                    "value": 1,
                    "measurementType": "LENGTH",
                    "unit": "FEET"
                  },
                  "targetUnit": "INCHES"
                }
                """;

        mockMvc.perform(post("/api/v1/quantities/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(12.0));
    }

    // Verifies that request validation rejects missing required quantities.
    @Test
    void givenInvalidRequest_whenAdded_thenReturnBadRequest() throws Exception {
        String request = """
                {
                  "first": null,
                  "second": {
                    "value": 2,
                    "measurementType": "LENGTH",
                    "unit": "FEET"
                  }
                }
                """;

        mockMvc.perform(post("/api/v1/quantities/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());
    }

    // Verifies that the measurement history endpoint returns successfully.
    @Test
    void whenHistoryRequested_thenReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/quantities/history"))
                .andExpect(status().isOk());
    }

    // Verifies that the history count endpoint returns a numeric count.
    @Test
    void whenHistoryCountRequested_thenReturnCount() throws Exception {
        mockMvc.perform(get("/api/v1/quantities/history/count"))
                .andExpect(status().isOk());
    }
}
