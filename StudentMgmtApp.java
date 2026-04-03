import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.io.*;
import java.util.List;

public class StudentMgmtApp {
    private String []colName = new String[]
    {"Delete", "ID", "Name","Age", "Course", "CGPA"};
    private static Object[][] studentList = new Object[][]
            {
            {false, 1, "Shubham", 23, "B.Tech", 8.0}
    };
    private DefaultTableModel studentModel;
    private JTable studentTable;

    private StudentMgmtApp()
    {
        studentList = loadStudentsFromFile();
        JFrame jFrame = new JFrame("Student Management Application");
        jFrame.setBounds(100,100,500,300);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        CreateUpdateStudentPanel createUpdateStudentPanel = new CreateUpdateStudentPanel();
        ReadDeleteStudentPanel   readDeleteStudentPanel   = new ReadDeleteStudentPanel();

        tabbedPane.addTab("Create Student", createUpdateStudentPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Read/Delete Student", readDeleteStudentPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        jFrame.setContentPane(tabbedPane);
        jFrame.setVisible(true);
    }
    private static void saveStudentToFile(Object[] studentData) {
        try {
            FileWriter fw = new FileWriter("students.txt", true); // append mode
            BufferedWriter bw = new BufferedWriter(fw);

            // skip checkbox (index 0)
            bw.write(studentData[1] + "," + studentData[2] + "," +
                    studentData[3] + "," + studentData[4] + "," +studentData[5]);

            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object[][] loadStudentsFromFile() {
        List<Object[]> data = new ArrayList<>();

        try {
            File file = new File("students.txt");
            if (!file.exists()) return studentList;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                Object[] row = new Object[]{
                        false,
                        parts[0], // ID
                        parts[1], // Name
                        parts[2], // Age
                        parts[3], // Standard
                        Double.parseDouble(parts[4])  // CGPA
                };

                data.add(row);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][]);
    }

     public static void main(String []arg)
     {
        new StudentMgmtApp();
     }

     private static JTextField addLabelAndTextField(String labelText, int yPos, Container containingPanel)
    {
        JLabel label =new JLabel (labelText);
        GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
        gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
        gridBagConstraintForLabel.insets = new Insets(0,0, 5, 5);
        gridBagConstraintForLabel.gridx = 0;
        gridBagConstraintForLabel.gridy = yPos;
        containingPanel.add(label, gridBagConstraintForLabel);

        JTextField textField = new JTextField();
        GridBagConstraints gridBagConstraintsForTextField = new GridBagConstraints();
        gridBagConstraintsForTextField.fill = GridBagConstraints.BOTH;
        gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 0);
        gridBagConstraintsForTextField.gridx = 1;
        gridBagConstraintsForTextField.gridy = yPos;
        containingPanel.add(textField, gridBagConstraintsForTextField);
        textField.setColumns(10);
        return textField;
    }

    private static JButton addButton (String btnText, int yPos, Container containingPanel){
        JButton button = new JButton(btnText);
        GridBagConstraints gridBagConstraintForButton = new GridBagConstraints();
        gridBagConstraintForButton.fill = GridBagConstraints.NONE;
        gridBagConstraintForButton.anchor = GridBagConstraints.CENTER;
        gridBagConstraintForButton.insets = new Insets(0, 0, 5, 5);
        gridBagConstraintForButton.gridx = 0;
        gridBagConstraintForButton.gridy = yPos;
        containingPanel.add(button, gridBagConstraintForButton);
        return button;
    }

    private static void append(Object[][] array, Object[] value)
    {
        Object[][] result = Arrays.copyOf(array, array.length+1);
        result[result.length-1] = value;
        StudentMgmtApp.studentList = result;
    }

    private static void rewriteFile(DefaultTableModel model) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"));

            for (int i = 0; i < model.getRowCount(); i++) {
                bw.write(model.getValueAt(i,1) + "," +
                        model.getValueAt(i,2) + "," +
                        model.getValueAt(i,3) + "," +
                        model.getValueAt(i,4) + "," +
                        model.getValueAt(i,5));
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class CreateUpdateStudentPanel extends JPanel
    {
        private JTextField idTextField;
        private JTextField nameTextField;
        private JTextField ageTextField;
        private JTextField courseTextField;
        private JTextField cgpaTextField;

        CreateUpdateStudentPanel(){

            Border border = getBorder();
            Border margin = new EmptyBorder(10, 10, 10, 10);
            setBorder(new CompoundBorder(border, margin));

            GridBagLayout panelGridBagLayout = new GridBagLayout();
            panelGridBagLayout.columnWidths  = new int[]{86, 86, 0};
            panelGridBagLayout.rowHeights    = new int[]{20, 20, 20, 20, 20, 0};
            panelGridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
            panelGridBagLayout.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
            setLayout(panelGridBagLayout);

            idTextField   = StudentMgmtApp.addLabelAndTextField("ID :",       0, this);
            nameTextField = StudentMgmtApp.addLabelAndTextField("Name :",     1, this);
            ageTextField  = StudentMgmtApp.addLabelAndTextField("Age :",      2, this);
            courseTextField  = StudentMgmtApp.addLabelAndTextField("Course :", 3, this);
            cgpaTextField = StudentMgmtApp.addLabelAndTextField("CGPA:", 4, this);

            JButton createStudentBtn = StudentMgmtApp. addButton("Create", 5, this);

            createStudentBtn.addActionListener(e -> createStudent());
        }
        private void createStudent()
        {
            String studentId   = idTextField.getText();
            String studentName = nameTextField.getText();
            String studentAge  = ageTextField.getText();
            String studentcourse = courseTextField.getText();
            double studentCgpa;

            try {
                studentCgpa = Double.parseDouble(cgpaTextField.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid CGPA");
                return;
            }

            Object[] studentData = new Object[]{false, studentId, studentName, studentAge, studentcourse,studentCgpa};
            StudentMgmtApp.append(StudentMgmtApp.studentList, studentData);
            studentModel.addRow(studentData);

            StudentMgmtApp.saveStudentToFile(studentData);

            idTextField.setText("");
            nameTextField.setText("");
            ageTextField.setText("");
            courseTextField.setText("");
            cgpaTextField.setText("");

        }
    }

    public class ReadDeleteStudentPanel extends JPanel
    {
        ReadDeleteStudentPanel()
        {
            setPreferredSize(new Dimension(400, 200));

            JButton deleteButton = StudentMgmtApp.addButton("Delete", 0, this);
            deleteButton.addActionListener(e -> deleteStudent());
            studentModel = new DefaultTableModel(StudentMgmtApp.studentList, colName);
            studentTable = new JTable(studentModel)
            {
                @Override
                public Class<?> getColumnClass(int column)
                {
                    switch (column)
                    {
                        case 0:
                            return Boolean.class;
                        case 1:
                            return String.class;
                        case 2:
                            return String.class;
                        case 3:
                            return String.class;
                        case 4:
                            return String.class;
                        case 5:
                            return Double.class;
                        default:
                            return Boolean.class;

                    }
                }
            };

            studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            JScrollPane pane = new JScrollPane(studentTable);
            add(pane, BorderLayout.CENTER);
        }
        private void deleteStudent()
        {
            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
            for(int i=0; i<model.getRowCount(); i++)
            {
                Boolean checked = (Boolean) model.getValueAt(i,0);
                if(checked !=null && checked)
                {
                    model.removeRow(i);
                    i--;
                }
            }
            StudentMgmtApp.rewriteFile(model);
        }
    }
}

