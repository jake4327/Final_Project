package com.qa.sfia3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A trainee with this id does not exist.")
public class TraineeNotFoundException extends EntityNotFoundException {
}
