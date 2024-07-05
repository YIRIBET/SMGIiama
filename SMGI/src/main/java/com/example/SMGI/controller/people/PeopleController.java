package com.example.SMGI.controller.people;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.controller.people.Dto.PeopleDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
@AllArgsConstructor

public class PeopleController {
    private final PersonService service;
    @PostMapping ("/crear/")
    public ResponseEntity<ApiResponse> registrer(@RequestBody PeopleDto Dto){
        return service.save(Dto.toEntity());
    }
    @DeleteMapping("/delete/{curp}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String curp){
        return service.delete(curp);
    }
    @PutMapping("/actualizar/{curp}")
    public ResponseEntity<ApiResponse> update(@PathVariable String curp, @RequestBody PeopleDto updateDto){
        return  service.update(curp,updateDto.toEntityId());
    }
    @GetMapping("/mostrar/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }
}
