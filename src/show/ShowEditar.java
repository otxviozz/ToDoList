package show;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Application;

public class ShowEditar {
	public static void ShowBtnEditar(JLabel lblStatus, JTextField campoTarefa, JPanel painelStatus, JButton btnConfirmar) {
	Application.lblStatus.setVisible(false);
    Application.campoTarefa.setVisible(true);
    Application.painelStatus.setVisible(true);
    Application.btnConfirmar.setVisible(true);
    Application.btnConfirmar.setText("Atualizar");
}
}
