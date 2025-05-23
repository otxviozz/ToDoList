package show;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import procedures.Inserir;

public class ShowInserir {
	public static void AparecerInserir(JTextField campoTarefa,JRadioButton rdbtnNaoIniciado,JRadioButton rdbtnEmAndamento,JRadioButton rdbtnConcluido,JPanel painelStatus,JLabel lblStatus,JButton btnConfirmar,ArrayList<String> tarefas,JPanel painelTarefas) {

        campoTarefa.setText("");
        campoTarefa.setVisible(true);

        rdbtnNaoIniciado.setSelected(true);
        painelStatus.setVisible(true);

        lblStatus.setVisible(false);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.setVisible(true);

        
        for (ActionListener al : btnConfirmar.getActionListeners()) {
            btnConfirmar.removeActionListener(al);
        }

        
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Inserir.inserirTarefa(campoTarefa, rdbtnNaoIniciado, rdbtnEmAndamento, rdbtnConcluido, tarefas, lblStatus, painelTarefas);

                campoTarefa.setText("");
                campoTarefa.setVisible(false);
                painelStatus.setVisible(false);
                btnConfirmar.setVisible(false);
            }
        });
    }
}
