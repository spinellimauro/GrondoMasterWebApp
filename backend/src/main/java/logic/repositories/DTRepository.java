package logic.repositories;

import logic.entities.DT;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "dt", path = "dt")
public interface DTRepository extends PagingAndSortingRepository<DT, Long> {

    @Transactional(readOnly = true)
    Optional<DT> findById(Long id);

}
