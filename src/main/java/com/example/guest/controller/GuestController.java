package com.example.guest.controller;

import com.example.guest.model.dto.GuestDto;
import com.example.guest.model.request.CreateGuestRequest;
import com.example.guest.model.request.UpdateRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import javax.validation.Valid;

@RequestMapping("/api/guest")
public interface GuestController {

  /**
   * Get a guest base on his/her id
   * @param guestId id of guest
   * @return GuestDto guest model response {@link GuestDto}
   */
  @GetMapping("/{guestId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found guest.",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = GuestDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Guest not found.",
          content = @Content)
  })
  ResponseEntity<GuestDto> getGuest(@PathVariable String guestId);

  /**
   * Get all the guests
   * @return List<GuestDto> list of guest {@link GuestDto}
   */
  @GetMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found guest.",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = GuestDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Guest not found.",
          content = @Content)
  })
  ResponseEntity<List<GuestDto>> getGuestList();

  /**
   * Create a new guest
   * @param request create guest request {@link CreateGuestRequest}
   * @return GuestDto guest model response {@link GuestDto}
   */
  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Guest is created.",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = GuestDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Failed to create guest.",
          content = @Content)
  })
  ResponseEntity<GuestDto> createGuest(@Valid @RequestBody CreateGuestRequest request);

  /**
   * Update an existing guest
   * @param guestId id of guest
   * @param request update info of guest {@link UpdateRequest}
   * @return GuestDto guest model response {@link GuestDto}
   */
  @PutMapping("/{guestId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Guest is updated.",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = GuestDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Failed to update guest.",
          content = @Content)
  })
  ResponseEntity<GuestDto> updateGuest(@PathVariable String guestId,@Valid @RequestBody UpdateRequest request);

  /**
   * Delete an existing guest
   * @param guestId id of guest
   * @return Boolean
   */
  @DeleteMapping("/{guestId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Guest is deleted.",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = GuestDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Failed to delete guest.",
          content = @Content)
  })
  ResponseEntity<Boolean> deleteGuest(@PathVariable String guestId);
}
