package generator;

import javax.swing.*;

public class PdfGeneratorAplication {
    public static void main(String[] args){
        SwingUtilities.invokeLater(()-> {
            CertificateScreen certificateScreen = new CertificateScreen();
            certificateScreen.setVisible(true);
        });
    }

}
