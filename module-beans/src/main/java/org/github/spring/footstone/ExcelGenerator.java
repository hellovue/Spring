package org.github.spring.footstone;

import java.util.List;

import org.github.spring.restful.FileReturn.ExcelFileReturn;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class ExcelGenerator {
  public static <T> XSSFWorkbook generate(ExcelFileReturn<T> excel) {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet();
    XSSFRow head = sheet.createRow(0);

    List<String> title = excel.getTitle();
    String[][] data = excel.getWrappedData();

    //SET title.
    for (int i = 0; i < title.size(); i++) {
      XSSFCell cell = head.createCell(i);
      cell.setCellType(CellType.STRING);
      cell.setCellValue(title.get(i));
    }

    //SET value.
    for (int i = 0; i < data.length; i++) {
      XSSFRow row = sheet.createRow(i + 1);
      for (int j = 0; j < data[i].length; j++) {
        XSSFCell cell = row.createCell(j);
        cell.setCellType(CellType.STRING);
        cell.setCellValue(data[i][j]);
      }
    }

    return workbook;
  }
}
