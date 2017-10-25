package com.bridgeit.todo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bridgeit.todo.model.ErrorMessage;
import com.bridgeit.todo.model.Note;
import com.bridgeit.todo.model.User;
import com.bridgeit.todo.service.NoteService;

@RestController
public class NoteController {

	@Autowired
	NoteService noteService;

	@Autowired
	ErrorMessage errorMessage;

	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	public ResponseEntity<ErrorMessage> saveNotes(@RequestBody Note note, HttpSession session) {

		User user = (User) session.getAttribute("user");
		
		Date date = new Date();
		note.setCreatedDate(date);
		note.setModifiedDate(date);
		note.setUser(user);
		int userId=noteService.saveNotes(note);

		if(userId!=0){
		errorMessage.setResponseMessage("Data Successfully inserted ");
		return ResponseEntity.status(HttpStatus.CREATED).body(errorMessage);
		
		}
		errorMessage.setResponseMessage("Note could not be added");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    //return ResponseEntity.ok(errorMessage);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ErrorMessage> updateNote(@PathVariable("id") int id, @RequestBody Note note) {

		Date date = new Date();
		note.setCreatedDate(date);
		note.setModifiedDate(date);
		/*note.setTitle(note.getTitle());
		note.setDescription(note.getDescription());*/
		 
		
		noteService.updateNote(id,note);
		errorMessage.setResponseMessage("Data Successfully updated ");
		return ResponseEntity.ok(errorMessage);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ErrorMessage> deletePersonById(@PathVariable("id") int id) {

		noteService.deleteNoteById(id);
		errorMessage.setResponseMessage("Successfully deleted");
		return ResponseEntity.ok(errorMessage);

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Note>> findAllNote() {
		List<Note> list = noteService.findAllNote();

		if (list == null) {
			return new ResponseEntity<List<Note>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Note>>(list, HttpStatus.OK);
		}
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandler(Exception e) {
		return "Exception";

	}
}
