package com.bridgeit.todo.dao;

import java.util.List;

import com.bridgeit.todo.model.Note;

public interface NoteDao {
	
	public int saveNotes(Note note);

	public void deleteNoteById(int id);

	public List<Note> findAllNote();

	void updateNote(int id, Note note);

}
