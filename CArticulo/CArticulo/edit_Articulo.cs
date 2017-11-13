using System;
namespace CArticulo
{
    public partial class edit_Articulo : Gtk.Window
    {
        public edit_Articulo() :
                base( Gtk.WindowType.Toplevel )
        {
            this.Build();

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
