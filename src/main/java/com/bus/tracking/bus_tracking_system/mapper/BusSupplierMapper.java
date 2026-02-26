package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.BusSupplierRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusSupplierResponseDTO;
import com.bus.tracking.bus_tracking_system.model.BusSupplier;

public class BusSupplierMapper {

    // DTO → Entity
    public static BusSupplier toEntity(BusSupplierRequestDTO dto) {

        BusSupplier supplier = new BusSupplier();

        supplier.setSupplierName(dto.getSupplierName());
        supplier.setCompanyName(dto.getCompanyName());
        supplier.setMobile(dto.getMobile());
        supplier.setEmail(dto.getEmail());
        supplier.setAddress(dto.getAddress());

        return supplier;
    }

    // Entity → DTO
    public static BusSupplierResponseDTO toDTO(BusSupplier supplier) {

        BusSupplierResponseDTO dto = new BusSupplierResponseDTO();

        dto.setId(supplier.getId());
        dto.setSupplierName(supplier.getSupplierName());
        dto.setCompanyName(supplier.getCompanyName());
        dto.setMobile(supplier.getMobile());
        dto.setEmail(supplier.getEmail());
        dto.setAddress(supplier.getAddress());

        return dto;
    }
}