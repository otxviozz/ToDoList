package procedures;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Inserir {
    public static void inserirTarefa(JTextField campoTarefa, JRadioButton rdbtnNaoIniciado, JRadioButton rdbtnEmAndamento,
                                     JRadioButton rdbtnConcluido, ArrayList<String> tarefas, JLabel lblStatus, JPanel painelTarefas) {
        String statusSelecionado = "";

        if (rdbtnNaoIniciado.isSelected()) {
            statusSelecionado = rdbtnNaoIniciado.getText();
        } else if (rdbtnEmAndamento.isSelected()) {
            statusSelecionado = rdbtnEmAndamento.getText();
        } else if (rdbtnConcluido.isSelected()) {
            statusSelecionado = rdbtnConcluido.getText();
        }

        String inserir = campoTarefa.getText().trim() + "," + statusSelecionado;

        if (!inserir.isEmpty() && !inserir.equals("Insira a tarefa")) {
            tarefas.add(inserir);
            AtualizarPainel.atualizarPainelTarefas(painelTarefas, tarefas);

            lblStatus.setVisible(true);
            lblStatus.setText("Tarefa adicionada com sucesso!");

            campoTarefa.setText("");
            campoTarefa.setVisible(false);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Digite uma tarefa válida.");
        }
    }
}