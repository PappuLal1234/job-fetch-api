package com.user.repositories;

import com.user.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    // Custom finder methods based on fields
    List<Job> findByTitleContainingIgnoreCase(String title);

    List<Job> findByDescriptionContainingIgnoreCase(String description);

    // Custom query for update using @Query
    @Query("UPDATE Job j SET j.title = :title, j.description = :description WHERE j.id = :id")
    @Modifying
    int updateJob(@Param("id") Long id, @Param("title") String title, @Param("description") String description);

    // Custom query for delete using @Query
    @Query("DELETE FROM Job j WHERE j.id = :id")
    @Modifying
    int deleteJob(@Param("id") Long id);
}

