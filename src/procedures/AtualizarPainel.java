package procedures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import main.Application;

public class AtualizarPainel {
    public static void atualizarPainelTarefas(JPanel painelTarefas, java.util.ArrayList<String> tarefas) {
        painelTarefas.removeAll();

        for (int i = 1; i < tarefas.size(); i++) {
            String tarefa = tarefas.get(i);
            String[] partes = tarefa.split(",");

            JPanel linha = new JPanel(new BorderLayout());
            linha.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            JLabel lblTarefa = new JLabel(tarefa);
            lblTarefa.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lblTarefa.setVerticalAlignment(SwingConstants.TOP);
            linha.add(lblTarefa, BorderLayout.WEST);

            JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            JButton btnEditar = new JButton("Editar");
            JButton btnRemover = new JButton("Remover");
            painelBotoes.add(btnEditar);
            painelBotoes.add(btnRemover);
            linha.add(painelBotoes, BorderLayout.EAST);

            int index = i;

            btnEditar.addActionListener(e -> {
                String tarefaAtual = tarefas.get(index);

                if (partes.length == 2) {
                    String tarefaAntiga = partes[0];
                    String statusAntigo = partes[1];

                    Application.campoTarefa.setText(tarefaAntiga);

                    switch (statusAntigo) {
                        case "Não iniciado" -> Application.rdbtnNaoIniciado.setSelected(true);
                        case "Em andamento" -> Application.rdbtnEmAndamento.setSelected(true);
                        case "Concluído" -> Application.rdbtnConcluido.setSelected(true);
                    }

                    Application.lblStatus.setVisible(false);
                    Application.campoTarefa.setVisible(true);
                    Application.painelStatus.setVisible(true);
                    Application.btnConfirmar.setVisible(true);
                    Application.btnConfirmar.setText("Atualizar");

                    for (ActionListener al : Application.btnConfirmar.getActionListeners()) {
                        Application.btnConfirmar.removeActionListener(al);
                    }

                    Application.btnConfirmar.addActionListener(ev -> {
                        String novoTexto = Application.campoTarefa.getText().trim();
                        String novoStatus = Application.rdbtnNaoIniciado.isSelected() ? Application.rdbtnNaoIniciado.getText()
                                : Application.rdbtnEmAndamento.isSelected() ? Application.rdbtnEmAndamento.getText()
                                : Application.rdbtnConcluido.getText();

                        String novaTarefa = novoTexto + "," + novoStatus;
                        tarefas.set(index, novaTarefa);

                        atualizarPainelTarefas(painelTarefas, tarefas);

                        Application.campoTarefa.setText("");
                        Application.campoTarefa.setVisible(false);
                        Application.painelStatus.setVisible(false);
                        Application.btnConfirmar.setVisible(false);
                    });

                } else {
                    System.out.println("Formato inválido para: " + tarefaAtual);
                }
            });

            btnRemover.addActionListener(e -> {
                tarefas.remove(index);
                atualizarPainelTarefas(painelTarefas, tarefas);
            });

            painelTarefas.add(linha);
        }

        painelTarefas.revalidate();
        painelTarefas.repaint();
    }
}
