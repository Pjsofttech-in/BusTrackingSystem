package com.app.bustracking.service;

import com.app.bustracking.Request.MediumRequest;
import com.app.bustracking.Response.MediumResponse;

import java.util.List;

public interface MediumService {

    List<MediumResponse> getAll();

    MediumResponse getById(Long id);

    MediumResponse create(MediumRequest request);

    MediumResponse update(Long id, MediumRequest request);

    void delete(Long id);
}