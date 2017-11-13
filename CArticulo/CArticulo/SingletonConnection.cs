using System;
using System.Data;
using MySql.Data.MySqlClient;


namespace CArticulo
{
    public class SingletonConnection
    {
        private string servidorIP;
        private string dbName;
        private string UID;
        private string password;
        private string connectionString;

        private static SingletonConnection instance = new SingletonConnection();
        public static SingletonConnection Instancia
        {
            get { return instance; }
        }

        public static IDbConnection Connection
        { get; set; }

        public SingletonConnection( string servidor,string dbName,string UID,string password )
        {
            this.servidorIP = servidor;
            this.dbName = dbName;
            this.UID = UID;
            this.password = password;

            if ( Connection == null )
            {
                connectionString = "server=" + servidorIP + ";database=" + dbName + ";user=" + UID + ";password=" + password;
                Connection = new MySqlConnection( connectionString );
            }
        }

        private SingletonConnection()
        {
            if ( Connection == null )
            {
                connectionString = "server=127.0.0.1;database=pruebas;user=root;password=sistemas";
                Connection = new MySqlConnection( connectionString );
            }
        }
        public void iniciar()
        {
            Connection.Open();
        }
        public void parar()
        {
            Connection.Close();
        }


        //      public static void addParameter(IDbCommand dbComamnd, string name, object value)
        //{
        //	IDbDataParameter dbDataParameter = dbComamnd.CreateParameter();
        //	dbDataParameter.ParameterName = name;
        //	dbDataParameter.Value = value;
        //	dbComamnd.Parameters.Add(dbDataParameter);
        //}
    }
}
