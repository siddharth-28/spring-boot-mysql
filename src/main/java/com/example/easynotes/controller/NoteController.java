package com.example.easynotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.domain.Note;
import com.example.easynotes.exceptions.ResourceNotFoundException;
import com.example.easynotes.repository.NoteRepository;

@RestController
@RequestMapping("/api")
public class NoteController {
	
	 @Autowired
	    NoteRepository noteRepository;
	 
	 @GetMapping("/notes")
	 public List<Note> getAllNotes() {
	     return (List<Note>) noteRepository.findAll();
	 }
	 @PostMapping("/notes")
	 public Note createNote(@Valid @RequestBody Note note) {
	     return noteRepository.save(note);
	 }
	 @GetMapping("/notes/{id}")
	 public Note getNoteById(@PathVariable(value = "id") Long noteId) {
	     return noteRepository.findById(noteId)
	             .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	 }
	 @PutMapping("/notes/{id}")
	 public Note updateNote(@PathVariable(value = "id") Long noteId,
	                                         @Valid @RequestBody Note noteDetails) {

	     Note note = noteRepository.findById(noteId)
	             .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	     note.setTitle(noteDetails.getTitle());
	     note.setContent(noteDetails.getContent());

	     Note updatedNote = noteRepository.save(note);
	     return updatedNote;
	 }
	 @DeleteMapping("/notes/{id}")
	 public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
	     Note note = noteRepository.findById(noteId)
	             .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	     noteRepository.delete(note);

	     return ResponseEntity.ok().build();
	 }
	 @RequestMapping(value = "/note/{title}", method = RequestMethod.GET, produces = { "application/json" })
		public ResponseEntity<?> getNoteByTitle(@PathVariable(value="title") String title) {
			
			Note note =noteRepository.findByTitle(title);
			
			return new ResponseEntity<Note>(note, HttpStatus.OK);

		}
}
