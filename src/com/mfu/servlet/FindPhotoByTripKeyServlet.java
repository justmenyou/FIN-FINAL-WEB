
package com.mfu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.PhotoFacade;
import com.mfu.entity.Photo;

public class FindPhotoByTripKeyServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PhotoFacade facade = new PhotoFacade();
		List<Photo> photos = facade.findPhotoByTripKey(req.getParameter("tripKey"));

		facade.closeEntityManager();

		try {
			ObjectMapper mapper = new ObjectMapper();
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
			resp.addHeader("Access-Control-Allow-Headers",
					"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			resp.addHeader("Access-Control-Max-Age", "1728000");
			resp.setContentType("application/json");

			mapper.writeValue(resp.getOutputStream(), photos);
			resp.getOutputStream().flush();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
