package medicine;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Database {
    
    // MongoDB connection string
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    
    // Database name
    private static final String DB_NAME = "PharmacyManagement";
    
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    
    // Method to get database connection
    public static MongoDatabase getConnection() {
        if (database == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DB_NAME);
            System.out.println("Connected to MongoDB: " + DB_NAME);
        }
        return database;
    }
    
    // Close connection (optional)
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB connection closed.");
        }
    }
}
