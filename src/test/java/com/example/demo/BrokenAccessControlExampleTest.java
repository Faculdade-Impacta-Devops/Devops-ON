package com.example.demo;

public class BrokenAccessControlExampleTest {
    
}
@Test
public void testGetUserWithValidId() throws Exception {
    String validId = "1";
    String expectedResponse = "Informações do usuário com ID: " + validId;

    mockMvc.perform(get("/user/" + validId))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedResponse));
}

@Test
public void testGetUserWithInvalidId() throws Exception {
    String invalidId = "999";
    String expectedResponse = "Informações do usuário com ID: " + invalidId;

    mockMvc.perform(get("/user/" + invalidId))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedResponse));
}

@Test
public void testGetUserWithNegativeId() throws Exception {
    String negativeId = "-1";
    String expectedResponse = "Informações do usuário com ID: " + negativeId;

    mockMvc.perform(get("/user/" + negativeId))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedResponse));
}

@Test
public void testGetUserWithSpecialCharacters() throws Exception {
    String specialId = "admin";
    String expectedResponse = "Informações do usuário com ID: " + specialId;

    mockMvc.perform(get("/user/" + specialId))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedResponse));
}