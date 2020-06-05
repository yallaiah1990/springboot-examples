package com.employeemngt.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemngt.service.ReportService;

import io.swagger.annotations.Api;

@RestController
@Api("Downloading Report In PDF and Excel and CSV formate")
public class ReportController {

	private final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	ReportService reportService;

	@GetMapping(value = "/downloadReportInPdf", produces = "text/pdf")
	public void downloadReportInPdf(HttpServletResponse response) {
		reportService.downloadReportInPdf(response);
	}

	@GetMapping(value = "/downloadReportInExcel", produces = "text/pdf")
	public void downloadReportInExcel(HttpServletResponse response) {
		reportService.downloadReportInExcel(response);
		logger.info("downloadReport In Excel");
	}

	@GetMapping(value = "/downloadReportInCsv", produces = "text/csv")
	public void downloadReportInCsv(HttpServletResponse response) {
		reportService.downloadReportInCsv(response);
	}
}
