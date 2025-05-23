package procedures;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.Application;
import show.ShowEditar;

public class Editar {
    public static void BtnEditar(int index, ArrayList<String> tarefas, JPanel painelTarefas) {
        String tarefaAtual = tarefas.get(index);
        String[] partes = tarefaAtual.split(",");

        if (partes.length == 2) {
            String tarefaAntiga = partes[0];
            String statusAntigo = partes[1];

            Application.campoTarefa.setText(tarefaAntiga);

            switch (statusAntigo) {
                case "Não iniciado" -> Application.rdbtnNaoIniciado.setSelected(true);
                case "Em andamento" -> Application.rdbtnEmAndamento.setSelected(true);
                case "Concluído" -> Application.rdbtnConcluido.setSelected(true);
            }

            ShowEditar.ShowBtnEditar(Application.lblStatus, Application.campoTarefa, Application.painelStatus, Application.btnConfirmar);

            for (ActionListener al : Application.btnConfirmar.getActionListeners()) {
                Application.btnConfirmar.removeActionListener(al);
            }

            Application.btnConfirmar.addActionListener(ev -> {
                Confirmar.confirmarbotaoedit(index, tarefas, painelTarefas);
            });

        } else {
            System.out.println("Formato inválido para: " + tarefaAtual);
        }
    }
}
