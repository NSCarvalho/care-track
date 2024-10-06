package com.caretrack.rest.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestResponseTest {

    @Test
    void successResponseShouldContainCorrectDataAndMessage() {
        String message = "Operation successful";
        String data = "Test data";
        RestResponse<String> response = RestResponse.success(data, message);

        assertTrue(response.isSuccess());
        assertEquals(message, response.getMessage());
        assertEquals(data, response.getData());
    }

    @Test
    void successResponseShouldNotContainDataWhenNotProvided() {
        String message = "Operation successful";
        RestResponse<String> response = RestResponse.success(null, message);

        assertTrue(response.isSuccess());
        assertEquals(message, response.getMessage());
        assertNull(response.getData());
    }

    @Test
    void errorResponseShouldContainCorrectMessage() {
        String message = "Operation failed";
        RestResponse<String> response = RestResponse.error(message);

        assertFalse(response.isSuccess());
        assertEquals(message, response.getMessage());
        assertNull(response.getData());
    }
}