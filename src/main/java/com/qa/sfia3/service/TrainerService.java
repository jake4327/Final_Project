package com.qa.sfia3.service;

import com.qa.sfia3.domain.Trainee;
import com.qa.sfia3.domain.Trainer;
import com.qa.sfia3.dto.TraineeDTO;
import com.qa.sfia3.dto.TrainerDTO;
import com.qa.sfia3.exceptions.TraineeNotFoundException;
import com.qa.sfia3.exceptions.TrainerNotFoundException;
import com.qa.sfia3.repo.TrainerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    private final TrainerRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public TrainerService(TrainerRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private TrainerDTO mapToDTO(Trainer trainer) { return this.mapper.map(trainer, TrainerDTO.class); }

    public TrainerDTO getTrainerById(Long id) {
        return this.mapToDTO(this.repo.findById(id).orElseThrow(TrainerNotFoundException::new));
    }

    public TrainerDTO addTrainer(Trainer trainer) { return this.mapToDTO(this.repo.save(trainer)); }

    public TrainerDTO editTrainer(Long id, Trainer trainer) {
        Trainer update = this.repo.findById(id).orElseThrow(TrainerNotFoundException::new);
        update.setForename(trainer.getForename());
        update.setSurname(trainer.getSurname());
        return this.mapToDTO(this.repo.save(update));
    }

    public List<TrainerDTO> getAllTrainers() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
