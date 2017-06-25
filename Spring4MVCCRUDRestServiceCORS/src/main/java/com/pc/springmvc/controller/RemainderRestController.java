package com.pc.springmvc.controller;
 
import java.util.List;

import com.pc.springmvc.model.Remainder;
import com.pc.springmvc.service.RemainderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class RemainderRestController {
 

    @Autowired
    RemainderService remainderService;


    //-------------------Retrieve All Remainders--------------------------------------------------------

    @RequestMapping(value = "/remainder/", method = RequestMethod.GET)
    public ResponseEntity<List<Remainder>> listAllRemainders() {
        List<Remainder> remainders = remainderService.findAllRemainders();
        if(remainders.isEmpty()){
            return new ResponseEntity<List<Remainder>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Remainder>>(remainders, HttpStatus.OK);
    }


    //-------------------Retrieve Single Remainder--------------------------------------------------------

    @RequestMapping(value = "/remainder/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Remainder> getRemainder(@PathVariable("id") Integer id) {
        System.out.println("Fetching Remainder with id " + id);
        Remainder remainder = remainderService.findById(id);
        if (remainder == null) {
            System.out.println("Remainder with id " + id + " not found");
            return new ResponseEntity<Remainder>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Remainder>(remainder, HttpStatus.OK);
    }



    //-------------------Create a Remainder--------------------------------------------------------

    @RequestMapping(value = "/remainder/", method = RequestMethod.POST)
    public ResponseEntity<Void> createRemainder(@RequestBody Remainder remainder, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Remainder " + remainder.getName());

        if (remainderService.isRemainderPresent(remainder)) {
            System.out.println("A Remainder with name " + remainder.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        remainderService.saveRemainder(remainder);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/remainder/{id}").buildAndExpand(remainder.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //------------------- Update a Remainder --------------------------------------------------------

    @RequestMapping(value = "/remainder/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Remainder> updateRemainder(@PathVariable("id") Integer id, @RequestBody Remainder remainder) {
        System.out.println("Updating Remainder " + id);

        Remainder currentRemainder = remainderService.findById(id);

        if (currentRemainder==null) {
            System.out.println("Remainder with id " + id + " not found");
            return new ResponseEntity<Remainder>(HttpStatus.NOT_FOUND);
        }
        currentRemainder.setName(remainder.getName());
        currentRemainder.setDescription(remainder.getDescription());
        currentRemainder.setDueDate(remainder.getDueDate());
        currentRemainder.setStatus(remainder.getStatus());

        remainderService.updateRemainder(currentRemainder);
        return new ResponseEntity<Remainder>(currentRemainder, HttpStatus.OK);
    }

    //------------------- Delete a Remainder --------------------------------------------------------

    @RequestMapping(value = "/remainder/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Remainder> deleteRemainder(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting Remainder with id " + id);

        Remainder remainder = remainderService.findById(id);
        if (remainder == null) {
            System.out.println("Unable to delete. Remainder with id " + id + " not found");
            return new ResponseEntity<Remainder>(HttpStatus.NOT_FOUND);
        }

        remainderService.deleteRemainderById(id);
        return new ResponseEntity<Remainder>(HttpStatus.NO_CONTENT);
    }


    //------------------- Delete All Remainders --------------------------------------------------------

    @RequestMapping(value = "/remainder/", method = RequestMethod.DELETE)
    public ResponseEntity<Remainder> deleteAllRemainders() {
        System.out.println("Deleting All Remainders");

        remainderService.deleteAllRemainders();
        return new ResponseEntity<Remainder>(HttpStatus.NO_CONTENT);
    }

}