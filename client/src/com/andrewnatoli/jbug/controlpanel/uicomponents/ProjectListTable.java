package com.andrewnatoli.jbug.controlpanel.uicomponents;

import com.andrewnatoli.jbug.controlpanel.project.ProjectModel;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;


public class ProjectListTable extends AbstractTableModel {

    /**
     * A list of strings (usernames)
     */
    public static ArrayList<ProjectModel> projects = new ArrayList<ProjectModel>();

    /**
     * Table heading
     */
    private String[] columnNames = {"Projects you Manage"};

    /**
     * Blank constructor
     */
    public ProjectListTable() {
        //Empty constructor
    }

    /**
     * ProjectModel constructor
     * @param projects ProjectModel
     */
    public ProjectListTable(ArrayList<ProjectModel> projects) {
        this.projects = projects;
    }

    /**
     * fireModelChange - Updates the content in the table
     * @param projects ProjectModel
     */
    public void fireModelChange(ArrayList<ProjectModel> projects) {
        this.projects= projects;
        fireTableDataChanged();
    }

    /**
     * getColumnName - The column name at index i
     * @param index
     * @return
     */
    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }

    /**
     * The number of columns we have in the table
     * @return
     */
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * The number of rows in the table
     * @return
     */
    public int getRowCount() {
        return projects.size();
    }

    /**
     * Get the current value at which row/column
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return projects.get(rowIndex).getTitle();
            default:
                return null;
        }
    }
}