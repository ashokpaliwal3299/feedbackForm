package com.feedback.form.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.form.Exception.RecordNotFoundException;
import com.feedback.form.model.FeedbackForm;
import com.feedback.form.repository.FeedbackFormRepository;

@Service
public class FeedbackFormService {

	@Autowired
	private FeedbackFormRepository fedbackRepo;

	public FeedbackForm addFeedbackForm(FeedbackForm form, Long siteId) {
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
		return fedbackRepo.save(form);
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

	// EXCEL CODE ---------------------------->
//	public byte[] excelExportOfInspectionForm(Long id) throws IOException {
//		// Fetch the InspectionForm from the repository
//		Optional<FeedbackForm> optionalFeedbackForm = fedbackRepo.findByIdAndIsDeletedFalse(id);
//
//		if (optionalFeedbackForm.isPresent()) {
//			FeedbackForm feedbackForm = optionalFeedbackForm.get();
//
//			XSSFWorkbook workbook = new XSSFWorkbook();
//			XSSFSheet sheet = workbook.createSheet("Feedback Form");
//			sheet.setDisplayGridlines(false);
//
//			String[] headers = { "SECTION", "ITEM CHECKED", "OK", "NOT OK", "MINIMUM ACCEPTABLE STANDARD", "REMARKS",
//					"PHOTO ID" };
//			// XSSFCellStyle headerCellStyle = createHeaderCellStyle(workbook,
//			// IndexedColors.GREY_25_PERCENT);
//
//			// Create the border cell style
//			XSSFCellStyle companyborderStyle = createCompanyBorderCellStyle(workbook);
//			XSSFCellStyle borderStyle = createBorderCellStyle(workbook);
//
//			// Create the company name row
//			XSSFRow companyNameRow = sheet.createRow(0);
//			createAndStyleCells(companyNameRow, 0, headers.length - 1, feedbackForm.getCleaning1() + " - FEEDBACK FORM",
//					companyborderStyle);
//			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));
//			companyNameRow.setHeightInPoints(30);
//
//			// Create a cell style
//			XSSFCellStyle style = ExcelUtils.createCellStyle(workbook, (short) 12, // Font size
//					HorizontalAlignment.LEFT, // Horizontal alignment for BRAND
//					VerticalAlignment.CENTER, // Vertical alignment
//					BorderStyle.THIN // Border style
//			);
//
//			XSSFCellStyle dateStyle = ExcelUtils.createCellStyle(workbook, (short) 12, // Font size
//					HorizontalAlignment.RIGHT, // Horizontal alignment for DATE
//					VerticalAlignment.CENTER, // Vertical alignment
//					BorderStyle.THIN // Border style
//			);
//
//			// Create the store name row
//			XSSFRow storeNameRow = sheet.createRow(1);
//
//			// Create the cells
//			XSSFCell brandCell = storeNameRow.createCell(0);
//			brandCell.setCellValue("Brand: ");
//			brandCell.setCellStyle(style);
//
//			XSSFCell dateCell = storeNameRow.createCell(3); // Adjust column index as needed
//			dateCell.setCellValue("Date: "); // Example
//			dateCell.setCellStyle(dateStyle);
//
//			// Merge cells to ensure proper alignment
//			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2)); // Merges cells for BRAND
//			sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, headers.length - 1)); // Merges cells for DATE
//
//			// Apply the border style to all cells in the merged range
//			for (int col = 0; col <= 2; col++) {
//				XSSFCell cell = storeNameRow.getCell(col);
//				if (cell == null) {
//					cell = storeNameRow.createCell(col);
//				}
//				cell.setCellStyle(borderStyle);
//			}
//
//			for (int col = 3; col <= headers.length - 1; col++) {
//				XSSFCell cell = storeNameRow.getCell(col);
//				if (cell == null) {
//					cell = storeNameRow.createCell(col);
//				}
//				cell.setCellStyle(borderStyle);
//			}
//
//			// Create the location row
//			XSSFRow locationRow = sheet.createRow(2);
//			// Create the cells
//			XSSFCell locationCell = locationRow.createCell(0);
//			locationCell.setCellValue("Store Location: ");
//			locationCell.setCellStyle(style);
//
//			XSSFCell technicianNameCell = locationRow.createCell(3);
//			technicianNameCell.setCellValue("Technician Name: ");
//			technicianNameCell.setCellStyle(dateStyle);
//
//			// Merge cells to ensure proper alignment
//			sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2)); // Merges cells for BRAND
//			sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, headers.length - 1)); // Merges cells for DATE
//
//			// Apply the border style to all cells in the merged range
//			for (int col = 0; col <= 2; col++) {
//				XSSFCell cell = locationRow.getCell(col);
//				if (cell == null) {
//					cell = locationRow.createCell(col);
//				}
//				cell.setCellStyle(borderStyle);
//			}
//
//			for (int col = 3; col <= headers.length - 1; col++) {
//				XSSFCell cell = locationRow.getCell(col);
//				if (cell == null) {
//					cell = locationRow.createCell(col);
//				}
//				cell.setCellStyle(borderStyle);
//			}
//
//			XSSFCellStyle headerCellStyle = createHeaderCellStyle(workbook);
//			XSSFCellStyle borderCellStyle = createBorderCellStyle(workbook);
//
//			// Create the company name row
//			XSSFRow descriptionRow = sheet.createRow(3);
//			createAndStyleCells(descriptionRow, 0, headers.length - 1, "Description", companyborderStyle);
//			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, headers.length - 1));
//
//			// Header Row
//			XSSFRow headerRow = sheet.createRow(4);
//			for (int i = 0; i < headers.length; i++) {
//				XSSFCell cell = headerRow.createCell(i);
//				cell.setCellValue(headers[i]);
//				cell.setCellStyle(headerCellStyle);
//				cell.setCellStyle(borderCellStyle);
//			}
//
//			// Fill the inspection form data
//			int currentRow = 5;
//			String[][] inspectionData = { { "A", "Personal", "Points Earned", "Out of" },
//					{ "1", "Staff courtesy and presentation", String.valueOf(feedbackForm.getPersonal1()),
//							String.valueOf(feedbackForm.getPersonalOutOf()) },
//					{ "2", "Staff Personnel Hygiene", String.valueOf(feedbackForm.getPersonal2()),
//							String.valueOf(feedbackForm.getPersonalOutOf()) },
//					{ "3", "Uniforms, Shoes, Caps", String.valueOf(feedbackForm.getPersonal3()),
//							String.valueOf(feedbackForm.getPersonalOutOf()) },
//					{ "4", "Staff Turnover, Absenteeism, Late Coming", String.valueOf(feedbackForm.getPersonal4()),
//							String.valueOf(feedbackForm.getPersonalOutOf()) },
//					{ "5", "Staff knowledge and responsiveness", String.valueOf(feedbackForm.getPersonal5()),
//							String.valueOf(feedbackForm.getPersonalOutOf()) },
//
//					{ "", "", "", "" }, { "B", "Cleaning & Hygiene", "", "" },
//					{ "1", "Cleaning of premises", String.valueOf(feedbackForm.getCleaning1()),
//							String.valueOf(feedbackForm.getCleaningOutOf()) },
//					{ "2", "Cleaning of Toilets", String.valueOf(feedbackForm.getCleaning2()),
//							String.valueOf(feedbackForm.getCleaningOutOf()) },
//					{ "3", "Juditious use of House keeping material ", String.valueOf(feedbackForm.getCleaning3()),
//							String.valueOf(feedbackForm.getCleaningOutOf()) },
//					{ "4", "Documentation of tasks (todo & completed)", String.valueOf(feedbackForm.getCleaning4()),
//							String.valueOf(feedbackForm.getCleaningOutOf()) },
//					{ "5", "Suitable Garbage Disposable Measures", String.valueOf(feedbackForm.getCleaning5()),
//							String.valueOf(feedbackForm.getCleaningOutOf()) },
//
//					{ "", "", "", "" }, { "C.", "Site Supervision", "", "" },
//					{ "1.", "Supervision Standards", String.valueOf(feedbackForm.getSupervision1()),
//							String.valueOf(feedbackForm.getSupervisionOutOf()) },
//					{ "2.", "Job Knowledge", String.valueOf(feedbackForm.getSupervision2()),
//							String.valueOf(feedbackForm.getSupervisionOutOf()) },
//					{ "3.", "Speed, Efficiency, Work quality of site incharge",
//							String.valueOf(feedbackForm.getSupervision3()),
//							String.valueOf(feedbackForm.getSupervisionOutOf()) },
//					{ "4.", "Responsiveness To Customer Needs", String.valueOf(feedbackForm.getSupervision4()),
//							String.valueOf(feedbackForm.getSupervisionOutOf()) },
//					{ "5.", "End User Feedback", String.valueOf(feedbackForm.getSupervision5()),
//							String.valueOf(feedbackForm.getSupervisionOutOf()) },
//
//					{ "", "", "", "" }, { "D.", "Purshase & Stores", "", "" },
//					{ "1.", "Quality of Raw Material",
//						    String.valueOf(feedbackForm.getPurchase1()),
//							String.valueOf(feedbackForm.getPurchaseOutOf()) },
//					{ "2.", "Level of Stocking", 
//							String.valueOf(feedbackForm.getPurchase2()),
//							String.valueOf(feedbackForm.getPurchaseOutOf()) },
//					{ "3.", "Bin Card, Daily Indent System Being Followed", String.valueOf(feedbackForm.getPurchase3()),
//							String.valueOf(feedbackForm.getPurchaseOutOf()) },
//					{ "4.", "Purchase Specifications Being Followed", String.valueOf(feedbackForm.getPurchase4()),
//								String.valueOf(feedbackForm.getPurchaseOutOf())},
//					{ "5.", "Quality of Material Supplied", String.valueOf(feedbackForm.getPurchase5()),
//									String.valueOf(feedbackForm.getPurchaseOutOf()) },
//					
//					{ "", "", "", "" }, 
//					{ "E.", "Controls", "", ""},
//					{ "1.", "Appropriate documentation and Billing",
//						String.valueOf(feedbackForm.getControls1()),
//						String.valueOf(feedbackForm.getControlsOutOf()) },
//					{ "2.", "Attendance and other relevant documents submitted on time ", String.valueOf(feedbackForm.getControls2()),
//							String.valueOf(feedbackForm.getControlsOutOf()) },
//					{ "3.", "Regular trainings held (on site / off site)", String.valueOf(feedbackForm.getControls3()),
//								String.valueOf(feedbackForm.getControlsOutOf())  },
//					{ "4", "Recommendation on preventive maintenance ",String.valueOf(feedbackForm.getControls4()),
//									String.valueOf(feedbackForm.getControlsOutOf())},
//					{ "5", "Timely receipt of materials",String.valueOf(feedbackForm.getControls5()),
//										String.valueOf(feedbackForm.getControlsOutOf())},
//					{ "", "", "", "" }, 
//					{ "F.", "HO and Team Managers", "", "" },
//					{ "1.", "Management team in regular contact",
//						String.valueOf(feedbackForm.getHo1()),
//						String.valueOf(feedbackForm.getHoOutOf()) },
//					{ "2.", "Supervision and Knowldege ",
//							String.valueOf(feedbackForm.getHo2()),
//							String.valueOf(feedbackForm.getHoOutOf()) },
//					{ "3.", "Responsiveness to Client's Needs",
//								String.valueOf(feedbackForm.getHo3()),
//								String.valueOf(feedbackForm.getHoOutOf()) },
//					{ "4.", "Recommendation on new technology",
//									String.valueOf(feedbackForm.getHo4()),
//									String.valueOf(feedbackForm.getHoOutOf()) },
//					{ "5.", "Provide timely documentation and information ",
//										String.valueOf(feedbackForm.getHo5()),
//										String.valueOf(feedbackForm.getHoOutOf()) }
//					};
//
//			// Create data rows
//			for (String[] rowData : inspectionData) {
//				XSSFRow row = sheet.createRow(currentRow++);
//				for (int i = 0; i < rowData.length; i++) {
//					XSSFCell cell = row.createCell(i);
//					cell.setCellValue(rowData[i]);
//					cell.setCellStyle(borderCellStyle);
//				}
//			}
//
//			// Adjust column widths
//			for (int i = 0; i < headers.length; i++) {
//				sheet.autoSizeColumn(i);
//			}
//
//			// Write the workbook to a byte array output stream
//			try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//				workbook.write(outputStream);
//				return outputStream.toByteArray();
//			}
//		}
//		throw new RecordNotFoundException("Inspection Form not found with id: " + id);
//
//	}
//
//	// Method to create a border cell style
//	private XSSFCellStyle createBorderCellStyle(XSSFWorkbook workbook) {
//		XSSFCellStyle style = workbook.createCellStyle();
//		// Create a font and set it to bold
//		XSSFFont font = workbook.createFont();
//		font.setBold(true);
//		style.setFont(font);
//
//		style.setBorderTop(BorderStyle.THIN);
//		style.setBorderBottom(BorderStyle.THIN);
//		style.setBorderLeft(BorderStyle.THIN);
//		style.setBorderRight(BorderStyle.THIN);
//
//		return style;
//	}
//
//	// company name style
//	// Create a font object and set its size
//	private XSSFCellStyle createCompanyBorderCellStyle(XSSFWorkbook workbook) {
//		XSSFFont font = workbook.createFont();
//		font.setFontHeightInPoints((short) 14); // Font size 14
//
//		// Create a cell style object
//		XSSFCellStyle style = workbook.createCellStyle();
//		style.setFont(font);
//		style.setAlignment(HorizontalAlignment.CENTER); // Center-align text
//		style.setVerticalAlignment(VerticalAlignment.CENTER); // Center-align text vertically
//		style.setBorderTop(BorderStyle.THIN);
//		style.setBorderBottom(BorderStyle.THIN);
//		style.setBorderLeft(BorderStyle.THIN);
//		style.setBorderRight(BorderStyle.THIN);
//		return style;
//	}
//
//	private XSSFCellStyle createHeaderCellStyle(XSSFWorkbook workbook) {
//
//		XSSFCellStyle style = workbook.createCellStyle();
//		style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
//		// style.setFillForegroundColor(IndexedColors.YELLOW.getIndex()); // Set
//		// background color to yellow
//		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Fill the cell with the foreground color
//		style.setAlignment(HorizontalAlignment.CENTER); // Center align the text
//		style.setVerticalAlignment(VerticalAlignment.CENTER); // Center align vertically
//		style.setBorderBottom(BorderStyle.THIN);
//		style.setBorderLeft(BorderStyle.THIN);
//		style.setBorderRight(BorderStyle.THIN);
//		style.setBorderTop(BorderStyle.THIN);
//
//		return style;
//	}
//
//	private static void createAndStyleCells(XSSFRow row, int startCol, int endCol, String value, XSSFCellStyle style) {
//		for (int i = startCol; i <= endCol; i++) {
//			XSSFCell cell = row.createCell(i);
//			cell.setCellValue(value);
//			cell.setCellStyle(style);
//		}
//	}

}
