package com.example.guest;

import com.example.guest.controller.GuestController;
import com.example.guest.controller.GuestControllerImpl;
import com.example.guest.model.dto.GuestDto;
import com.example.guest.model.entity.Guest;
import com.example.guest.model.request.CreateGuestRequest;
import com.example.guest.model.request.UpdateRequest;
import com.example.guest.repository.GuestRepository;
import com.example.guest.service.GuestService;
import com.example.guest.service.GuestServiceImpl;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseTestClass {
  private final String EMAIL = "test@gmail.com";
  private final String NAME = "Name";
  private final String PHONE = "1234567890";
  private final BigInteger ID = BigInteger.ONE;
  private Guest guest = Guest.builder().build();
  List<GuestDto> guestDtoList = new ArrayList<>();
  List<Guest> guestList = new ArrayList<>();
  @Mock
  GuestRepository guestRepository;
  GuestService guestService;
  public GuestController guestController;

  @Autowired
  WebApplicationContext context;

  @BeforeEach
  void init() {
    guestService = new GuestServiceImpl(guestRepository);
    guest = buildGuest();
    guestDtoList.add(buildGuestDto());
    guestList.add(buildGuest());
    guestController = new GuestControllerImpl(guestService);

    RestAssuredMockMvc.webAppContextSetup(this.context);

    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(guest));

    StandaloneMockMvcBuilder standaloneMockMvcBuilder
        = MockMvcBuilders.standaloneSetup(guestController);
    RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);

  }


  private GuestDto buildGuestDto() {
    return GuestDto.builder()
        .id(ID)
        .phone(PHONE)
        .name(NAME)
        .email(EMAIL)
        .build();
  }

  private Guest buildGuest() {
    return Guest.builder()
        .id(ID)
        .phone(PHONE)
        .name(NAME)
        .email(EMAIL)
        .createdDate(Instant.now())
        .build();
  }

  private CreateGuestRequest buildCreateGuestRequest() {
    return CreateGuestRequest.builder()
        .email(EMAIL)
        .name(NAME)
        .phone(PHONE)
        .build();
  }

  private UpdateRequest buildUpdateRequest() {
    return UpdateRequest.builder()
        .name(NAME)
        .phone(PHONE)
        .build();
  }
}
