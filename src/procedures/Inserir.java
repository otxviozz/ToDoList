package procedures;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import show.ShowInserir;

public class Inserir {

    public static void LogicaInserir(JTextField campoTarefa, JRadioButton rdbtnNaoIniciado,
                                     JRadioButton rdbtnEmAndamento, JRadioButton rdbtnConcluido,
                                     JPanel painelStatus, JLabel lblStatus, JButton btnConfirmar,
                                     ArrayList<String> tarefas, JPanel painelTarefas) {

        
        ShowInserir.AparecerInserir(campoTarefa, rdbtnNaoIniciado, rdbtnEmAndamento, rdbtnConcluido,
                                    painelStatus, lblStatus, btnConfirmar, tarefas, painelTarefas);

        
        for (ActionListener al : btnConfirmar.getActionListeners()) {
            btnConfirmar.removeActionListener(al);
        }

        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Inserir.enviarDados(campoTarefa, rdbtnNaoIniciado, rdbtnEmAndamento, rdbtnConcluido,
                                    tarefas, lblStatus, painelTarefas);

                campoTarefa.setText("");
                campoTarefa.setVisible(false);
                painelStatus.setVisible(false);
                btnConfirmar.setVisible(false);
            }
        });
    }

    public static void enviarDados(JTextField campoTarefa, JRadioButton rdbtnNaoIniciado,
                                   JRadioButton rdbtnEmAndamento, JRadioButton rdbtnConcluido,
                                   ArrayList<String> tarefas, JLabel lblStatus, JPanel painelTarefas) {

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
        } else {
            JOptionPane.showMessageDialog(null, "Digite uma tarefa válida.");
        }
    }
}