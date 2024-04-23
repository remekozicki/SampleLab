package agh.edu.pl.slpbackend.service;

import agh.edu.pl.slpbackend.dto.ExaminationDto;
import agh.edu.pl.slpbackend.dto.IndicationDto;
import agh.edu.pl.slpbackend.dto.SampleDto;
import agh.edu.pl.slpbackend.mapper.ExaminationMapper;
import agh.edu.pl.slpbackend.mapper.IndicationMapper;
import agh.edu.pl.slpbackend.mapper.SampleMapper;
import agh.edu.pl.slpbackend.model.Examination;
import agh.edu.pl.slpbackend.model.Indication;
import agh.edu.pl.slpbackend.model.ProductGroup;
import agh.edu.pl.slpbackend.model.Sample;
import agh.edu.pl.slpbackend.repository.ExaminationRepository;
import agh.edu.pl.slpbackend.repository.ProductGroupRepository;
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
public class SampleService extends AbstractService implements SampleMapper, IndicationMapper, ExaminationMapper {

    private final SampleRepository sampleRepository;
    private final ExaminationRepository examinationRepository;


    public List<SampleDto> selectAll() {
        List<Sample> sampleList = sampleRepository.findAll();
        return sampleList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public SampleDto selectSampleById(final Long id) {
        Sample sample = sampleRepository.getReferenceById(id);
        return toDto(sample);
    }

    public List<IndicationDto> selectIndicationsForSample(final Long id) {
        final Sample sample = sampleRepository.getReferenceById(id);

        final ProductGroup productGroup = sample.getGroup();
        List<Indication> indications = productGroup.getIndications();

        return indications.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<ExaminationDto> selectExaminationsForSample(final Long id) {
        List<Examination> examinations = examinationRepository.findBySampleId(id);
        return examinations.stream().map(this::toDto).collect(Collectors.toList());
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


}
