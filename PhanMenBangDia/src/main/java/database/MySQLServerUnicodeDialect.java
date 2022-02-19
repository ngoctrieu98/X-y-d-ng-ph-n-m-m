package database;

import java.sql.Types;

import org.hibernate.dialect.SQLServer2012Dialect;

public class MySQLServerUnicodeDialect extends SQLServer2012Dialect{

	public MySQLServerUnicodeDialect() {
		super();
        registerColumnType(Types.VARCHAR, 4000, "nvarchar($l)");
        registerColumnType(Types.VARCHAR, "nvarchar(max)");
	}
}
