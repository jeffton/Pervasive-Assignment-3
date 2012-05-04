package dk.itu.spct.locomotion.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;

import dk.itu.spct.locomotion.shared.DataPoint;
import dk.itu.spct.locomotion.shared.LocomotionData;

@SuppressWarnings("serial")
public class UploadServlet extends LocomotionServlet {
	private static char CSV_DELIMITER = ',';
	private static SimpleDateFormat formatter = new SimpleDateFormat(
			"d-M-y_H-m");

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String json = req.getParameter("data").trim();

		// validate content
		if (json.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"The 'data' argument is empty or missing.");
			return;
		}

		// save uploaded data
		LocomotionData data = LocomotionData.fromJson(json);

		// Get a file service
		FileService fileService = FileServiceFactory.getFileService();

		// Create a new Blob file with mime-type "text/plain"
		AppEngineFile file = fileService.createNewBlobFile("text/csv",
				createFileName(data));

		FileWriteChannel writeChannel = fileService
				.openWriteChannel(file, true);

		// Write to the channel directly
		writeChannel.write(ByteBuffer.wrap(createCsvFile(data).getBytes()));

		// Now finalize
		writeChannel.closeFinally();
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.sendRedirect("/");
	}

	private String createCsvFile(LocomotionData data) {

		StringBuilder res = new StringBuilder();

		// header row
		res.append("timestamp");
		res.append(CSV_DELIMITER);
		res.append("x");
		res.append(CSV_DELIMITER);
		res.append("y");
		res.append(CSV_DELIMITER);
		res.append("z");
		res.append('\n');

		for (DataPoint dp : data.getDataPoints()) {
			res.append(dp.getTimestamp());
			res.append(CSV_DELIMITER);
			res.append(dp.getX());
			res.append(CSV_DELIMITER);
			res.append(dp.getY());
			res.append(CSV_DELIMITER);
			res.append(dp.getZ());
			res.append('\n');
		}

		res.deleteCharAt(res.length() - 1);

		return res.toString();
	}

	private String createFileName(LocomotionData data) {
		String filename = formatter.format(data.getDate());
		filename += "-" + data.getName().replace(' ', '-') + ".csv";
		return filename;
	}

}
