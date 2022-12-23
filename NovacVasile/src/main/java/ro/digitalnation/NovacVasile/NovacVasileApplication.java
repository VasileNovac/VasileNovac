package ro.digitalnation.NovacVasile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection; 
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException; 
import java.sql.Statement;
//import java.sql.ResultSet; 

@SpringBootApplication
public class NovacVasileApplication {

	private static String sql ;
//	JDBC driver name and database URL 
	   static final String JDBC_DRIVER = "org.h2.Driver";   
//	   static final String DB_URL = "jdbc:h2:~/test;IFEXISTS=TRUE;DB_CLOSE_ON_EXIT=FALSE"; 
	   static final String DB_URL = "jdbc:h2:~/test;IFEXISTS=TRUE"; 

//	Database credentials 
	   static final String USER = "sa"; 
	   static final String PASS = ""; 

	   public static Connection conn = null; 
	   public static Statement stmt = null; 
	   
	public static void main(String[] args) {
		SpringApplication.run(NovacVasileApplication.class, args);

		try { 
//		Register JDBC driver 
	        Class.forName(JDBC_DRIVER); 
//		Open a connection 
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

//		Drop table. Simplifica testele cu cateva CNPRL.
	        stmt = conn.createStatement();
	        sql = "DROP TABLE IF EXISTS familie" ;
	        stmt.executeUpdate(sql) ;
//		Create table
        	stmt = conn.createStatement(); 
	       	sql = "CREATE TABLE IF NOT EXISTS familie " + 
	       			 "( cnpRL varchar(13) PRIMARY KEY," +  
	        		 "numeRL varchar(30)," +  
	        		 "prenumeRL varchar(30)," +
	        		 "adresaLoc varchar(30)," +
	        		 "adresaStr varchar(50)," +
	        		 "adresaNr varchar(5)," +
	        		 "adresaBl varchar(6)," +
	        		 "adresaSc varchar(6)," +
	        		 "adresaEt varchar(2)," +
	        		 "adresaAp varchar(5)," +
	        		 "adresaSector varchar(2)," +
	        		 "adresaJud varchar(16)," +
	        		 "adresaCodP varchar(8)," +
	        		 "primarie varchar(20)," +
	        		 "nrPersoaneMajore integer," +
	        		 "nrCopii integer )";
	        stmt.executeUpdate(sql);

//		Drop table. Simplifica testele cu cateva CNP.
	        stmt = conn.createStatement();
	        sql = "DROP TABLE IF EXISTS copil" ;
	        stmt.executeUpdate(sql) ;
//		Create table
        	stmt = conn.createStatement(); 
	        sql = "CREATE TABLE IF NOT EXISTS copil " + 
	        		 "( cnp varchar(13) PRIMARY KEY," +  
	        		 "nume varchar(30)," +  
	        		 "prenume varchar(30)," +
	        		 "actId varchar(4)," +
	        		 "serieActId varchar(4)," +
	        		 "nrActId varchar(10)," +
	        		 "dataExpActId varchar(10)," +
	        		 "dataNastere varchar(10)," +
	        		 "situatieScolara varchar(17)," +
	        		 "cuDizabilitati varchar(2)," +
	        		 "beneficiatAlteDreptSociale varchar(2)," +
	        		 "categDreptSociale varchar(30)," +
	        		 "gradRudaRL varchar(16)," +
	        		 "sex varchar(1) )";  
	        stmt.executeUpdate(sql);

//			Drop table. Simplifica testele cu cateva CNP.
	        stmt = conn.createStatement();
	        sql = "DROP TABLE IF EXISTS masculinParinte" ;
	        stmt.executeUpdate(sql) ;
//		Create table
        	stmt = conn.createStatement(); 
	        sql = "CREATE TABLE IF NOT EXISTS masculinParinte " + 
	        		 "( cnp varchar(13) PRIMARY KEY," +  
	        		 "nume varchar(30)," +  
	        		 "prenume varchar(30)," +
	        		 "actId varchar(4)," +
	        		 "serieActId varchar(4)," +
	        		 "nrActId varchar(10)," +
	        		 "dataExpActId varchar(10)," +
	        		 "stareCivila varchar(20)," +
	        		 "dataNastere varchar(10)," +
	        		 "cetatenie varchar(20)," +
	        		 "situatieScolara varchar(17)," +
	        		 "situatieProfesionala varchar(18)," +
	        		 "venitTotalUltimaLuna integer," +
	        		 "cuDizabilitati varchar(2)," +
	        		 "beneficiatAlteDreptSociale varchar(2)," +
	        		 "categDreptSociale varchar(30)," +
	        		 "sex varchar(1) )";  
	        stmt.executeUpdate(sql);

//			Drop table. Simplifica testele cu cateva CNP.
	        stmt = conn.createStatement();
	        sql = "DROP TABLE IF EXISTS femininParinte" ;
	        stmt.executeUpdate(sql) ;
//		Create table
        	stmt = conn.createStatement(); 
	        sql = "CREATE TABLE IF NOT EXISTS femininParinte " + 
	        		 "( cnp varchar(13) PRIMARY KEY," +  
	        		 "nume varchar(30)," +  
	        		 "prenume varchar(30)," +
	        		 "actId varchar(4)," +
	        		 "serieActId varchar(4)," +
	        		 "nrActId varchar(10)," +
	        		 "dataExpActId varchar(10)," +
	        		 "stareCivila varchar(20)," +
	        		 "dataNastere varchar(10)," +
	        		 "cetatenie varchar(20)," +
	        		 "situatieScolara varchar(17)," +
	        		 "situatieProfesionala varchar(18)," +
	        		 "venitTotalUltimaLuna integer," +
	        		 "cuDizabilitati varchar(2)," +
	        		 "beneficiatAlteDreptSociale varchar(2)," +
	        		 "categDreptSociale varchar(30)," +
	        		 "sex varchar(1) )";  
	        stmt.executeUpdate(sql);

//			Drop table. Simplifica testele cu cateva CNP.
	        stmt = conn.createStatement();
	        sql = "DROP TABLE IF EXISTS altaPersMajora" ;
	        stmt.executeUpdate(sql) ;
//		Create table
        	stmt = conn.createStatement(); 
	        sql = "CREATE TABLE IF NOT EXISTS altaPersMajora " + 
	        		 "( cnp varchar(13) PRIMARY KEY," +  
	        		 "nume varchar(30)," +  
	        		 "prenume varchar(30)," +
	        		 "actId varchar(4)," +
	        		 "serieActId varchar(4)," +
	        		 "nrActId varchar(10)," +
	        		 "dataExpActId varchar(10)," +
	        		 "stareCivila varchar(20)," +
	        		 "dataNastere varchar(10)," +
	        		 "cetatenie varchar(20)," +
	        		 "situatieScolara varchar(17)," +
	        		 "situatieProfesionala varchar(18)," +
	        		 "venitTotalUltimaLuna integer," +
	        		 "cuDizabilitati varchar(2)," +
	        		 "beneficiatAlteDreptSociale varchar(2)," +
	        		 "categDreptSociale varchar(30)," +
	        		 "sex varchar(1) )";  
	        stmt.executeUpdate(sql);

//			Drop table altfel apare conflict cu ID primary key
	        stmt = conn.createStatement();
	        sql = "DROP TABLE IF EXISTS dispozitieprimar" ;
	        stmt.executeUpdate(sql) ;
//		Create table
        	stmt = conn.createStatement(); 
	        sql = "CREATE TABLE IF NOT EXISTS dispozitieprimar " + 
	        		 "( id integer not NULL," + 
	        		 "nrDispozitie varchar(10)," +  
	        		 "dataDispozitie varchar(10)," +  
	        		 "categDispozitie varchar(1)," +
	        		 "dataIntrareVigoare varchar(10)," +
	        		 "primary key(id) )";
	        stmt.executeUpdate(sql);

		} catch(SQLException se) { 
//		Handle errors for JDBC 
	        se.printStackTrace(); 
	    } catch(Exception e) { 
//		Handle errors for Class.forName 
	        e.printStackTrace(); 
	    } finally { }
	}
}
