using System;
using System.Data;
using Gtk;
using CArticulo;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base( Gtk.WindowType.Toplevel )
    {
        Build();

        SingletonConnection.Instancia.iniciar();

        IDbCommand dbCommand = SingletonConnection.Connection.CreateCommand();
        TreeViewHelper.fillV2( treeview1,"SELECT * FROM articulo",dbCommand );

        treeview1.Selection.Changed += delegate
        {
            deleteAction.Sensitive = treeview1.Selection.CountSelectedRows() > 0;
            editAction.Sensitive = treeview1.Selection.CountSelectedRows() > 0;
        };

        newAction.Activated += delegate
        {
            new edit_Articulo( null ).Destroyed += delegate
            {
                TreeViewHelper.fillV2( treeview1,"SELECT * FROM articulo",dbCommand );
            }; ;
        };

        editAction.Activated += delegate
        {
            new edit_Articulo( Articulo.seleccionado( treeview1 ) ).Destroyed += delegate
            {
                TreeViewHelper.fillV2( treeview1,"SELECT * FROM articulo",dbCommand );
            }; ;
        };

        refreshAction.Activated += delegate
        {
            TreeViewHelper.fillV2( treeview1,"SELECT * FROM articulo",dbCommand );
        };
        deleteAction.Activated += delegate
        {
            Articulo.Delete( Articulo.seleccionado( treeview1 ) );
            TreeViewHelper.fillV2( treeview1,"SELECT * FROM articulo",dbCommand );
        };

    }

    protected void OnDeleteEvent( object sender,DeleteEventArgs a )
    {
        Application.Quit();
        a.RetVal = true;
    }
}
