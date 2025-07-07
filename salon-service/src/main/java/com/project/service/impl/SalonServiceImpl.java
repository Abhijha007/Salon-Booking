
package com.project.service.impl;

import com.project.model.Salon;
import com.project.payload.dto.SalonDTO;
import com.project.payload.dto.UserDTO;
import com.project.repository.SalonRepository;
import com.project.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {

        Salon salon = new Salon();

        salon.setName(req.getName());
        salon.setAddress(req.getAddress());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setImages(req.getImages());
        salon.setCloseTime(req.getCloseTime());
        salon.setOpenTime(req.getOpenTime());
        salon.setOwnerId(user.getId());
        salon.setPhoneNumber(req.getPhoneNumber());

        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception {

        Salon existingSalon = salonRepository.findById(salonId).orElse(null);

        if(existingSalon != null){
            existingSalon.setCity(salon.getCity());
            existingSalon.setEmail(salon.getEmail());
            existingSalon.setName(salon.getName());
            existingSalon.setImages(salon.getImages());
            existingSalon.setAddress(salon.getAddress());
            existingSalon.setPhoneNumber(salon.getPhoneNumber());
            existingSalon.setOpenTime(salon.getOpenTime());
            existingSalon.setCloseTime(salon.getCloseTime());
            existingSalon.setOwnerId(user.getId());

            return salonRepository.save(existingSalon);

        }
        throw new Exception("salon not exist");
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) throws Exception {
        Salon salon =  salonRepository.findById(salonId).orElse(null);
        if(salon == null){
            throw new Exception("Salon doesn't exist");
        }
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
}
