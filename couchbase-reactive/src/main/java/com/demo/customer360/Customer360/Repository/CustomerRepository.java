package com.demo.customer360.Customer360.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.transcoder.JsonTranscoder;
import com.demo.customer360.Customer360.Model.Customer;
import com.demo.customer360.Customer360.Model.Entity;
import com.demo.customer360.Customer360.Model.customer360;
import com.demo.customer360.Customer360.configuration.Config;
import com.demo.customer360.Customer360.configuration.converter;
import com.demo.customer360.Customer360.response.CustomerResponse;
import com.demo.customer360.Customer360.response.Response;
import com.google.gson.Gson;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

@Repository
public class CustomerRepository {

	private final converter converterObj = new converter();
	private final JsonTranscoder transcoder = new JsonTranscoder();

	public customer360 getCustomerData(String customerId) {
		customer360 customer = new customer360();
		JsonDocument collection = null;
		Gson gson = new Gson();
		JsonObject obj = null;
		Bucket bucket = null;
		Response response = new Response();
		try {
			bucket = new Config().getCluster().openBucket("customer360");
			collection = bucket.get(customerId);
			if (collection == null) {
				response.setResCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
				response.setDaoResponseStatus(false);
				response.setResMsg("No data found for " + customerId);
				customer.setId(customerId);

			} else {
				obj = collection.content();
				obj = obj.put("id", collection.id());
				customer = gson.fromJson(obj.toString(), customer360.class);
				customer.setCas(collection.cas());
				response.setResCode(HttpStatus.OK.toString());
				response.setDaoResponseStatus(true);
				response.setResMsg("Data fetched successfully");
			}
			System.out.println("Customer " + customer.toString());

		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.fillInStackTrace().toString());

		} finally {
			bucket.close();
		}
		customer.setResponse(response);
		return customer;

	}

	public customer360 addNewCustomer(customer360 customer, String id) throws Exception {

		Bucket bucket = null;
		customer360 newCustomer = null;
		try {
			bucket = new Config().getCluster().openBucket("customer360");
			JsonDocument doc = toJSon(customer, id);
			doc = bucket.insert(doc);

			if (doc == null) {
				throw new Exception("Insert Failed");
			}
			JsonObject result = doc.content();
			newCustomer = converterObj.fromJson(result.toString(), customer360.class);
			newCustomer.setId(id);
			newCustomer.setCas(doc.cas());
			System.out.println("new Customer " + newCustomer.toString());
			bucket.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e.toString());
		}
		return newCustomer;

	}

	public customer360 updateCustomerDetails(customer360 customer, String id) {
		Bucket bucket = null;
		customer360 newCustomer = null;
		try {
			bucket = new Config().getCluster().openBucket("customer360");
			JsonDocument doc = toJSon(customer, id);
			doc = bucket.replace(doc);
			if (doc == null) {
				throw new Exception("Update failed");
			}
			JsonObject result = doc.content();
			newCustomer = converterObj.fromJson(result.toString(), customer360.class);
			newCustomer.setCas(doc.cas());
			System.out.println("new Customer " + newCustomer.toString());

		} catch (Exception e) {
			System.out.println("Exception is " + e.toString());
		}
		return newCustomer;
	}

	public customer360 upsertCustomerRecord(customer360 customer, String id) {

		Bucket bucket = null;
		customer360 newCustomer = null;
		try {
			bucket = new Config().getCluster().openBucket("customer360");
			JsonDocument doc = toJSon(customer, id);
			doc = bucket.upsert(doc);
			if (doc == null) {
				throw new Exception("Update failed");
			}
			JsonObject result = doc.content();
			newCustomer = converterObj.fromJson(result.toString(), customer360.class);
			System.out.println("new Customer " + newCustomer.toString());
			newCustomer.setCas(doc.cas());
		} catch (Exception e) {
			System.out.println("Exception is " + e.toString());
		}
		return newCustomer;

	}

	public Response deleteCustomerRecord(String id) {
		Bucket bucket = null;
		Response response = new Response();
		try {
			bucket = new Config().getCluster().openBucket("customer360");
			JsonDocument doc = null;
			doc = bucket.remove(id);
			System.out.println("doc " + doc.toString());
			if (doc.id().equals(id)) {
				response.setDaoResponseStatus(true);
				response.setResMsg("Record Deleted");
			}

			bucket.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e.toString());
		}
		return response;
	}

	private <T extends Entity> JsonDocument toJSon(T source, String id) throws Exception {

		if (source == null) {
			throw new IllegalArgumentException("entity is null");
		}

		if (id == null) {
			throw new IllegalStateException("entity ID is null");
		}
		try {
			JsonObject content = transcoder.stringToJsonObject(converterObj.toJson(source));
			JsonDocument doc = JsonDocument.create(id, content); // , source.getCas());
			return doc;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public Customer selectWithN1QL(String userName) {
		Bucket bucket = null;
		Customer customer = new Customer();
		Response response = new Response();
		Gson gson = new Gson();
		try {
			JsonObject ob = JsonObject.create().put("userName", userName);
			String sql = "select * from customer360 where userName=$userName";
			bucket = new Config().getCluster().openBucket("customer360");
			N1qlQueryResult result = bucket.query(N1qlQuery.parameterized(sql, ob));
			System.out.println("Result " + result.allRows().size());
			if (result.finalSuccess() && result.allRows().size() > 0) {
				System.out.println("query " + result.finalSuccess());
				for (N1qlQueryRow values : result) {
					JsonObject obj = values.value();
					customer = gson.fromJson(obj.toString(), Customer.class);
				}
				response.setDaoResponseStatus(result.finalSuccess());
				response.setResMsg("Insert Successful for " + userName);
			} else {
				response.setDaoResponseStatus(false);
				response.setResMsg("No documents found for " + userName);
			}
			customer.setResponse(response);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return customer;
	}

	public CustomerResponse updateWithN1Ql(customer360 customer, String userId) {

		Bucket bucket = null;
		Gson gson = new Gson();
		CustomerResponse customerObj = new CustomerResponse();
		try {
			JsonObject ob = JsonObject.create();
			String sql = "update customer360 use keys \"" + userId + "\" " + "set ";
			if (customer.getEmail() != null) {
				ob.put("email", customer.getEmail());
				sql = sql + "email=$email and ";
			}
			if (customer.getUserName() != null) {
				ob.put("userName", customer.getUserName());
				sql = sql + "userName=$userName and ";
			}
			if (customer.getLastName() != null) {
				ob.put("lastName", customer.getLastName());
				sql = sql + "lastName=$email";
			}

			if (sql.trim().endsWith("and")) {
				int pos = sql.lastIndexOf("and");
				sql = sql.substring(0, pos);
			}
			sql = sql + " Returning *";
			bucket = new Config().getCluster().openBucket("customer360");
			System.out.println(sql.toString());
			N1qlQueryResult resultSet = bucket.query(N1qlQuery.parameterized(sql, ob));
			System.out.println("Result " + resultSet.allRows().size());
			if (resultSet.finalSuccess() && resultSet.allRows().size() > 0) {
				for (N1qlQueryRow results : resultSet) {
					JsonObject obj = results.value();
					customerObj = gson.fromJson(obj.toString(), CustomerResponse.class);

				}
				customerObj.setResCode(HttpStatus.OK.toString());
				customerObj.setDaoResponseStatus(resultSet.finalSuccess());

			} else {
				customerObj.setResMsg("No record found for " + userId);
				customerObj.setDaoResponseStatus(false);
				customerObj.setResCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bucket.close();
		}
		return customerObj;
	}

	public Customer getDataWithObservable(String docId) {

		Gson gson = new Gson();
		final Customer customerObj = new Customer();
		Response response = new Response();
		try {
			final Bucket bucket = new Config().getCluster().openBucket("customer360");
			final AsyncBucket asyncBucket = bucket.async();
			Observable.just("customer360").flatMap(bucketInstance -> asyncBucket.get(docId)).doOnError(e -> {
				e.printStackTrace();
			}).doOnCompleted(() -> {
				bucket.close();
				asyncBucket.close();
			}).toBlocking().subscribe(new Subscriber<JsonDocument>() {

				@Override
				public void onCompleted() {
					response.setDaoResponseStatus(true);
					response.setResMsg("Data Fetched Successfully for " + docId);
					response.setResCode(HttpStatus.OK.toString());
					System.out.println("Response " + customerObj.toString());
					bucket.close();
					asyncBucket.close();
				}

				@Override
				public void onError(Throwable e) {
					e.printStackTrace();
					response.setDaoResponseStatus(false);
					response.setResMsg(e.fillInStackTrace().toString());
					response.setResCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
				}

				@Override
				public void onNext(JsonDocument t) {
					JsonObject obj = t.content();
					customer360 customer = new customer360();
					customer = gson.fromJson(obj.toString(), customer360.class);
					System.out.println("Customer " + customer.toString());
					customerObj.setId(t.id());
					customerObj.setCas(t.cas());
					customerObj.setCustomer360(customer);

				}

			});

		} catch (Exception e) {
			e.printStackTrace();
			response.setDaoResponseStatus(false);
			response.setResMsg(e.fillInStackTrace().toString());
			response.setResCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
		customerObj.setResponse(response);
		System.out.println("Customer finally " + customerObj.toString());
		return customerObj;
	}

	public customer360 getAsyncly(String docId) {

		customer360 response = new customer360();
		Gson gson = new Gson();
		Bucket bucket = null;
		AsyncBucket asyncBucket = null;
		JsonObject obj = JsonObject.create();
		Response res = new Response();
		try {

			bucket = new Config().getCluster().openBucket("customer360");
			asyncBucket = bucket.async();
			JsonDocument doc = asyncBucket.get(docId).map(docs -> {
				return docs;
			}).toBlocking().singleOrDefault(null);

			if (doc == null) {
				response.setId(docId);
				res.setResCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
				res.setDaoResponseStatus(false);
				res.setResMsg("No data found for " + docId);
			} else {
				obj = doc.content();
				response = gson.fromJson(obj.toString(), customer360.class);
				response.setId(docId);
				response.setCas(doc.cas());
				res.setDaoResponseStatus(true);
				res.setResCode(HttpStatus.OK.toString());
				res.setResMsg("Data fetched successfully");
				System.out.println("Response " + response.toString());
			}
		} catch (Exception e) {
			res.setResCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			res.setDaoResponseStatus(false);
			res.setResMsg(e.fillInStackTrace().toString());
			e.printStackTrace();
		} finally {
			response.setResponse(res);
			bucket.close();
		}
		return response;
	}

	public Response createAsyncDocument() {

		int docsToCreate = 100;
		List<JsonDocument> documents = new ArrayList<JsonDocument>();
		Response response = new Response();
		JsonObject obj = JsonObject.create();
		try {
			final Bucket bucket = new Config().getCluster().openBucket("customer360");
			for (int i = 1; i < docsToCreate; i++) {
				JsonObject content = JsonObject.create().put("counter", i).put("name", "Foo Bar");
				documents.add(JsonDocument.create("doc-" + i, content));
			}

			Observable.from(documents).flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
				@Override
				public Observable<JsonDocument> call(final JsonDocument docToInsert) {
					return bucket.async().insert(docToInsert);
				}
			}).doOnCompleted(() -> {
				response.setResCode(HttpStatus.OK.toString());
				response.setDaoResponseStatus(true);
				response.setResMsg("Insert is Successfull");
				bucket.close();
			}).doOnError(e -> {
				e.printStackTrace();
				response.setResCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
				response.setDaoResponseStatus(false);
				response.setResMsg(e.fillInStackTrace().toString());
				bucket.close();
			}).last().toBlocking().single(); // toBlocking()

			System.out.println("Obj " + obj.toString());
			/*
			 * subscribe(doc -> { response.setDaoResponseStatus(true);
			 * response.setResCode(HttpStatus.OK.toString());
			 * response.setResMsg("Insert is Successful"); System.out.println("Doc " +
			 * doc.content().toString()); });
			 */ // toBlocking().single();

			// sb.unsubscribe();
			// bucket.closef.();
			// System.out.println("Response "+response.toString());
		} catch (Exception e) {
			response.setDaoResponseStatus(false);
			response.setResMsg(e.fillInStackTrace().toString());
			e.printStackTrace();
		}
		return response;
	}
}
