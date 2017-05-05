
package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.PhotoFacade;
import com.mfu.entity.Photo;

public class SavePhotoservlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			Photo photo = mapper.readValue(req.getReader(), Photo.class);

			PhotoFacade facade = new PhotoFacade();

			if (photo != null)
				facade.savePhoto(photo);

			facade.closeEntityManager();
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
			resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			resp.addHeader("Access-Control-Max-Age", "1728000");

			resp.setContentType("application/json");
			resp.getWriter().print(1);
			resp.getWriter().flush();
			
		} catch (Exception e) {

			e.printStackTrace();
			throw new IOException(e.getMessage());
		}

	}
}
