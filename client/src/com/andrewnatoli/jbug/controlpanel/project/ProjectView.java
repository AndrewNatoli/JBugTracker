package com.andrewnatoli.jbug.controlpanel.project;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectView extends JPanel{
    protected JPanel projectForm;

    protected String frameTitle;
    protected String btnText;

    protected JLabel     label_projectName;
    protected JTextField input_projectName;

    protected JButton   btn_submit;
    protected ProjectModel project;

    /**
     * Constructor to edit an existing project
     * @param id
     */
    public ProjectView(int id) {
        System.out.println("[ProjectView] Loading project " + id + "...");
        frameTitle  = "Edit Project";
        btnText     = "Save Changes";
        project     = new ProjectModel(id);
        buildLayout();
    }

    /**
     * Constructor to create a new project
     */
    public ProjectView() {
        frameTitle  = "Add Project";
        btnText     = "Create Project";
        project     = new ProjectModel();
        buildLayout();
    }

    /**
     * buildLayout()
     */
    private void buildLayout() {
        System.out.println("[ProjectView] Building Interface");
        setPreferredSize(new Dimension(510, 82));
        setMaximumSize(new Dimension(510,82));
        setBorder(BorderFactory.createTitledBorder(frameTitle));
        setLayout(new BorderLayout());
        projectForm = new JPanel();
        projectForm.setLayout(new GridLayout(1,2));

        label_projectName = new JLabel("Project Name");

        //Populate a JTextArea with the project's title
        if(project.getTitle().equals("") || project.getTitle() == null)
            input_projectName = new JTextField(30);
        else {
            input_projectName = new JTextField(project.getTitle(),30);
            System.out.println("[ProjectView] Got project name "+project.getTitle());
        }

        projectForm.add(label_projectName);
        projectForm.add(input_projectName);

        //Submit button calls the controller to do validation and the update
        btn_submit = new JButton(btnText);
        btn_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ProjectController.doUpdate(project, input_projectName.getText().trim());
            }
        });



        add(projectForm, BorderLayout.CENTER);
        add(btn_submit,BorderLayout.SOUTH);
        System.out.println("[ProjectView] Built interface");
    }

}
