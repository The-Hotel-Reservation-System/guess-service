package com.example.guest.controller;

import com.example.guest.model.dto.GuestDto;
import com.example.guest.model.request.CreateGuestRequest;
import com.example.guest.model.request.UpdateRequest;
import com.example.guest.service.GuestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class GuestControllerTest {

  private final String EMAIL = "test@gmail.com";
  private final String NAME = "Name";
  private final String PHONE = "1234567890";
  private final BigInteger ID = BigInteger.ONE;
  public GuestController guestController;
  public GuestService guestService;
  GuestDto guestDto;

  private GuestDto buildGuestDto() {
    return GuestDto.builder()
        .id(ID)
        .phone(PHONE)
        .name(NAME)
        .email(EMAIL)
        .build();
  }

  @BeforeEach
  public void init() {
    guestService = Mockito.mock(GuestService.class);
    guestController = new GuestControllerImpl(guestService);
    guestDto = buildGuestDto();
  }

  @Test
  public void getGuestByGuestId_shouldWork() {
    Mockito.when(guestService.getGuestById(Mockito.any(String.class)))
        .thenReturn(guestDto);
    ResponseEntity<GuestDto> responses = guestController.getGuest("123");
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
    Assertions.assertEquals(guestDto, responses.getBody());
  }

  @Test
  public void getAll_shouldWork() {
    List<GuestDto> list = new ArrayList<>();
    GuestDto guestDto = GuestDto.builder().build();
    list.add(guestDto);
    Mockito.when(guestService.getAllGuest()).thenReturn(list);
    ResponseEntity<List<GuestDto>> responses = guestController.getGuestList();
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void createGuest_shouldWork() {
    Mockito.when(guestService.createGuest(Mockito.any(CreateGuestRequest.class)))
        .thenReturn(GuestDto.builder().build());
    ResponseEntity<GuestDto> responses = guestController.createGuest(Mockito.any(CreateGuestRequest.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void updateGuest_shouldWork() throws Exception {
    Mockito.when(guestService.updateGuest(Mockito.any(String.class), Mockito.any(UpdateRequest.class)))
        .thenReturn(GuestDto.builder().build());
    ResponseEntity<GuestDto> responses = guestController.updateGuest(Mockito.any(String.class), Mockito.any(UpdateRequest.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void   deleteGuest_shouldWork() {
    Mockito.when(guestService.deleteGuest(Mockito.any(String.class)))
        .thenReturn(Boolean.TRUE);
    ResponseEntity<Boolean> responses = guestController.deleteGuest(Mockito.any(String.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }


}
