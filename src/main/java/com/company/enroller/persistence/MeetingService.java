package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;

@Component("meetingService")
public class MeetingService {

	DatabaseConnector connector;
	Session session;

	public MeetingService() {
		//connector = DatabaseConnector.getInstance();
		session = DatabaseConnector.getInstance().getSession();
	}

	public Collection<Meeting> getAll() {
		String hql = "FROM Meeting";
		Query query = session.createQuery(hql);
		return query.list();
	}

	public Meeting findById(long id) {
		return (Meeting) session.get(Meeting.class, id);	
	}

	public Meeting add(Meeting meeting) {
		Transaction transaction = this.session.beginTransaction();
		session.save(meeting);
		transaction.commit();
		return meeting;
	}
	
	
	public boolean presentParticipant(String login) {
		return !(session.get(Participant.class, login) != null);
	}
	

	public void addParticipantToTheMeeting(Meeting meeting) {
		Transaction transaction = session.beginTransaction();
		session.save(meeting);
		transaction.commit();
	}
	
	public Participant findParticipantByLoginInMeeting(long id, String login) {
		Collection<Participant> participants = ((Meeting) session.get(Meeting.class, id)).getParticipants();
		for (Participant participant : participants) {
			if (participant.getLogin().equals(login)) {
				return participant;
			}
		}
		session.get(Meeting.class, id);
		return null;
	}
	
	public Collection<Participant> getAllParticipants(long id) {
		return ((Meeting) session.get(Meeting.class, id)).getParticipants();
	}
	
	public void deleteMeeting(Meeting meeting) {
		Transaction transaction = session.beginTransaction();
		session.delete(meeting);
		transaction.commit();
	}
	
	public void update(Meeting meeting) {
		Transaction transaction = session.beginTransaction();
		session.merge(meeting);
		transaction.commit();
	}

	public void deleteParticipantFromMeeting(Meeting meeting) {
		Transaction transaction = session.beginTransaction();
		session.save(meeting);
		transaction.commit();
		
	}

}
