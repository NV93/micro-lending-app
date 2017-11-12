package lt.nevytas.repository;

import lt.nevytas.entity.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nevyt
 */
@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    public List<Loan> findByUserId(long id);

}
