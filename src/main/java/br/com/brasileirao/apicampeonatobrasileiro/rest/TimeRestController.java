package br.com.brasileirao.apicampeonatobrasileiro.rest;


import br.com.brasileirao.apicampeonatobrasileiro.dto.TimeDto;
import br.com.brasileirao.apicampeonatobrasileiro.service.TimeService;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/times")
public class TimeRestController {

    @Autowired
    private TimeService timeService;

    @GetMapping
    public ResponseEntity<List<TimeDto>> getTimes() {
        return ResponseEntity.ok().body(timeService.listarTimes());
    }
    
    @ApiOperation(value = "Obtem os dados um time")
    @GetMapping(value = "{id}")
    public ResponseEntity<TimeDto> getTime(@PathVariable Integer id)  {
        return ResponseEntity.ok().body(timeService.obterTime(id));
    }

    @PostMapping
    public ResponseEntity<TimeDto> cadastrarTime(@Valid @RequestBody TimeDto timeDTO) throws Exception {
        return ResponseEntity.ok().body(timeService.cadastrarTime(timeDTO));
    }

}
