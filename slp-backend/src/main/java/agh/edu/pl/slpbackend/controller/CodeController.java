package agh.edu.pl.slpbackend.controller;

import agh.edu.pl.slpbackend.controller.iface.AbstractController;
import agh.edu.pl.slpbackend.dto.CodeDto;
import agh.edu.pl.slpbackend.model.Code;
import agh.edu.pl.slpbackend.service.CodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/code") //TODO odpowiedni rooting jeszcze nie wiem XDD
public class CodeController extends AbstractController {

    private final CodeService codeService;

    @GetMapping("/list")
    public ResponseEntity<List<CodeDto>> list() {
        try {
            List<CodeDto> list = codeService.selectAll();

            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Code> add(@RequestBody CodeDto codeDto) {
        return new ResponseEntity<>(add(codeDto, codeService).getStatusCode()); //TODO nie wiem, trzeba przetestować
    }
}
