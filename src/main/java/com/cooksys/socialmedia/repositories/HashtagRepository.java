package com.cooksys.socialmedia.repositories;

import com.cooksys.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Optional<Hashtag> findByLabel(String label);

}