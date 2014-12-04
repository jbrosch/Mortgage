package main;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.JInternalFrame;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JSpinner interestSpinner;
	private JComboBox comboBox;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					frame.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lblGrossIncome = new JLabel("Gross Income");
		panel.add(lblGrossIncome, "4, 2, center, center");

		textField = new JTextField();
		textField.setText("0");
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				try{
					change(e);
				}catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					change(e,0);
					// e1.printStackTrace();
				}
			}
			public void removeUpdate(DocumentEvent e) {
				try{
					change(e);
				}catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					change(e,0);
					// e1.printStackTrace();
				}
			}
			public void insertUpdate(DocumentEvent e) {
				try{
					change(e);
				}catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					change(e,0);
					//e1.printStackTrace();
				}
			}
			public void change(DocumentEvent e) throws NumberFormatException{
				double income = Double.parseDouble(textField.getText());
				double debt = Double.parseDouble(textField_1.getText());
				double interest = ((double) ((Integer) interestSpinner.getValue()))*.01;
				int years = Integer.parseInt(comboBox.getSelectedItem().toString());
				double down = Double.parseDouble(textField_2.getText());
				double house = calculator.houseOnly(income);
				double obligations = calculator.houseObligations(income, debt);
				double maxAllowed = calculator.allowedPayment(income, debt);
				double presentValue = calculator.PV(maxAllowed, interest, years) + down;
				DecimalFormat df = new DecimalFormat("#.##");
				label.setText(String.valueOf(df.format(house)));
				label_1.setText(String.valueOf(df.format(obligations)));
				label_2.setText(String.valueOf(df.format(maxAllowed)));
				label_3.setText(String.valueOf(df.format(presentValue)));

			}
			@SuppressWarnings("unused")
			public void change(DocumentEvent e, double workAround){
				double income = 0;
				double debt = 0;
				double interest = (double) ((Integer) interestSpinner.getValue());
				int years = Integer.parseInt(comboBox.getSelectedItem().toString());
				double down = 0;
				double house = calculator.houseOnly(income);
				double obligations = calculator.houseObligations(income, debt);
				double maxAllowed = calculator.allowedPayment(income, debt);
				double presentValue = calculator.PV(income, interest, years);
				DecimalFormat df = new DecimalFormat("#.##");
				label.setText(String.valueOf(df.format(house)));
				label_1.setText(String.valueOf(df.format(obligations)));
				label_2.setText(String.valueOf(df.format(maxAllowed)));
				label_3.setText(String.valueOf(df.format(presentValue + down)));
			}

		});
		panel.add(textField, "8, 2, fill, center");
		textField.setColumns(10);

		JLabel lblMonthlyDebt = new JLabel("Monthly Debt");
		panel.add(lblMonthlyDebt, "4, 4, center, center");

		textField_1 = new JTextField();
		textField_1.setText("0");
		panel.add(textField_1, "8, 4, fill, center");
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				try{
					change(e);
				}catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					change(e,0);
					// e1.printStackTrace();
				}
			}
			public void removeUpdate(DocumentEvent e) {
				try{
					change(e);
				}catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					change(e,0);
					// e1.printStackTrace();
				}
			}
			public void insertUpdate(DocumentEvent e) {
				try{
					change(e);
				}catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					change(e,0);
					//e1.printStackTrace();
				}
			}
			public void change(DocumentEvent e) throws NumberFormatException{
				double income = Double.parseDouble(textField.getText());
				double debt = Double.parseDouble(textField_1.getText());
				double interest = ((double) ((Integer) interestSpinner.getValue()))*.01;
				int years = Integer.parseInt(comboBox.getSelectedItem().toString());
				double down = Double.parseDouble(textField_2.getText());
				double house = calculator.houseOnly(income);
				double obligations = calculator.houseObligations(income, debt);
				double maxAllowed = calculator.allowedPayment(income, debt);
				double presentValue = calculator.PV(maxAllowed, interest, years) + down;
				DecimalFormat df = new DecimalFormat("#.##");
				label.setText(String.valueOf(df.format(house)));
				label_1.setText(String.valueOf(df.format(obligations)));
				label_2.setText(String.valueOf(df.format(maxAllowed)));
				label_3.setText(String.valueOf(df.format(presentValue)));

			}
			@SuppressWarnings("unused")
			public void change(DocumentEvent e, double workAround){
				double income = Double.parseDouble(textField.getText());
				double debt = 0;
				double interest = (double) ((Integer) interestSpinner.getValue());
				int years = Integer.parseInt(comboBox.getSelectedItem().toString());
				double down = Double.parseDouble(textField_2.getText());
				double house = calculator.houseOnly(income);
				double obligations = calculator.houseObligations(income, debt);
				double maxAllowed = calculator.allowedPayment(income, debt);
				double presentValue = calculator.PV(income, interest, years);
				DecimalFormat df = new DecimalFormat("#.##");
				label.setText(String.valueOf(df.format(house)));
				label_1.setText(String.valueOf(df.format(obligations)));
				label_2.setText(String.valueOf(df.format(maxAllowed)));
				label_3.setText(String.valueOf(df.format(presentValue + down)));
			}

		});
		textField_1.setColumns(10);

		JLabel lblMonthlyInterest = new JLabel("Monthly Interest");
		panel.add(lblMonthlyInterest, "4, 6, center, center");

		interestSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100,1));
		interestSpinner.setToolTipText("0");
		interestSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				double income = Double.parseDouble(textField.getText());
				double debt = Double.parseDouble(textField_1.getText());
				double interest = ((double) ((Integer) interestSpinner.getValue()))*.01;
				int years = Integer.parseInt(comboBox.getSelectedItem().toString());
				double down = Double.parseDouble(textField_2.getText());
				double house = calculator.houseOnly(income);
				double obligations = calculator.houseObligations(income, debt);
				double maxAllowed = calculator.allowedPayment(income, debt);
				double presentValue = calculator.PV(maxAllowed, interest, years) + down;
				DecimalFormat df = new DecimalFormat("#.##");
				label.setText(String.valueOf(df.format(house)));
				label_1.setText(String.valueOf(df.format(obligations)));
				label_2.setText(String.valueOf(df.format(maxAllowed)));
				label_3.setText(String.valueOf(df.format(presentValue)));
			}
		});
		panel.add(interestSpinner, "8, 6, center, center");


		JLabel lblTerm = new JLabel("Term");
		panel.add(lblTerm, "4, 8, center, center");

		Integer[] term = new Integer[]{10, 15, 30};
		comboBox = new JComboBox(term);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				double income = Double.parseDouble(textField.getText());
				double debt = Double.parseDouble(textField_1.getText());
				double interest = ((double) ((Integer) interestSpinner.getValue()))*.01;
				int years = Integer.parseInt(comboBox.getSelectedItem().toString());
				double down = Double.parseDouble(textField_2.getText());
				double house = calculator.houseOnly(income);
				double obligations = calculator.houseObligations(income, debt);
				double maxAllowed = calculator.allowedPayment(income, debt);
				double presentValue = calculator.PV(maxAllowed, interest, years) + down;
				DecimalFormat df = new DecimalFormat("#.##");
				label.setText(String.valueOf(df.format(house)));
				label_1.setText(String.valueOf(df.format(obligations)));
				label_2.setText(String.valueOf(df.format(maxAllowed)));
				label_3.setText(String.valueOf(df.format(presentValue)));
			}
		});
		panel.add(comboBox, "8, 8, fill, center");

		JLabel lblDownPaymnet = new JLabel("Down Paymnet");
		panel.add(lblDownPaymnet, "4, 10, center, center");

		textField_2 = new JTextField();
		textField_2.setText("0");
		panel.add(textField_2, "8, 10, fill, center");
		textField_2.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				try{
					change(e);
				}catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					change(e,0);
					// e1.printStackTrace();
				}
			}
			public void removeUpdate(DocumentEvent e) {
				try{
					change(e);
				}catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					change(e,0);
					// e1.printStackTrace();
				}
			}
			public void insertUpdate(DocumentEvent e) {
				try{
					change(e);
				}catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					change(e,0);
					//e1.printStackTrace();
				}
			}
			public void change(DocumentEvent e) throws NumberFormatException{
				double income = Double.parseDouble(textField.getText());
				double debt = Double.parseDouble(textField_1.getText());
				double interest = ((double) ((Integer) interestSpinner.getValue()))*.01;
				int years = Integer.parseInt(comboBox.getSelectedItem().toString());
				double down = Double.parseDouble(textField_2.getText());
				double house = calculator.houseOnly(income);
				double obligations = calculator.houseObligations(income, debt);
				double maxAllowed = calculator.allowedPayment(income, debt);
				double presentValue = calculator.PV(maxAllowed, interest, years) + down;
				DecimalFormat df = new DecimalFormat("#.##");
				label.setText(String.valueOf(df.format(house)));
				label_1.setText(String.valueOf(df.format(obligations)));
				label_2.setText(String.valueOf(df.format(maxAllowed)));
				label_3.setText(String.valueOf(df.format(presentValue)));

			}
			@SuppressWarnings("unused")
			public void change(DocumentEvent e, double workAround){
				double income = Double.parseDouble(textField.getText());
				double debt = Double.parseDouble(textField_1.getText());
				double interest = (double) ((Integer) interestSpinner.getValue());
				int years = Integer.parseInt(comboBox.getSelectedItem().toString());
				double down = 0;
				double house = calculator.houseOnly(income);
				double obligations = calculator.houseObligations(income, debt);
				double maxAllowed = calculator.allowedPayment(income, debt);
				double presentValue = calculator.PV(income, interest, years);
				DecimalFormat df = new DecimalFormat("#.##");
				label.setText(String.valueOf(df.format(house)));
				label_1.setText(String.valueOf(df.format(obligations)));
				label_2.setText(String.valueOf(df.format(maxAllowed)));
				label_3.setText(String.valueOf(df.format(presentValue + down)));
			}

		});
		textField_2.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		panel.add(btnUpdate, "8, 12, center, center");

		JLabel lblHousingPaymnetOnly = new JLabel("Housing Paymnet Only");
		panel.add(lblHousingPaymnetOnly, "4, 14, center, center");

		label = new JLabel("0");
		panel.add(label, "8, 14, center, center");

		JLabel lblHousingPayment = new JLabel("Housing Payment + Obligations");
		panel.add(lblHousingPayment, "4, 16, center, center");

		label_1 = new JLabel("0");
		panel.add(label_1, "8, 16, center, center");

		JLabel lblMaximumPaymentAllowed = new JLabel("Maximum Payment Allowed");
		panel.add(lblMaximumPaymentAllowed, "4, 18, center, center");

		label_2 = new JLabel("0");
		panel.add(label_2, "8, 18, center, center");

		JLabel lblFinancedAmount = new JLabel("Financed Amount");
		panel.add(lblFinancedAmount, "4, 20, center, center");

		label_3 = new JLabel("0");
		panel.add(label_3, "8, 20, center, center");
	}

}
