package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

/**
 * Created by maverick on 11/4/2016.
 */

@RestController

public class Main {

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException{

        ObjectMapper mapper = new ObjectMapper();
        FileWriter output = new FileWriter("./inventory.txt", true);
        mapper.writeValue(output, newVehicle);
        FileUtils.writeStringToFile(new File("./inventory.txt"), System.lineSeparator(), CharEncoding.UTF_8, true);
        return newVehicle;
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle) throws IOException{

        ObjectMapper objec = new ObjectMapper();
        List<String> list = FileUtils.readLines(new File ("./inventory.txt"), CharEncoding.UTF_8);
        Vehicle vehicle = new Vehicle();
        for(int j=0;j<list.size();j++ ){
            vehicle = objec.readValue(list.get(j), Vehicle.class);
            if (vehicle.getId() == newVehicle.getId()) {
                vehicle.setId(newVehicle.getId());
                vehicle.setMakeModel(newVehicle.getMakeModel());
                vehicle.setYear(newVehicle.getYear());
                vehicle.setRetailPrice(newVehicle.getRetailPrice());
                String updatedLine=objec.writeValueAsString(vehicle);
                list.set(j,updatedLine);
            }
        }
        FileUtils.writeLines(new File("./inventory.txt"),list);
        return vehicle;
    }

    @RequestMapping(value="/getVehicle/{id}", method=RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) throws IOException {
        ObjectMapper obj=new ObjectMapper();
        File file=new File("./inventory.txt");
        BufferedReader bufferReader=new BufferedReader(new FileReader(file));
        String line;
        Vehicle vehicle1= new Vehicle();
        while((line = bufferReader.readLine()) != null) {
            vehicle1 = obj.readValue(line, Vehicle.class);
            if(vehicle1.getId()==id)
            {
                return vehicle1;
            }
        }
        return null;
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException {
        ObjectMapper obj=new ObjectMapper();
        List<String> list=FileUtils.readLines(new File("./inventory.txt"),CharEncoding.UTF_8);
        Vehicle vehicle2=new Vehicle();
        boolean isMatched=false;
        for(int i=0;i<list.size();i++){
            vehicle2 = obj.readValue(list.get(i), Vehicle.class);
            if(vehicle2.getId()==id)
            {
                isMatched=true;
                list.remove(i);
                break;
            }
        }
        FileUtils.writeLines(new File("./inventory.txt"),list);
        if(isMatched==true)
        {

            return new ResponseEntity<String>("deleted", HttpStatus.OK);
        }

        return new ResponseEntity<String>("not found",HttpStatus.OK);
    }

}


