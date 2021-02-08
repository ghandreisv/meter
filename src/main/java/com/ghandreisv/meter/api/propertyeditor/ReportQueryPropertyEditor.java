package com.ghandreisv.meter.api.propertyeditor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghandreisv.meter.api.dto.ReportQueryDto;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class ReportQueryPropertyEditor extends PropertyEditorSupport {

    private final ObjectMapper objectMapper;

    public ReportQueryPropertyEditor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException("Query object must not be empty");
        }
        try {
            ReportQueryDto reportQueryDto = objectMapper.readValue(text, ReportQueryDto.class);
            setValue(reportQueryDto);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
