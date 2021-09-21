package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;


public class Price extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Price frame = new Price();
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
	public Price() {
		setTitle("Card\u00E1pio: carrinho");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JPanel panel = new JPanel();
		panel.setBounds(300, 0, 308, 308);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel priceEl = new JLabel("R$ 0");
		priceEl.setForeground(Color.ORANGE);
		priceEl.setBackground(Color.WHITE);
		priceEl.setHorizontalAlignment(SwingConstants.CENTER);
		priceEl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		priceEl.setBounds(95, 95, 117, 78);
		panel.add(priceEl);

		
		table = new JTable();
		
		JButton btnNewButton = new JButton("Calcular pre\u00E7o");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int items = (int) (table.getModel().getRowCount());
				
				double[] infoPrice = new double[items];
				// quantidade selecionados
				int[] selectPrice = new int[items];
							
				// seta ids selecionados
				for(int i = 0; i < items; i++) {
					infoPrice[i] = (double) table.getModel().getValueAt(i, 2);
					selectPrice[i] = (int) table.getModel().getValueAt(i, 3);
				}
				
				double total = 0;
				for(int i = 0; i < items; i++) {
					total += infoPrice[i] * (double) selectPrice[i];
				}
				
				if(total > 0) {
					priceEl.setText(String.format("R$ %.2f", total));
				}
				
			}
		});
		btnNewButton.setBounds(79, 274, 154, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Selecione a quantidade");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 42, 288, 33);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 302, 308);
		contentPane.add(scrollPane_1);
		
		ItemController cardapio = new ItemController();
		
		// merge arrays
		String[] title = Stream.concat(Arrays.stream(cardapio.titles()), Arrays.stream(new String[] {"Quantidade"})).toArray(String[]::new);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "X-Bacon", new Double(20.0), new Integer(0)},
				{"2", "X-Salada", new Double(15.0), new Integer(0)},
				{"3", "X-Picanha", new Double(22.0), new Integer(0)},
				{"4", "X-Tudo", new Double(30.0), new Integer(0)},
			},
			new String[] {
				"ID", "Lanche", "Pre\u00E7o (R$)", "Quantidade"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Double.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane_1.setViewportView(table);
		
		
	}
}
