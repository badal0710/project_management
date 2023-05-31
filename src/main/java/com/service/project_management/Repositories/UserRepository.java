package com.service.project_management.Repositories;


import com.service.project_management.Entities.Investor_Project;
import com.service.project_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query(value = "select * from users where email= :e",nativeQuery = true)
  User getuserpasswordbyEmail(@Param("e")String e);

  @Query(value = "select * from investor_project pi where pi.investor_id = :i_id and pi.project_id = :p_id",nativeQuery = true)
  Optional<Investor_Project> findProjectByIp(@Param("i_id")Integer investorId,@Param("p_id") Integer projectId);

}


