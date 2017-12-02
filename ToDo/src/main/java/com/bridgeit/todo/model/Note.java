package com.bridgeit.todo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="note_ToDo")
public class Note {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String description;
	
	private Date createdDate;
	private Date modifiedDate;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userId")
	private User user = new User();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="idgen")
	@GenericGenerator(name="idgen", strategy="increment")
	private int noteId;


	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	private boolean archive;
	
	private boolean trash;
	
	private boolean pinned;
	
	private Date reminder;
	
	private String noteColor; 
	
	@Column(columnDefinition = "LONGBLOB")
	private String image;
	

	public Set<User> getCollabUsers() {
		return collabUsers;
	}

	public void setCollabUsers(Set<User> collabUsers) {
		this.collabUsers = collabUsers;
	}

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToMany
	@JoinTable(name = "collabUsers", joinColumns = @JoinColumn(name = "noteid"), inverseJoinColumns = @JoinColumn(name = "userid"))
	private Set<User> collabUsers = new HashSet<>();
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public boolean isPinned() {
		return pinned;
	}

	public void setPinned(boolean pinned) {
		this.pinned = pinned;
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	public String getNoteColor() {
		return noteColor;
	}

	public void setNoteColor(String noteColor) {
		this.noteColor = noteColor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", description=" + description + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}