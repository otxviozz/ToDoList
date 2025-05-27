package show;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class ShowInserir {
    public static void AparecerInserir(JTextField campoTarefa, JRadioButton rdbtnNaoIniciado,
                                       JRadioButton rdbtnEmAndamento, JRadioButton rdbtnConcluido,
                                       JPanel painelStatus, JLabel lblStatus, JButton btnConfirmar,
                                       ArrayList<String> tarefas, JPanel painelTarefas) {

        campoTarefa.setText("");
        campoTarefa.setVisible(true);

        rdbtnNaoIniciado.setSelected(true);
        painelStatus.setVisible(true);

        lblStatus.setVisible(false);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.setVisible(true);
    }
}
