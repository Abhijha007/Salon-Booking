package com.project.service.offering.service.impl;

import com.project.service.offering.dto.CategoryDTO;
import com.project.service.offering.dto.SalonDTO;
import com.project.service.offering.dto.ServiceDTO;
import com.project.service.offering.model.ServiceOffering;
import com.project.service.offering.repository.ServiceOfferingRepository;
import com.project.service.offering.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(SalonDTO salonDto, ServiceDTO serviceDto, CategoryDTO categoryDto) {

        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setImage(serviceDto.getImage());
        serviceOffering.setSalonId(serviceDto.getSalonId());
        serviceOffering.setName(serviceDto.getName());
        serviceOffering.setDescription(serviceDto.getDescription());
        serviceOffering.setCategoryId(serviceDto.getCategory());
        serviceOffering.setPrice(serviceDto.getPrice());
        serviceOffering.setDuration(serviceDto.getDuration());

        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception {

        ServiceOffering serviceOffering = serviceOfferingRepository.findById(serviceId).orElse(null);

        if(serviceOffering==null){
            throw new Exception("Service doesn't exist with id "+ serviceId);
        }

        serviceOffering.setImage(service.getImage());
        serviceOffering.setName(service.getName());
        serviceOffering.setDescription(service.getDescription());
        serviceOffering.setPrice(service.getPrice());
        serviceOffering.setDuration(service.getDuration());

        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {

        Set<ServiceOffering> services = serviceOfferingRepository
                .findBySalonId(salonId);

        if(categoryId != null){
            services=services.stream().filter((service)-> service.getCategoryId() != null
            && service.getCategoryId().equals(categoryId)).collect(Collectors.toSet());
        }
        return services;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {
        List<ServiceOffering> services =  serviceOfferingRepository.findAllById(ids);

        return new HashSet<>(services);
    }

    @Override
    public ServiceOffering getServiceById(Long id) throws Exception {
        ServiceOffering serviceOffering = serviceOfferingRepository.findById(id).orElse(null);

        if(serviceOffering==null){
            throw new Exception("Service doesn't exist with id "+ id);
        }
        return serviceOffering;
    }
}
