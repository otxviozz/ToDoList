package procedures;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import show.ShowSalvar;

public class Salvar {
    public static void LogicaSalvar(ArrayList<String> tarefas, JLabel lblStatus) {
        String path = ShowSalvar.MostrarSalvar();
        if (path == null) {
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String linha : tarefas) {
                bw.write(linha);
                bw.newLine();
            }
            JOptionPane.showMessageDialog(null, "Tarefas salvas com sucesso!");
            lblStatus.setVisible(false);
            lblStatus.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar tarefas!");
        }
    }
}
