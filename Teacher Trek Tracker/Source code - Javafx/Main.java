package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    // ObservableList to hold student names
    private ObservableList<String> students;
    // TextArea for displaying reports
    private TextArea reportTextArea;

    /**
     * Main entry point for the JavaFX application.
     * Initializes the GUI components and sets up event handling.
     *
     * @param primaryStage the primary stage for this application
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Teacher Trek Tracker");

        // Initialize the list of students
        students = FXCollections.observableArrayList("Student1", "Student2", "Student3");

        // ListView for displaying the list of students
        ListView<String> studentListView = new ListView<>(students);
        reportTextArea = new TextArea();
        reportTextArea.setEditable(false);

        // Button to generate a report for the selected student
        Button generateReportButton = new Button("Generate Report");

        // Event handler for the Generate Report button
        generateReportButton.setOnAction(event -> generateReport(studentListView.getSelectionModel().getSelectedItem()));

        // TextField and Button for adding a new student
        TextField addStudentTextField = new TextField();
        Button addStudentButton = new Button("Add Student");

        // Event handler for the Add Student button
        addStudentButton.setOnAction(event -> {
            String newStudent = addStudentTextField.getText();
            if (!newStudent.isEmpty()) {
                students.add(newStudent);
                addStudentTextField.clear();
            }
        });

        // TextField and Button for editing the selected student
        TextField editStudentTextField = new TextField();
        Button editStudentButton = new Button("Edit Student");

        // Event handler for the Edit Student button
        editStudentButton.setOnAction(event -> {
            String editedName = editStudentTextField.getText();
            if (!editedName.isEmpty() && studentListView.getSelectionModel().getSelectedItem() != null) {
                int selectedIndex = studentListView.getSelectionModel().getSelectedIndex();
                students.set(selectedIndex, editedName);
                editStudentTextField.clear();
            }
        });

        // Button to delete the selected student
        Button deleteStudentButton = new Button("Delete Student");

        // Event handler for the Delete Student button
        deleteStudentButton.setOnAction(event -> {
            if (studentListView.getSelectionModel().getSelectedItem() != null) {
                students.remove(studentListView.getSelectionModel().getSelectedItem());
            }
        });

        // Button to clear the report text area
        Button clearReportButton = new Button("Clear Report");

        // Event handler for the Clear Report button
        clearReportButton.setOnAction(event -> reportTextArea.clear());

        // Layout configuration
        HBox topBox = new HBox(
                new Label("Select Student: "), studentListView, generateReportButton,
                new Label("Add/Edit Student: "), addStudentTextField, addStudentButton,
                new Label("Edit Student: "), editStudentTextField, editStudentButton,
                deleteStudentButton, clearReportButton
        );
        topBox.setSpacing(10);
        topBox.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topBox);
        borderPane.setCenter(reportTextArea);

        // Scene configuration
        Scene scene = new Scene(borderPane, 700, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Generates a report for the selected student and displays it in the reportTextArea.
     *
     * @param selectedStudent the name of the selected student
     */
    private void generateReport(String selectedStudent) {
        if (selectedStudent != null) {
            // Generate a sample report for the selected student
            String report = "Report for " + selectedStudent + ":\n\n"
                    + "Test 1: A\n"
                    + "Test 2: B\n"
                    + "Test 3: A+\n"
                    + "Overall Performance: Excellent";

            reportTextArea.setText(report);
        } else {
            reportTextArea.setText("Please select a student to generate a report.");
        }
    }

    /**
     * The main method launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
