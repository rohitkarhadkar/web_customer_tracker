package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//Inject sessionFactory 
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query....sort customer by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//execute the query and get result
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the customer to db
		//currentSession.save(theCustomer);
		
		//saveOrUpdate the customer to db
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		
		//get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//get customer from db using theId
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		//return customer
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		//get curr session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete customer by id using hql
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
	}

}












