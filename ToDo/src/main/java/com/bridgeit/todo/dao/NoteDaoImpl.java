package com.bridgeit.todo.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgeit.todo.model.Note;
import com.bridgeit.todo.model.User;

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
		 Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(note);
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
		 Transaction transaction= null;
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

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Note> findAllNote(User user) {
		Session session = sessionFactory.openSession();
	    Criteria criteria = session.createCriteria(Note.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.addOrder(Order.desc("modifiedDate"));
		List<Note> notes = criteria.list();
		System.out.println(notes);
		return notes;
	}
	

	/*@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Note> findAllNote(User user) {
		Session session = sessionFactory.openSession();
	    Criteria criteria = session.createCriteria(Note.class);
		criteria.add(Restrictions.eq("user", user))
		.addOrder(Order.desc("modifiedDate"))
		.setResultTransformer(Transformers.aliasToBean(Note.class));
		List<Note> notes = criteria.list();
		System.out.println(notes);
		return notes;
	}*/

	
	public void updateNote(int id, Note note) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			/*Note note1 = session.byId(Note.class).load(id);
			note1.setTitle(note.getTitle());
			note1.setDescription(note.getDescription());
			note1.setModifiedDate(note.getModifiedDate());
			session.update(note1);*/
			
			session.saveOrUpdate(note);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}
	
	public Note getNoteById(int id) {
		Session session = sessionFactory.openSession();
		Note note=session.get(Note.class, id);
		session.close();
		return note;
	}
	
	@Override
	public void collaborateUser(User cUser, Note cNote) {
		Session session = sessionFactory.openSession();

		boolean isNoteCollab = false;
		Set<User> collabUsers = cNote.getCollabUsers();
		if (collabUsers == null) {
			collabUsers = new HashSet<>();
		} else {
			for (User tempUser : collabUsers) {
				if (cUser.getId()==(tempUser.getId()) ) {
					isNoteCollab = true;
				}
			}
		}
		// add other to note collaboration
		if (!isNoteCollab) {
			cNote.getCollabUsers().add(cUser);
		}
		for (User tempUser : collabUsers) {
			System.out.println("***********" + tempUser.getFirstName());
		}
		session.merge(cNote);
		 session.close(); 
		 return;
	}

}