package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.MediumEntity;
import com.bus.tracking.bus_tracking_system.repository.MediumEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediumService {

    @Autowired
    private MediumEntityRepository mediumRepository;

    public MediumEntity saveMedium(MediumEntity medium){
        return mediumRepository.save(medium);
    }

    public List<MediumEntity> getAllMedium(){
        return mediumRepository.findAll();
    }

    public MediumEntity getMediumById(Long id){
        Optional<MediumEntity> medium = mediumRepository.findById(id);
        return medium.orElse(null);
    }

    public MediumEntity updateMedium(Long id, MediumEntity updatedMedium) {
        MediumEntity medium = mediumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medium not found"));

        medium.setMediumName(updatedMedium.getMediumName());

        return mediumRepository.save(medium);
    }

    public void deleteMedium(Long id){
        mediumRepository.deleteById(id);
    }
}