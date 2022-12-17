package generator;

import lombok.Cleanup;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.apache.commons.collections4.IteratorUtils.toList;


public abstract class CertificateForm extends JFrame {
    protected JPanel footerPanel;
    protected JPanel mainPanel;

    protected JButton searchButton;
    protected JButton pathButton;
    protected JButton generateButton;
    protected JButton cleanButton;

    protected JLabel localLabel;
    protected JTextField localTextField;

    public CertificateForm(){
        this.init();
        this.events();
    }
    private void init(){
        this.setTitle("Gerador de certificados.");
        this.setSize(640, 400);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(getMainPanel(), BorderLayout.CENTER);
        this.getContentPane().add(getFooterPanel(), BorderLayout.PAGE_END);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setResizable(false);
    }
    protected abstract void cleanButtonClick(ActionEvent evt);

    protected abstract void searchButtonClick(ActionEvent evt);

    protected  abstract void generateButtonClick(ActionEvent evt);


    private void events(){
        cleanButton.addActionListener(this::cleanButtonClick);
//        pathButton.addActionListener(this::pathButtonClick);
        searchButton.addActionListener(this::searchButtonClick);
        generateButton.addActionListener(this::generateButtonClick);
    }

    public JPanel getMainPanel(){
        if(mainPanel==null){
            mainPanel = new JPanel( new GridLayout(3,2));

            localLabel = new JLabel("Local do arquivo:");
            localTextField = new JTextField();

            mainPanel.add(localLabel);
            mainPanel.add(localTextField);
        }

        return mainPanel;
    }

    public JPanel getFooterPanel() {
        if(footerPanel == null){
            footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            searchButton = new JButton("Procurar arquivo");
            generateButton = new JButton("Gerar certificados");
            cleanButton = new JButton("Limpar");

            footerPanel.add(searchButton);
            footerPanel.add(generateButton);
            footerPanel.add(cleanButton);

        }

        return footerPanel;
    }

    public java.util.List<CertificateDto> create (String str) throws IOException {
        java.util.List<CertificateDto> certificateDtoList = new ArrayList<>();
        str = localTextField.getText().trim();
        @Cleanup FileInputStream fileInputStream = new FileInputStream(str);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        java.util.List<Row> rows = toList(sheet.rowIterator());
        rows.remove(0);
        rows.forEach(row->{
        java.util.List<Cell> cells =  toList(row.cellIterator());

            CertificateDto certificateDto = CertificateDto.builder()
                    .name (cells.get(0).toString())
                    .workLoad(String.valueOf(cells.get(1).getNumericCellValue()))
                    .course(cells.get(2).toString())
                    .build();

            certificateDtoList.add(certificateDto);
        });
        return certificateDtoList;
    }
}
