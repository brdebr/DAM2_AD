using System;
using GLib;
using Gtk;
using System.Globalization;
using System.Collections.Generic;
using System.Data;

namespace CArticulo
{
    public class Articulo
    {
        public Articulo( string id,string nombre,string precio,string categoria_fk )
        {
            UInt64 id_n;
            Decimal precio_n;
            UInt64 categoria_n;
            if ( UInt64.TryParse( id,out id_n ) )
            {
                this.id = id_n;
            }
            this.nombre = nombre;
            if ( Decimal.TryParse( precio,out precio_n ) )
            {
                this.precio = precio_n;
            }
            if ( UInt64.TryParse( categoria_fk,out categoria_n ) )
            {
                this.categoria_fk = categoria_n;
            }
        }

        public Articulo( ulong id,string nombre,decimal precio,ulong categoria_fk )
        {
            this.id = id;
            this.nombre = nombre;
            this.precio = precio;
            this.categoria_fk = categoria_fk;
        }

        public UInt64 id
        {
            get;
            set;
        }

        public String nombre
        {
            get;
            set;
        }

        public decimal precio
        {
            get;
            set;
        }
        public UInt64 categoria_fk
        {
            get;
            set;
        }

        public static List<Articulo> Select()
        {

            List<Articulo> lista = new List<Articulo>();

            IDbCommand dbCommand = SingletonConnection.Connection.CreateCommand();

            dbCommand.CommandText = "SELECT * FROM categoria";

            IDataReader datareader = dbCommand.ExecuteReader();
            while ( datareader.Read() )
            {
                Articulo aux = new Articulo(
                                            datareader["id"].ToString(),
                                            datareader["nombre"].ToString(),
                                            datareader["precio"].ToString(),
                                            datareader["categoria"].ToString()
                );
                lista.Add( aux );
            }

            int numColumns = lista.Count;

            datareader.Close();

            return lista;
        }

        public static void Delete( Articulo articulo )
        {

            IDbCommand dbCommand = SingletonConnection.Connection.CreateCommand();
            dbCommand.CommandText = "DELETE FROM articulo WHERE id=@id";
            DbCommandHelper.addParameter( dbCommand,"id",articulo.id );
            dbCommand.ExecuteNonQuery();

        }

        public static void Update( Articulo articulo,Articulo nuevo )
        {

            IDbCommand dbCommand = SingletonConnection.Connection.CreateCommand();
            dbCommand.CommandText = "UPDATE articulo SET nombre = @nombre, precio=@precio, categoria=@categoria WHERE id =@id;";
            DbCommandHelper.addParameter( dbCommand,"id",articulo.id );
            DbCommandHelper.addParameter( dbCommand,"nombre",nuevo.nombre );
            DbCommandHelper.addParameter( dbCommand,"precio",nuevo.precio );
            DbCommandHelper.addParameter( dbCommand,"categoria",nuevo.categoria_fk );
            dbCommand.ExecuteNonQuery();

        }

        public static void Insert( Articulo articulo )
        {
            IDbCommand dbCommand = SingletonConnection.Connection.CreateCommand();
            dbCommand.CommandText = "INSERT INTO articulo (nombre, precio, categoria) VALUES (@nombre, @precio, @categoria);";
            DbCommandHelper.addParameter( dbCommand,"nombre",articulo.nombre );
            DbCommandHelper.addParameter( dbCommand,"precio",articulo.precio );
            DbCommandHelper.addParameter( dbCommand,"categoria",articulo.categoria_fk );
            dbCommand.ExecuteNonQuery();
        }

        public static Articulo seleccionado( TreeView treeview )
        {
            TreeIter iter;
            treeview.Selection.GetSelected( out iter );
            TreeModel aux = treeview.Model;
            Articulo art =
                new Articulo( aux.GetValue( iter,0 ) as string,
                                aux.GetValue( iter,1 ) as string,
                                aux.GetValue( iter,2 ) as string,
                                aux.GetValue( iter,3 ) as string );
            return art;
        }

        public static string[] categorias()
        {
            List<string> lista = new List<string>();
            IDbCommand dbCommand = SingletonConnection.Connection.CreateCommand();
            dbCommand.CommandText = "SELECT nombre FROM categoria";
            IDataReader datareader = dbCommand.ExecuteReader();
            while ( datareader.Read() )
            {
                lista.Add( datareader["nombre"].ToString() );
            }
            return lista.ToArray();
        }

    }
}
