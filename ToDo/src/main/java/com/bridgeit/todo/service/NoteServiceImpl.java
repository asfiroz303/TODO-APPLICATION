package com.bridgeit.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.todo.dao.NoteDao;
import com.bridgeit.todo.model.Note;

@Service("noteService")
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	NoteDao noteDao;
	
	public int saveNotes(Note note){
		return  noteDao.saveNotes(note);
		
	}

	@Override
	public void deleteNoteById(int id) {
		noteDao.deleteNoteById(id);
		
	}

	public List<Note> findAllNote() {
		return noteDao.findAllNote();
		
	}

	public void updateNote(int id, Note note) {
		noteDao.updateNote(id,note);
		
	}

}
