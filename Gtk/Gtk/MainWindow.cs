using System;
using libreria_AD;
using Gtk;
using System.Data;
using MySql.Data.MySqlClient;

public partial class MainWindow : Gtk.Window
{


    public MainWindow() : base( Gtk.WindowType.Toplevel )
    {
        Build();

        SingletonConnection.Instancia.iniciar();

        string selectSQL = "select * from categoria order by ID";
        IDbCommand dbCommand = SingletonConnection.Connection.CreateCommand();
        TreeViewHelper.fill( treeview,selectSQL,dbCommand );


        categoria_create_w categoria = null;

        treeview.Selection.Changed += delegate
        {
            deleteAction.Sensitive = treeview.Selection.CountSelectedRows() > 0;
            editAction.Sensitive = treeview.Selection.CountSelectedRows() > 0;
        };


        newAction.Activated += delegate
        {
            if ( categoria == null )
            {
                categoria = new categoria_create_w();

                categoria.Hidden += delegate
                {
                    TreeViewHelper.fill( treeview,selectSQL,dbCommand );
                };
            }
            else if ( categoria.Visible == false )
            {
                categoria.ShowAll();
            }

        };

        refreshAction.Activated += delegate
        {
            TreeViewHelper.fill( treeview,selectSQL,dbCommand );
        };

        deleteAction.Activated += delegate
        {
            MessageDialog messageDialog = new MessageDialog(
                                            this,
                                            DialogFlags.Modal,
                                            MessageType.Question,
                                            ButtonsType.YesNo,
                                            "¿Quieres eliminar el registro?"
            );

            TreeViewHelper.del( treeview,"categoria",dbCommand,messageDialog );
            TreeViewHelper.fill( treeview,selectSQL,dbCommand );
        };

    }



    protected void OnDeleteEvent( object sender,DeleteEventArgs a )
    {

        SingletonConnection.Instancia.parar();

        Application.Quit();
        a.RetVal = true;
    }


}


//  private void leerDatos(IDbCommand dbCommand, ListStore listaValores){
//      listaValores.Clear();
//dbCommand.CommandText = "select * from categoria order by ID";
//IDataReader datareader = dbCommand.ExecuteReader();
//while (datareader.Read())
//          listaValores.AppendValues(datareader["id"].ToString(), datareader["nombre"]);
//datareader.Close();
//}