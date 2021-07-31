package com.meli.test.dna.presentation.exception;

public class ValidationExceptionDetails extends ExceptionDetails {
    private String fields;
    private String fieldsMessage;

    public String getFields() {
        return fields;
    }

    public String getFieldsMessage() {
        return fieldsMessage;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public void setFieldsMessage(String fieldsMessage) {
        this.fieldsMessage = fieldsMessage;
    }
}
