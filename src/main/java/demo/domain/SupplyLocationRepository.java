package demo.domain;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "supplyLocations")
public interface SupplyLocationRepository extends MongoRepository<SupplyLocation, String> { // it's an interface, spring data will automatically generate a concrete class
//public interface SupplyLocationRepository extends PagingAndSortingRepository<SupplyLocation, String>{ // it's an interface, spring data will automatically generate a concrete class

    @RestResource(path = "nearby")
    SupplyLocation findFirstByLocationNear(@Param("location") Point location); // springboot.geo.point support Near operation in CRUD.
}
