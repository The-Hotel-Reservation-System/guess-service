package com.example.guest.service;

import com.example.guest.exception.GuestServiceErrorResponse;
import com.example.guest.exception.GuestServiceException;
import com.example.guest.model.dto.GuestDto;
import com.example.guest.model.entity.Guest;
import com.example.guest.model.request.CreateGuestRequest;
import com.example.guest.model.request.UpdateRequest;
import com.example.guest.repository.GuestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GuestServiceImpl implements GuestService {

  private GuestRepository guestRepository;

  public GuestServiceImpl(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }

  @Override
  public GuestDto getGuestById(String guestId) {
    log.info("Get guest with id {}", guestId);
    return guestRepository.findById(new BigInteger(guestId))
        .map(guest -> convertGuestToDto(guest))
        .orElseThrow(() -> {
          log.error("Guest with id {} not found.", guestId);
          return new GuestServiceException(GuestServiceErrorResponse.GUEST_NOT_FOUND);
        });
  }

  @Override
  public List<GuestDto> getAllGuest() {
    log.info("Get all guests");
    List<GuestDto> list = new ArrayList<>();
    guestRepository.findAll().forEach(guest -> list.add(convertGuestToDto(guest)));
    return list;
  }

  @Override
  public GuestDto createGuest(CreateGuestRequest createGuestRequest) {
    log.info("Create guest with request {}", createGuestRequest);
    Guest guest = buildGuestModel(createGuestRequest);
    return convertGuestToDto(guestRepository.save(guest));
  }

  private Guest buildGuestModel(CreateGuestRequest createGuestRequest) {
    Guest guest = Guest.builder()
        .phone(createGuestRequest.getPhone())
        .name(createGuestRequest.getName())
        .email(createGuestRequest.getEmail())
        .createdDate(Instant.now())
        .build();
    return guest;
  }

  @Override
  public GuestDto updateGuest(String guestId, UpdateRequest request) {
    log.info("Update guest with request {}", request);
    return guestRepository.findById(new BigInteger(guestId))
        .map(guest -> executeUpdateRequest(request, guest))
        .orElseThrow(() -> {
          log.error("Guest with id {} not found.", guestId);
          return new GuestServiceException(GuestServiceErrorResponse.GUEST_NOT_FOUND);
        });
  }

  private GuestDto executeUpdateRequest(UpdateRequest request, Guest guest) {
    guest.setName(Optional.ofNullable(request.getName()).orElse(guest.getName()));
    guest.setPhone(Optional.ofNullable(request.getPhone()).orElse(guest.getPhone()));
    return convertGuestToDto(guestRepository.save(guest));
  }

  @Override
  public Boolean deleteGuest(String guestId) {
    log.info("delete guest with id {}", guestId);
    return guestRepository.findById(new BigInteger(guestId))
        .map(guest -> deleteGuestByGuestId(guestId))
        .orElseThrow(() -> {
          log.error("Guest with id {} not found.", guestId);
          return new GuestServiceException(GuestServiceErrorResponse.GUEST_NOT_FOUND);
        });
  }

  private Boolean deleteGuestByGuestId(String guestId) {
    guestRepository.deleteById(new BigInteger(guestId));
    return Boolean.TRUE;
  }

  private GuestDto convertGuestToDto(Guest guest) {
    return GuestDto.builder()
        .email(guest.getEmail())
        .name(guest.getName())
        .phone(guest.getPhone())
        .id(guest.getId())
        .build();
  }
}
