package com.crsms.service;

import java.io.IOException;
import java.util.List;

import com.crsms.dto.VacancyJsonDto;

public interface VacancyService {

  List<VacancyJsonDto> getAllVacancies() throws IOException;
  
}
