package com.example.guest.service;

import com.example.guest.exception.GuestServiceException;
import com.example.guest.model.dto.GuestDto;
import com.example.guest.model.entity.Guest;
import com.example.guest.model.request.CreateGuestRequest;
import com.example.guest.model.request.UpdateRequest;
import com.example.guest.repository.GuestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class GuestServiceTest {

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


  @BeforeEach
  void init() {
    guestService = new GuestServiceImpl(guestRepository);
    guest = buildGuest();
    guestDtoList.add(buildGuestDto());
    guestList.add(buildGuest());
  }

  @Test
  void getGuest_shouldWork() {
    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(guest));
    GuestDto result  = guestService.getGuestById("200");
    Assertions.assertEquals(EMAIL, result.getEmail());
    Assertions.assertEquals(PHONE, result.getPhone());
    Assertions.assertEquals(NAME, result.getName());
    Assertions.assertEquals(ID, result.getId());

  }

  @Test
  void getGuest_NotFound_shouldThrowException() {
    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(GuestServiceException.class, () -> guestService.getGuestById("200"));
  }

  @Test
  void getAllGuest_shouldWork() {
    Mockito.when(guestRepository.findAll()).thenReturn(guestList);
    List<GuestDto> result  = guestService.getAllGuest();
    Assertions.assertEquals(EMAIL, result.get(0).getEmail());
  }

  @Test
  void createGuest_shouldWork() {
    Mockito.when(guestRepository.save(Mockito.any(Guest.class))).thenReturn(guest);
    GuestDto result  = guestService.createGuest(buildCreateGuestRequest());
    Assertions.assertEquals(EMAIL, result.getEmail());
  }

  @Test
  void updateGuest_shouldWork() throws Exception {
    Mockito.when(guestRepository.save(Mockito.any(Guest.class))).thenReturn(guest);
    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(guest));
    GuestDto result = guestService.updateGuest("12", buildUpdateRequest());
    Assertions.assertEquals(EMAIL, result.getEmail());
  }

  @Test
  void updateGuest_GuestNotFound_shouldThrowException() throws Exception {
    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    UpdateRequest request = buildUpdateRequest();
    Assertions.assertThrows(GuestServiceException.class, () -> guestService.updateGuest("12", request));
  }

  @Test
  void deleteGuest_shouldWork() throws Exception {
    Mockito.doNothing().when(guestRepository).deleteById(Mockito.any(BigInteger.class));
    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(guest));
    Boolean result = guestService.deleteGuest("12");
    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  void deleteGuest_GuestNotFound_shouldThrowException() throws Exception {
    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(GuestServiceException.class, () -> guestService.deleteGuest("12"));
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
