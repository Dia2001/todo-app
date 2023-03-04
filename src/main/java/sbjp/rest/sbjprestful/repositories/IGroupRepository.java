package sbjp.rest.sbjprestful.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbjp.rest.sbjprestful.entities.Group;

@Repository
public interface IGroupRepository extends JpaRepository<Group, Integer> {

}
