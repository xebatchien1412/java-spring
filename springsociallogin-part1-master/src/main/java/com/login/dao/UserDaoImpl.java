package com.login.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Repository;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.login.entites.User;
import com.mongodb.MongoClient;


@Repository
public class UserDaoImpl implements UserDao {
	
    @Autowired 
    MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "test"));
	MongoTemplate mongoTemplate;
    private static final String COLLECTION_NAME = "users";
	@Override
	public List<User> listUser() {
//	       BasicQuery query1 = (BasicQuery) new BasicQuery("{  }").limit(2);
//	        Query query2 = new Query();
//	        return mongoTemplate.findAll(User.class, COLLECTION_NAME);
//	        User userTest = mongoOperations.findOne(query1, User.class);
//	        System.out.println(userTest);
            return mongoOps.findAll(User.class);
//	        return mongoTemplate.findAll(User.class);
//	        return mongoTemplate.find(query1, User.class);
//	        return mongoOperations.findOne(query1, User.class);
	}

	@Override
	public void add(User user) {
		if (!mongoOps.collectionExists(User.class)) {
			mongoOps.createCollection(User.class);

        }
        user.setId(UUID.randomUUID().toString());
        mongoOps.insert(user, COLLECTION_NAME);
		
	}

	@Override
	public void update(User user) {
		mongoOps.save(user, COLLECTION_NAME);
		
	}

	@Override
	public void delete(User user) {
		mongoOps.remove(user, COLLECTION_NAME);
		
	}

	@Override
	public User findUserByEmail(User user) {
		return mongoOps.findOne(query(where("email").is(user.getEmail())), User.class);
	}

	@Override
	public List<User> findUserByName(String name) {
		return mongoOps.find(query(where("name").is(name)), User.class);
	}

	@Override
	public boolean checkUserExist(User user) {
		User userExist =  mongoOps.findOne(query(where("email").is(user.getEmail())), User.class);
		if(userExist==null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean loginUser(User user) {
		User userExist =  mongoOps.findOne(query(where("email").is(user.getEmail())), User.class);
		if(userExist!=null) {
		if(userExist.getPassword().equals(user.getPassword())) {		
			return true;
			
			
		  }
		}
		return false;
	}

	@Override
	public User findUserById(String id) {
		// TODO Auto-generated method stub
		return  mongoOps.findOne(query(where("id").is(id)), User.class);
	}

}
