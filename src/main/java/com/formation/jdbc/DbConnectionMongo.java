package com.formation.jdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class DbConnectionMongo implements java.lang.AutoCloseable{
    MongoClient mongoClient;

    public MongoCollection<Document> getCollection(){
        String connectionString = "mongodb://localhost:27017"; //A voir si le même sous Linux 
        mongoClient = MongoClients.create(connectionString);
        MongoDatabase sampleTrainingDB = mongoClient.getDatabase("db_factures");
        return sampleTrainingDB.getCollection("factures");
    }

    @Override
    public void close() throws Exception {
        mongoClient = null;
    }
}
