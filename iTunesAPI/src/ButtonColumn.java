import java.util.*;
import java.util.logging.*;
import org.json.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JEditorPane;
import javax.swing.table.*;
import javax.imageio.ImageIO;

class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    JTable table;
    JButton renderButton;
    JButton editButton;
    String text = "Lyt";

    public ButtonColumn(JTable table, int column) {
        super();
        this.table = table;
        renderButton = new JButton();

        editButton = new JButton();
        editButton.setFocusPainted( false );
        editButton.addActionListener( this );

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (hasFocus) {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        } else if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        renderButton.setText( "Lyt" );
        return renderButton;
    }

    public Component getTableCellEditorComponent( JTable table, Object value, boolean isSelected, int row, int column) {
        text = (value == null) ? "" : value.toString();
        editButton.setText( "Lyt" );
        return editButton;
    }

    public Object getCellEditorValue() {
        return text;
    }

    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
        System.out.println( e.getActionCommand() + " : " + table.getSelectedRow());
    }
}
