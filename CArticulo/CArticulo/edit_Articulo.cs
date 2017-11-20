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

            CellRendererText id_cellRenderText = new CellRendererText();
            combo_categorias.PackStart( id_cellRenderText,false );
            combo_categorias.AddAttribute( id_cellRenderText,"text",0 );
            id_cellRenderText.Visible = false;

            CellRendererText labe_cellRenderText = new CellRendererText();
            combo_categorias.PackStart( labe_cellRenderText,false );
            combo_categorias.AddAttribute( labe_cellRenderText,"text",1 );

            ListStore listStoreAux = new ListStore( typeof( string ),typeof( string ) );

            combo_categorias.Model = listStoreAux;

            List<string[]> listaAux = Articulo.categorias();
            for ( int i = 0 ; i < listaAux.Count ; i++ )
            {
                listStoreAux.AppendValues( listaAux[i][0],listaAux[i][1] );
            }
            combo_categorias.Active = 0;



            if ( articulo != null )
            {
                articulo_aux = articulo;
                entry_ID.Text = articulo.id.ToString();
                entry_nombre.Text = articulo.nombre;
                spin_precio.Text = articulo.precio.ToString();
                // TODO seleccionar el que toca
                int row = 0;
                for ( int i = 0 ; i < listaAux.Count ; i++ )
                {
                    if ( UInt64.Parse( listaAux[i][0] ) == articulo.categoria_fk )
                    {
                        row = i;
                    }
                }
                TreeIter iter;
                combo_categorias.Model.IterNthChild( out iter,row );
                combo_categorias.SetActiveIter( iter );
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
