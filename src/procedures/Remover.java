package procedures;

import java.util.ArrayList;
import procedures.AtualizarPainel;
import javax.swing.JPanel;

public class Remover {
	public static void removertarefa(int index, ArrayList<String> tarefas, JPanel painelTarefas) {
		tarefas.remove(index);
		AtualizarPainel.atualizarPainelTarefas(painelTarefas, tarefas);
	}
}
