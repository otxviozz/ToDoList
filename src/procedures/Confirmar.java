package procedures;

import java.util.ArrayList;

import javax.swing.JPanel;

import main.Application;

public class Confirmar {
	public static void confirmarbotaoedit(int index, ArrayList<String> tarefas, JPanel painelTarefas) {
		String novoTexto = Application.campoTarefa.getText().trim();
	    String novoStatus = Application.rdbtnNaoIniciado.isSelected() ? Application.rdbtnNaoIniciado.getText()
	            : Application.rdbtnEmAndamento.isSelected() ? Application.rdbtnEmAndamento.getText()
	            : Application.rdbtnConcluido.getText();

	    String novaTarefa = novoTexto + "," + novoStatus;
	    tarefas.set(index, novaTarefa);

	    AtualizarPainel.atualizarPainelTarefas(painelTarefas, tarefas);

	    Application.campoTarefa.setText("");
	    Application.campoTarefa.setVisible(false);
	    Application.painelStatus.setVisible(false);
	    Application.btnConfirmar.setVisible(false);
}
}
