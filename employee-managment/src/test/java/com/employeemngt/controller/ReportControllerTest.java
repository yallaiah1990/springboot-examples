package com.employeemngt.controller;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeemngt.service.impl.ReportServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReportControllerTest {

	@InjectMocks
	public ReportController reportController;

	@Mock
	public ReportServiceImpl reportService;

	@Mock
	HttpServletResponse response;

	@Test
	public void downloadReportInPdfTest() {

		doNothing().when(reportService).downloadReportInPdf(response);

		reportController.downloadReportInPdf(response);

		verify(reportService, times(1)).downloadReportInPdf(eq(response));

	}

	@Test
	public void downloadReportInExcelTest() {
		doNothing().when(reportService).downloadReportInExcel(response);

		reportController.downloadReportInExcel(response);

		verify(reportService, times(1)).downloadReportInExcel(eq(response));
	}

	@Test
	public void downloadReportInCsvTest() {
		doNothing().when(reportService).downloadReportInCsv(response);

		reportController.downloadReportInCsv(response);

		verify(reportService, times(1)).downloadReportInCsv(eq(response));
	}
}
