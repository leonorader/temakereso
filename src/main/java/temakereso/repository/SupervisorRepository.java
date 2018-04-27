package temakereso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import temakereso.entity.Supervisor;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

	@Query("select s from Supervisor s where s.account.username = :username")
	Supervisor getByUsername(@Param("username") String username);

	List<Supervisor> findByConfirmedFalse();

}
