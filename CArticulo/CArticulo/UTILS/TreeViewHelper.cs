using System;
using GLib;
using Gtk;
using System.Globalization;
using System.Collections.Generic;
using System.Data;

namespace libreria_AD
{
    public class TreeViewHelper
    {

        public static void del(TreeView text_view, string tabla, IDbCommand dbCommand, MessageDialog dialog)
        {

            TreeIter iter;
            text_view.Selection.GetSelected(out iter);

            ResponseType response = (ResponseType)dialog.Run();
            dialog.Destroy();

            if (response == ResponseType.Yes)
            {
                if (!iter.Equals(TreeIter.Zero))
                {
                    String aux = text_view.Model.GetValue(iter, 0).ToString();
                    String deleteSQL = "delete from " + tabla + " where ID = " + aux;
                    dbCommand.CommandText = deleteSQL;
                    dbCommand.ExecuteNonQuery();
                }
            }
        }

        public static void fill(TreeView text_view, string select, IDbCommand dbCommand)
        {
            // Objeto necesario para pasar al Title
            //  el string de los nombres de las columnas
            TextInfo txt = new CultureInfo("es-ES", false).TextInfo;

            ListStore listStore = (ListStore)text_view.Model;

            var type_columnas = new List<Type>();

            var nom_columnas = new List<string>();

            //// Poner el select en el dbCommand y ejecutar el reader

            dbCommand.CommandText = select;
            IDataReader datareader = dbCommand.ExecuteReader();

            //// ----------------------------------------------------

            //// Sacar tipos de las columnas y crear el Model a usar

            for (int i = 0; i < datareader.FieldCount; i++)
            {
                //// Convertir el texto a title_case
                string aux = txt.ToTitleCase(datareader.GetName(i));
                //// -------------------------------
                //type_columnas.Add(datareader.GetFieldType(i));
                type_columnas.Add(typeof(string));
                nom_columnas.Add(aux);

            }

            //// ---------------------------------------------------

            //// Añadir las columnas al treeView en caso de que haga falta

            if (listStore == null)
            {
                listStore = new ListStore(type_columnas.ToArray());
                for (int i = 0; i < datareader.FieldCount; i++)
                {
                    text_view.AppendColumn(nom_columnas[i], new CellRendererText(), "text", i);
                }
            }
            listStore.Clear();


            //// ---------------------------------------------------------

            //// Añadir datos al tree view

            while (datareader.Read())
            {
                var lista_aux = new List<string>();
                for (int i = 0; i < listStore.NColumns; i++)
                {
                    lista_aux.Add(datareader.GetString(i));
                }
                listStore.AppendValues(lista_aux.ToArray());
            }

            //// ----------------------------

            text_view.Model = listStore;

            datareader.Close();

        }


        public static void fill_OLD(TreeView text_view, string select, IDbCommand dbCommand)
        {

            TextInfo txt = new CultureInfo("es-ES", false).TextInfo;

            ListStore listStore;

            var type_columnas = new List<Type>();

            var nom_columnas = new List<string>();

            //// Poner el select en el dbCommand y ejecutar el reader

            dbCommand.CommandText = select;
            IDataReader datareader = dbCommand.ExecuteReader();
            // ----------------------------------------------------

            //ListStore listStore;

            //// Sacar tipos de las columnas y crear el Model a usar

            for (int i = 0; i < datareader.FieldCount; i++)
            {
                //// Convertir el texto a title_case
                string aux = txt.ToTitleCase(datareader.GetName(i));
                // -------------------------------
                //type_columnas.Add(datareader.GetFieldType(i));
                type_columnas.Add(typeof(string));
                nom_columnas.Add(aux);

            }
            // ---------------------------------------------------

            listStore = null;

            //// Añadir las columnas al treeView

            listStore = new ListStore(type_columnas.ToArray());
            for (int i = 0; i < datareader.FieldCount; i++)
            {
                text_view.AppendColumn(nom_columnas[i], new CellRendererText(), "text", i);
            }

            // ---------------------------------------------------

            //// Añadir datos al tree view

            while (datareader.Read())
            {
                var lista_aux = new List<string>();
                for (int i = 0; i < listStore.NColumns; i++)
                {
                    lista_aux.Add(datareader.GetString(i));
                }
                listStore.AppendValues(lista_aux.ToArray());
            }
            // --------------------------------------------------

            text_view.Model = listStore;

            //// Al no poder conseguir que no añada columnas adicionales
            ///  con cada refresh borro las primeras filas

            if (text_view.Columns.Length > nom_columnas.Count)
            {
                for (int i = 0; i < text_view.Columns.Length; i++)
                {
                    text_view.RemoveColumn(text_view.Columns[0]);
                }
            }

            datareader.Close();

        }

    }
}

// CODIGO DIOGNENES


//treeview.AppendColumn("ID", new CellRendererText(), "text", 0);
//treeview.AppendColumn("Nombre", new CellRendererText(), "text", 1);
//ListStore listStore = new ListStore(typeof(string), typeof(string));
//treeview.Model = listStore;
//IDbCommand dbCommand = SingletonConnection.Connection.CreateCommand();


//listaValores.Clear();
//dbCommand.CommandText = "select * from categoria order by ID";
//IDataReader datareader = dbCommand.ExecuteReader();
//while (datareader.Read())
//  listaValores.AppendValues(datareader["id"].ToString(), datareader["nombre"]);
//datareader.Close();
