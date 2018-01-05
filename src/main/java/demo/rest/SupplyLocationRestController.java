package demo.rest;

import demo.Service.SupplyLocationService;
import demo.domain.SupplyLocation;
import demo.domain.SupplyLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplyLocationRestController {

    /*
    * 这里有repository是因为我们用了混合方法，
    * CRUD一部分由Repository完成(注意这里是RestRepository)
    * 另一部分由service完成。
    *
    * 比较常见的方法是全部delegate给service完成
    */
    private SupplyLocationRepository repository;
    private SupplyLocationService service;

    @Autowired
    public SupplyLocationRestController(SupplyLocationRepository repository, SupplyLocationService service) {
        this.repository = repository;
        this.service = service;
    }

    @RequestMapping(value = "/bulk/supplyLocations", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED) // returns 201 created, otherwise return Http.200 (okay)
    public void upload(@RequestBody List<SupplyLocation> locations) { // Don't forget about @RequestBody
        this.repository.save(locations);
    }

    @RequestMapping(value = "/bulk/supplyLocations/{keyword}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED) // returns 201 created, otherwise return Http.200 (okay)
    public void upload(@PathVariable(value = "keyword") String keyword, @RequestBody List<SupplyLocation> locations) { // Don't forget about @RequestBody
        this.service.saveSupplyLocationsZipContains504(locations);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        this.repository.deleteAll();
    }
}
