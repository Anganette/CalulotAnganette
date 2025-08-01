package DashBoard;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class Index extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idField, nameField, emailField, courseField, searchField;
	private JTable table;
	private DefaultTableModel tableModel;
	private int selectedRow = -1;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Index frame = new Index();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 651);
		setTitle("Student Management");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(menuFile);

		JMenuItem menuLogout = new JMenuItem("Logout");
		menuFile.add(menuLogout);
		menuLogout.addActionListener(e -> {
			int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				dispose();
				new Login().main(null);
			}
		});

		JMenu menuHelp = new JMenu("Help");
		menuHelp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(menuHelp);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("About");
		menuHelp.add(mntmNewMenuItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId.setBounds(51, 63, 46, 18);
		contentPane.add(lblId);

		idField = new JTextField();
		idField.setBounds(100, 63, 97, 20);
		contentPane.add(idField);
		idField.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setBounds(51, 125, 46, 18);
		contentPane.add(lblName);

		nameField = new JTextField();
		nameField.setBounds(100, 123, 221, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);

		JLabel lblGmail = new JLabel("Gmail:");
		lblGmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGmail.setBounds(51, 158, 46, 18);
		contentPane.add(lblGmail);

		emailField = new JTextField();
		emailField.setBounds(100, 156, 221, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);

		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCourse.setBounds(51, 192, 63, 18);
		contentPane.add(lblCourse);

		courseField = new JTextField();
		courseField.setBounds(100, 190, 221, 20);
		contentPane.add(courseField);
		courseField.setColumns(10);

		JLabel lblSearchName = new JLabel("Search name:");
		lblSearchName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSearchName.setBounds(34, 249, 91, 18);
		contentPane.add(lblSearchName);

		searchField = new JTextField();
		searchField.setBounds(119, 249, 97, 20);
		contentPane.add(searchField);
		searchField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(244, 241, 86, 34);
		contentPane.add(btnSearch);

		btnSearch.addActionListener(e -> {
			String searchName = searchField.getText().toLowerCase();
			for (int i = 0; i < tableModel.getRowCount(); i++) {
				String name = tableModel.getValueAt(i, 1).toString().toLowerCase();
				if (name.contains(searchName)) {
					table.setRowSelectionInterval(i, i);
					return;
				}
			}
			JOptionPane.showMessageDialog(this, "No record found!");
		});

		
		String[] columnNames = {"ID", "Name", "Gmail", "Course"};
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 329, 671, 242);
		contentPane.add(scrollPane);

		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.setBounds(34, 286, 86, 34);
		contentPane.add(btnAdd);

		btnAdd.addActionListener(e -> {
			String id = idField.getText();
			String name = nameField.getText();
			String email = emailField.getText();
			String course = courseField.getText();
			if (id.isEmpty() || name.isEmpty() || email.isEmpty() || course.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill all fields.");
				return;
			}
			tableModel.addRow(new Object[]{id, name, email, course});
			clearFields();
		});

		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBounds(129, 286, 85, 34);
		contentPane.add(btnDelete);

		btnDelete.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (row != -1) {
				tableModel.removeRow(row);
				clearFields();
			} else {
				JOptionPane.showMessageDialog(this, "Please select a row to delete.");
			}
		});

		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEdit.setBounds(244, 286, 86, 34);
		contentPane.add(btnEdit);

		btnEdit.addActionListener(e -> {
			selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				idField.setText(tableModel.getValueAt(selectedRow, 0).toString());
				nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
				emailField.setText(tableModel.getValueAt(selectedRow, 2).toString());
				courseField.setText(tableModel.getValueAt(selectedRow, 3).toString());
			} else {
				JOptionPane.showMessageDialog(this, "Please select a row to edit.");
			}
		});

		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnUpdate.setBounds(338, 285, 85, 33);
		contentPane.add(btnUpdate);

		btnUpdate.addActionListener(e -> {
			if (selectedRow != -1) {
				tableModel.setValueAt(idField.getText(), selectedRow, 0);
				tableModel.setValueAt(nameField.getText(), selectedRow, 1);
				tableModel.setValueAt(emailField.getText(), selectedRow, 2);
				tableModel.setValueAt(courseField.getText(), selectedRow, 3);
				selectedRow = -1;
				clearFields();
			} else {
				JOptionPane.showMessageDialog(this, "No row selected to update.");
			}
		});

		
		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnPrint.setBounds(447, 286, 86, 34);
		contentPane.add(btnPrint);

		btnPrint.addActionListener(e -> {
			try {
				boolean complete = table.print();
				if (complete) {
					JOptionPane.showMessageDialog(this, "Printing Complete");
				} else {
					JOptionPane.showMessageDialog(this, "Printing Cancelled");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Print Failed: " + ex.getMessage());
			}
		});
	}

	private void clearFields() {
		idField.setText("");
		nameField.setText("");
		emailField.setText("");
		courseField.setText("");
	}
}
