package com.hncu.taoshu.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HttpClient {
	private static final int OK = 200;// OK: Success!
	private static final int NOT_MODIFIED = 304;// Not Modified: There was no
	// new data to return.
	private static final int BAD_REQUEST = 400;// Bad Request: The request was
	// invalid. An accompanying
	// error message will explain
	// why. This is the status code
	// will be returned during rate
	// limiting.
	private static final int NOT_AUTHORIZED = 401;// Not Authorized:
	// Authentication
	// credentials were missing
	// or incorrect.
	private static final int FORBIDDEN = 403;// Forbidden: The request is
	// understood, but it has been
	// refused. An accompanying
	// error message will explain
	// why.
	private static final int NOT_FOUND = 404;// Not Found: The URI requested is
	// invalid or the resource
	// requested, such as a user,
	// does not exists.
	private static final int NOT_ACCEPTABLE = 406;// Not Acceptable: Returned by
	// the Search API when an
	// invalid format is
	// specified in the request.
	private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server
	// Error: Something
	// is broken. Please
	// post to the group
	// so the Twitter
	// team can
	// investigate.
	private static final int BAD_GATEWAY = 502;// Bad Gateway: Twitter is down
	// or being upgraded.
	private static final int SERVICE_UNAVAILABLE = 503;// Service Unavailable:


	private final static boolean DEBUG = false;
	
	public Response get(String url) throws Exception{
//		return httpRequest(url,null,false,false);
		return httpRequest1(url,null,false,false);
	}
	public Response get(String url,boolean authenticated) throws Exception{
		return httpRequest(url,null,authenticated,false);
	}
	
	public Response post(String url, PostParameter[] postParams) throws Exception{
//		return httpRequest(url,postParams,false,false);
		return httpRequest1(url,postParams,false,false);
		
	}
	public Response post(String url, PostParameter[] postParams,boolean login) throws Exception{
//		return httpRequest(url,postParams,false,false);
		return httpRequest1(url,postParams,true,false);
		
	}
	
	
	protected Response httpRequest(String url, PostParameter[] postParams,
			boolean login, boolean redirect) throws Exception 
	{
		int retriedCount;
		int retry = 4;
		Response res = null;

		for (retriedCount = 0; retriedCount < retry; retriedCount++)
		{
			int responseCode = -1;
			try
			{
				HttpURLConnection con = null;
				OutputStream osw = null;
				try
				{
//					con = (HttpURLConnection) new URL(url).openConnection();
					con = getConnection(url);
					con.setInstanceFollowRedirects(redirect);
					con.setDoInput(true);
					if (null != postParams)
					{
						con.setRequestMethod("POST");
						con.setRequestProperty("Content-Type",
								"application/x-www-form-urlencoded");
//						con.setRequestProperty("connection", "close");
						con.setDoOutput(true);
						String postParam = encodeParameters(postParams);

						byte[] bytes = postParam.getBytes("UTF-8");

						con.setRequestProperty("Content-Length",
								Integer.toString(bytes.length));
						osw = con.getOutputStream();
						osw.write(bytes);
						osw.flush();
						osw.close();
					}
					else
					{
						con.setRequestMethod("GET");
					}
					res = new Response(con);
					if(login){
						String key,sessionId = null;
						System.out.println("cookies-->"+con.getHeaderField("Set-Cookie"));
				        if (con != null) { 
				            for (int i = 1; (key = con.getHeaderFieldKey(i)) != null; i++) { 
				               if (key.equalsIgnoreCase("Set-Cookie")) { 
				                   sessionId = con.getHeaderField(key); 
				                   sessionId = sessionId.substring(0, sessionId.indexOf(";")); 
				               } 
				            } 
				         } 
				         System.out.println(sessionId);
//						con.getHeaderFields();
					}
					
					responseCode = con.getResponseCode();
				
					if (responseCode != 302 && responseCode != OK)
					{
						if (responseCode < INTERNAL_SERVER_ERROR)
//								|| retriedCount == retryCount)
						{
							throw new  Exception(getCause(responseCode)+ "\n" + res.asString());
						}

					}

				}
				finally
				{
					try
					{
						osw.close();
					}
					catch (Exception ignore)
					{
					}
				}
			}
			catch (IOException ioe)
			{
				// connection timeout or read timeout
				if (retriedCount == 4)
				{
					throw new IOException(ioe.getMessage());
				}
			}
			try
			{
				if (DEBUG && null != res)
				{
					res.asString();
				}
				Thread.sleep(1000);
			}
			catch (InterruptedException ignore)
			{
				// nothing to do
			}
		}

		return res;
	}

	
	protected Response httpRequest1(String url, PostParameter[] postParams,
			boolean login, boolean redirect) throws Exception 
	{

		Response res = null;

			int responseCode = -1;
			try
			{
				HttpURLConnection con = null;
				OutputStream osw = null;
				try
				{
//					con = (HttpURLConnection) new URL(url).openConnection();
					con = getConnection(url);
					con.setInstanceFollowRedirects(redirect);
					con.setDoInput(true);
					if (null != postParams)
					{
						con.setRequestMethod("POST");
						con.setRequestProperty("Content-Type",
								"application/x-www-form-urlencoded");
						con.setRequestProperty("connection", "close");
						con.setDoOutput(true);
						String postParam = encodeParameters(postParams);
						
						byte[] bytes = postParam.getBytes("UTF-8");

						con.setRequestProperty("Content-Length",
								Integer.toString(bytes.length));
						osw = con.getOutputStream();
						osw.write(bytes);
						osw.flush();
						osw.close();
					}
					else
					{
						con.setRequestMethod("GET");
					}
					res = new Response(con);
					
					responseCode = con.getResponseCode();
				
					if(login){
						String key,sessionId = null;
				        if (con != null) { 
				            for (int i = 1; (key = con.getHeaderFieldKey(i)) != null; i++) {
				               if (key.equalsIgnoreCase("Set-Cookie")) { 
				                   sessionId = con.getHeaderField(i); 
				                   sessionId = sessionId.substring(0, sessionId.indexOf(";"));
				                  // cookie = cookie+"-->"+sessionId;
				                   break;
				               } 
				            } 
				         } 
				         System.out.println("sessionId-->"+sessionId);
					}
					if (responseCode != 302 && responseCode != OK)
					{
						if (responseCode < INTERNAL_SERVER_ERROR)
//								|| retriedCount == retryCount)
						{
							throw new  Exception(getCause(responseCode)+ "\n" + res.asString());

						}

					}

				}
				finally
				{
					try
					{
						osw.close();
					}
					catch (Exception ignore)
					{
					}
				}
			}
			catch (IOException ioe)
			{
				// connection timeout or read timeout
			}

		return res;
	}
	/**
	 * sets HTTP headers
	 * 
	 * @param connection
	 *            HttpURLConnection
	 * @param authenticated
	 *            boolean
	 */
	/*private void setHeaders(String url, PostParameter[] params,
			HttpURLConnection connection, boolean authenticated)
	{


		if (authenticated)
		{
			if (basic == null && oauth == null)
			{
			}
			String authorization = null;
			if (null != oauth)
			{
				// use OAuth
				authorization = oauth.generateAuthorizationHeader(
						params != null ? "POST" : "GET", url, params,
						oauthToken);
			}
			else if (null != basic)
			{
				// use Basic Auth
				authorization = this.basic;
			}
			else
			{
				throw new IllegalStateException(
						"Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
			}
			connection.addRequestProperty("Authorization", authorization);
			log("Authorization: " + authorization);
		}
		for (String key : requestHeaders.keySet())
		{
			connection.addRequestProperty(key, requestHeaders.get(key));
			log(key + ": " + requestHeaders.get(key));
		}
	}*/
	
	private HttpURLConnection getConnection(String url) throws IOException
	{
		HttpURLConnection con = null;
		con = (HttpURLConnection) new URL(url).openConnection();
			con.setConnectTimeout(3000);
			con.setReadTimeout(3000);
		return con;
	}

	
	public static String encodeParameters(PostParameter[] postParams)
	{
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < postParams.length; j++)
		{
			if (j != 0)
			{
				buf.append("&");
			}
			try
			{
				buf.append(URLEncoder.encode(postParams[j].name, "UTF-8"))
						.append("=")
						.append(URLEncoder.encode(postParams[j].value, "UTF-8"));
			}
			catch (Exception neverHappen)
			{
			}
		}
		return buf.toString();

	}
	
	
	private static String getCause(int statusCode)
	{
		String cause = null;
		// http://apiwiki.twitter.com/HTTP-Response-Codes-and-Errors
		switch (statusCode)
		{
			case NOT_MODIFIED:
				break;
			case BAD_REQUEST:
				cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
				break;
			case NOT_AUTHORIZED:
				cause = "Authentication credentials were missing or incorrect.";
				break;
			case FORBIDDEN:
				cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
				break;
			case NOT_FOUND:
				cause = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
				break;
			case NOT_ACCEPTABLE:
				cause = "Returned by the Search API when an invalid format is specified in the request.";
				break;
			case INTERNAL_SERVER_ERROR:
				cause = "Something is broken.  Please post to the group so the Twitter team can investigate.";
				break;
			case BAD_GATEWAY:
				cause = "Twitter is down or being upgraded.";
				break;
			case SERVICE_UNAVAILABLE:
				cause = "Service Unavailable: The Twitter servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
				break;
			default:
				cause = "";
		}
		return statusCode + ":" + cause;
	}
}
