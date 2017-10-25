package com.bridgeit.todo.service;

import java.util.List;

import com.bridgeit.todo.model.Note;

public interface NoteService {

	int saveNotes(Note note);

	void deleteNoteById(int id);

	List<Note> findAllNote();

	void updateNote(int id, Note note);

}
