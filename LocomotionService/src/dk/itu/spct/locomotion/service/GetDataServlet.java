package dk.itu.spct.locomotion.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class GetDataServlet extends LocomotionServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String id = req.getParameter("id");
		
		if(id == null) {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query q = new Query("__BlobInfo__");
 
			PreparedQuery pq = datastore.prepare(q);
			List<Entity> entList = pq.asList(FetchOptions.Builder.withDefaults());
			Gson gson = new Gson();
		    resp.setContentType("application/json");
			resp.getWriter().write(gson.toJson(entList));
			resp.setStatus(HttpServletResponse.SC_OK);
			
		} else {
			BlobKey blobKey = new BlobKey(id);
			blobstoreService.serve(blobKey, resp);
		}
	}
}
