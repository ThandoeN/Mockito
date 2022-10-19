package com.mockito.mock.controller;

import com.mockito.mock.entity.Arrival;
import com.mockito.mock.repository.ArrivalRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

public class ArrivalController {

    @Autowired
    private ArrivalRepository arrivalRepository;

    @ApiOperation(value = "Get all arrivals")
    @RequestMapping(value = "all", method = GET)
    @ResponseBody
    public List<Arrival> getAllArrivals() {
        return arrivalRepository.findAll();
    }

    @ApiOperation(value = "Get arrival by ID")
    @RequestMapping(value = "{id}", method = GET)
    @ResponseBody
    public Arrival getArrivalById(@PathVariable(value = "id") int id) {
        return arrivalRepository.findAllById(id);
    }
}

