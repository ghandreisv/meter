package com.ghandreisv.meter.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghandreisv.meter.api.dto.MeterReadingDto;
import com.ghandreisv.meter.service.MeterReadingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.ghandreisv.meter.api.dto.MeterReadingDtoFixture.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MeterReadingController.class)
class MeterReadingControllerTest {

    @MockBean
    private MeterReadingService meterReadingService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final String readingMeterId = UUID.randomUUID().toString();

    @Test
    void should_add_with_meter_id() throws Exception {
        MeterReadingDto meterReadingDto = withMeterId();

        when(meterReadingService.addMeterReadingByMeterId(
                meterReadingDto.getMeterId(),
                meterReadingDto.getDate(),
                meterReadingDto.getValue()))
                .thenReturn(readingMeterId);

        mockMvc.perform(post("/meter-readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(meterReadingDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(readingMeterId));

        verify(meterReadingService).addMeterReadingByMeterId(
                meterReadingDto.getMeterId(),
                meterReadingDto.getDate(),
                meterReadingDto.getValue());
    }

    @Test
    void should_add_with_address_id() throws Exception {
        MeterReadingDto meterReadingDto = withAddressId();

        when(meterReadingService.addMeterReadingByAddressId(
                meterReadingDto.getAddressId(),
                meterReadingDto.getDate(),
                meterReadingDto.getValue()))
                .thenReturn(readingMeterId);

        mockMvc.perform(post("/meter-readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(meterReadingDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(readingMeterId));

        verify(meterReadingService).addMeterReadingByAddressId(
                meterReadingDto.getAddressId(),
                meterReadingDto.getDate(),
                meterReadingDto.getValue());
    }

    @Test
    void should_add_with_client_id() throws Exception {
        MeterReadingDto meterReadingDto = withClientId();

        when(meterReadingService.addMeterReadingByClientId(
                meterReadingDto.getClientId(),
                meterReadingDto.getDate(),
                meterReadingDto.getValue()))
                .thenReturn(readingMeterId);

        mockMvc.perform(post("/meter-readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(meterReadingDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(readingMeterId));

        verify(meterReadingService).addMeterReadingByClientId(
                meterReadingDto.getClientId(),
                meterReadingDto.getDate(),
                meterReadingDto.getValue());
    }

    @Test
    void should_fail_without_ids() throws Exception {
        MeterReadingDto meterReadingDto = noIds();

        when(meterReadingService.addMeterReadingByClientId(
                meterReadingDto.getClientId(),
                meterReadingDto.getDate(),
                meterReadingDto.getValue()))
                .thenReturn(readingMeterId);

        mockMvc.perform(post("/meter-readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(meterReadingDto)))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(meterReadingService);
    }

    private String asJson(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }
}