package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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


import procedures.AtualizarPainel;
import procedures.Inserir;
import procedures.Carregar;
import procedures.Salvar;
import procedures.Editar;
import procedures.Remover;

import show.ShowInserir;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static JTextField campoTarefa;
	public static JButton btnConfirmar;
	public static JRadioButton rdbtnNaoIniciado;
	public static JRadioButton rdbtnEmAndamento;
	public static JRadioButton rdbtnConcluido;
	public static JPanel painelStatus;
	public static JLabel lblStatus;
	public static String path = "";
	public static ArrayList<String> tarefas = new ArrayList<>();
	
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
		
		JPanel painelTarefas = new JPanel();
		painelTarefas.setLayout(new BoxLayout(painelTarefas, BoxLayout.Y_AXIS));
		painelTarefas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JScrollPane scrollPane = new JScrollPane(painelTarefas);
		scrollPane.setBounds(48, 250, 646, 282);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);
		
		rdbtnNaoIniciado = new JRadioButton("Não iniciado");
		rdbtnNaoIniciado.setBounds(0, 0, 120, 40); 
		rdbtnEmAndamento = new JRadioButton("Em andamento");
		rdbtnEmAndamento.setBounds(130, 0, 130, 40);
		rdbtnConcluido = new JRadioButton("Concluído");
		rdbtnConcluido.setBounds(270, 0, 100, 40);

		ButtonGroup grupoStatus = new ButtonGroup();
		grupoStatus.add(rdbtnNaoIniciado);
		grupoStatus.add(rdbtnEmAndamento);
		grupoStatus.add(rdbtnConcluido);

		painelStatus = new JPanel();
		painelStatus.setLayout(null);
		painelStatus.setBounds(48, 179, 415, 42);
		painelStatus.add(rdbtnNaoIniciado);
		painelStatus.add(rdbtnEmAndamento);
		painelStatus.add(rdbtnConcluido);
		painelStatus.setVisible(false);
		contentPane.add(painelStatus);
		
		campoTarefa = new JTextField();
		campoTarefa.setBounds(48, 127, 415, 30);
		campoTarefa.setVisible(false);
		contentPane.add(campoTarefa);

		lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStatus.setBounds(48, 153, 415, 42);
		lblStatus.setVisible(false);
		contentPane.add(lblStatus);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(516, 151, 150, 30);
		btnConfirmar.setVisible(false);
		AtualizarPainel.atualizarPainelTarefas(painelTarefas, tarefas);
		contentPane.add(btnConfirmar);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInserir.setBounds(139, 64, 100, 40);
		contentPane.add(btnInserir);
		
		JLabel lblNewLabel = new JLabel("To Do List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(200, 14, 281, 39);
		contentPane.add(lblNewLabel);
		
		JButton btnCarregar = new JButton("Carregar");
		btnCarregar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCarregar.setBounds(302, 64, 100, 40);
		contentPane.add(btnCarregar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.setBounds(464, 64, 100, 40);
		contentPane.add(btnSalvar);

		
		JButton btnSair = new JButton("SAIR");
		btnSair.setBounds(605, 11, 89, 23);
		contentPane.add(btnSair);
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnConfirmar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		btnInserir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ShowInserir.AparecerInserir(campoTarefa, rdbtnNaoIniciado, rdbtnEmAndamento, rdbtnConcluido, painelStatus, lblStatus, btnConfirmar, tarefas, painelTarefas);
		    }
		});
		
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		    }
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		    }
		});
	}
}
