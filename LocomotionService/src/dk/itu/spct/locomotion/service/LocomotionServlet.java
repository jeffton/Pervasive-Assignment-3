package dk.itu.spct.locomotion.service;

import javax.servlet.http.HttpServlet;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public abstract class LocomotionServlet extends HttpServlet {
	protected static BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
}
