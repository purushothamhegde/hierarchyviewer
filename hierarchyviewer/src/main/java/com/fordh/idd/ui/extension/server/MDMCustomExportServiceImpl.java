package com.fordh.idd.ui.extension.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class MDMCustomExportServiceImpl extends HttpServlet  {
	
	public static Logger logger = Logger.getLogger(MDMCustomExportServiceImpl.class.getName());
	
	private String encoding = "ISO-8859-1";
	public MDMCustomExportServiceImpl() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String initParameterEncoding = config.getInitParameter("encoding");
		if (initParameterEncoding != null) {
			setEncoding(initParameterEncoding);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		export(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		export(request, response);
	}

	private void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Inside Export");
		String content = request.getParameter("txthierarchyname");
		String Outtext="";
		String selArr[]= content.split("-");
		
		if (selArr[0].equals("MRU")){
			logger.info("Inside MRU Export");
			MRUHeirarchyData myMruData = new MRUHeirarchyData();
			Outtext=myMruData.ExportMRUHeirarchy(content);
				
		}
		else if (selArr[0].equals("ORU")){
			logger.info("Inside ORU Export");
			ORUHeirarchyData myORUData = new ORUHeirarchyData();
			Outtext=myORUData.ExportORUHeirarchy(content);
		}	
		else if (selArr[0].equals("CCH")){
			logger.info("Inside CCH Export");
			CCHeirarchyData myccData = new CCHeirarchyData();
			Outtext=myccData.ExportCCHeirarchy(content);
		}
		else if (selArr[0].equals("GEO")){
			logger.info("Inside Geo Export");
			GeoHierarchyData mygeoData = new GeoHierarchyData();
			Outtext=mygeoData.ExportGeoHeirarchy(content);

		}
		else if (selArr[0].equals("PRODUCT_TREE")){
			logger.info("Inside Product Tree Export");
			ProductTreeData myptData = new ProductTreeData() ;
			Outtext=myptData.ExportProductTreeHeirarchy(content);

		}
		else if (selArr[0].equals("BMC")){
			logger.info("Inside BMC Export");
			BMCData myBMCData = new BMCData() ;
			Outtext=myBMCData.ExportBMCHeirarchy(content);

		}
		
		else if (selArr[0].equals("FSITEM")){
			logger.info("Inside FS Item Export");
			FsItemData myFsItemData = new FsItemData() ;
			Outtext=myFsItemData.ExportFSItemHeirarchy(content);

		}

		else if (selArr[0].equals("REPITEM")){
			logger.info("Inside Reporting Item Export");
			ReportingItemData myRepItemData = new ReportingItemData() ;
			Outtext=myRepItemData.ExportReportingItemHeirarchy(content);

		}
		
		
		
		
		String fileName = content + "_Data_Export.csv";
		
		response.setContentType("application/vnd.ms-excel");
		//response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentLength(Outtext.length());
		IOUtils.write(Outtext, response.getOutputStream(), encoding);
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	

}
