package com.project.service.offering.service;

import com.project.service.offering.dto.CategoryDTO;
import com.project.service.offering.dto.SalonDTO;
import com.project.service.offering.dto.ServiceDTO;
import com.project.service.offering.model.ServiceOffering;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(SalonDTO salonDto, ServiceDTO serviceDto, CategoryDTO categoryDto);

    ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception;

    Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId);

    Set<ServiceOffering> getServicesByIds(Set<Long> ids);

    ServiceOffering getServiceById(Long id) throws Exception;

}
