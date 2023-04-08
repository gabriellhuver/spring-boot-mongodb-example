package com.gh.api.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.gh.api.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
