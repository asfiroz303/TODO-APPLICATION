package com.bridgeit.todo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgeit.todo.model.Note;

@Service("NoteDao")
public class NoteDaoImpl implements NoteDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int saveNotes(Note note) {
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.persist(note);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 1;

	}

	@Override
	public void deleteNoteById(int id) {

		Session session = sessionFactory.openSession();
		org.hibernate.Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Note note = new Note();
			note.setId(id);
			session.delete(note);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}

	@Override
	public List<Note> findAllNote() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Note.class);
		@SuppressWarnings("unchecked")
		List<Note> list = criteria.list();
		return list;
	}

	public void updateNote(int id, Note note) {
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			Note note1 = session.byId(Note.class).load(id);

			// Criteria criteria = session.createCriteria(Note.class);
			// Note note1 = (Note) criteria.add(Restrictions.eq("id",
			// note.getId()));

			note1.setTitle(note.getTitle());
			note1.setDescription(note.getDescription());
			note1.setModifiedDate(note.getModifiedDate());

			session.update(note1);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();

		}

	}

}
