package com.akbar.rahul;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {
	
	public List<String> getData(String sheetName, String testCaseName) throws IOException {
		
		List<String> list = new ArrayList<>();
		
		// TO GET THE EXCEL FILE LOCATION
		FileInputStream fis = new FileInputStream("D:" + File.separator + "PC" + File.separator + "Desktop" + File.separator + "Testing" + File.separator + "Selenium" + File.separator + "cricket.xlsx");
		
		// CREATE THE OBJECT FOR XSSFWORKBOOK CLASS AND PASS FILEINPUTSTREAM AS AN ARGUMENT
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		// TO KNOW HOW MANY SHEETS ARE PRESENT IN THE WORKBOOK
		int sheets = workbook.getNumberOfSheets();
		//System.out.println(sheets);
		
		// USING FOR LOOP TO IDENTIFY THE SHEET
		for(int i = 0; i < sheets; i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				
				// IDENTIFIED THE SHEET BASED ON INDEX
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				// ITERATE THE SHEET TO GET ACCESS OF ALL THE ROWS
				Iterator<Row> rows = sheet.iterator(); // Sheet is collection of rows
				
				// IDENTIFIED THE FIRST ROW
				Row firstRow = rows.next();
				
				// ITERATE THE ROW TO GET ACCESS OF ALL THE CELLS
				Iterator<Cell> cellIterator = firstRow.cellIterator(); // Row is collection of cells
				
				// IDENTIFY THE "TESTCASES" CELL
				int column = 0;
				int k = 0;
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// USE SMART LOGIC AND IDENTIFY THE "TESTCASES" COLUMN NUMBER
					if(cell.getStringCellValue().equalsIgnoreCase("TestCases")) {
						column = k;
					}
					k++;
				}
				//System.out.println(column);
				while(rows.hasNext()) {
					
					// ITERATE THE ALL THE ROWS
					Row row = rows.next();
					
					// WRITE A CONDITION TO ITERATE IN SPECIFIC COLUMN AND IDENTIFY THE TEST CASE
					if(row.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						
						// ITERATE THE CELL IN THAT PARTICULAR ROW AND ADD DATA TO THE LIST
						Iterator<Cell> cellValues = row.cellIterator();
						while(cellValues.hasNext()) {
							Cell cellValue = cellValues.next();
							if(cellValue.getCellType() == CellType.STRING) {
								list.add(cellValue.getStringCellValue());
							} else {
								list.add(NumberToTextConverter.toText(cellValue.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		
		return list;
	}
}
