package com.qa.sfia3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A cohort with that id does not exist.")
public class CohortNotFoundException extends EntityNotFoundException {
}
