package com.example.easynotes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.easynotes.domain.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
	 
	
	public Note findByTitle(String title);


}
