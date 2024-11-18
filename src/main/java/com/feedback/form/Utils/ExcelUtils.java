package com.feedback.form.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static final String COMPANY_NAME = "I SMART FACITECH PRIVATE LIMITED";
	private static final String report_heading = "REPORT : ";

	private static final String note0 = "NOTE :-";
	private static final String note1 = "1. Above quoted rates are as per current prevailing minimum wages. However, there will be revision in DA every Six Months. We will submit the revise rates along with minimum wages notification for your approval.";
	private static final String note2 = "2. If any extra hike in minimum wages basic rate or implementation of fair wages by the central or state government, We will submit the revise rates along with minimum wages notification for your approval.";
	private static final String note3 = "3. Gratuity will be charged at actual as and when applicable.";
	private static final String note4 = "4. GST will be charged extra as applicable.";

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
	private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
	private static final DateTimeFormatter timestampformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static String getCompanyName() {
		return COMPANY_NAME;
	}

	public static String getReportHeading() {
		return report_heading;
	}

	public static String getNote0() {
		return note0;
	}

	public static String getNote1() {
		return note1;
	}

	public static String getNote2() {
		return note2;
	}

	public static String getNote3() {
		return note3;
	}

	public static String getNote4() {
		return note4;
	}

	public static String formatDate(LocalDate date) {
		return date.format(formatter);
	}

	public static String formatDateTime(LocalDateTime createdAt) {
		return createdAt.format(formatter);
	}

	public static String formatTime(LocalTime createdAt) {
		return createdAt.format(timeFormatter);
	}

	public static String formatTimeStamp(LocalDateTime createdAt) {
		if (createdAt == null) {
			return null;
		}
		return createdAt.format(timestampformatter);
	}

	public static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, short fontSize, HorizontalAlignment hAlign,
			VerticalAlignment vAlign, BorderStyle borderStyle) {

		// Create a font object and set its size
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints(fontSize); // Set the font size

		// Create a cell style object
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(hAlign); // Set horizontal alignment
		style.setVerticalAlignment(vAlign); // Set vertical alignment

		// Set border styles
		style.setBorderTop(borderStyle);
		style.setBorderBottom(borderStyle);
		style.setBorderLeft(borderStyle);
		style.setBorderRight(borderStyle);

		return style;
	}

//	 // Method to apply cell style
//    public static void applyCellStyle(Cell cell, Workbook workbook, IndexedColors color) {
//        CellStyle style = workbook.createCellStyle();
//        style.setFillForegroundColor(color.getIndex());
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        cell.setCellStyle(style);
//    }
//
//    // Method to auto-size columns
//    public static void autoSizeColumns(Sheet sheet, int numberOfColumns) {
//        for (int i = 0; i < numberOfColumns; i++) {
//            sheet.autoSizeColumn(i);
//        }
//    }

}
