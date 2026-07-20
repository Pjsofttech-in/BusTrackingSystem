package com.app.bustracking.Mapper;

import com.app.bustracking.Model.BusRouteModel;
import com.app.bustracking.Request.BusRouteRequest;
import com.app.bustracking.Response.BusRouteResponse;
import com.app.bustracking.Response.BusRouteStopResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
@Mapper(componentModel = "spring")
public interface BusRouteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bus", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "conductor", ignore = true)
    @Mapping(target = "stops", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "startStop", ignore = true)
    @Mapping(target = "endStop", ignore = true)
    BusRouteModel toModel(BusRouteRequest request);

    @Mapping(target = "busId", source = "bus.id")
    @Mapping(target = "busNumber", source = "bus.busNumber")
    @Mapping(target = "driverId", source = "driver.id")
    @Mapping(target = "driverName", source = "driver.name")
    @Mapping(target = "conductorId", source = "conductor.id")
    @Mapping(target = "conductorName", source = "conductor.name")
    @Mapping(target = "stops", expression = "java(mapStops(route))")
    @Mapping(target = "startStopId", source = "startStop.id")
    @Mapping(target = "startStopName", source = "startStop.stopName")
    @Mapping(target = "endStopId", source = "endStop.id")
    @Mapping(target = "endStopName", source = "endStop.stopName")
    BusRouteResponse toResponse(BusRouteModel route);

    default List<BusRouteStopResponse> mapStops(BusRouteModel route) {
        return route.getStops().stream()
                .map(rs -> new BusRouteStopResponse(
                        rs.getStop().getId(),
                        rs.getStop().getStopName(),
                        rs.getStop().getLatitude(),
                        rs.getStop().getLongitude(),
                        rs.getSequence()
                ))
                .toList();
    }
}