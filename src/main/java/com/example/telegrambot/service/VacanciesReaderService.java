package com.example.telegrambot.service;

import com.example.telegrambot.dto.VacancyDto;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class VacanciesReaderService {

    public List<VacancyDto> getVacanciesFromFile(String filename) {
        Resource resource = new ClassPathResource(filename);

        try (InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            CsvToBean<VacancyDto> csvToBean = new CsvToBeanBuilder<VacancyDto>(inputStreamReader)
                    .withType(VacancyDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + filename, e);
        }
    }
}
