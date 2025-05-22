package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String path = "C:\\Users\\Aluno\\Desktop\\repositorio\\ToDoList\\dados\\tarefas.csv";
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
		tarefas.add("Tarefas a serem feitas, Status");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnNaoIniciado = new JRadioButton("Não iniciado");
		rdbtnNaoIniciado.setBounds(0, 0, 120, 40); 
		JRadioButton rdbtnEmAndamento = new JRadioButton("Em andamento");
		rdbtnEmAndamento.setBounds(130, 0, 130, 40);
		JRadioButton rdbtnConcluido = new JRadioButton("Concluído");
		rdbtnConcluido.setBounds(270, 0, 100, 40);

		ButtonGroup grupoStatus = new ButtonGroup();
		grupoStatus.add(rdbtnNaoIniciado);
		grupoStatus.add(rdbtnEmAndamento);
		grupoStatus.add(rdbtnConcluido);

		JPanel painelStatus = new JPanel();
		painelStatus.setLayout(null);
		painelStatus.setBounds(48, 179, 415, 42);

		painelStatus.add(rdbtnNaoIniciado);
		painelStatus.add(rdbtnEmAndamento);
		painelStatus.add(rdbtnConcluido);
		painelStatus.setVisible(false);
		contentPane.add(painelStatus);
		
		JTextField campoTarefa = new JTextField();
		campoTarefa.setBounds(48, 127, 415, 30);
		campoTarefa.setVisible(false);
		contentPane.add(campoTarefa);

		JLabel lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStatus.setBounds(48, 153, 415, 42);
		lblStatus.setVisible(false);
		contentPane.add(lblStatus);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String statusSelecionado = "";

				if (rdbtnNaoIniciado.isSelected()) {
				    statusSelecionado = rdbtnNaoIniciado.getText();
				} else if (rdbtnEmAndamento.isSelected()) {
				    statusSelecionado = rdbtnEmAndamento.getText();
				} else if (rdbtnConcluido.isSelected()) {
				    statusSelecionado = rdbtnConcluido.getText();
				}

				String inserir = campoTarefa.getText().trim() + ", " + statusSelecionado;

				if (!inserir.isEmpty() && !inserir.equals("Insira a tarefa")) {
					tarefas.add(inserir);

					System.out.println("Tarefa adicionada: " + inserir);
					lblStatus.setVisible(true);
					lblStatus.setText("Tarefa adicionada com sucesso!");
					

					campoTarefa.setText("");
					campoTarefa.setVisible(false);
					painelStatus.setVisible(false);
					btnConfirmar.setVisible(false);
				} else {
					javax.swing.JOptionPane.showMessageDialog(null, "Digite uma tarefa válida.");
				}
			}
		});
		btnConfirmar.setBounds(516, 151, 150, 30);
		btnConfirmar.setVisible(false);
		contentPane.add(btnConfirmar);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoTarefa.setVisible(true);
				painelStatus.setVisible(true);
				lblStatus.setVisible(false);
				btnConfirmar.setVisible(true);
				btnConfirmar.setText("Inserir");
			}
		});
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInserir.setBounds(139, 64, 100, 40);
		contentPane.add(btnInserir);
		
		JLabel lblNewLabel = new JLabel("To Do List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(200, 14, 281, 39);
		contentPane.add(lblNewLabel);
		
		JButton btnCarregar = new JButton("Carregar");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int resultado = fileChooser.showOpenDialog(null);

				if (resultado == JFileChooser.APPROVE_OPTION) {
					File arquivoSelecionado = fileChooser.getSelectedFile();
					String caminhoArquivo = arquivoSelecionado.getAbsolutePath();
					path = caminhoArquivo;

					System.out.println("Arquivo escolhido: " + caminhoArquivo);
					lblStatus.setVisible(true);
					lblStatus.setText("Arquivo carregado com sucesso!");

					FileReader fr = null;
					BufferedReader br = null;

					try {
						fr = new FileReader(path);
						br = new BufferedReader(fr);
						tarefas.clear();

						String linha = br.readLine();
						while (linha != null) {
							System.out.println(linha);
							tarefas.add(linha);
							linha = br.readLine();
						}
					} catch (IOException ex) {
						System.out.println("Erro: " + ex.getMessage());
					} finally {
						try {
							if (fr != null) fr.close();
							if (br != null) br.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});
		btnCarregar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCarregar.setBounds(302, 64, 100, 40);
		contentPane.add(btnCarregar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
		            for (String linha : tarefas) {
		                bw.write(linha);
		                bw.newLine();
		            }
		            javax.swing.JOptionPane.showMessageDialog(null, "Tarefas salvas com sucesso!");
		            lblStatus.setVisible(false);
					lblStatus.setText("");
		        } catch (IOException ex) {
		            System.out.println("Erro ao salvar: " + ex.getMessage());
		            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao salvar tarefas.");
		        }
		    }
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.setBounds(464, 64, 100, 40);
		contentPane.add(btnSalvar);

		
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
		scrollPane.setBounds(48, 250, 646, 282);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		contentPane.add(scrollPane);
		
	}
}
