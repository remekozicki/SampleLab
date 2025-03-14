package agh.edu.pl.slpbackend.controller.dictionary;

import agh.edu.pl.slpbackend.controller.iface.AbstractController;
import agh.edu.pl.slpbackend.dto.AssortmentDto;
import agh.edu.pl.slpbackend.service.dictionary.AssortmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/assortment")
@CrossOrigin(origins = "http://localhost:3000")
public class AssortmentController extends AbstractController {

    private final AssortmentService assortmentService;

    @GetMapping("/list")
    public ResponseEntity<List<AssortmentDto>> list() {
        return ResponseEntity.ok(assortmentService.selectAll());
    }

    @PreAuthorize("hasRole('WORKER')")
    @PostMapping("/save")
    public ResponseEntity<Void> add(@RequestBody @Valid AssortmentDto assortmentDto) {
        return add(assortmentDto, assortmentService);
    }

    @PreAuthorize("hasRole('WORKER')")
    @PutMapping("/update")
    public ResponseEntity<Void> edit(@RequestBody @Valid AssortmentDto assortmentDto) {
        return edit(assortmentDto, assortmentService);
    }

    @PreAuthorize("hasRole('WORKER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return delete(AssortmentDto.builder().id(id).build(), assortmentService);
    }
}
