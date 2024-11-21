package com.feedback.form.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.form.Dto.feedbackFormDto;
import com.feedback.form.Exception.AlreadyExistsException;
import com.feedback.form.Exception.RecordNotFoundException;
import com.feedback.form.Utils.ExcelUtils;
import com.feedback.form.model.FeedbackForm;
import com.feedback.form.model.SiteMaster;
import com.feedback.form.repository.FeedbackFormRepository;
import com.feedback.form.repository.SiteMasterRepository;

import jakarta.mail.MessagingException;

@Service
public class FeedbackFormService {

	@Autowired
	private FeedbackFormRepository fedbackRepo;

	@Autowired
	private SiteMasterRepository siteRepo;

	@Autowired
	private EmailService emailService;

	public FeedbackForm addFeedbackForm(FeedbackForm form, Long siteId) throws IOException, MessagingException {
		
		LocalDate currentDate = LocalDate.now();
		int year = currentDate.getYear();

		String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

		if (siteId == 0 || siteId == null) {
			throw new RecordNotFoundException("Site id should not 0 or null");
		}

		Optional<FeedbackForm> optForm = fedbackRepo.findBySiteIdAndMonthAndYearAndIsDeletedFalse(siteId, month, year);
		
		if (optForm.isPresent()) {
			throw new AlreadyExistsException("Feedback form for siteId : " + siteId + " already filled.");
		}

		if (form.getCleaning1() == 0) {
			form.setCleaningOutOf1(0);
		} else {
			form.setCleaningOutOf1(5);
		}

		if (form.getCleaning2() == 0) {
			form.setCleaningOutOf2(0);
		} else {
			form.setCleaningOutOf2(5);
		}

		if (form.getCleaning3() == 0) {
			form.setCleaningOutOf3(0);
		} else {
			form.setCleaningOutOf3(5);
		}

		if (form.getCleaning4() == 0) {
			form.setCleaningOutOf4(0);
		} else {
			form.setCleaningOutOf4(5);
		}

		if (form.getCleaning5() == 0) {
			form.setCleaningOutOf5(0);
		} else {
			form.setCleaningOutOf5(5);
		}

		// ******************************
		if (form.getControls1() == 0) {
			form.setControlsOutOf1(0);
		} else {
			form.setControlsOutOf1(5);
		}

		if (form.getControls2() == 0) {
			form.setControlsOutOf2(0);
		} else {
			form.setControlsOutOf2(5);
		}

		if (form.getControls3() == 0) {
			form.setControlsOutOf3(0);
		} else {
			form.setControlsOutOf3(5);
		}

		if (form.getControls4() == 0) {
			form.setControlsOutOf4(0);
		} else {
			form.setControlsOutOf4(5);
		}

		if (form.getControls5() == 0) {
			form.setControlsOutOf5(0);
		} else {
			form.setControlsOutOf5(5);
		}

		// *******************************
		if (form.getHo1() == 0) {
			form.setHoOutOf1(0);
		} else {
			form.setHoOutOf1(5);
		}

		if (form.getHo2() == 0) {
			form.setHoOutOf2(0);
		} else {
			form.setHoOutOf2(5);
		}

		if (form.getHo3() == 0) {
			form.setHoOutOf3(0);
		} else {
			form.setHoOutOf3(5);
		}

		if (form.getHo4() == 0) {
			form.setHoOutOf4(0);
		} else {
			form.setHoOutOf4(5);
		}

		if (form.getHo5() == 0) {
			form.setHoOutOf5(0);
		} else {
			form.setHoOutOf5(5);
		}

		// *****************
		if (form.getPersonal1() == 0) {
			form.setPersonalOutOf1(0);
		} else {
			form.setPersonalOutOf1(5);
			;
		}

		if (form.getPersonal2() == 0) {
			form.setPersonalOutOf2(0);
		} else {
			form.setPersonalOutOf2(5);
			;
		}

		if (form.getPersonal3() == 0) {
			form.setPersonalOutOf3(0);
		} else {
			form.setPersonalOutOf3(5);
			;
		}

		if (form.getPersonal4() == 0) {
			form.setPersonalOutOf4(0);
		} else {
			form.setPersonalOutOf4(5);
			;
		}

		if (form.getPersonal5() == 0) {
			form.setPersonalOutOf5(0);
		} else {
			form.setPersonalOutOf5(5);
			;
		}

		// ******************************
		if (form.getPurchase1() == 0) {
			form.setPurchaseOutOf1(0);
		} else {
			form.setPurchaseOutOf1(5);
		}

		if (form.getPurchase2() == 0) {
			form.setPurchaseOutOf2(0);
		} else {
			form.setPurchaseOutOf2(5);
		}

		if (form.getPurchase3() == 0) {
			form.setPurchaseOutOf3(0);
		} else {
			form.setPurchaseOutOf3(5);
		}

		if (form.getPurchase4() == 0) {
			form.setPurchaseOutOf4(0);
		} else {
			form.setPurchaseOutOf4(5);
		}

		if (form.getPurchase5() == 0) {
			form.setPurchaseOutOf5(0);
		} else {
			form.setPurchaseOutOf5(5);
		}

		// ********************************
		if (form.getSupervision1() == 0) {
			form.setSupervisionOutOf1(0);
		} else {
			form.setSupervisionOutOf1(5);
		}

		if (form.getSupervision2() == 0) {
			form.setSupervisionOutOf2(0);
		} else {
			form.setSupervisionOutOf2(5);
		}

		if (form.getSupervision3() == 0) {
			form.setSupervisionOutOf3(0);
		} else {
			form.setSupervisionOutOf3(5);
		}

		if (form.getSupervision4() == 0) {
			form.setSupervisionOutOf4(0);
		} else {
			form.setSupervisionOutOf4(5);
		}

		if (form.getSupervision5() == 0) {
			form.setSupervisionOutOf5(0);
		} else {
			form.setSupervisionOutOf5(5);
		}

		form.setSiteId(siteId);

		form.setYear(year);
		form.setMonth(month);
		FeedbackForm savedForm = fedbackRepo.save(form);

		Optional<SiteMaster> siteMaster = siteRepo.findByIdAndIsDeletedFalse(siteId);

		if (siteMaster.isPresent() && siteMaster.get().getSiteInchargeEmail() != null) {

			String inschargeEmail = siteMaster.get().getSiteInchargeEmail();

			byte[] file = excelExportOfInspectionForm(savedForm.getId());

			emailService.sendReportToSiteIncharge(inschargeEmail, file);
		}

		return savedForm;
	}

	public Optional<FeedbackForm> getFeedbackFormById(Long id) {
		Optional<FeedbackForm> optionalForm = fedbackRepo.findByIdAndIsDeletedFalse(id);
		if (!optionalForm.isPresent()) {
			throw new RecordNotFoundException("There is no feedback form with id: " + id);
		}
		return optionalForm;
	}

	public List<FeedbackForm> allFeedbackForm() {
		return fedbackRepo.findAllByIsDeletedFalse();
	}
	
	public List<FeedbackForm> siteWhoFilledFormInYearAndMonth(Integer year, String month){
		return fedbackRepo.findAllByYearAndMonthAndIsDeletedFalse(year, month);
	}
	
	public List<FeedbackForm> findAllFormOfSiteIdAndYear(Long siteId, Integer year){
		return fedbackRepo.findAllBySiteIdAndYearAndIsDeletedFalse(siteId, year);
	}

	public double allFeedbackFormOfSiteByYear(Long siteId, int year) {
		List<feedbackFormDto> formList = allFeedbackFormPercentage();

		List<feedbackFormDto> filteredList = formList.stream()
				.filter(form -> form.getSiteId().equals(siteId) && form.getYear() == year).collect(Collectors.toList());

		double totalPercentage = filteredList.stream().mapToDouble(feedbackFormDto::getPercentage).sum();

		double averagePercentage = totalPercentage / filteredList.size();
		
		return averagePercentage;
	}

	public List<feedbackFormDto> allFeedbackFormPercentage() {

		List<FeedbackForm> forms = fedbackRepo.findAllByIsDeletedFalse();

		List<feedbackFormDto> dtoList = new ArrayList<>();

		for (FeedbackForm form : forms) {

			feedbackFormDto dto = new feedbackFormDto();

			int subTotal1 = form.getPersonal1() + form.getPersonal2() + form.getPersonal3() + form.getPersonal4()
					+ form.getPersonal5();
			int outOfTotal1 = form.getPersonalOutOf1() + form.getPersonalOutOf2() + form.getPersonalOutOf3()
					+ form.getPersonalOutOf4() + form.getPersonalOutOf5();

			int subtotal2 = form.getCleaning1() + form.getCleaning2() + form.getCleaning3() + form.getCleaning4()
					+ form.getCleaning5();
			int outOf2 = form.getCleaningOutOf1() + form.getCleaningOutOf2() + form.getCleaningOutOf3()
					+ form.getCleaningOutOf4() + form.getCleaningOutOf5();

			int subtotal3 = form.getSupervision1() + form.getSupervision2() + form.getSupervision3()
					+ form.getSupervision4() + form.getSupervision5();
			int outof3 = form.getSupervisionOutOf1() + form.getSupervisionOutOf2() + form.getSupervisionOutOf3()
					+ form.getSupervisionOutOf4() + form.getSupervisionOutOf5();

			int subtotal4 = form.getPurchase1() + form.getPurchase2() + form.getPurchase3() + form.getPurchase4()
					+ form.getPurchase5();
			int outof4 = form.getPurchaseOutOf1() + form.getPurchaseOutOf2() + form.getPurchaseOutOf3()
					+ form.getPurchaseOutOf4() + form.getPurchaseOutOf5();

			int subtotal5 = form.getControls1() + form.getControls2() + form.getControls3() + form.getControls4()
					+ form.getControls5();
			int outof5 = form.getControlsOutOf1() + form.getControlsOutOf2() + form.getControlsOutOf3()
					+ form.getControlsOutOf4() + form.getControlsOutOf5();

			int subtotal6 = form.getHo1() + form.getHo2() + form.getHo3() + form.getHo4() + form.getHo5();
			int outof6 = form.getHoOutOf1() + form.getHoOutOf2() + form.getHoOutOf3() + form.getHoOutOf4()
					+ form.getHoOutOf5();

			// Calculate total earned and total possible points
			int totalEarn = subTotal1 + subtotal2 + subtotal3 + subtotal4 + subtotal5 + subtotal6;
			int totalOutOf = outOfTotal1 + outOf2 + outof3 + outof4 + outof5 + outof6;

			// Calculate percentage
			double percentage = 0.0;
			if (totalOutOf != 0) {
				percentage = ((double) totalEarn / totalOutOf) * 100;
			}

			// Retrieve additional site information from ClientSiteMaster
			Optional<SiteMaster> optForm = siteRepo.findByIdAndIsDeletedFalse(form.getSiteId());
			if (optForm.isPresent()) {
				SiteMaster siteMaster = optForm.get();
				dto.setFormId(form.getId());			
				dto.setSiteId(siteMaster.getId());
				dto.setClientName(siteMaster.getClientMaster().getName());
				dto.setInchargeName(siteMaster.getSiteInchargeName());
				dto.setPercentage(percentage); // Set the calculated percentage
				dto.setSiteName(siteMaster.getLocation_name());
				dto.setMonth(form.getMonth());
				dto.setYear(form.getYear());
			}

			dtoList.add(dto);
		}
		return dtoList;
	}

	public String deleteFeedBack(Long id) {
		Optional<FeedbackForm> optionalForm = fedbackRepo.findByIdAndIsDeletedFalse(id);
		if (!optionalForm.isPresent()) {
			throw new RecordNotFoundException("There is no feedback form with id: " + id);
		}

		FeedbackForm form = optionalForm.get();
		form.setDeleted(true);
		fedbackRepo.save(form);
		return "Feedback form is deleted succesfully";
	}

	private void applyBorders(XSSFRow row, int startColumn, int endColumn, XSSFCellStyle borderStyle) {
		for (int col = startColumn; col <= endColumn; col++) {
			XSSFCell cell = row.createCell(col);
			cell.setCellStyle(borderStyle);
		}
	}

	// EXCEL CODE ---------------------------->
	public byte[] excelExportOfInspectionForm(Long id) throws IOException {
		System.out.println("report making");

		// Fetch the InspectionForm from the repository
		Optional<FeedbackForm> optionalFeedbackForm = fedbackRepo.findByIdAndIsDeletedFalse(id);

		if (optionalFeedbackForm.isPresent()) {
			FeedbackForm feedbackForm = optionalFeedbackForm.get();

			Optional<SiteMaster> optionalSite = siteRepo.findByIdAndIsDeletedFalse(feedbackForm.getSiteId());

			if (!optionalSite.isPresent()) {
				throw new RecordNotFoundException("Site with id : " + feedbackForm.getSiteId() + " not found.");
			}

			SiteMaster siteMaster = optionalSite.get();

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Feedback Form");
			sheet.setDisplayGridlines(false);

			String[] headers = { "SECTION", "PARTICULAR", "POINTS EARNS", "OUT OF" };
			// XSSFCellStyle headerCellStyle = createHeaderCellStyle(workbook,
			// IndexedColors.GREY_25_PERCENT);

			// Create the border cell style
			XSSFCellStyle companyborderStyle = createCompanyBorderCellStyle(workbook);
			XSSFCellStyle borderStyle = createBorderCellStyle(workbook);

			// Create the company name row
			XSSFRow companyNameRow = sheet.createRow(0);
			createAndStyleCells(companyNameRow, 0, headers.length - 1, "FEEDBACK FORM", companyborderStyle);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));
			companyNameRow.setHeightInPoints(30);

//			// Create a cell style
			XSSFCellStyle style = ExcelUtils.createCellStyle(workbook, (short) 12, // Font size
					HorizontalAlignment.LEFT, // Horizontal alignment for BRAND
					VerticalAlignment.CENTER, // Vertical alignment
					BorderStyle.THIN // Border style
			);

			// Create the store name row
			XSSFRow storeNameRow = sheet.createRow(1);
			XSSFCell brandCell = storeNameRow.createCell(0);
			brandCell.setCellValue("Client: ");
			brandCell.setCellStyle(style);

			XSSFCell clientValueCell = storeNameRow.createCell(1);
			clientValueCell.setCellValue(siteMaster.getClientMaster().getName()); // Replace with actual client name value
			clientValueCell.setCellStyle(style);

			// Apply borders from column 2 to the last column of this row
			applyBorders(storeNameRow, 2, headers.length - 1, borderStyle);
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, headers.length - 1));

			// Create the site name row
			XSSFRow siteNameRow = sheet.createRow(2);
			XSSFCell siteLabelCell = siteNameRow.createCell(0);
			siteLabelCell.setCellValue("Site: ");
			siteLabelCell.setCellStyle(style);

			XSSFCell siteValueCell = siteNameRow.createCell(1);
			siteValueCell.setCellValue(siteMaster.getLocation_name());
			siteValueCell.setCellStyle(style);

			// Apply borders from column 2 to the last column of this row
			applyBorders(siteNameRow, 2, headers.length - 1, borderStyle);
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, headers.length - 1));

			// Create the site in-charge name row
			XSSFRow inchargeNameRow = sheet.createRow(3);
			XSSFCell inchargeLabelCell = inchargeNameRow.createCell(0);
			inchargeLabelCell.setCellValue("Site Incharge Name: ");
			inchargeLabelCell.setCellStyle(style);

			XSSFCell inchargeValueCell = inchargeNameRow.createCell(1);
			inchargeValueCell.setCellValue(siteMaster.getSiteInchargeName()); 
			inchargeValueCell.setCellStyle(style);

			// Apply borders from column 2 to the last column of this row
			applyBorders(inchargeNameRow, 2, headers.length - 1, borderStyle);
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, headers.length - 1));

			// Create the filled-on date-time row
			XSSFRow dateTimeRow = sheet.createRow(4);
			XSSFCell dateTimeLabelCell = dateTimeRow.createCell(0);
			dateTimeLabelCell.setCellValue("Filled On Date-time: ");
			dateTimeLabelCell.setCellStyle(style);

			XSSFCell dateTimeValueCell = dateTimeRow.createCell(1);
			dateTimeValueCell.setCellValue(ExcelUtils.formatTimeStamp(feedbackForm.getCreatedAt())); 
			dateTimeValueCell.setCellStyle(style);

			// Apply borders from column 2 to the last column of this row
			applyBorders(dateTimeRow, 2, headers.length - 1, borderStyle);
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, headers.length - 1));

			XSSFCellStyle headerCellStyle = createHeaderCellStyle(workbook);
			XSSFCellStyle borderCellStyle = createBorderCellStyle(workbook);

			// Create the company name row
			XSSFRow descriptionRow = sheet.createRow(5);
			createAndStyleCells(descriptionRow, 0, headers.length - 1, "Description", companyborderStyle);
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, headers.length - 1));

			// Header Row
			XSSFRow headerRow = sheet.createRow(6);
			for (int i = 0; i < headers.length; i++) {
				XSSFCell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(headerCellStyle);
				cell.setCellStyle(borderCellStyle);
			}

			// Fill the inspection form data
			int subTotal1 = feedbackForm.getPersonal1() + feedbackForm.getPersonal2() + feedbackForm.getPersonal3()
					+ feedbackForm.getPersonal4() + feedbackForm.getPersonal5();

			int outOfTotal1 = feedbackForm.getPersonalOutOf1() + feedbackForm.getPersonalOutOf2()
					+ feedbackForm.getPersonalOutOf3() + feedbackForm.getPersonalOutOf4()
					+ feedbackForm.getPersonalOutOf5();

			int subtotal2 = feedbackForm.getCleaning1() + feedbackForm.getCleaning2() + feedbackForm.getCleaning3()
					+ feedbackForm.getCleaning4() + feedbackForm.getCleaning5();

			int outOf2 = feedbackForm.getCleaningOutOf1() + feedbackForm.getCleaningOutOf2()
					+ feedbackForm.getCleaningOutOf3() + feedbackForm.getCleaningOutOf4()
					+ feedbackForm.getCleaningOutOf5();

			int subtotal3 = feedbackForm.getSupervision1() + feedbackForm.getSupervision2()
					+ feedbackForm.getSupervision3() + feedbackForm.getSupervision4() + feedbackForm.getSupervision5();

			int outof3 = feedbackForm.getSupervisionOutOf1() + feedbackForm.getSupervisionOutOf2()
					+ feedbackForm.getSupervisionOutOf3() + feedbackForm.getSupervisionOutOf4()
					+ feedbackForm.getSupervisionOutOf5();

			int subtotal4 = feedbackForm.getPurchase1() + feedbackForm.getPurchase2() + feedbackForm.getPurchase3()
					+ feedbackForm.getPurchase4() + feedbackForm.getPurchase5();

			int outof4 = feedbackForm.getPurchaseOutOf1() + feedbackForm.getPurchaseOutOf2()
					+ feedbackForm.getPurchaseOutOf3() + feedbackForm.getPurchaseOutOf4()
					+ feedbackForm.getPurchaseOutOf5();

			int subtotal5 = feedbackForm.getControls1() + feedbackForm.getControls2() + feedbackForm.getControls3()
					+ feedbackForm.getControls4() + feedbackForm.getControls5();

			int outof5 = feedbackForm.getControlsOutOf1() + feedbackForm.getControlsOutOf2()
					+ feedbackForm.getControlsOutOf3() + feedbackForm.getControlsOutOf4()
					+ feedbackForm.getControlsOutOf5();

			int subtotal6 = feedbackForm.getHo1() + feedbackForm.getHo2() + feedbackForm.getHo3()
					+ feedbackForm.getHo4() + feedbackForm.getHo5();

			int outof6 = feedbackForm.getHoOutOf1() + feedbackForm.getHoOutOf2() + feedbackForm.getHoOutOf3()
					+ feedbackForm.getHoOutOf4() + feedbackForm.getHoOutOf5();

			int totalEarn = subTotal1 + subtotal2 + subtotal3 + subtotal4 + subtotal5 + subtotal6;
			int totalOutOf = outOfTotal1 + outOf2 + outof3 + outof4 + outof5 + outof6;

			double percentage = ((double) totalEarn / totalOutOf) * 100;

			int currentRow = 7;
			String[][] inspectionData = { { "A", "Personal", "", "" },
					{ "1", "Staff courtesy and presentation", String.valueOf(feedbackForm.getPersonal1()),
							String.valueOf(feedbackForm.getPersonalOutOf1()) },
					{ "2", "Staff Personnel Hygiene", String.valueOf(feedbackForm.getPersonal2()),
							String.valueOf(feedbackForm.getPersonalOutOf2()) },
					{ "3", "Uniforms, Shoes, Caps", String.valueOf(feedbackForm.getPersonal3()),
							String.valueOf(feedbackForm.getPersonalOutOf3()) },
					{ "4", "Staff Turnover, Absenteeism, Late Coming", String.valueOf(feedbackForm.getPersonal4()),
							String.valueOf(feedbackForm.getPersonalOutOf4()) },
					{ "5", "Staff knowledge and responsiveness", String.valueOf(feedbackForm.getPersonal5()),
							String.valueOf(feedbackForm.getPersonalOutOf5()) },

					{ "", "Sub Total", String.valueOf(subTotal1), String.valueOf(outOfTotal1) }, { "", "", "", "" },
					{ "B", "Cleaning & Hygiene", "", "" },
					{ "1", "Cleaning of premises", String.valueOf(feedbackForm.getCleaning1()),
							String.valueOf(feedbackForm.getCleaningOutOf1()) },
					{ "2", "Cleaning of Toilets", String.valueOf(feedbackForm.getCleaning2()),
							String.valueOf(feedbackForm.getCleaningOutOf2()) },
					{ "3", "Juditious use of House keeping material ", String.valueOf(feedbackForm.getCleaning3()),
							String.valueOf(feedbackForm.getCleaningOutOf3()) },
					{ "4", "Documentation of tasks (todo & completed)", String.valueOf(feedbackForm.getCleaning4()),
							String.valueOf(feedbackForm.getCleaningOutOf4()) },
					{ "5", "Suitable Garbage Disposable Measures", String.valueOf(feedbackForm.getCleaning5()),
							String.valueOf(feedbackForm.getCleaningOutOf5()) },
					{ "", "Sub Total", String.valueOf(subtotal2), String.valueOf(outOf2) },

					{ "", "", "", "" }, { "C.", "Site Supervision", "", "" },
					{ "1.", "Supervision Standards", String.valueOf(feedbackForm.getSupervision1()),
							String.valueOf(feedbackForm.getSupervisionOutOf1()) },
					{ "2.", "Job Knowledge", String.valueOf(feedbackForm.getSupervision2()),
							String.valueOf(feedbackForm.getSupervisionOutOf2()) },
					{ "3.", "Speed, Efficiency, Work quality of site incharge",
							String.valueOf(feedbackForm.getSupervision3()),
							String.valueOf(feedbackForm.getSupervisionOutOf3()) },
					{ "4.", "Responsiveness To Customer Needs", String.valueOf(feedbackForm.getSupervision4()),
							String.valueOf(feedbackForm.getSupervisionOutOf4()) },
					{ "5.", "End User Feedback", String.valueOf(feedbackForm.getSupervision5()),
							String.valueOf(feedbackForm.getSupervisionOutOf5()) },

					{ "", "Sub Total", String.valueOf(subtotal3), String.valueOf(outof3) }, { "", "", "", "" },
					{ "D.", "Purshase & Stores", "", "" },
					{ "1.", "Quality of Raw Material", String.valueOf(feedbackForm.getPurchase1()),
							String.valueOf(feedbackForm.getPurchaseOutOf1()) },
					{ "2.", "Level of Stocking", String.valueOf(feedbackForm.getPurchase2()),
							String.valueOf(feedbackForm.getPurchaseOutOf2()) },
					{ "3.", "Bin Card, Daily Indent System Being Followed", String.valueOf(feedbackForm.getPurchase3()),
							String.valueOf(feedbackForm.getPurchaseOutOf3()) },
					{ "4.", "Purchase Specifications Being Followed", String.valueOf(feedbackForm.getPurchase4()),
							String.valueOf(feedbackForm.getPurchaseOutOf4()) },
					{ "5.", "Quality of Material Supplied", String.valueOf(feedbackForm.getPurchase5()),
							String.valueOf(feedbackForm.getPurchaseOutOf5()) },

					{ "", "Sub Total", String.valueOf(subtotal4), String.valueOf(outof4) }, { "", "", "", "" },
					{ "E.", "Controls", "", "" },
					{ "1.", "Appropriate documentation and Billing", String.valueOf(feedbackForm.getControls1()),
							String.valueOf(feedbackForm.getControlsOutOf1()) },
					{ "2.", "Attendance and other relevant documents submitted on time ",
							String.valueOf(feedbackForm.getControls2()),
							String.valueOf(feedbackForm.getControlsOutOf2()) },
					{ "3.", "Regular trainings held (on site / off site)", String.valueOf(feedbackForm.getControls3()),
							String.valueOf(feedbackForm.getControlsOutOf3()) },
					{ "4", "Recommendation on preventive maintenance ", String.valueOf(feedbackForm.getControls4()),
							String.valueOf(feedbackForm.getControlsOutOf4()) },
					{ "5", "Timely receipt of materials", String.valueOf(feedbackForm.getControls5()),
							String.valueOf(feedbackForm.getControlsOutOf5()) },
					{ "", "Sub Total", String.valueOf(subtotal5), String.valueOf(outof5) }, { "", "", "", "" },
					{ "F.", "HO and Team Managers", "", "" },
					{ "1.", "Management team in regular contact", String.valueOf(feedbackForm.getHo1()),
							String.valueOf(feedbackForm.getHoOutOf1()) },
					{ "2.", "Supervision and Knowldege ", String.valueOf(feedbackForm.getHo2()),
							String.valueOf(feedbackForm.getHoOutOf2()) },
					{ "3.", "Responsiveness to Client's Needs", String.valueOf(feedbackForm.getHo3()),
							String.valueOf(feedbackForm.getHoOutOf3()) },
					{ "4.", "Recommendation on new technology", String.valueOf(feedbackForm.getHo4()),
							String.valueOf(feedbackForm.getHoOutOf4()) },
					{ "5.", "Provide timely documentation and information ", String.valueOf(feedbackForm.getHo5()),
							String.valueOf(feedbackForm.getHoOutOf5()) },
					{ "", "Sub Total", String.valueOf(subtotal6), String.valueOf(outof6) }, { "", "", "", "" },
					{ "", "Total", String.valueOf(totalEarn), String.valueOf(totalOutOf) },
					{ "", "Quick %", String.format("%.2f", percentage), "" } };

			// Create data rows
			for (String[] rowData : inspectionData) {
				XSSFRow row = sheet.createRow(currentRow++);
				for (int i = 0; i < rowData.length; i++) {
					XSSFCell cell = row.createCell(i);
					cell.setCellValue(rowData[i]);
					cell.setCellStyle(borderCellStyle);
				}
			}

			// Adjust column widths
			for (int i = 0; i < headers.length; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the workbook to a byte array output stream
			try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
				workbook.write(outputStream);
				return outputStream.toByteArray();
			}
		}
		throw new RecordNotFoundException("Inspection Form not found with id: " + id);

	}

	// Method to create a border cell style
	private XSSFCellStyle createBorderCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle style = workbook.createCellStyle();
		// Create a font and set it to bold
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		style.setFont(font);

		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		return style;
	}

	// company name style
	// Create a font object and set its size
	private XSSFCellStyle createCompanyBorderCellStyle(XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 14); // Font size 14

		// Create a cell style object
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER); // Center-align text
		style.setVerticalAlignment(VerticalAlignment.CENTER); // Center-align text vertically
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		return style;
	}

	private XSSFCellStyle createHeaderCellStyle(XSSFWorkbook workbook) {

		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		// style.setFillForegroundColor(IndexedColors.YELLOW.getIndex()); // Set
		// background color to yellow
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Fill the cell with the foreground color
		style.setAlignment(HorizontalAlignment.CENTER); // Center align the text
		style.setVerticalAlignment(VerticalAlignment.CENTER); // Center align vertically
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);

		return style;
	}

	private static void createAndStyleCells(XSSFRow row, int startCol, int endCol, String value, XSSFCellStyle style) {
		for (int i = startCol; i <= endCol; i++) {
			XSSFCell cell = row.createCell(i);
			cell.setCellValue(value);
			cell.setCellStyle(style);
		}
	}

}
