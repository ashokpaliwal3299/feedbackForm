package com.feedback.form.scheduledTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.feedback.form.Utils.ExcelUtils;
import com.feedback.form.model.FeedbackForm;
import com.feedback.form.model.SiteMaster;
import com.feedback.form.repository.UrlRepository;
import com.feedback.form.service.EmailService;
import com.feedback.form.service.FeedbackFormService;
import com.feedback.form.service.SiteMasterService;

import jakarta.mail.MessagingException;

@Service
public class ScheduledTasks {

	@Autowired
	private EmailService emailService;

	@Autowired
	private SiteMasterService siteService;

	@Autowired
	private FeedbackFormService formService;
	
	@Autowired
	private UrlRepository urlRepo;

//	private String url = "https://feed-back-form-nine.vercel.app/feedBackForm/";
	
	private String url = "";
	
	public String getUrl() {
		url = urlRepo.findById(1L).get().getUrlLink();
		return url;
	}

	// *************** Initial Mail trigger *****

	@Scheduled(cron = "0 10 15 19 * ?", zone = "Asia/Kolkata")
	public void checkAndProcessForms() {
		System.out.println("scheduler runing...");
		List<SiteMaster> formsToProcess = siteService.getAllSiteMaster();

		for (SiteMaster site : formsToProcess) {
			triggerEmails(site, url + site.getId());
		}
	}

	private void triggerEmails(SiteMaster site, String url) {
		if (site != null && site.getSiteInchargeEmail() != null) {
			String to = site.getSiteInchargeEmail();
			System.out.println("to " + to);
			String subject = "Your Feedback Matters - Share Your Experience";
			String body = "Dear Sir, \n\n" + "We hope this email finds you well.\n\n"
					+ "At iSmart Facitech Pvt Ltd, we strive to provide exceptional Facilities Management services. Your feedback is invaluable to us as it helps us identify areas for improvement and continue delivering the highest quality service.\n\n"
					+ "We would be grateful if you could take a few minutes to complete the attached feedback form. Your insights will help us enhance our services and better meet your needs.\n\n"
					+ "Thank you for your time and cooperation.\n\n" + "Feedback form link - " + url + " \n\n"
					+ "Sincerely,\r\n" + "Team Operations \r\n" + "iSmart Facitech Pvt Ltd \n\n"
					+ "This is a system generated email and not manned. To communicate with us, kindly email on  _____________";
			emailService.sendEmail(to, subject, body);
		}
	}

	// ***** Remainder mail trigger *******

	public List<SiteMaster> remainigSites() {

		LocalDate currentDate = LocalDate.now();
		Integer year = currentDate.getYear();

		String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

		List<SiteMaster> allSite = siteService.getAllSiteMaster();

		List<FeedbackForm> formFilledSites = formService.siteWhoFilledFormInYearAndMonth(year, month);

		List<SiteMaster> remainingSites = allSite.stream()
				.filter(site -> formFilledSites.stream().noneMatch(form -> form.getSiteId().equals(site.getId())))
				.collect(Collectors.toList());

		return remainingSites;
	}

	@Scheduled(cron = "0 0 11 19 * ?", zone = "Asia/Kolkata")
	public void processRemainderMail() {

		List<SiteMaster> remainingSiteList = remainigSites();

		for (SiteMaster site : remainingSiteList) {
			triggerRemainderEmails(site, url + site.getId());
		}
	}

	private void triggerRemainderEmails(SiteMaster site, String url) {
		if (site != null && site.getSiteInchargeEmail() != null) {
			String to = site.getSiteInchargeEmail();
			String subject = "Remainder : Your Feedback Matters - Share Your Experience";
			String body = "Dear Sir, \n\n" + "We hope this email finds you well.\n\n"
					+ "We're following up on our previous email regarding your feedback on the Facilities Management services provided by iSmart Facitech Pvt Ltd. Your insights are crucial to help us improve our services.\n\n"
					+ "Please take a moment to review the attached feedback form and share your thoughts. Your feedback will directly contribute to enhancing our future service delivery.\n\n"
					+ "Thank you for your time and cooperation.\n\n" + "Feedback form link - " + url + " \n\n"
					+ "Sincerely,\r\n" + "Team Operations \r\n" + "iSmart Facitech Pvt Ltd \n\n"
					+ "This is a system generated email and not manned. To communicate with us, kindly email on  _____________";
			emailService.sendEmail(to, subject, body);
		}
	}

	// ***** Remaining site report ******

//	@Scheduled(cron = "0 31 23 19 * ?", zone = "Asia/Kolkata")
	public void remainingSitesExcelReportMail() throws IOException, MessagingException {

		List<SiteMaster> remainingSiteList = remainigSites();

		if(remainingSiteList != null && remainingSiteList.size() != 0) {
			byte[] report = writeToExcel(remainingSiteList);
			emailService.sendReportToAdmin("ashok.k@ismartFacitech.com", report);
		}
	}

	private byte[] writeToExcel(List<SiteMaster> siteList) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sites");

		String[] headers = { "CLIENT NAME", "SITE NAME", "SITE CODE", "SITE INCHARGE EMAIL" };

		// Company Name
		XSSFRow companyNameRow = sheet.createRow(0);
		companyNameRow.createCell(0).setCellValue(ExcelUtils.getCompanyName());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));

		// Summary Report
		XSSFRow summaryRow = sheet.createRow(1);
		summaryRow.createCell(0).setCellValue(ExcelUtils.getReportHeading() + "Site Report - Pending Feedback Forms");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, headers.length - 1));

		XSSFCellStyle headerCellStyle = createHeaderCellStyle(workbook, IndexedColors.GREY_25_PERCENT);

		// Blank Row
		sheet.createRow(2);

		// Create header row
		XSSFRow headerRow = sheet.createRow(3);
		createHeaderRow(headerRow, headers, headerCellStyle);

		int rowCount = 4;

		for (SiteMaster site : siteList) {
			XSSFRow row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(site.getClientMaster().getName());
			row.createCell(1).setCellValue(site.getLocation_name());
			row.createCell(2).setCellValue(site.getLocation_code());
			row.createCell(3).setCellValue(site.getSiteInchargeEmail());
		}

		// Auto-size columns
		for (int i = 0; i < headers.length; i++) {
			sheet.autoSizeColumn(i);
		}

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			workbook.write(bos);
			return bos.toByteArray();
		} finally {
			workbook.close();
		}
	}

	private static void createHeaderRow(XSSFRow row, String[] headers, XSSFCellStyle style) {
		for (int i = 0; i < headers.length; i++) {
			createHeaderCell(row, i, headers[i], style);
		}
	}

	private XSSFCellStyle createHeaderCellStyle(XSSFWorkbook workbook, IndexedColors color) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(color.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Set font bold
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		style.setFont(font);

		return style;
	}

	private static void createHeaderCell(XSSFRow row, int column, String value, XSSFCellStyle style) {
		XSSFCell cell = row.createCell(column);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}
}
