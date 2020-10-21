package com.qa.sfia3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A Trainer with this id does not exist.")
public class TrainerNotFoundException extends EntityNotFoundException {
}
