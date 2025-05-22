package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String path = "C:\\Users\\eletr\\eclipse-workspace\\ToDoListJava\\dados\\tarefas.csv";
	ArrayList<String> tarefas = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Application() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInserir.setBounds(139, 64, 100, 40);
		contentPane.add(btnInserir);
		
		JLabel lblNewLabel = new JLabel("To Do List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(199, 11, 281, 39);
		contentPane.add(lblNewLabel);
		
		JButton btnCarregar = new JButton("Carregar");
		btnCarregar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCarregar.setBounds(302, 64, 100, 40);
		contentPane.add(btnCarregar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.setBounds(464, 64, 100, 40);
		contentPane.add(btnSalvar);
		
		JTextField campoTarefa = new JTextField();
		campoTarefa.setBounds(48, 127, 486, 30);
		campoTarefa.setVisible(false);
		contentPane.add(campoTarefa);

		JButton botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setBounds(544, 127, 150, 30);
		botaoConfirmar.setVisible(false);
		contentPane.add(botaoConfirmar);
		
		JButton btnSair = new JButton("SAIR");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setBounds(605, 11, 89, 23);
		contentPane.add(btnSair);
		
		JPanel painelTarefas = new JPanel();
		painelTarefas.setLayout(new BoxLayout(painelTarefas, BoxLayout.Y_AXIS));
		painelTarefas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JScrollPane scrollPane = new JScrollPane(painelTarefas);
		scrollPane.setBounds(48, 168, 646, 282);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		contentPane.add(scrollPane);
	}
}
