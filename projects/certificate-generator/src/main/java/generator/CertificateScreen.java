package generator;

import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CertificateScreen extends CertificateForm {
    File file;

    @Override
    protected void cleanButtonClick(ActionEvent evt) {
        localTextField.setText("");
    }

    @Override
    protected void searchButtonClick(ActionEvent evt) {


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setPreferredSize(new Dimension(600, 400));

        int result = fileChooser.showOpenDialog(this);

        if(result == JFileChooser.CANCEL_OPTION){

        }
        else{
            file = fileChooser.getSelectedFile();

            localTextField.setText(file.toString().trim());


        }
    }
    @SneakyThrows
    @Override
    protected void generateButtonClick(ActionEvent evt) {
        String str = file.toString().trim();
        List<CertificateDto> dtoList = new ArrayList<>(create(str));


        for (int i = 0; i < dtoList.size(); i++){

            List<CertificateDto> certificatePrint = new ArrayList<>();
            certificatePrint.add(dtoList.get(i));
            JRBeanCollectionDataSource collectionDataSource = new JRBeanCollectionDataSource(certificatePrint);
            JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("C:\\Users\\Renan\\java\\projects\\certificate-generator\\src\\main\\resources\\Certificate_Sample_Blank.jrxml"));
            HashMap<String, Object> map = new HashMap<>();
            JasperPrint report = JasperFillManager.fillReport(compileReport, map, collectionDataSource);
            JasperExportManager.exportReportToPdfFile(report, "certificado" + certificatePrint.get(0).getName()+".pdf");
            System.out.println("sucesso");

        }
    }
}
