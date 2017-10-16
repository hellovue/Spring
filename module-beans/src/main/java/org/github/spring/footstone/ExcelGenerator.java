package org.github.spring.footstone;

import lombok.val;

import org.github.spring.restful.FileReturn.ExcelFileReturn;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel文件生成工具类.
 *
 * @author JYD_XL
 * @since 0.0.1-SNAPSHOT
 */
public abstract class ExcelGenerator {
  public static XSSFWorkbook generate(ExcelFileReturn excel) {
    val workbook = new XSSFWorkbook();

    val sheet = workbook.createSheet();
    val first = sheet.createRow(0);
    val title = excel.getTitle();

    val origin = excel.getWrappedData();

    // SET 写入标题.
    for (int i = 0; i < title.size(); i++) {
      val cell = first.createCell(i);

      // TODO 标题必须加特技...

      cell.setCellType(CellType.STRING);
      cell.setCellValue(title.get(i));
    }

    // SET 写入数据.
    for (int i = 0; i < origin.length; i++) {
      val row = sheet.createRow(i + 1);
      for (int j = 0; j < origin[i].length; j++) {
        val cell = row.createCell(j);
        cell.setCellType(CellType.STRING);
        cell.setCellValue(origin[i][j]);
      }
    }

    return workbook;
  }
}