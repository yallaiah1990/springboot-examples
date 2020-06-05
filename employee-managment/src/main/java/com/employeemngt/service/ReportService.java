package com.employeemngt.service;

import javax.servlet.http.HttpServletResponse;

public interface ReportService {

	public void downloadReportInExcel(HttpServletResponse response);

	public void downloadReportInPdf(HttpServletResponse response);

	public void downloadReportInCsv(HttpServletResponse response);

}
