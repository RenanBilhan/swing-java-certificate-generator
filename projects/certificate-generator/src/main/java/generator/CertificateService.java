package generator;

import lombok.Cleanup;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CertificateService extends CertificateForm {
    public List<CertificateDto> create (String str) throws IOException {
        List<CertificateDto> certificateDtoList = new ArrayList<>();
        str = localTextField.getText().trim();
        @Cleanup FileInputStream fileInputStream = new FileInputStream(str);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        List<Row> rows = (List<Row>) sheet.rowIterator();
        rows.remove(0);
        rows.forEach(row->{
            List<Cell> cells = (List<Cell>) row.cellIterator();

            CertificateDto certificateDto = CertificateDto.builder()
                    .name (cells.get(0).toString())
                    .workLoad(String.valueOf(cells.get(1).getNumericCellValue()))
                    .course(cells.get(2).toString())
                    .build();

            certificateDtoList.add(certificateDto);
        });
        return certificateDtoList;
    }

    @Override
    protected void cleanButtonClick(ActionEvent evt) {

    }

    @Override
    protected void searchButtonClick(ActionEvent evt) {

    }

    @Override
    protected void generateButtonClick(ActionEvent evt) {

    }
}
