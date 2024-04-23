package agh.edu.pl.slpbackend.service;

import agh.edu.pl.slpbackend.dto.IndicationDto;
import agh.edu.pl.slpbackend.mapper.IndicationMapper;
import agh.edu.pl.slpbackend.model.Indication;
import agh.edu.pl.slpbackend.repository.IndicationRepository;
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
public class IndicationService extends AbstractService implements IndicationMapper {

    private final IndicationRepository indicationRepository;

    public List<IndicationDto> selectAll() {
        List<Indication> indicationList = indicationRepository.findAll();
        return indicationList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Indication> insert(IModel model) {

        final IndicationDto indicationDto = (IndicationDto) model;
        final Indication indication = toModel(indicationDto);
        final Indication saveResult = indicationRepository.save(indication);

        return new ResponseEntity<>(saveResult, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Indication> update(IModel model) {
        return null;
    }

    @Override
    public ResponseEntity<Indication> delete(IModel model) {
        return null;
    }

}
