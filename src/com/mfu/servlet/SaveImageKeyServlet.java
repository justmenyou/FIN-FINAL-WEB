package com.mfu.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.apphosting.utils.config.ClientDeployYamlMaker.Request;
import com.mfu.dao.PhotoFacade;
import com.mfu.entity.Photo;

@SuppressWarnings("serial")
public class SaveImageKeyServlet extends HttpServlet {
	public static BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private static final Logger logger = Logger.getLogger(SaveImageKeyServlet.class.getName());
	public static ImagesService imagesService = ImagesServiceFactory.getImagesService();

	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		logger.info("inside the doPost method");

		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("photo");

		ObjectMapper mapper = new ObjectMapper();
		Photo photo = new Photo();

		photo.setTripKey(req.getParameter("tripKey"));

		PhotoFacade photoFacade = new PhotoFacade();

		try {
			if (blobKeys == null || blobKeys.isEmpty()) {
				logger.info("inside the doPost method-recieved null");
				resp.sendRedirect("/");
			} else {
				logger.info("inside the doPost method-processing blobs");

				ServingUrlOptions servingOptions = ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0));
				String servingUrl = imagesService.getServingUrl(servingOptions);

				String outputJson = "{ \"key\":\"" + blobKeys.get(0).getKeyString() + "\" ,\"url\":\"" + servingUrl
						+ "\"}";

				photo.setPhotoKey(blobKeys.get(0).getKeyString());

				photoFacade.savePhoto(photo);

				photoFacade.closeEntityManager();

				logger.info("inside the doPost method-done-outputjson ::  " + outputJson);
				resp.setContentType("application/json");
				resp.getWriter().println(outputJson);
				resp.getWriter().flush();
			}
		} catch (Exception e) {
			logger.warning("Exception occured while saving the file " + e.getMessage());
			e.printStackTrace();
		}
	}
}
