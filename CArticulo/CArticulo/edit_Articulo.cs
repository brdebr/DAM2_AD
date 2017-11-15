using System;
using System.Data;
using GLib;
using Gtk;
using System.Globalization;
using System.Collections.Generic;

namespace CArticulo
{
    public partial class edit_Articulo : Gtk.Window
    {
        public edit_Articulo( Articulo articulo ) :
                base( Gtk.WindowType.Toplevel )
        {
            this.Build();



            ListStore listStoreAux = new ListStore( typeof( String ) );

            combo_categorias.Model = listStoreAux;

            string[] listaAux = Articulo.categorias();
            for ( int i = 0 ; i < listaAux.Length ; i++ )
            {
                listStoreAux.AppendValues( listaAux[i] );
            }
            combo_categorias.Active = 0;



            if ( articulo != null )
            {
                entry_ID.Text = articulo.id.ToString();
                entry_nombre.Text = articulo.nombre;
                spin_precio.Text = articulo.precio.ToString();
            }

            newAction.Activated += delegate
            {

            };

            editAction.Activated += delegate
            {

            };

            refreshAction.Activated += delegate
            {

            };

            deleteAction.Activated += delegate
            {

            };
        }

    }
}
