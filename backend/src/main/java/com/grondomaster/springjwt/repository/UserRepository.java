package com.grondomaster.springjwt.repository;

import java.util.Optional;

import com.grondomaster.springjwt.models.DT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DT, Long> {
	Optional<DT> findByUsername(String username);

	@Query("Select u from DT u where u.username =:username and u.enabled = true ")
	DT findByUsernameAndEnabled(@Param("username") String username);

	Boolean existsByUsername(String username);
}
