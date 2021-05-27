package com.example.guest.controller;

import com.example.guest.model.dto.GuestDto;
import com.example.guest.model.request.CreateGuestRequest;
import com.example.guest.model.request.UpdateRequest;
import com.example.guest.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GuestControllerImpl implements GuestController{

  @Autowired
  private GuestService service;

  public GuestControllerImpl(GuestService guestService) {
    this.service = guestService;
  }

  @Override
  public ResponseEntity<GuestDto> getGuest(String guestId) {
    return ResponseEntity.ok(service.getGuestById(guestId));
  }

  @Override
  public ResponseEntity<List<GuestDto>> getGuestList() {
    return ResponseEntity.ok(service.getAllGuest());
  }

  @Override
  public ResponseEntity<GuestDto> createGuest(CreateGuestRequest request) {
    return ResponseEntity.ok(service.createGuest(request));
  }

  @Override
  public ResponseEntity<GuestDto> updateGuest(String guestId, UpdateRequest guestDto) {
    return ResponseEntity.ok(service.updateGuest(guestId, guestDto));
  }

  @Override
  public ResponseEntity<Boolean> deleteGuest(String guestId) {
    return ResponseEntity.ok(service.deleteGuest(guestId));
  }

}
