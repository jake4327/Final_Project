package com.qa.sfia3.rest;

import com.qa.sfia3.domain.Trainer;
import com.qa.sfia3.dto.TrainerDTO;
import com.qa.sfia3.service.TrainerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TrainerController {

    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) { this.trainerService = trainerService; }

    @GetMapping("/getAllTrainers")
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        return ResponseEntity.ok(this.trainerService.getAllTrainers());
    }

    @PostMapping("/addTrainer")
    public ResponseEntity<TrainerDTO> addTrainer(@RequestBody Trainer trainer) {
        return new ResponseEntity<TrainerDTO>(this.trainerService.addTrainer(trainer), HttpStatus.CREATED);
    }

    @PutMapping("/addTrainerCohort/{id}")
    public ResponseEntity<TrainerDTO> addTrainerCohort(@PathVariable Long id, @RequestBody Trainer trainer) {
        return ResponseEntity.ok(this.trainerService.addTrainerCohort(id, trainer));
    }

    @GetMapping("/getTrainerById/{id}")
    public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable Long id) {
        return ResponseEntity.ok(this.trainerService.getTrainerById(id));
    }

    @PutMapping("/editTrainer/{id}")
    public ResponseEntity<TrainerDTO> editTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        return ResponseEntity.ok(this.trainerService.editTrainer(id, trainer));
    }

}
