package com.qa.sfia3.service;

import com.qa.sfia3.domain.Trainee;
import com.qa.sfia3.dto.TraineeDTO;
import com.qa.sfia3.exceptions.TraineeNotFoundException;
import com.qa.sfia3.repo.TraineeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TraineeService {

    private final TraineeRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public TraineeService(TraineeRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private TraineeDTO mapToDTO(Trainee trainee) { return this.mapper.map(trainee, TraineeDTO.class); }

    public TraineeDTO getTraineeById(Long id) {
        return this.mapToDTO(this.repo.findById(id).orElseThrow(TraineeNotFoundException::new));
    }

    public TraineeDTO addTrainee(Trainee trainee) { return this.mapToDTO(this.repo.save(trainee)); }

    public TraineeDTO editTrainee(Long id, Trainee trainee) {
        Trainee update = this.repo.findById(id).orElseThrow(TraineeNotFoundException::new);
        update.setForename(trainee.getForename());
        update.setSurname(trainee.getSurname());
        return this.mapToDTO(this.repo.save(update));
    }

    public List<TraineeDTO> getAllTrainees() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
