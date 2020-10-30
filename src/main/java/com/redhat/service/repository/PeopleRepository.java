package com.redhat.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.redhat.service.model.People;

@RepositoryRestResource(collectionResourceRel = "peoples", path = "peoples")
public interface PeopleRepository extends JpaRepository<People, String> {

}
