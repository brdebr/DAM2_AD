using System;
using System.Data;
using MySql.Data.MySqlClient;

namespace CArticulo
{
    public class DbCommandHelper
    {

        public static void addParameter( IDbCommand dbComamnd,string name,object value )
        {
            IDbDataParameter dbDataParameter = dbComamnd.CreateParameter();
            dbDataParameter.ParameterName = name;
            dbDataParameter.Value = value;
            dbComamnd.Parameters.Add( dbDataParameter );
        }
    }
}
