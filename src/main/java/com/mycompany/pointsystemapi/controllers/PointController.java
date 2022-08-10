package com.mycompany.pointsystemapi.controllers;

import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.pointsystemapi.dtos.PointDto;
import com.mycompany.pointsystemapi.models.PointModel;
import com.mycompany.pointsystemapi.services.PointService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/save")
    public ResponseEntity<Object> savePoint(@RequestBody @Valid PointDto pointDto) {
        if (pointService.existsByEmployeeAndDate(pointDto.getEmployee(), pointDto.getDate())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: User point already register for that date.");
        }

        PointModel pointModel = new PointModel();
        BeanUtils.copyProperties(pointDto, pointModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(pointService.save(pointModel));
    }

    @GetMapping
    public ResponseEntity<Page<PointModel>> getAllPoint(@PageableDefault(page = 0, size = 10, 
                                                        sort = "employee", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pointService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPoint(@PathVariable(value = "id") UUID id) {
        Optional<PointModel> pointModelOptional = pointService.findById(id);

        if (!pointModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Point not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pointModelOptional.get());
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePoint(@PathVariable(value = "id") UUID id) {
        Optional<PointModel> pointModel = pointService.findById(id);

        if (!pointModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Point not found.");
        }

        pointService.delete(pointModel.get());

        return ResponseEntity.status(HttpStatus.OK).body("Point successfully deleted.");
    } 

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePoint(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid PointDto pointDto) {
        Optional<PointModel> pointModelOptional = pointService.findById(id);

        if (!pointModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Point not found.");
        }

        PointModel pointModel = new PointModel();
        BeanUtils.copyProperties(pointDto, pointModel);
        pointModel.setId(pointModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(pointService.save(pointModel));
    } 
    
}
