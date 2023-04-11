package com.intern.hrmanagementapi.constant;

import com.intern.hrmanagementapi.entity.Employee;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelExporter {
        private XSSFWorkbook workbook;
        private XSSFSheet sheet;
        private List<Employee> list;


        public ExcelExporter(List<Employee> list) {
            this.list = list;
            workbook = new XSSFWorkbook();
        }


        private void writeHeaderLine() {
            sheet = workbook.createSheet("Employees");

            Row row = sheet.createRow(0);

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16);
            style.setFont(font);

            createCell(row, 0, "ID", style);
            createCell(row, 1, "FirstName", style);
            createCell(row, 2, "LastName", style);
            createCell(row, 3, "Gender", style);
            createCell(row, 4, "Address", style);
            createCell(row, 4, "Date of birth", style);
            createCell(row, 4, "Department Id", style);
            createCell(row, 4, "Position Id", style);
            createCell(row, 4, "Contract Id", style);
            createCell(row, 4, "Education Id", style);
            createCell(row, 4, "Create Date", style);
            createCell(row, 4, "Update Date", style);

        }

        private void createCell(Row row, int columnCount, Object value, CellStyle style) {
            sheet.autoSizeColumn(columnCount);
            Cell cell = row.createCell(columnCount);
            if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            } else if (value instanceof Boolean) {
                cell.setCellValue((Boolean) value);
            }else {
                cell.setCellValue((String) value);
            }
            cell.setCellStyle(style);
        }

        private void writeDataLines() {
            int rowCount = 1;

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setFontHeight(14);
            style.setFont(font);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (Employee employee : list) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;

                createCell(row, columnCount++, employee.getId().toString(), style);
                createCell(row, columnCount++, employee.getFirstName(), style);
                createCell(row, columnCount++, employee.getLastName(), style);
                createCell(row, columnCount++, employee.getGender(), style);
                createCell(row, columnCount++, employee.getAddress(), style);
                createCell(row, columnCount++, employee.getDob().format(formatter), style);
                createCell(row, columnCount++, employee.getDepartmentId(), style);
                createCell(row, columnCount++, employee.getPositionId(), style);
                createCell(row, columnCount++, employee.getContractId(), style);
                createCell(row, columnCount++, employee.getEducationId(), style);
                createCell(row, columnCount++, employee.getCreateDate().format(formatter), style);
                createCell(row, columnCount++, employee.getUpdateDate().format(formatter), style);

            }
        }

        public void export(HttpServletResponse response) throws IOException {
            writeHeaderLine();
            writeDataLines();

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();

            outputStream.close();

        }
}
