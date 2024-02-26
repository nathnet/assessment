package com.kbtg.bootcamp.posttest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kbtg.bootcamp.posttest.api.request.PostLotteryRequest;
import com.kbtg.bootcamp.posttest.api.response.PostLotteryResponse;
import com.kbtg.bootcamp.posttest.service.LotteryService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AdminControllerTests {
  
  @Mock
  private LotteryService lotteryService;

  private MockMvc mockMvc;
  private AdminController adminController;

  public void setup() {
    adminController = new AdminController(lotteryService);
    mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
  }

  @Test
  public void testCreateOneLottery_success() {
    String lotteryId = "123456";

    PostLotteryRequest request = PostLotteryRequest.builder()
        .lotteryId(lotteryId)
        .price(new BigDecimal(80))
        .amount(1)
        .build();
    
    ResponseEntity<PostLotteryResponse> results = adminController.createOneLottery(request);
  
    assertEquals(HttpStatus.CREATED, results.getStatusCode());

    PostLotteryResponse response = results.getBody();
    assertEquals(lotteryId, response.getLotteryId());
  }

  @ParameterizedTest
  @MethodSource("provideCreateOneLotteryInvalidInputs")
  public void testCreateOneLottery_errors(final String inputLotteryId,
        final HttpStatusCode expectedStatusCode,
        final String expectedBody) {
    PostLotteryRequest request = PostLotteryRequest.builder()
        .lotteryId(inputLotteryId)
        .price(new BigDecimal(80))
        .amount(1)
        .build();
    
    ResponseEntity<PostLotteryResponse> results = adminController.createOneLottery(request);
  
    assertEquals(expectedStatusCode, results.getStatusCode());

    String response = results.toString();
    assertTrue(response.contains(expectedBody));
  }


  private static Stream<Arguments> provideCreateOneLotteryInvalidInputs() {
    return Stream.of(
      Arguments.of(null, HttpStatus.BAD_REQUEST, "Ticket number cannot be empty."),
      Arguments.of("", HttpStatus.BAD_REQUEST, "Ticket number cannot be empty."),
      Arguments.of("12345", HttpStatus.BAD_REQUEST, "Ticket number must be exactly 6 digits."),
      Arguments.of("123ddr", HttpStatus.BAD_REQUEST, "Ticket number should be from 0-9 for 6 digits. Example: 123456")
    );
  }
}
