/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import Hibernate.Classes.Pedidolinea;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author brybre
 */
public class PedidoLineaTableModel extends AbstractTableModel {

    private String[] columnNames
            = {
                "Ref.",
                "Artículo",
                "Precio",
                "Unidades",
                "Total"
            };

    private List<Pedidolinea> lineas;

    public PedidoLineaTableModel() {
        lineas = new ArrayList<Pedidolinea>();
    }

    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return lineas.size();
    }

    @Override
    public String getColumnName(int column) {
        //return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
        return columnNames[column];
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return columnNames.length;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //super.setValueAt(aValue, rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
        Pedidolinea lineaPed = lineas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                lineaPed.setId(Long.parseLong((String) aValue));
                break;
            case 1:
//                lineaPed.setArticulo((String) aValue);
                break;
            case 2:
//                lineaPed.setPrecio(BigDecimal.ZERO);
                break;
            case 3:
//                lineaPed.setCategoria_fk((String) aValue);
                break;
        }
//        Pedidolinea.update(lineaPed, lineaPed);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        Pedidolinea lineaPed = lineas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return lineaPed.getId();
            case 1:
                return lineaPed.getArticulo().toString();
            case 2:
                return String.valueOf(lineaPed.getPrecio());
            case 3:
                return String.valueOf(lineaPed.getUnidades());
            case 4:
                return String.valueOf(lineaPed.getImporte());
            default:
                return null;
        }
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return long.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        switch (column) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            default:
                return true;
        }
    }

    public Pedidolinea getLinea(int row) {
        return lineas.get(row);
    }

    public void addLinea(Pedidolinea linea) {
        insertLinea(getRowCount(), linea);
    }

    public void insertLinea(int row, Pedidolinea linea) {
        lineas.add(row, linea);
        fireTableRowsInserted(row, row);
    }

    public void removeArticulo(int row) {
//        Pedidolinea.delete(lineas.get(row));
        lineas.remove(row);
        fireTableRowsDeleted(row, row);
    }

}
