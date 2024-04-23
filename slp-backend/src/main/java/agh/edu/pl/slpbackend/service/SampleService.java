package agh.edu.pl.slpbackend.service;

import agh.edu.pl.slpbackend.dto.SampleDto;
import agh.edu.pl.slpbackend.exception.SampleNotFoundException;
import agh.edu.pl.slpbackend.mapper.SampleMapper;
import agh.edu.pl.slpbackend.model.ReportData;
import agh.edu.pl.slpbackend.model.Sample;
import agh.edu.pl.slpbackend.repository.SampleRepository;
import agh.edu.pl.slpbackend.service.iface.AbstractService;
import agh.edu.pl.slpbackend.service.iface.IModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SampleService extends AbstractService implements SampleMapper {

    private final SampleRepository sampleRepository;


    public List<SampleDto> selectAll() {
        List<Sample> sampleList = sampleRepository.findAll();
        return sampleList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public SampleDto selectOne(Long id) {
        Sample sample = sampleRepository.getOne(id);
        return toDto(sample);
    }

    @Override
    public ResponseEntity<Sample> insert(IModel model) {

        final SampleDto sampleDto = (SampleDto) model;
        final Sample sample = toModel(sampleDto);
        final Sample saveResult = sampleRepository.save(sample);

        return new ResponseEntity<>(saveResult, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Sample> update(IModel model) {
        return null;
    }

    @Override
    public ResponseEntity<Sample> delete(IModel model) {
        return null;
    }


    public void addReportData(long sampleId, ReportData reportData) {
        Sample sample = sampleRepository.findById(sampleId)
                .orElseThrow(SampleNotFoundException::new);

        sample.setReportData(reportData);
        sampleRepository.save(sample);
    }
}
