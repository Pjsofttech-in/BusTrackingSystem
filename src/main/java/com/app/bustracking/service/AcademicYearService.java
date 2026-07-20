package com.app.bustracking.service;

import com.app.bustracking.Request.AcademicYearRequest;
import com.app.bustracking.Response.AcademicYearResponse;

import java.util.List;

public interface AcademicYearService {

    List<AcademicYearResponse> getAll();

    AcademicYearResponse getById(Long id);

    AcademicYearResponse create(AcademicYearRequest request);

    AcademicYearResponse update(Long id, AcademicYearRequest request);

    void delete(Long id);
}