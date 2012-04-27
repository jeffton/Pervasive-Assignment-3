package dk.itu.spct.locomotion.service;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import dk.itu.spct.locomotion.shared.LocomotionData;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(UploadServlet.class.getName());
	
	static {
		ObjectifyService.register(LocomotionData.class);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String json = req.getParameter("data");
		
		// validate content
		if(json.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "The 'data' argument is empty or missing.");
			return;
		}
		
		// save uploaded data
		LocomotionData data = LocomotionData.fromJson(json);
		Objectify ofy = ObjectifyService.begin();
		ofy.put(data);
		
		resp.setStatus(HttpServletResponse.SC_OK);
	}
	
}
