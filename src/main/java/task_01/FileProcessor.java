package task_01;
/**
 * The {@code task_01.FileProcessor$} class represents Functionalities
 *
 * @author ArunKumar D
 */

import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;

class FileProcessor {
    public static DefaultTableModel processExcelFile(String filePath, int startRow) throws Exception {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        DefaultTableModel model = new DefaultTableModel();
        Row headerRow = sheet.getRow(startRow);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            model.addColumn(headerRow.getCell(i).toString());
        }

        for (int i = startRow + 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Object[] rowData = new Object[row.getLastCellNum()];
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                rowData[j] = (cell != null) ? cell.toString() : "";
            }
            model.addRow(rowData);
        }

        workbook.close();
        file.close();
        return model;
    }

    public static DefaultTableModel processCSVFile(String filePath, int startRow) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> rows = reader.readAll();
        DefaultTableModel model = new DefaultTableModel();

        String[] headers = rows.get(startRow);
        for (String header : headers) {
            model.addColumn(header);
        }

        for (int i = startRow + 1; i < rows.size(); i++) {
            model.addRow(rows.get(i));
        }

        reader.close();
        return model;
    }
}
