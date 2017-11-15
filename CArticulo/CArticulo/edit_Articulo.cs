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
        public Articulo articulo_aux;

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
                articulo_aux = articulo;
                entry_ID.Text = articulo.id.ToString();
                entry_nombre.Text = articulo.nombre;
                spin_precio.Text = articulo.precio.ToString();
            }

            saveAction.Activated += delegate
            {

                if ( articulo_aux != null )
                {
                    //TODO Editar
                    TreeIter iter;
                    if ( combo_categorias.GetActiveIter( out iter ) )
                    {
                        string nom_categoria = combo_categorias.Model.GetValue( iter,0 ).ToString();
                        Articulo nuevo = new Articulo( articulo_aux.id.ToString(),
                                                        entry_nombre.Text,
                                                        spin_precio.Text,
                                                        Articulo.IdCategoria( nom_categoria ) );
                        Articulo.Update( articulo_aux,nuevo );
                    }
                }
                else
                {
                    //TODO Añadir
                    TreeIter iter;
                    if ( combo_categorias.GetActiveIter( out iter ) )
                    {
                        string nom_categoria = combo_categorias.Model.GetValue( iter,0 ).ToString();
                        Articulo nuevo = new Articulo( " ",
                                                        entry_nombre.Text,
                                                        spin_precio.Text,
                                                        Articulo.IdCategoria( nom_categoria ) );
                        Articulo.Insert( nuevo );
                    }
                }

                this.Destroy();
            };
        }

    }
}
