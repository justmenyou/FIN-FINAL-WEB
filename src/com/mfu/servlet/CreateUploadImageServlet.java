package com.mfu.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import java.util.logging.Logger;

/**
 * Created by Ajay on 4/23/16.
 * https://cloud.google.com/appengine/docs/java/blobstore/
 */
public class CreateUploadImageServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(CreateUploadImageServlet.class.getName());

	public static BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	public static ImagesService imagesService = ImagesServiceFactory.getImagesService();

	/**
	 * Upload URL request to get the upload url Example output JSON { "url":
	 * "http://localhost:8080/_ah/upload/ag9teUFwcGxpY2F0aW9uSWRyIgsSFV9fQmxvYlVwbG9hZFNlc3Npb25fXxiAgICAgICACQw"
	 * } Call blobstoreService.createUploadUrl to create an upload URL for the
	 * form that the user will fill out, passing the application path to load
	 * when the POST of the form is completed.
	 */

	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("inside the doGet method");
		resp.setContentType("application/json");
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(resp.getWriter(), blobstoreService.createUploadUrl("/saveImageKey"));
			resp.getWriter().println("{ \"url\":\"" + blobstoreService.createUploadUrl("/saveImageKey") + "\"}");

		} catch (Exception e) {
			e.printStackTrace();
			logger.warning("Exception occurred while getting the URL from Blob Store " + e.getMessage());
		}

	}

	/**
	 * Post request to upload file. Make sure the blob file is sent as form-data
	 * with --> myfile = files The form must include a file upload field, and
	 * the form's enctype must be set to multipart/form-data. When the user
	 * submits the form, the POST is handled by the Blobstore API, which creates
	 * the blob. The API also creates an info record for the blob and stores the
	 * record in the datastore, and passes the rewritten request to your
	 * application on the given path as a URL and Blob Key.
	 */
	/*
	 * @Override public void doPost(HttpServletRequest req, HttpServletResponse
	 * resp) throws IOException{
	 * 
	 * logger.info("inside the doPost method");
	 * 
	 * Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
	 * List<BlobKey> blobKeys = blobs.get("photo"); try { if (blobKeys == null
	 * || blobKeys.isEmpty()) { logger.info(
	 * "inside the doPost method-recieved null"); resp.sendRedirect("/"); } else
	 * { logger.info("inside the doPost method-processing blobs");
	 * 
	 * ServingUrlOptions servingOptions =
	 * ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0)); String servingUrl
	 * = imagesService.getServingUrl(servingOptions);
	 * 
	 * String outputJson = "{ \"key\":\""+blobKeys.get(0).getKeyString()+
	 * "\" ,\"url\":\""+servingUrl+"\"}";
	 * 
	 * logger.info("inside the doPost method-done-outputjson ::  "+outputJson);
	 * resp.setContentType("application/json");
	 * resp.getWriter().println(outputJson); resp.getWriter().flush(); } }catch
	 * (Exception e){ logger.warning("Exception occured while saving the file "
	 * +e.getMessage()); e.printStackTrace(); } }
	 */

	/**
	 * Delete request to upload file. key = blobstore id Example URL
	 * /upload?key=abcew1232
	 */
	/*
	 * @Override public void doDelete(HttpServletRequest req,
	 * HttpServletResponse resp) throws ServletException, IOException{
	 * 
	 * String key = req.getParameter("key"); logger.info(
	 * "inside the doDelete method for key :+"+key);
	 * 
	 * try{ if(key!=null) blobstoreService.delete(new BlobKey(key));
	 * 
	 * }catch (BlobstoreFailureException e){ logger.info(
	 * "inside the doDelete occurred BlobstoreFailureException : "
	 * +e.getMessage()); e.printStackTrace(); } catch (Exception e){
	 * logger.info("inside the doDelete occurred Exception : "+e.getMessage());
	 * e.printStackTrace(); }
	 * 
	 * resp.setContentType("text/plain"); resp.getWriter().println(
	 * "Delete request is processed without any problems "); }
	 */

}
