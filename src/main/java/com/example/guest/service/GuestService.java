package com.example.guest.service;

import com.example.guest.model.dto.GuestDto;
import com.example.guest.model.request.CreateGuestRequest;
import com.example.guest.model.request.UpdateRequest;

import java.util.List;

public interface GuestService {

  /**
   * Get a guest by his/her guestId
   * @param guestId the id of a guest
   * @return GuestDto guest's response model {@link GuestDto}
   */
  GuestDto getGuestById(String guestId);

  /**
   * Get all the guests
   * @return List<GuestDto>
   */
  List<GuestDto> getAllGuest();

  /**
   * Create a new guest
   * @param createGuestRequest create guest request {@link CreateGuestRequest}
   * @return GuestDto guest's response model {@link GuestDto}
   */
  GuestDto createGuest(CreateGuestRequest createGuestRequest);

  /**
   * update an existing guest
   * @param guestId guestId the id of a guest
   * @param request update info guest requeset {@link UpdateRequest}
   * @return GuestDto guest's response model {@link GuestDto}
   */
  GuestDto updateGuest(String guestId, UpdateRequest request);

  /**
   * delete an existing guest
   * @param guestId guestId the id of a guest
   * @return Boolean
   */
  Boolean deleteGuest(String guestId);
}
