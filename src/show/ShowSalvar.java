package show;

import java.io.File;

import javax.swing.JFileChooser;

public class ShowSalvar {
    public static String MostrarSalvar() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showSaveDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            return arquivoSelecionado.getAbsolutePath();
        }
        return null;
    }
}