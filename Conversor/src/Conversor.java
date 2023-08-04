import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Conversor {

	private JFrame frame;
	private JButton btn;
	private JComboBox cmb;
	private JLabel lblNewLabel;
	
	public enum Moneda{
		pesos_dolar,
		pesos_euro,
		pesos_libra,
		pesos_yen,
		pesos_won,
		dolar_pesos,
		euro_pesos,
		libra_pesos,
		yen_pesos,
		won_pesos
	}
	
	public double dolar = 278;
	public double euro = 303;
	public double libra = 320;
	public double yen = 2;
	public double won = 4.7;
	
	public double valorInput = 0.00;
	private JTextField text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversor window = new Conversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Conversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 204));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		text = new JTextField();
		text.setFont(new Font("Tahoma", Font.PLAIN, 15));
		text.setBounds(20, 32, 205, 58);
		frame.getContentPane().add(text);
		text.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel(Moneda.values()));
		cmb.setBounds(20, 136, 205, 35);
		frame.getContentPane().add(cmb);
		
		//evento botn
		btn = new JButton("Convertir");
		btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(256, 134, 168, 37);
		frame.getContentPane().add(btn);
		
		lblNewLabel = new JLabel("00.00");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(285, 45, 98, 45);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public void Convertir() {
		if(Validar(text.getText())) {			
			Moneda moneda = (Moneda) cmb.getSelectedItem();
			
			switch (moneda) {
			case pesos_dolar:
				PesosAMoneda(dolar);
			break;
			case pesos_euro:
				PesosAMoneda(euro);
			break;
			case pesos_libra:
				PesosAMoneda(libra);
			break;
			case pesos_yen:
				PesosAMoneda(yen);
			break;
			case pesos_won:
				PesosAMoneda(won);
			break;
			case dolar_pesos:
				MonedaAPesos(dolar);
			break;
			case euro_pesos:
				MonedaAPesos(euro);
			break;
			case libra_pesos:
				MonedaAPesos(libra);
			break;
			case yen_pesos:
				MonedaAPesos(yen);
			break;
			case won_pesos:
				MonedaAPesos(won);
			break;
				default:
					throw new IllegalArgumentException("Unexceted value: " + moneda);			
			
		}

		}
	}
	
	public void PesosAMoneda(double moneda) {
		double res = valorInput / moneda;
		lblNewLabel.setText(Redondear(res));
	}
	
	public void MonedaAPesos(double moneda) {
		double res = valorInput * moneda;
		lblNewLabel.setText(Redondear(res));
	}
	
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if(x > 0);
			valorInput = x;
			return true;
		}catch(NumberFormatException e) {
			lblNewLabel.setText("Solamente números!");
			return false;
		}
	}
}
