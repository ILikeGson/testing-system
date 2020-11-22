package com.example.demo;

import org.example.service.IOService;
import org.example.service.ConsoleIOService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Test ConsoleIOService")
public class ConsoleIOServiceTest {
    private static final String TEXT_TO_PRINT1 = "Hello";
    private static final String TEXT_TO_PRINT2 = "\n=================GOOD BYE=================\n";

    private ByteArrayOutputStream arrayOutputStream;
    private IOService ioService;


    @Before
    public void setUp() {
        arrayOutputStream = new ByteArrayOutputStream();
        ioService = new ConsoleIOService(System.in, new PrintStream(arrayOutputStream));
    }

    @DisplayName("should print" + TEXT_TO_PRINT1)
    @Test
    public void isIOServicePrintFirstMessage() {
        ioService.out(TEXT_TO_PRINT1);
        assertThat(arrayOutputStream.toString()).isEqualTo(TEXT_TO_PRINT1);
    }

    @DisplayName("should print" + TEXT_TO_PRINT2)
    @Test
    public void isIOServicePrintSecondMessage() {
        ioService.out(TEXT_TO_PRINT2);
        assertThat(arrayOutputStream.toString()).isEqualTo(TEXT_TO_PRINT2);
    }


}
