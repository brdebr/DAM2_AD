/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpis.ad;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author brybre
 */
public class ArticuloTableModel extends AbstractTableModel {

    private String[] columnNames
            = {
                "ID",
                "Nombre",
                "Precio",
                "Categoría",
                "Nombre Categoría"
            };

    private List<Articulo> articulos;

    public ArticuloTableModel() {
        articulos = new ArrayList<Articulo>();
    }

    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return articulos.size();
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
        Articulo articulo = articulos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                articulo.setId((String) aValue);
                break;
            case 1:
                articulo.setNombre((String) aValue);
                break;
            case 2:
                articulo.setPrecio((String) aValue);
                break;
            case 3:
                articulo.setCategoria_fk((String) aValue);
                break;
        }
        Articulo.update(articulo, articulo);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        Articulo articulo = articulos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return articulo.getId_String();
            case 1:
                return articulo.getNombre();
            case 2:
                return articulo.getPrecio_String();
            case 3:
                return articulo.getCategoria_fk_String();
            case 4:
                return articulo.getCategoriaNombre();
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
                return long.class;
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

    public Articulo getArticulo(int row) {
        return articulos.get(row);
    }

    public void addArticulo(Articulo articulo) {
        insertArticulo(getRowCount(), articulo);
    }

    public void insertArticulo(int row, Articulo articulo) {
        articulos.add(row, articulo);
        fireTableRowsInserted(row, row);
    }

    public void removeArticulo(int row) {
        Articulo.delete(articulos.get(row));
        articulos.remove(row);
        fireTableRowsDeleted(row, row);
    }

}
