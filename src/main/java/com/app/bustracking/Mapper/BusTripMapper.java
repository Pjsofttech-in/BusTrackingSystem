package com.app.bustracking.Mapper;

import com.app.bustracking.Model.BusTripModel;
import com.app.bustracking.Request.BusTripRequest;
import com.app.bustracking.Response.BusTripResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusTripMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bus", ignore = true)
    @Mapping(target = "route", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "conductor", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    BusTripModel toModel(BusTripRequest request);

    @Mapping(target = "busId", source = "bus.id")
    @Mapping(target = "busNumber", source = "bus.busNumber")
    @Mapping(target = "routeId", source = "route.id")
    @Mapping(target = "routeName", source = "route.routeName")
    @Mapping(target = "driverId", source = "driver.id")
    @Mapping(target = "driverName", source = "driver.name")
    @Mapping(target = "conductorId", source = "conductor.id")
    @Mapping(target = "conductorName", source = "conductor.name")
    BusTripResponse toResponse(BusTripModel trip);
}