package com.project.controller;

import com.project.mapper.SalonMapper;
import com.project.model.Salon;
import com.project.payload.dto.SalonDTO;
import com.project.payload.dto.UserDTO;
import com.project.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;
// http://localhost:5002/api/salons
    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.createSalon(salonDTO,userDTO);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    // http://localhost:5002/api/salons/2
    @PatchMapping("/{SalonId}")
    public ResponseEntity<SalonDTO> updateSalon(
            @PathVariable Long salonId,
            @RequestBody SalonDTO salonDTO) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon salon = salonService.updateSalon(salonDTO, userDTO, salonId);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    // http://localhost:5002/api/salons
    @GetMapping()
    public ResponseEntity<List<SalonDTO>> getSalons() throws Exception{

        List<Salon> salons = salonService.getAllSalons();
        List<SalonDTO> salonDTOS = salons.stream().map((salon) ->
        {
            SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
            return salonDTO;
        }
        ).toList();

        return  ResponseEntity.ok(salonDTOS);
    }

    // http://localhost:5002/api/salons/5
    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getSalonsById(
            @PathVariable Long salonId
    ) throws Exception{

        Salon salon=salonService.getSalonById(salonId);

        SalonDTO salonDTO=SalonMapper.mapToDTO(salon);

        return ResponseEntity.ok(salonDTO);
    }

    // http://localhost:5002/api/salons/search?city=mumbai
    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(
            @RequestParam("city") String city
    ) throws Exception{

        List<Salon> salons = salonService.searchSalonByCity(city);
        List<SalonDTO> salonDTOS = salons.stream().map((salon) ->
                {
                    SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
                    return salonDTO;
                }
        ).toList();

        return  ResponseEntity.ok(salonDTOS);
    }

    // http://localhost:5002/api/salons/5
    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonsByOwnerId(
            @PathVariable Long salonId
    ) throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon salon=salonService.getSalonByOwnerId(userDTO.getId());

        SalonDTO salonDTO=SalonMapper.mapToDTO(salon);

        return ResponseEntity.ok(salonDTO);
    }
}


