package com.gifprojects;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.HttpCookie;
import java.util.List;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public List<SoftwareEngineerDTO> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll()
                .stream()
                .map(entity -> new SoftwareEngineerDTO(
                        entity.getId(),
                        entity.getName(),
                        entity.getTechStack()
                ))
                .toList();
    }

    public SoftwareEngineerDTO getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id)
                .map(entity -> new SoftwareEngineerDTO(
                        entity.getId(),
                        entity.getName(),
                        entity.getTechStack()
                ))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, id + "not found"));
    }

    public void insertSoftwareEngineer(SoftwareEngineerDTO dto) {
        SoftwareEngineer entity = new SoftwareEngineer();
        entity.setName(dto.getName());
        entity.setTechStack(dto.getTechStack());

        softwareEngineerRepository.save(entity);
    }

    public void deleteSoftwareEngineerById(Integer id) {
        softwareEngineerRepository.findById(id)
                .ifPresentOrElse(
                        entity -> softwareEngineerRepository.deleteById(id),
                        () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + "not found.");}
                );
    }

    public void updateSoftwareEngineerById(Integer id, SoftwareEngineerDTO dto) {

        softwareEngineerRepository.findById(id)
                .ifPresentOrElse(
                        entity -> {
                            entity.setName(dto.getName());
                            entity.setTechStack(dto.getTechStack());
                            softwareEngineerRepository.save(entity);
                        },
                        () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + "not found.");}
                );
    }
}
