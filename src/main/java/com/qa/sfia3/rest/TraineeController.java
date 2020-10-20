package com.qa.sfia3.rest;

import com.qa.sfia3.domain.Trainee;
import com.qa.sfia3.dto.TraineeDTO;
import com.qa.sfia3.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TraineeController {

    private final TraineeService traineeService;

    @Autowired
    public TraineeController(TraineeService traineeService) { this.traineeService = traineeService; }

    @GetMapping("/getAllTrainees")
    public ResponseEntity<List<TraineeDTO>> getAllTrainees() {
        return ResponseEntity.ok(this.traineeService.getAllTrainees());
    }

    @PostMapping("/addTrainee")
    public ResponseEntity<TraineeDTO> addTrainee(@RequestBody Trainee trainee) {
        return new ResponseEntity<TraineeDTO>(this.traineeService.addTrainee(trainee), HttpStatus.CREATED);
    }

    @GetMapping("/getTraineeById/{id}")
    public ResponseEntity<TraineeDTO> getTraineeById(@PathVariable Long id) {
        return ResponseEntity.ok(this.traineeService.getTraineeById(id));
    }

    @PutMapping("/editTrainee/{id}")
    public ResponseEntity<TraineeDTO> editTrainee(@PathVariable Long id, @RequestBody Trainee trainee) {
        return ResponseEntity.ok(this.traineeService.editTrainee(id, trainee));
    }

}
