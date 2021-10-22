package com.mdrain.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import com.mdrain.logic.Date;

import org.apache.poi.hssf.record.CFRuleBase.ComparisonOperator;
import org.apache.poi.hssf.record.aggregates.WorksheetProtectionBlock;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PaperSize;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFConditionalFormattingRule;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFFontFormatting;
import org.apache.poi.xssf.usermodel.XSSFPatternFormatting;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheetConditionalFormatting;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.time.temporal.ChronoUnit;

import com.mdrain.objects.Operators;

public class ExcelTables {

	private static void formatDateCell(XSSFWorkbook workbook, Cell cell) {
		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper creationHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd.MM.yyyy"));
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		cell.setCellStyle(cellStyle);
	}

	private static CellStyle formatTable(XSSFWorkbook workbook, XSSFSheet sheet, Cell cell) {
		CellStyle cellStyle = workbook.createCellStyle();
		CellStyle cellStyleRotate = workbook.createCellStyle();
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyleRotate.setRotation((short) 90);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);

		return cellStyle;
	}

	public static void excelTableMonthlyPresentBlank(XSSFWorkbook workbook, XSSFSheet worksheet,
			ArrayList<String> excelTableFieldCollection, ArrayList<String> excelTableFieldCollectionNext,
			ArrayList<Operators> excelObjectCollection, ArrayList<String> excelObjectInfoCollection) {

		// Header ***********************************************

		XSSFRow headerRow = worksheet.createRow(0);
		Cell headerCell = null;
		XSSFFont font = workbook.createFont();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setRotation((short) 90);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		worksheet.createFreezePane(0, 2);
		font.setBold(true);
		cellStyle.setFont(font);
		worksheet.setMargin(Sheet.LeftMargin, 0.3);
		worksheet.setMargin(Sheet.RightMargin, 0.3);
		worksheet.getPrintSetup().setLandscape(true);
		worksheet.getPrintSetup().setPaperSize(PaperSize.A4_PAPER);

		XSSFSheetConditionalFormatting conditionalFormating = worksheet.getSheetConditionalFormatting();
		XSSFConditionalFormattingRule conditionalFormatingRule = conditionalFormating
				.createConditionalFormattingRule(ComparisonOperator.EQUAL, "п");
		XSSFFontFormatting conditionalFormatingPatern = conditionalFormatingRule.createFontFormatting();
		conditionalFormatingPatern.setFontColorIndex(IndexedColors.RED.getIndex());

		XSSFPatternFormatting fill_pattern = conditionalFormatingRule.createPatternFormatting();
		fill_pattern.setFillBackgroundColor(IndexedColors.YELLOW.index);

		CellRangeAddress[] myDataRange = { CellRangeAddress.valueOf("A1:AK2000") };
		conditionalFormating.addConditionalFormatting(myDataRange, conditionalFormatingRule);

		for (int i = 0; i < excelTableFieldCollection.size(); i++) {

			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(excelTableFieldCollection.get(i));
			headerCell.setCellStyle(formatTable(workbook, worksheet, headerCell));

		}

		headerRow = worksheet.createRow(1);

		for (int i = 0; i < excelTableFieldCollectionNext.size(); i++) {

			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(excelTableFieldCollectionNext.get(i));
			headerCell.setCellStyle(cellStyle);
		}

		// Data *****************************************************

		int rowCount = 2;
		int i = 0;
		int j = 0;

		XSSFRow row = worksheet.createRow(rowCount);
		CellStyle cellStyleBody = workbook.createCellStyle();
		cellStyleBody.setBorderBottom(BorderStyle.THIN);
		cellStyleBody.setBorderLeft(BorderStyle.THIN);
		cellStyleBody.setBorderRight(BorderStyle.THIN);
		cellStyleBody.setBorderTop(BorderStyle.THIN);
		cellStyleBody.setAlignment(HorizontalAlignment.LEFT);
		cellStyleBody.setFont(font);
		Cell cell = null;

		for (j = 0; j < excelObjectCollection.size(); j++) {

			row = worksheet.createRow(rowCount++);

			cell = row.createCell(0);
			cell.setCellValue(excelObjectCollection.get(j).getFullName());

			cell.setCellStyle(cellStyleBody);

			cell = row.createCell(excelObjectInfoCollection.size());
			cell.setCellValue(excelObjectCollection.get(j).getTeamLeader());
			cell.setCellStyle(cellStyleBody);

			for (i = 1; i < excelObjectInfoCollection.size(); i++) {

				cell = row.createCell(i);
				cell.setCellValue(excelObjectInfoCollection.get(i));
				if (excelObjectInfoCollection.get(i).equals("п")) {
					System.out.print("Почивка" + ", ");
					CellStyle style = workbook.createCellStyle();
					style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
					style.setFillPattern(FillPatternType.LEAST_DOTS);
					style.setAlignment(HorizontalAlignment.CENTER);
					style.setBorderBottom(BorderStyle.THIN);
					style.setBorderLeft(BorderStyle.THIN);
					style.setBorderRight(BorderStyle.THIN);
					style.setBorderTop(BorderStyle.THIN);
					cell.setCellStyle(style);

				} else {
					cell.setCellStyle(formatTable(workbook, worksheet, cell));
				}

				worksheet.setColumnWidth(i, 1000);
				worksheet.setColumnWidth(0, 10000);
				worksheet.setColumnWidth(excelObjectInfoCollection.size(), 7000);

			}
		}

	}

	public static void excelTableAbsence(XSSFWorkbook workbook, XSSFSheet worksheet,
			ArrayList<String> excelTableFieldCollection, ArrayList<Object> excelObjectInfoCollection,
			ArrayList<Operators> excelObjectCollection) {

		// Header ***********************************************
		XSSFRow headerRow = worksheet.createRow(0);
		Cell headerCell = null;

		CellStyle style = workbook.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);

		for (int i = 0; i < excelTableFieldCollection.size(); i++) {

			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(excelTableFieldCollection.get(i));
			headerCell.setCellStyle(style);

		}

		// Data *****************************************************

		int rowCount = 1;
		int columnCount = 0;
		int next = 0;
		int i = 0;
		int j = 0;

		XSSFRow row = worksheet.createRow(rowCount);
		Cell cell = null;
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);

		for (j = 0; j < excelObjectCollection.size(); j++) {
			row = worksheet.createRow(rowCount++);
			next = i;
			columnCount = 0;
			worksheet.autoSizeColumn(columnCount + j);

			for (i = next; i < excelObjectInfoCollection.size() / excelObjectCollection.size() + next; i++) {

				cell = row.createCell(columnCount++);
				cell.setCellStyle(cellStyle);
				worksheet.setColumnWidth(i, 20000);

				if (excelObjectInfoCollection.get(i) instanceof Double) {

					cell.setCellValue((double) excelObjectInfoCollection.get(i));
				}

				else if (excelObjectInfoCollection.get(i) instanceof LocalDate) {
					formatDateCell(workbook, cell);

					long numberOfDays;
					LocalDate firstDay = LocalDate.of(1900, 1, 1);
					LocalDate currentDate = (LocalDate) excelObjectInfoCollection.get(i);
					numberOfDays = ChronoUnit.DAYS.between(firstDay, currentDate) + 2;
					cell.setCellValue(numberOfDays);
				}

				else if (excelObjectInfoCollection.get(i) instanceof Boolean) {

					cell.setCellValue((boolean) excelObjectInfoCollection.get(i));
				}

				else if (excelObjectInfoCollection.get(i) instanceof Integer) {

					cell.setCellValue((int) excelObjectInfoCollection.get(i));
				}

				else if (excelObjectInfoCollection.get(i) instanceof Float) {

					cell.setCellValue((float) excelObjectInfoCollection.get(i));
				} else {
					cell.setCellValue((String) excelObjectInfoCollection.get(i));
				}
			}
		}

	}

	public static void excelTableProductionPlan(XSSFWorkbook workbook, XSSFSheet worksheet,
			ArrayList<String> excelTableFieldCollection, ArrayList<Object> excelObjectInfoCollection, int weekNumber) {

		// Header ***********************************************

		XSSFRow headerRow = worksheet.createRow(0);
		Cell headerCell = null;

		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.DIAMONDS);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		worksheet.setColumnWidth(0, 3000);
		worksheet.setColumnWidth(1, 4700);
		worksheet.setColumnWidth(2, 9000);
		worksheet.setColumnWidth(3, 2300);
		worksheet.setColumnWidth(4, 3000);
		worksheet.setColumnWidth(5, 3000);
		worksheet.setColumnWidth(6, 5000);
		worksheet.setColumnWidth(7, 3000);
		worksheet.setColumnWidth(8, 3000);
		headerRow.setHeight((short) 800);
		worksheet.setAutoFilter(CellRangeAddress.valueOf("A1:I1"));
		worksheet.createFreezePane(0, 1);
		worksheet.setMargin(Sheet.LeftMargin, 0.3);
		worksheet.setMargin(Sheet.RightMargin, 0.3);
		worksheet.getPrintSetup().setLandscape(true);
		worksheet.getPrintSetup().setPaperSize(PaperSize.A4_PAPER);

		for (int i = 0; i < excelTableFieldCollection.size(); i++) {

			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(excelTableFieldCollection.get(i));
			headerCell.setCellStyle(style);
		}

		// Data *****************************************************

		int rowCount = 1;
		int columnCount = 0;
		int next = 0;
		int i = 0;
		int j = 0;

		XSSFRow row = worksheet.createRow(rowCount);
		Cell cell = null;
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		font.setFontHeightInPoints((short) 10);
		cellStyle.setFont(font);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setWrapText(true);

		Header header = worksheet.getHeader();

		header.setCenter("Производствен план седмица " + weekNumber);

		for (j = 0; j < excelObjectInfoCollection.size() / excelTableFieldCollection.size(); j++) {
			row = worksheet.createRow(rowCount++);

			next = i;

			columnCount = 0;
			// worksheet.autoSizeColumn(columnCount + j);
			row.setHeight((short) 500);

			for (i = next; i < (excelTableFieldCollection.size()) + next; i++) {

				cell = row.createCell(columnCount++);
				cell.setCellStyle(cellStyle);

				if (excelObjectInfoCollection.get(i) instanceof Double) {

					cell.setCellValue((double) excelObjectInfoCollection.get(i));
				}

				else if (excelObjectInfoCollection.get(i) instanceof LocalDate) {
					formatDateCell(workbook, cell);

					long numberOfDays;
					LocalDate firstDay = LocalDate.of(1900, 1, 1);
					LocalDate currentDate = (LocalDate) excelObjectInfoCollection.get(i);
					numberOfDays = ChronoUnit.DAYS.between(firstDay, currentDate) + 2;
					cell.setCellValue(numberOfDays);

				}

				else if (excelObjectInfoCollection.get(i) instanceof Boolean) {

					cell.setCellValue((boolean) excelObjectInfoCollection.get(i));
				}

				else if (excelObjectInfoCollection.get(i) instanceof Integer) {

					cell.setCellValue((int) excelObjectInfoCollection.get(i));
				}

				else if (excelObjectInfoCollection.get(i) instanceof Float) {

					cell.setCellValue((float) excelObjectInfoCollection.get(i));
				} else {
					cell.setCellValue((String) excelObjectInfoCollection.get(i));
				}
			}
		}
	}

	public static void createExcelProtocolDma(XSSFWorkbook workbook, XSSFSheet worksheet,
			ArrayList<Object> protocolDataCollection, ArrayList<String> protocolFieldsCollection, int protocolNumber) {

		String documentDate = Date.convertDate(protocolDataCollection.get(0));

		worksheet.setMargin(Sheet.LeftMargin, 0.3);
		worksheet.setMargin(Sheet.RightMargin, 0.3);
		worksheet.setMargin(Sheet.HeaderMargin, 0.1);
		worksheet.setMargin(Sheet.FooterMargin, 0.1);
		XSSFRow row = null;
		Cell cell = null;
		CellStyle cellStyle = workbook.createCellStyle();
		CellStyle cellStyleH = workbook.createCellStyle();
		CellStyle cellStyleА = workbook.createCellStyle();
		CellStyle cellStyleB = workbook.createCellStyle();
		CellStyle cellStyleC = workbook.createCellStyle();
		CellStyle cellStyleD = workbook.createCellStyle();
		CellStyle cellStyleE = workbook.createCellStyle();
		CellStyle cellStyleF = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		XSSFFont fontE = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		fontE.setFontHeightInPoints((short) 9);
		worksheet.setColumnWidth(0, 12000);
		worksheet.setColumnWidth(1, 13000);
		worksheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
		worksheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 1));
		worksheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));
		cellStyleC.setBorderBottom(BorderStyle.THIN);
		cellStyleC.setBorderLeft(BorderStyle.THIN);
		cellStyleC.setBorderRight(BorderStyle.THIN);
		cellStyleC.setBorderTop(BorderStyle.THIN);
		cellStyleC.setFont(font);
		cellStyleC.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyleC.setWrapText(true);

		cellStyleD.setAlignment(HorizontalAlignment.CENTER);

		cellStyleE.setAlignment(HorizontalAlignment.CENTER);
		cellStyleE.setFont(fontE);

		cellStyleF.setAlignment(HorizontalAlignment.RIGHT);

		row = worksheet.createRow(0);
		cell = row.createCell(1);
		cell.setCellValue("АПС ТРЕЙДИНГ ООД");
		cellStyleH.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellStyle(cellStyleH);

		row = worksheet.createRow(1);
		cell = row.createCell(1);
		cell.setCellValue("гр. Ботевград");
		cellStyleH.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellStyle(cellStyleH);

		row = worksheet.createRow(2);
		cell = row.createCell(1);
		cell.setCellValue("Промишлена зона Микроелектроника");
		cellStyleH.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellStyle(cellStyleH);

		row = worksheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue("ПРОТОКОЛ");
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);

		row = worksheet.createRow(5);
		cell = row.createCell(0);
		cell.setCellValue("за въвеждане в експлоатация на ДМА");
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);

		row = worksheet.createRow(6);
		cell = row.createCell(0);
		cell.setCellValue("съгласно чл.58 от ЗКПО");
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);

		row = worksheet.createRow(8);
		cell = row.createCell(0);
		cellStyleB.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellValue("Номер: " + protocolNumber);
		cell.setCellStyle(cellStyleB);
		cell = row.createCell(1);
		cell.setCellStyle(cellStyleB);
		cell.setCellValue("Дата:  " + documentDate);

		for (int i = 10; i < protocolFieldsCollection.size() + 10; i++) {

			row = worksheet.createRow(i);
			row.setHeight((short) 500);
			cell = row.createCell(0);
			cell.setCellStyle(cellStyleC);
			cell.setCellValue(protocolFieldsCollection.get(i - 10));

			cell = row.createCell(1);
			cell.setCellStyle(cellStyleC);
			cell.setCellValue((String) protocolDataCollection.get((i + 1) - 10));

		}

		row = worksheet.createRow(24);
		cell = row.createCell(0);
		cell.setCellValue("Приел:.......................................................");
		cell.setCellStyle(cellStyleD);
		cell = row.createCell(1);
		cell.setCellValue("Подпис:.......................................................");
		cell.setCellStyle(cellStyleD);

		row = worksheet.createRow(25);
		cell = row.createCell(0);
		cell.setCellValue("(име, длъжност и подпис)");
		cell.setCellStyle(cellStyleE);

		row = worksheet.createRow(27);
		row.setHeight((short) 500);
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue("Номер на актив:");
		cell = row.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(28);
		row.setHeight((short) 500);
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue("Данъчна амортизируема стойност на актива:");
		cell = row.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(29);
		row.setHeight((short) 500);
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue("Приета данъчна амортизационна норма:");
		cell = row.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(30);
		row.setHeight((short) 500);
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue("Категория съгласно чл.55 от ЗКПО:");
		cell = row.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(31);
		row.setHeight((short) 500);
		worksheet.addMergedRegion(new CellRangeAddress(31, 31, 0, 1));
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue(
				"Активът е включен в данъчния амортизационен план, считано от ..............................:");
		cell = row.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(33);
		row.setHeight((short) 500);
		worksheet.addMergedRegion(new CellRangeAddress(30, 30, 0, 1));
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue("Основание за покупка:");
		cell = row.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(36);
		cell = row.createCell(0);
		cell.setCellValue("Главен счетоводител:");
		cell.setCellStyle(cellStyleF);
		cell = row.createCell(1);
		cell.setCellValue("......................................................................");
		cell.setCellStyle(cellStyleD);

		row = worksheet.createRow(37);
		cell = row.createCell(1);
		cell.setCellValue("(име и подпис)");
		cell.setCellStyle(cellStyleE);

	}

	public static void extractFullExcelProtocolDma(XSSFWorkbook workbook, XSSFSheet worksheet,
			ArrayList<String> protocolDataCollection, ArrayList<String> protocolFieldsCollection) {

		String documentDate = Date.convertDate(protocolDataCollection.get(0));

		worksheet.setMargin(Sheet.LeftMargin, 0.3);
		worksheet.setMargin(Sheet.RightMargin, 0.3);
		worksheet.setMargin(Sheet.HeaderMargin, 0.1);
		worksheet.setMargin(Sheet.FooterMargin, 0.1);
		XSSFRow row = null;
		Cell cell = null;
		CellStyle cellStyle = workbook.createCellStyle();
		CellStyle cellStyleH = workbook.createCellStyle();
		CellStyle cellStyleА = workbook.createCellStyle();
		CellStyle cellStyleB = workbook.createCellStyle();
		CellStyle cellStyleC = workbook.createCellStyle();
		CellStyle cellStyleD = workbook.createCellStyle();
		CellStyle cellStyleE = workbook.createCellStyle();
		CellStyle cellStyleF = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		XSSFFont fontE = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		fontE.setFontHeightInPoints((short) 9);
		worksheet.setColumnWidth(0, 12000);
		worksheet.setColumnWidth(1, 13000);
		worksheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
		worksheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 1));
		worksheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));
		cellStyleC.setBorderBottom(BorderStyle.THIN);
		cellStyleC.setBorderLeft(BorderStyle.THIN);
		cellStyleC.setBorderRight(BorderStyle.THIN);
		cellStyleC.setBorderTop(BorderStyle.THIN);
		cellStyleC.setFont(font);
		cellStyleC.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyleC.setWrapText(true);

		cellStyleD.setAlignment(HorizontalAlignment.CENTER);

		cellStyleE.setAlignment(HorizontalAlignment.CENTER);
		cellStyleE.setFont(fontE);

		cellStyleF.setAlignment(HorizontalAlignment.RIGHT);

		row = worksheet.createRow(0);
		cell = row.createCell(1);
		cell.setCellValue("АПС ТРЕЙДИНГ ООД");
		cellStyleH.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellStyle(cellStyleH);

		row = worksheet.createRow(1);
		cell = row.createCell(1);
		cell.setCellValue("гр. Ботевград");
		cellStyleH.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellStyle(cellStyleH);

		row = worksheet.createRow(2);
		cell = row.createCell(1);
		cell.setCellValue("Промишлена зона Микроелектроника");
		cellStyleH.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellStyle(cellStyleH);

		row = worksheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue("ПРОТОКОЛ");
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);

		row = worksheet.createRow(5);
		cell = row.createCell(0);
		cell.setCellValue("за въвеждане в експлоатация на ДМА");
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);

		row = worksheet.createRow(6);
		cell = row.createCell(0);
		cell.setCellValue("съгласно чл.58 от ЗКПО");
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);

		row = worksheet.createRow(8);
		cell = row.createCell(0);
		cellStyleB.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellValue("Номер: " + protocolDataCollection.get(0));
		cell.setCellStyle(cellStyleB);
		cell = row.createCell(1);
		cell.setCellStyle(cellStyleB);
		cell.setCellValue("Дата:  " + protocolDataCollection.get(1));

		for (int i = 10; i < 12 + 10; i++) {

			row = worksheet.createRow(i);
			row.setHeight((short) 500);
			cell = row.createCell(0);
			cell.setCellStyle(cellStyleC);
			cell.setCellValue(protocolFieldsCollection.get(i - 8));

			cell = row.createCell(1);
			cell.setCellStyle(cellStyleC);
			cell.setCellValue((String) protocolDataCollection.get((i - 8)));

		}

		row = worksheet.createRow(24);
		cell = row.createCell(0);
		cell.setCellValue("Приел:.......................................................");
		cell.setCellStyle(cellStyleD);
		cell = row.createCell(1);
		cell.setCellValue("Подпис:.......................................................");
		cell.setCellStyle(cellStyleD);

		row = worksheet.createRow(25);
		cell = row.createCell(0);
		cell.setCellValue("(име, длъжност и подпис)");
		cell.setCellStyle(cellStyleE);

		row = worksheet.createRow(27);
		row.setHeight((short) 500);
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue("Номер на актив:");
		cell = row.createCell(1);
		cell.setCellValue(protocolDataCollection.get(14));
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(28);
		row.setHeight((short) 500);
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue("Данъчна амортизируема стойност на актива:");
		cell = row.createCell(1);
		cell.setCellValue(protocolDataCollection.get(15) + " лв.");
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(29);
		row.setHeight((short) 500);
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue("Приета данъчна амортизационна норма:" + "лв.");
		cell = row.createCell(1);
		cell.setCellValue(protocolDataCollection.get(16));
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(30);
		row.setHeight((short) 500);
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue("Категория съгласно чл.55 от ЗКПО: " + protocolDataCollection.get(17));
		cell = row.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(31);
		row.setHeight((short) 500);
		worksheet.addMergedRegion(new CellRangeAddress(31, 31, 0, 1));
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue(
				"Активът е включен в данъчния амортизационен план, считано от: " + protocolDataCollection.get(18));
		cell = row.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(33);
		row.setHeight((short) 500);
		worksheet.addMergedRegion(new CellRangeAddress(30, 30, 0, 1));
		cell = row.createCell(0);
		cell.setCellStyle(cellStyleC);
		cell.setCellValue("Основание за покупка:");
		cell = row.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(cellStyleC);

		row = worksheet.createRow(36);
		cell = row.createCell(0);
		cell.setCellValue("Главен счетоводител:");
		cell.setCellStyle(cellStyleF);
		cell = row.createCell(1);
		cell.setCellValue("......................................................................");
		cell.setCellStyle(cellStyleD);

		row = worksheet.createRow(37);
		cell = row.createCell(1);
		cell.setCellValue("(име и подпис)");
		cell.setCellStyle(cellStyleE);

	}

	public static void createExcelSkillsMatrix(XSSFWorkbook workbook, XSSFSheet worksheet,
			ArrayList<String> productTypeFields, ArrayList<String> operatorsName,
			ArrayList<String> skillsCollectionFromProductionCards, int productTypeSize) {

		XSSFRow headerRow = worksheet.createRow(0);
		Cell headerCell = null;

		XSSFCellStyle style = workbook.createCellStyle();
		XSSFCellStyle styleFirstCell = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();

		styleFirstCell.setBorderBottom(BorderStyle.THIN);
		styleFirstCell.setBorderLeft(BorderStyle.THIN);
		styleFirstCell.setBorderRight(BorderStyle.THIN);
		styleFirstCell.setBorderTop(BorderStyle.THIN);
		styleFirstCell.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		styleFirstCell.setFillPattern(FillPatternType.DIAMONDS);
		styleFirstCell.setVerticalAlignment(VerticalAlignment.CENTER);
		styleFirstCell.setAlignment(HorizontalAlignment.CENTER);
		styleFirstCell.setWrapText(true);

		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.DIAMONDS);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		style.setRotation((short) 90);
		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		styleFirstCell.setFont(font);

		worksheet.setColumnWidth(0, 9000);

		headerRow.setHeight((short) 2500);
		worksheet.setAutoFilter(CellRangeAddress.valueOf("A1:DW1"));
		worksheet.createFreezePane(1, 1);
		worksheet.setMargin(Sheet.LeftMargin, 0.3);
		worksheet.setMargin(Sheet.RightMargin, 0.3);
		worksheet.getPrintSetup().setLandscape(true);
		worksheet.getPrintSetup().setPaperSize(PaperSize.A4_PAPER);

		for (int i = 0; i < productTypeFields.size(); i++) {

			headerCell = headerRow.createCell(0);
			headerCell.setCellValue("Оператор");
			headerCell.setCellStyle(styleFirstCell);
			headerCell = headerRow.createCell(i + 1);
			headerCell.setCellValue(productTypeFields.get(i));
			headerCell.setCellStyle(style);
		}

		XSSFRow dataRow = worksheet.createRow(1);
		Cell dataCell = null;

		int next = 1;
		int nextOperator = 0;
		int j = 0;
		int rowCount = 1;
		int columnCount = 1;

		for (int i = 0; i < operatorsName.size(); i++) {

			dataRow = worksheet.createRow(rowCount++);
			columnCount = 1;

			for (int h = nextOperator; h <= nextOperator; h++) {

				dataCell = dataRow.createCell(0);
				dataCell.setCellValue(operatorsName.get(h));

			}

			for (j = next; j < (productTypeSize + next); j++) {

				dataCell = dataRow.createCell(columnCount++);
				dataCell.setCellValue(skillsCollectionFromProductionCards.get(j - 1));
			}

			next = j;
			nextOperator++;
		}
	}

	public void generateSerialNumberExcel(XSSFWorkbook workbook, XSSFSheet worksheet,
			String[] columnName, String[] data) {

		XSSFRow headerRow = worksheet.createRow(0);
		Cell headerCell = null;

		for (int i = 0; i < columnName.length; i++) {

			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(columnName[i]);

		}

		XSSFRow dataRow = null;
		Cell dataCell = null;
		int rowCount = 1;
		int i = 0;
		int nextData = 0;
		int next = 0;
        int nextNext = 0;
		
		for (int j = 0; j < data.length / 4; j++) {

			dataRow = worksheet.createRow(rowCount);
			nextNext = 0;

			for (i = nextData; i < 4 + next; i++) {
  
							
				dataCell = dataRow.createCell(nextNext);
				dataCell.setCellValue(data[i]);
				nextData++;
				nextNext++;
			}
			rowCount++;
			next = nextData;
		}
	}
}
