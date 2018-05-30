package com.jignesh.zycus.CustomerOnboarding;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;



@Path("/template")
public class ExcelService {

	public static final String UPLOAD_FILE_SERVER = "C:/Demo/upload/";

	
	@GET
	@Path("/download")
	@Produces("application/vnd.ms-excel")
	public Response downloadExcelTemplate() {

		// set file (and path) to be download
		File file = new File("C:/Demo/download/Cust-details.xlsx");

		ResponseBuilder responseBuilder = Response.ok((Object) file);
		responseBuilder.header("Content-Disposition", "attachment; filename=\"Cust-details.xlsx\"");
		return responseBuilder.build();
	}

	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadExcelTemplate(
			@FormDataParam("uploadFile") InputStream fileInputStream, 
			@FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition) {

		// local variables
		String fileName = null;
		String uploadFilePath = null;

		try {
			fileName = fileFormDataContentDisposition.getFileName();
			uploadFilePath = writeToFileServer(fileInputStream, fileName);
		} 
		catch(IOException e){
			e.printStackTrace();
		}
		
		return Response.ok("File uploaded successfully at " + uploadFilePath).build();
	}

	/**
	 * write input stream to file server
	
	 */
	private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {

		OutputStream outputStream = null;
		String qualifiedUploadFilePath = UPLOAD_FILE_SERVER + fileName;

		try {
			outputStream = new FileOutputStream(new File(qualifiedUploadFilePath));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.flush();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			//release resources
			outputStream.close();
		}
		return qualifiedUploadFilePath;
	}
}