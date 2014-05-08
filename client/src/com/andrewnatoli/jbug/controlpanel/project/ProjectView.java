package com.andrewnatoli.jbug.controlpanel.project;

import com.andrewnatoli.jbug.controlpanel.ControlPanelController;

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

    protected JLabel        label_projectName;
    protected JTextField    input_projectName;

    protected JPanel        buttons_panel;
    protected JButton       btn_delete;
    protected JButton       btn_submit;
    protected ProjectModel  project;

    protected JButton       btn_submitIssue;

    /**
     * Constructor to edit an existing project
     * @param id
     */
    public ProjectView(int id) {
        System.out.println("[ProjectView] Loading project " + id + "...");
        frameTitle  = "Manage Project";
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
        setPreferredSize(new Dimension(510, 150));
        setMaximumSize(new Dimension(510,125));
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
        buttons_panel = new JPanel();
        buttons_panel.setLayout(new GridLayout(3,1));

        /*
            Submit button
         */
        btn_submit = new JButton(btnText);
        btn_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ProjectController.doUpdate(project, input_projectName.getText().trim());
            }
        });

        /*
            Delete Button
         */
        btn_delete = new JButton("Remove Project");
        if(project.getProject_id() == -1) {
            btn_delete.setVisible(false);
        }
        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int confirm = JOptionPane.showOptionDialog(null, "Are you sure you want to delete this project?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    ProjectController.doDelete(project);
                }
            }
        });

        /*
            Add issue, button
         */
        btn_submitIssue = new JButton("Report an Issue");
        if(project.getProject_id() == -1) {
            btn_submitIssue.setVisible(false);
        }
        btn_submitIssue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ControlPanelController.showIssue(-1,project.getProject_id());
            }
        });

        //Add the buttons to our button panel
        buttons_panel.add(btn_submit);
        if(project.getProject_id() != -1) {
            buttons_panel.add(btn_submitIssue);
            buttons_panel.add(btn_delete);
        }

        //Add everything to the view
        add(projectForm, BorderLayout.CENTER);
        add(buttons_panel,BorderLayout.SOUTH);
        System.out.println("[ProjectView] Built interface");
    }

}
