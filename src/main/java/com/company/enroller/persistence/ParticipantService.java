package com.company.enroller.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;


import com.company.enroller.model.Participant;

@Component("participantService")
public class ParticipantService {

	Session session;
	
	DatabaseConnector connector;
	//ArrayList<Participant> participants;

	public ParticipantService() {
		//connector = DatabaseConnector.getInstance();
		session = DatabaseConnector.getInstance().getSession();
	}

	public Collection<Participant> getAll() {
		return session.createCriteria(Participant.class).list();
		
		//return connector.getSession().createCriteria(Participant.class).list();
	}

	public Participant findByLogin(String login) {
		
	  //return (Participant) connector.getSession().get(Participant.class, login);	
		return (Participant) session.get(Participant.class, login);	
	}

	public Participant add(Participant participant) {
		Transaction transaction = this.session.beginTransaction();
		session.save(participant);
		transaction.commit();
		return participant;
		
	}

	public void delete(Participant participant) {
		Transaction transaction = this.session.beginTransaction();
		session.save(participant);
		transaction.commit();		
	}
	
	public void update(Participant participant) {
		Transaction transaction = this.session.beginTransaction();
		session.update(participant);
		transaction.commit();
	}
	

}
