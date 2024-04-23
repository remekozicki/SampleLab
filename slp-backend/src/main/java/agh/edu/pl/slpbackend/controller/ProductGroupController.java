package agh.edu.pl.slpbackend.controller;

import agh.edu.pl.slpbackend.controller.iface.AbstractController;
import agh.edu.pl.slpbackend.dto.ProductGroupDto;
import agh.edu.pl.slpbackend.model.ProductGroup;
import agh.edu.pl.slpbackend.service.ProductGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product-group") //TODO odpowiedni rooting jeszcze nie wiem XDD
@CrossOrigin(origins = "http://localhost:3000")
public class ProductGroupController extends AbstractController {

    private final ProductGroupService productGroupService;

    @GetMapping("/list")
    public ResponseEntity<List<ProductGroupDto>> list() {
        try {
            List<ProductGroupDto> list = productGroupService.selectAll();

            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ProductGroup> add(@RequestBody ProductGroupDto productGroupDto) {
        return new ResponseEntity<>(add(productGroupDto, productGroupService).getStatusCode()); //TODO nie wiem, trzeba przetestować
    }
}
