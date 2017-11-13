using System;
using libreria_AD;
using System.Data;
namespace Gtk
{
    public partial class categoria_create_w : Gtk.Window
    {
        
        public categoria_create_w(object id): this (){
            
        }


        public categoria_create_w() :
                base(Gtk.WindowType.Toplevel)
        {
            this.Build();


            saveAction.Activated += delegate
            {
                string aux = edit_nombre.Text;
                IDbCommand dbCommand = SingletonConnection.Connection.CreateCommand();
                dbCommand.CommandText = "insert into categoria (nombre) values (@nombre)";
                DbCommandHelper.addParameter(dbCommand, "nombre", aux);
                dbCommand.ExecuteNonQuery();
                edit_nombre.Text = "";
                this.Hide();
            };

            DeleteEvent += delegate (object o, DeleteEventArgs args)  {
                Hide();
                args.RetVal = true;
            };

            // Cuando se oculte debe borrar el texto
            this.Hidden += delegate {
                edit_nombre.Text = "";
            };
        }

    }
}
