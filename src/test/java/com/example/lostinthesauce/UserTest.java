package com.example.lostinthesauce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User userTest;

    @BeforeEach
    void setUp() {
        userTest = new User("","");
    }

    @Test
    void getUsername() {
        userTest.setUsername("Lobster");
        assertEquals("Lobster", userTest.getUsername());
    }

    @Test
    void getPassword() {
        userTest.setPassword("Cake");
        assertEquals("Cake", userTest.getPassword());
    }

}