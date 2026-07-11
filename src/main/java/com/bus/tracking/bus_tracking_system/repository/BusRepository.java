package com.bus.tracking.bus_tracking_system.repository;

import com.bus.tracking.bus_tracking_system.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    // Find by bus number
    Optional<Bus> findByBusNumber(String busNumber);

    // Find by status
    List<Bus> findByStatus(String status);

    // Find by service provider ID
    List<Bus> findByServiceProviderId(Long serviceProviderId);

    // JOIN QUERY: Find buses where bus.busNumber = serviceProvider.busNumber
    @Query("SELECT b FROM Bus b JOIN b.serviceProvider sp WHERE b.busNumber = sp.busNumber")
    List<Bus> findBusesMatchingProviderBusNumber();

    // JOIN QUERY: Find buses by service provider bus number
    @Query("SELECT b FROM Bus b JOIN b.serviceProvider sp WHERE sp.busNumber = :providerBusNumber")
    List<Bus> findByServiceProviderBusNumber(@Param("providerBusNumber") String providerBusNumber);

    // JOIN QUERY: Find bus with service provider data (Eager Loading)
    @Query("SELECT b FROM Bus b LEFT JOIN FETCH b.serviceProvider WHERE b.id = :id")
    Optional<Bus> findByIdWithServiceProvider(@Param("id") Long id);

    // JOIN QUERY: Find all buses with service provider data
    @Query("SELECT b FROM Bus b LEFT JOIN FETCH b.serviceProvider")
    List<Bus> findAllWithServiceProvider();

    // JOIN QUERY: Find by provider bus number and status
    @Query("SELECT b FROM Bus b JOIN b.serviceProvider sp WHERE sp.busNumber = :providerBusNumber AND b.status = :status")
    List<Bus> findByProviderBusNumberAndStatus(
            @Param("providerBusNumber") String providerBusNumber,
            @Param("status") String status
    );

    // JOIN QUERY: Find buses where busNumber matches provider busNumber and is active
    @Query("SELECT b FROM Bus b JOIN b.serviceProvider sp WHERE b.busNumber = sp.busNumber AND b.status = 'ACTIVE'")
    List<Bus> findActiveBusesMatchingProviderBusNumber();

    // Custom query to get bus with provider details
    @Query("SELECT b.id, b.busNumber, sp.busNumber, sp.name FROM Bus b JOIN b.serviceProvider sp WHERE b.busNumber = sp.busNumber")
    List<Object[]> findBusProviderMatchDetails();
}