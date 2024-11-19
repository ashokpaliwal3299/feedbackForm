package com.feedback.form.scheduledTask;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.feedback.form.model.ClientSiteMaster;
import com.feedback.form.service.ClientSiteMasterService;
import com.feedback.form.service.EmailSchedulerService;
import com.feedback.form.service.EmailService;
import com.feedback.form.service.FeedbackFormService;

import jakarta.mail.MessagingException;

@Service
public class RemainingSites {

	@Autowired
	private EmailService emailService;

	@Autowired
	private ClientSiteMasterService siteService;

	@Autowired
	private FeedbackFormService formService;

	@Autowired
	private EmailSchedulerService schedulerService;

	// @Scheduled(cron = "0 00 11 19 * ?")
	public void checkAndProcessForms() throws MessagingException, IOException {
		System.out.println("scheduler runing...");
		List<ClientSiteMaster> formsToProcess = schedulerService.remainigClientSites();

		byte[] report = writeToExcel(formsToProcess);

		emailService.sendReportToSiteIncharge("ashokpaliwal3299@gmail.com", report);
	}

	private byte[] writeToExcel(List<ClientSiteMaster> formsToProcess) {
		Workbook workbook = new XSSFWorkbook(); // Create a new workbook
		Sheet sheet = workbook.createSheet("Client Sites");

		// Create header row
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Client Name");
		headerRow.createCell(1).setCellValue("Site Name");
		headerRow.createCell(2).setCellValue("Email");

		// Write data rows
		int rowNum = 1;
		for (ClientSiteMaster site : formsToProcess) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(site.getClientName());
			row.createCell(1).setCellValue(site.getSiteName());
			row.createCell(2).setCellValue(site.getEmail());
		}

		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		try {
			workbook.write(byteArrayOut); // Write the workbook to the ByteArrayOutputStream
			byteArrayOut.flush(); // Ensure all data is written
			System.out.println("Data has been written to byte array successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close(); // Close the workbook
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return byteArrayOut.toByteArray();
	}
}
