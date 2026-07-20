// src/main/java/com/app/bustracking/mapper/BusStopMapper.java
package com.app.bustracking.Mapper;

import com.app.bustracking.Model.BusStopModel;
import com.app.bustracking.Request.BusStopRequest;
import com.app.bustracking.Response.BusStopResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusStopMapper {

    @Mapping(target = "id", ignore = true)

    @Mapping(target = "reached", ignore = true)
    @Mapping(target = "reachedAt", ignore = true)
    BusStopModel toModel(BusStopRequest request);



    BusStopResponse toResponse(BusStopModel model);
}