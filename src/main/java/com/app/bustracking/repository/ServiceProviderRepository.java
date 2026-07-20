package com.app.bustracking.repository;

import com.app.bustracking.Model.ServiceProviderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProviderRepository extends JpaRepository<ServiceProviderModel, Long> {
}