package com.qa.sfia3.service;

import com.qa.sfia3.domain.Trainee;
import com.qa.sfia3.dto.TraineeDTO;
import com.qa.sfia3.repo.TraineeRepository;
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
public class TraineeUnitTest {
    @Autowired
    private TraineeService service;

    @MockBean
    private TraineeDTO DTO;

    @MockBean
    private TraineeRepository repo;

    @Test
    void testAddTrainee(){
        //Given
        Long id = 1L;
        Trainee newTrainee = new Trainee ("Areeb","Panjwani");
        Trainee savedTrainee = new Trainee("Areeb","Panjwani");
        savedTrainee.setTraineeId(id);
        //When
        Mockito.when(this.repo.save(newTrainee)).thenReturn(savedTrainee);
        //then
        assertThat(this.service.addTrainee(newTrainee)).isEqualToComparingOnlyGivenFields(savedTrainee, "forename");

        Mockito.verify(this.repo, Mockito.times(1)).save(newTrainee);
    }
    @Test
    void testUpdate() {
        // GIVEN

        Long id = 1L;

        // will be passed in
        Trainee newTrainee = new Trainee("Areeb","Panjwani");
        // will be found by findById()
        Trainee oldTrainee = new Trainee("Areeb","Panjwani");
        oldTrainee.setTraineeId(id);
        // will be saved back to db and returned by method
        Trainee updatedTrainee = new Trainee("Areeb","Panjwani");
        updatedTrainee.setTraineeId(id);

        // WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(oldTrainee));
        Mockito.when(this.repo.save(updatedTrainee)).thenReturn(updatedTrainee);

        // THEN
        assertThat(this.service.editTrainee(id, newTrainee)).isEqualToComparingOnlyGivenFields(updatedTrainee,"forename");

        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
        Mockito.verify(this.repo, Mockito.times(1)).save(updatedTrainee);
    }
    @Test
    void testGet() {
        // GIVEN
        Trainee trainee = new Trainee("Areeb","Panjwani");
        trainee.setTraineeId(1L); // trainee object to match the one in trainee-data.sql
        List<Trainee> trainees = new ArrayList<>();
        trainees.add(trainee);

        // WHEN
        Mockito.when(this.repo.findAll()).thenReturn(trainees);

        // THEN
        assertThat(this.service.getAllTrainees());

        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }
    @Test
    void testGetTraineeById() {
        // GIVEN
        Long id =1L;
        Trainee trainee = new Trainee("Areeb","Panjwani");
        trainee.setTraineeId(id); // trainee object to match the one in trainee-data.sql
        List<Trainee> trainees = new ArrayList<>();
        trainees.add(trainee);

        // WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(trainee));

        // THEN
        assertThat(this.service.getTraineeById(id));

        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
    }
}
