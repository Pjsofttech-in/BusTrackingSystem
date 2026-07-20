package com.app.bustracking.Mapper;

import com.app.bustracking.Model.BusLocationModel;
import com.app.bustracking.Model.BusModel;
import com.app.bustracking.Model.DirectionModel;
import com.app.bustracking.Request.BusLocationRequest;
import com.app.bustracking.Response.BusLocationResponse;
import com.app.bustracking.repository.BusRepository;
import com.app.bustracking.repository.DirectionRepository;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface BusLocationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bus", ignore = true)
    @Mapping(target = "direction", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "timestamp", expression = "java(request.timestamp() != null ? request.timestamp() : LocalDateTime.now())")
    BusLocationModel toModel(BusLocationRequest request,
                             @Context BusRepository busRepository,
                             @Context DirectionRepository directionRepository);

    @AfterMapping
    default void afterToModel(@MappingTarget BusLocationModel model,
                              BusLocationRequest request,
                              @Context BusRepository busRepository,
                              @Context DirectionRepository directionRepository) {
        // Set bus relation
        if (request.busId() != null) {
            BusModel bus = busRepository.findById(request.busId())
                    .orElseThrow(() -> new RuntimeException("Bus not found with id: " + request.busId()));
            model.setBus(bus);
        }

        // Set direction relation
        if (request.directionId() != null) {
            DirectionModel direction = directionRepository.findById(request.directionId())
                    .orElseThrow(() -> new RuntimeException("Direction not found with id: " + request.directionId()));
            model.setDirection(direction);
        } else if (request.heading() != null) {
            // Optional: auto-assign direction from heading
            directionRepository.findByHeading(request.heading())
                    .ifPresent(model::setDirection);
        }
    }

    @Mapping(target = "busId", source = "bus.id")
    @Mapping(target = "busNumber", source = "bus.busNumber")
    @Mapping(target = "directionId", source = "direction.id")
    @Mapping(target = "directionName", source = "direction.name")
    BusLocationResponse toResponse(BusLocationModel model);
}