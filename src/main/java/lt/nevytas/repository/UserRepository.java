package lt.nevytas.repository;

import lt.nevytas.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nevyt
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByPersonsId(String personsId);



}
