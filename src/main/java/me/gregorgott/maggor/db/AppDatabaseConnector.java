package me.gregorgott.maggor.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.util.List;

public class AppDatabaseConnector {
    public static MongoClient getAppMongoClient(String dbName, String dbPort, String username, char[] password) {
        MongoCredential credential = MongoCredential.createCredential(username, "admin", password);
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(List.of(new ServerAddress(dbName, Integer.parseInt(dbPort)))))
                        .credential(credential)
                        .build());
    }
}
