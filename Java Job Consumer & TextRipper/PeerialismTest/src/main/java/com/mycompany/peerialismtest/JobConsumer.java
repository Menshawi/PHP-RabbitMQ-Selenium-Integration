
package com.mycompany.peerialismtest;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author em
 */
public class JobConsumer {
    private final static String QUEUE_NAME = "peerialism";
    public static void main(String [] argv) throws IOException, InterruptedException, JSONException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("[*] Waiting for a message.");
        
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);
        
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            
            JSONObject messageObject = new JSONObject(message);
            
            
            System.out.println("[x] URL is '"+messageObject.getString("url")+"'");
            System.out.println("[y] ID is '"+messageObject.getString("id")+"'");
            
            TextRipper MessageRipper = new TextRipper(messageObject.getString("url"), 
                    messageObject.getString("id"));
            String result = TextRipper.getInformation();
            System.out.println("RIPPER OUTPUT: " + result);
            
            if(insertInDB(messageObject.getString("jid"), messageObject.getString("url"), messageObject.getString("id"), result) == "done"){
                System.out.println("updated in db");
            }
            
        }
        
        
    }
    
    
    public static String insertInDB(String jid, String url, String id, String result){
            HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://127.0.0.1/index.php?i=update");
	    //HttpResponse response;
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("jid", jid));
                nameValuePairs.add(new BasicNameValuePair("url", url));
	        nameValuePairs.add(new BasicNameValuePair("id", id));
                nameValuePairs.add(new BasicNameValuePair("result", result));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        
	     // Execute HTTP Post Request
	        ResponseHandler<String> responseHandler = new BasicResponseHandler();
	        String response = httpclient.execute(httppost, responseHandler);
	        return response;
	    }
	    catch (NullPointerException e) {
			// TODO: handle exception
			throw new Error("NULL POINTER EXCEPTION");
		}
		catch (IOException e) {
			// TODO: handle exception
			throw new Error("Unable to Create the Database!");
		}
	}

    
}
