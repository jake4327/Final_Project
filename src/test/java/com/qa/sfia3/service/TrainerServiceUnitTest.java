package com.qa.sfia3.service;

import com.qa.sfia3.domain.Trainer;
import com.qa.sfia3.dto.TicketDTO;
import com.qa.sfia3.dto.TrainerDTO;
import com.qa.sfia3.repo.TicketRepository;
import com.qa.sfia3.repo.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TrainerServiceUnitTest {
    @Autowired
    private TrainerService service;

    @MockBean
    private TrainerDTO DTO;

    @MockBean
    private TrainerRepository repo;

    @Test
    void testAddTrainer(){
        //Given
        Long id = 1L;
        Trainer newTrainer = new Trainer ("Areeb","Panjwani");
        Trainer savedTrainer = new Trainer("Areeb","Panjwani");
        savedTrainer.setTrainerId(id);
        //When
        Mockito.when(this.repo.save(newTrainer)).thenReturn(savedTrainer);
        //then
        assertThat(this.service.addTrainer(newTrainer)).isEqualToComparingOnlyGivenFields(savedTrainer, "forename");

        Mockito.verify(this.repo, Mockito.times(1)).save(newTrainer);
    }
    @Test
    void testUpdate() {
        // GIVEN

        Long id = 1L;

        // will be passed in
        Trainer newTrainer = new Trainer("Areeb","Panjwani");
        // will be found by findById()
        Trainer oldTrainer = new Trainer("Areeb","Panjwani");
        oldTrainer.setTrainerId(id);
        // will be saved back to db and returned by method
        Trainer updatedTrainer = new Trainer("Areeb","Panjwani");
        updatedTrainer.setTrainerId(id);

        // WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(oldTrainer));
        Mockito.when(this.repo.save(updatedTrainer)).thenReturn(updatedTrainer);

        // THEN
        assertThat(this.service.editTrainer(id, newTrainer)).isEqualToComparingOnlyGivenFields(updatedTrainer,"forename");

        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
        Mockito.verify(this.repo, Mockito.times(1)).save(updatedTrainer);
    }
    @Test
    void testGet() {
        // GIVEN
        Trainer trainer = new Trainer("Areeb","Panjwani");
        trainer.setTrainerId(1L); // trainer object to match the one in trainer-data.sql
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(trainer);

        // WHEN
        Mockito.when(this.repo.findAll()).thenReturn(trainers);

        // THEN
        assertThat(this.service.getAllTrainers());

        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }
    @Test
    void testGetTrainerById() {
        Long id = 1L;
        // GIVEN
        Trainer trainer = new Trainer("Areeb","Panjwani");
        trainer.setTrainerId(id); // trainer object to match the one in trainer-data.sql
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(trainer);

        // WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(trainer));

        // THEN
        assertThat(this.service.getTrainerById(id));

        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
    }

}
