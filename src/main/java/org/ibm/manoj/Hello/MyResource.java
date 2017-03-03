package org.ibm.manoj.Hello;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource 
{

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() 
    {
        return "Got it!";
    }
    
   @GET
   @Path("everything")
   @Produces(MediaType.APPLICATION_JSON)
   public String getEverything()
   {
	   String urls[] = new String[2];
	   
	   try
	   {
		   FileInputStream fstream = new FileInputStream("/Users/Manoj/Documents/urls.txt");
		   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		   String strLine;

		   //Read File Line By Line
		   System.out.println("Reading");
		   int i=0;
		   while ((strLine = br.readLine()) != null)  
		   {
		     // Print the content on the console
		     System.out.println (strLine);
		     urls[i] = strLine;
		     i++;
		   }

		   //Close the input stream
		   br.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   
	   System.out.println("Calling one!");
	   String oneResult = callOne(urls[0]);
	   System.out.println("One returned!: " + oneResult);
	   
	   System.out.println("Calling two!");
	   String twoResult = callTwo(urls[1]);
	   System.out.println("Two returned!: " + twoResult);
	   
	  JSONObject result = new JSONObject();	  
	  try
	  {
		  //result.put("Key", "Value");
		  JSONObject one = new JSONObject(oneResult);
		  one.put("URL", urls[0]);
		  JSONObject two = new JSONObject(twoResult);
		  two.put("URL", urls[1]);
		  
		  JSONArray jsonArray = new JSONArray();
		  jsonArray.put(one);
		  jsonArray.put(two);
		
		  result.put("Result",jsonArray);
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return result.toString();

   }
   
   @GET
   @Path("one")
   @Produces(MediaType.APPLICATION_JSON)
   public String getOne()
   {
	  JSONObject result = new JSONObject();
	  try
	  {
		  result.put("JARNAME", "JAR_ONE");
		  result.put("JARVERSION", "JAR_ONE_V1");
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return result.toString();
   }
   
   @GET
   @Path("two")
   @Produces(MediaType.APPLICATION_JSON)
   public String getTwo()
   {
	  JSONObject result = new JSONObject();
	  try
	  {
		  result.put("JARNAME", "JAR_TWO");
		  result.put("JARVERSION", "JAR_TWO_V1");
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return result.toString();
   }

   public String callOne(String url)
	{
		String USER_AGENT = "Mozilla/5.0";

		try
		{
			//String url = "http://localhost:8080/Hello/webapi/myresource/one";

			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
						
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) 
			{
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println("One called: " + response.toString());
			
			return response.toString();


		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
   
   public String callTwo(String url)
	{
		String USER_AGENT = "Mozilla/5.0";

		try
		{
			//String url = "http://localhost:8080/Hello/webapi/myresource/two";

			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
						
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) 
			{
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println("Two called: " + response.toString());
			
			return response.toString();

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	
	}
   
   
   public void writeFile()
   {
	   FileOutputStream out = null;
	   try
	   {
		   out = new FileOutputStream("/Users/Manoj/Documents/urls.txt");
		   System.out.println("File Created");
		   out.write('A');
		   out.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   
   public void readFile()
   {
	   try
	   {
		   FileInputStream fstream = new FileInputStream("/Users/Manoj/Documents/urls.txt");
		   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		   String strLine;

		   //Read File Line By Line
		   System.out.println("Reading");
		   while ((strLine = br.readLine()) != null)  
		   {
		     // Print the content on the console
		     System.out.println (strLine);
		   }

		   //Close the input stream
		   br.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   
}
