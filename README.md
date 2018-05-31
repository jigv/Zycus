For implementation of excel upload and download code through /customers/upload API
I have used JAX RS API with jersey.
Below are the steps I have taken in development of the code.
In Eclipse IDE, created a Maven Project by name CustomerOnboarding.
While creating Maven project selected Archetype as jersey-quickstart-webapp with version 2.26 for jersey.
Created a package called com.jignesh.zycus and a new class named ExcelService.java which will contain code for
Mock API and code for upload/download of template.
Added apache tomcat v7.0 application server in eclipse.
In Maven pom.xml a dependency is added for Jersey Multipart.


 In the ExcelService.java class file there are 2 methods implemented for Upload and Download of excel file. Following implementation were done:

a. For excel upload Get InputStream and FormDataContentDisposition was used and excel file written to upload path through using file handling operations.
   Content Disposition was used to get actual filename.

b. To download an excel file object was creared supplying file path location for .xlsx file. This file object is then set in the ResponseBuilder to return response object.


To test the API following steps need to be followed:

a. To test excel template upload, in a web browser enter URI http://localhost:8080/CustomerOnboarding/webapi/template/upload.
    You should get response saying file successfully uploaded to the server path mentioned in the code.

b. To test excel template download, in a web browser enter URI http://localhost:8080/CustomerOnboarding/webapi/template/download.
   You should get file download dialogue box from where you can save your file.
