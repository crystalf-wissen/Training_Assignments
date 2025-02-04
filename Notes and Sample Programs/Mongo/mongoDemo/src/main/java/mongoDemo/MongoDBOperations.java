package mongoDemo;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;

public class MongoDBOperations {
	public static void main(String args[]) {
		System.out.println("**MONGO OPERATIONS**");
		
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		
		MongoDatabase database = mongoClient.getDatabase("demodb");
		
		MongoCollection<Document> collection = database.getCollection("Employee");
		
//		FindIterable<Document> i = collection.find();
//		for(Document d : i) {
//			System.out.println(d);
//		}
		
//		Document doc = new Document();
//		doc.append("name", "Pooja");
//		doc.append("age", 45);
//		doc.append("salary", 40000);
//		doc.append("designation", "Tester");
//		collection.insertOne(doc);
		
//		collection.insertOne(new Document().append("name", "Suman").append("age", 25).append("salary", 10000).append("designation", "Clerk"));
		
//		List<Document> empList = new ArrayList<Document>();
//		empList.add((new Document().append("name", "Raju").append("age", 50).append("salary", 1000000).append("designation", "Manager")));
//		empList.add((new Document().append("name", "Manju").append("age", 32).append("salary", 800000).append("designation", "Programmer")));
//		empList.add((new Document().append("name", "Sanju").append("age", 47).append("salary", 500000).append("designation", "Admin")));
//		
//		collection.insertMany(empList);
		
//		Bson projection1 = Projections.exclude("_id","designation");
//		Bson filter = Filters.eq("age",47);
//		Bson sort = Sorts.ascending("salary");
//		FindIterable<Document> i = collection.find(filter).projection(projection1).sort(sort);
//		for(Document d : i) {
//			System.out.println(d.toJson());
//		}
		
//		Bson filter = Filters.gte("age", 45);
//		Bson update = Updates.set("designation", "Manager");
//		collection.updateMany(filter,update);
		
		Bson filter = Filters.eq("name","Crystal");
		collection.deleteOne(filter);
		
		mongoClient.close();
	}
}
