package show;

import java.io.File;

import javax.swing.JFileChooser;

import main.Application;

public class ShowCarregar {
    public static void MostrarCarregar() {
        Application.campoTarefa.setVisible(false);
        Application.painelStatus.setVisible(false);
        Application.lblStatus.setVisible(false);
        Application.btnConfirmar.setVisible(false);

        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            String caminhoArquivo = arquivoSelecionado.getAbsolutePath();
            Application.path = caminhoArquivo;

            System.out.println("Arquivo escolhido: " + caminhoArquivo);
            Application.lblStatus.setText("Arquivo carregado com sucesso!");
            Application.lblStatus.setVisible(true);
        }
    }
}
