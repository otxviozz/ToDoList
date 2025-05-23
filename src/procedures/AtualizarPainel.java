package procedures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import main.Application;

public class AtualizarPainel {
	public static void atualizarPainelTarefas(JPanel painelTarefas, ArrayList<String> tarefas) {
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
                Editar.BtnEditar(index, Application.tarefas, painelTarefas);
            });

            btnRemover.addActionListener(e -> {
                Remover.removertarefa(index, Application.tarefas, painelTarefas);
            });

            painelTarefas.add(linha);
        }

        painelTarefas.revalidate();
        painelTarefas.repaint();
    }
}
