/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skodanew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class methodz {
    //mos harro me hek static
    public static boolean checkLogin(String user, String pw){
        Connection c = null;
        Statement stmt = null;
        String lolz = "";
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src\\com\\datBar\\Storage.db");
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT Username, Password FROM Users WHERE Username = '"+user+"';" );
        while ( rs.next() ) {
         String  name = rs.getString("Username");
         String  hash = rs.getString("Password");
         lolz = hash;
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        JOptionPane.showMessageDialog(null, "Problem me DataBase." /*+e.getMessage()*/, "Error", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
        }
        //System.out.println("Operation done successfully");

        if(BCrypt.checkpw(pw, lolz)){
            return true;
        }
        else{
            return false;
        }
    }
    static void ndryshoKlient(String Emri, String Targa, String kodiModelit, String kubikCcm, String gjendjaKm, String Tipi, String kodiMotorrit, String fuqiaKw, String dataUrdherit, String nrShasise, String karburanti, String dataLejes, String nrTelefonit, javax.swing.JLabel jLabelhandleMsg) {
        Connection c = null;
        Statement stmt = null;
        int goterror = 0;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:contents.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "UPDATE Klientet SET Targa = '"+Targa+"', Kodi_Modelit = '"+kodiModelit+"', Kubik = '"+kubikCcm+"'"
                + ", Gjendja_KM = '"+gjendjaKm+"', "
                + "Tipi = '"+Tipi+"', Kodi_Motorrit = '"+kodiMotorrit+"', Fuqia_KWPS = '"+fuqiaKw+"', Data_Urdherit = '"+dataUrdherit+"', Nr_Shasise = '"+nrShasise+"', Karburanti = '"+karburanti+"', dataLejes = '"+dataLejes+"', nrTel='"+nrTelefonit+"'"
                + "where Emri = '"+Emri+"';";
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.err.println( e.getMessage() );
            if(e.getMessage().equals("UNIQUE constraint failed: Produktet.Emri")){
                System.out.println("Wtfdude?");
                jLabelhandleMsg.setText("Nje produkt me ate emer ekziston.");
                goterror = 1;
            }
            else{
                System.out.println("Not working");
                System.exit(0);
            }
        }
        if(goterror == 0){jLabelhandleMsg.setText("The dhenat u aktualizuan.");}
        System.out.println("Records created successfully.");
        //return "?";
    }

    static void shtoKlient(String Emri, String Targa, String kodiModelit, String kubikCcm, String gjendjaKm, String Tipi, String kodiMotorrit, String fuqiaKw, String dataUrdherit, String nrShasise, String karburanti, String dataLejes, String nrTelefonit, javax.swing.JLabel jLabelhandleMsg) {
        Connection c = null;
        Statement stmt = null;
        int goterror = 0;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:contents.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "INSERT INTO Klientet (Emri, Targa, Kodi_Modelit, Kubik, Gjendja_KM, Tipi, Kodi_Motorrit, Fuqia_KWPS, Data_Urdherit, Nr_Shasise, Karburanti, dataLejes, nrTel) " +
                    "VALUES ('"+Emri+"','"+Targa+"', '"+kodiModelit+"', '"+kubikCcm+"', '"+gjendjaKm+"', '"+Tipi+"', '"+kodiMotorrit+"', '"+fuqiaKw+"', '"+dataUrdherit+"', '"+nrShasise+"', '"+karburanti+"', '"+dataLejes+"', '"+nrTelefonit+"');";
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.err.println( e.getMessage() );
            if(e.getMessage().equals("UNIQUE constraint failed: Produktet.Emri")){
                System.out.println("Wtfdude?");
                jLabelhandleMsg.setText("Nje produkt me ate emer ekziston.");
                goterror = 1;
            }
            else{
                System.out.println("Not working");
                System.exit(0);
            }
        }
        if(goterror == 0){jLabelhandleMsg.setText("Klienti u shtua");}
        System.out.println("Records created successfully.");
        //return "?";
    }
    
    public static void shtoBilancin(long time1, long time2){
        Connection c = null;
        Statement stmt = null;
        String lolz = "";
        java.sql.Timestamp data1 = new java.sql.Timestamp(time1);
        java.sql.Timestamp data2 = new java.sql.Timestamp(time2);
        
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src\\com\\datBar\\Storage.db");
        stmt = c.createStatement();
        ResultSet rs = null;
        if(data1.after(data2)){
            
             rs = stmt.executeQuery("SELECT * FROM Fatura "+
            "WHERE DataOra "+
            "BETWEEN '"+data2+"' and '"+data1+"'");
        }else{
             rs = stmt.executeQuery("SELECT * FROM Fatura "+
            "WHERE DataOra "+
            "BETWEEN '"+data1+"' and '"+data2+"'");

        }
        System.out.println("SELECT * FROM Fatura "+
            "WHERE DataOra "+
            "BETWEEN '"+data2+"' and '"+data1+"'");
        System.out.println("Step 1:"+data1.toString());
        while ( rs.next() ) {
         System.out.println("fatura id: "+rs.getString("ID_Fatura"));
         System.out.println("User id: "+rs.getString("ID_User"));
         System.out.println("DataOra: "+rs.getString("DataOra"));
        }
        System.out.println("Step 2:");
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        JOptionPane.showMessageDialog(null, "Problem me DataBase." /*+e.getMessage()*/, "Error", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
        }
        //System.out.println("Operation done successfully");

    }

    
    public static Pair meIShitur(String tipi, String koha, long time1, long time2){
        Connection c = null;
        Statement stmt = null;
        String  emri = "";
        int sasia = 0;
        java.sql.Timestamp data1 = new java.sql.Timestamp(time1);
        java.sql.Timestamp data2 = new java.sql.Timestamp(time2);
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src\\com\\datBar\\Storage.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs = null;
        if(koha == "sot"){
             rs = stmt.executeQuery("SELECT SUM(ProduktiShitur.Sasia) as Sassia, "+
                    "Produktet."+tipi+"  as value "+
                    "FROM ProduktiShitur  "+
                    "INNER JOIN Produktet ON ProduktiShitur.ID_Prod = Produktet.ID_Prod "+
                    "INNER JOIN Fatura ON ProduktiShitur.ID_Fatura = Fatura.ID_Fatura "+
                    "WHERE dataOra >= date('now','start of day') "+
                    "GROUP BY value ORDER BY Sassia DESC;");
        }
        else if(koha == "javor"){
             rs = stmt.executeQuery("SELECT SUM(ProduktiShitur.Sasia) as Sassia, "+
                    "Produktet."+tipi+"  as value "+
                    "FROM ProduktiShitur  "+
                    "INNER JOIN Produktet ON ProduktiShitur.ID_Prod = Produktet.ID_Prod "+
                    "INNER JOIN Fatura ON ProduktiShitur.ID_Fatura = Fatura.ID_Fatura "+
                    "WHERE dataOra >= datetime('now', '-6 days', 'localtime') AND datetime('now', 'localtime') "+
                    "GROUP BY value ORDER BY Sassia DESC;");
             System.out.print("SELECT SUM(ProduktiShitur.Sasia) as Sassia, "+
                    "Produktet."+tipi+"  as value "+
                    "FROM ProduktiShitur  "+
                    "INNER JOIN Produktet ON ProduktiShitur.ID_Prod = Produktet.ID_Prod "+
                    "INNER JOIN Fatura ON ProduktiShitur.ID_Fatura = Fatura.ID_Fatura "+
                    "WHERE dataOra >= datetime('now', '-6 days', 'localtime') AND datetime('now', 'localtime') "+
                    "GROUP BY value ORDER BY Sassia DESC;");
             System.out.println("dafuq?");
        }
        else if(koha == "mujor"){
             rs = stmt.executeQuery("SELECT SUM(ProduktiShitur.Sasia) as Sassia, "+
                    "Produktet."+tipi+"  as value "+
                    "FROM ProduktiShitur  "+
                    "INNER JOIN Produktet ON ProduktiShitur.ID_Prod = Produktet.ID_Prod "+
                    "INNER JOIN Fatura ON ProduktiShitur.ID_Fatura = Fatura.ID_Fatura "+
                    "BETWEEN datetime('now', 'start of month', 'localtime') AND datetime('now', 'localtime')"+
                    "GROUP BY value ORDER BY Sassia DESC;");
        }
        else if(koha == "vjetor"){
             rs = stmt.executeQuery("SELECT SUM(ProduktiShitur.Sasia) as Sassia, "+
                    "Produktet."+tipi+"  as value "+
                    "FROM ProduktiShitur  "+
                    "INNER JOIN Produktet ON ProduktiShitur.ID_Prod = Produktet.ID_Prod "+
                    "INNER JOIN Fatura ON ProduktiShitur.ID_Fatura = Fatura.ID_Fatura "+
                    "BETWEEN datetime('now', 'start of year', 'localtime') AND datetime('now', 'localtime')"+
                    "GROUP BY value ORDER BY Sassia DESC;");
        }
        if(koha == "caktume"){
            if(data1.after(data2)){
                rs = stmt.executeQuery("SELECT SUM(ProduktiShitur.Sasia) as Sassia, "+
                        "Produktet."+tipi+"  as value "+
                        "FROM ProduktiShitur  "+
                        "INNER JOIN Produktet ON ProduktiShitur.ID_Prod = Produktet.ID_Prod "+
                        "INNER JOIN Fatura ON ProduktiShitur.ID_Fatura = Fatura.ID_Fatura "+
                        "WHERE Fatura.DataOra BETWEEN '"+data2+"' and '"+data1+"'"+
                        "GROUP BY value ORDER BY Sassia DESC;");
            }else{
                rs = stmt.executeQuery("SELECT SUM(ProduktiShitur.Sasia) as Sassia, "+
                        "Produktet."+tipi+"  as value "+
                        "FROM ProduktiShitur  "+
                        "INNER JOIN Produktet ON ProduktiShitur.ID_Prod = Produktet.ID_Prod "+
                        "INNER JOIN Fatura ON ProduktiShitur.ID_Fatura = Fatura.ID_Fatura "+
                        "WHERE Fatura.DataOra BETWEEN '"+data1+"' and '"+data2+"'"+
                        "GROUP BY value ORDER BY Sassia DESC;");
            }
        }
        
        while ( rs.next() ) {
            emri = rs.getString("value");
            sasia = rs.getInt("Sassia");
            break;
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
        }
        return new Pair(emri, sasia);
    }

        public static Pair getTotalin(){
        Connection c = null;
        Statement stmt = null;
        int cmimi = 0;
        int faturaid = 0;
        int totali = 0;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src\\com\\datBar\\Storage.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs;
            rs = stmt.executeQuery("SELECT SUM(ProduktiShitur.Sasia*Produktet.Cmimi) as Cmimi, ProduktiShitur.ID_Fatura  as faturaid " +
            "FROM ProduktiShitur  " +
            "INNER JOIN Produktet " +
            "ON ProduktiShitur.ID_Prod = Produktet.ID_Prod " +
            "GROUP BY faturaid ORDER BY Cmimi DESC;");
        while ( rs.next() ) {
            cmimi = rs.getInt("Cmimi");
            faturaid = rs.getInt("faturaid");
            totali += cmimi;
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
        }
        return new Pair(String.valueOf(faturaid), totali);
    }

            
        public static void addProducts(javax.swing.table.DefaultTableModel model, String kat, String emriKlikuar){
        Connection c = null;
        Statement stmt = null;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:contents.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs;
        if(emriKlikuar.equals("")){
             rs = stmt.executeQuery( "SELECT * FROM "+kat+";" );
        }else{
             rs = stmt.executeQuery( "SELECT * FROM "+kat+" WHERE Emri = '"+emriKlikuar+"';" );
        }
        while ( rs.next() ) {
            //int id = rs.getInt("id");
            if(kat.equals("Klientet")){
                String  emri = rs.getString("Emri");
            //String  cmimi = rs.getString("Cmimi");
            //int age  = rs.getInt("age");
            //String  address = rs.getString("address");
            //float salary = rs.getFloat("salary");
            Object [] row = {emri};
            model.addRow(row);
            }
            else if(kat.equals("Produktet")){
                String kodi = rs.getString("Kodi");
                String emri = rs.getString("Emri");
                String sasia = rs.getString("Sasia");
                String chyrjes = rs.getString("Cmim_hyrje");
                String cdalje = rs.getString("Cmim_dalje");
                Object [] row = {kodi, emri, sasia, chyrjes, cdalje};
                model.addRow(row);
            }
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
        }
    }
        public static void shtoProdukteFature(javax.swing.table.DefaultTableModel model, String kat, String Kodi, int sas){
        Connection c = null;
        Statement stmt = null;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:contents.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery( "SELECT * FROM "+kat+" WHERE Kodi = '"+Kodi+"';" );
        while ( rs.next() ) {
            //int id = rs.getInt("id");
            String kodi = rs.getString("Kodi");
            String emri = rs.getString("Emri");
            String chyrjes = rs.getString("Cmim_hyrje");
            String cdalje = rs.getString("Cmim_dalje");
            Object [] row = {sas, kodi, emri, chyrjes, cdalje};
            model.addRow(row);
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
        }
    }

    public static void addProducts(javax.swing.table.DefaultTableModel model){
        Connection c = null;
        Statement stmt = null;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src\\com\\datBar\\Storage.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery( "SELECT * FROM Produktet;" );
        while ( rs.next() ) {
            //int id = rs.getInt("id");
            String  emri = rs.getString("Emri");
            String  cmimi = rs.getString("Cmimi");
            String  id = rs.getString("ID_Prod");
            String  kat = rs.getString("Kategoria");
            kat = kat.replace("Te", "Te ");
            //int age  = rs.getInt("age");
            //String  address = rs.getString("address");
            //float salary = rs.getFloat("salary");
            Object [] row = {id, emri, cmimi, kat};
            model.addRow(row);
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    public static void main(String[] args) {
        //System.out.println(checkLogin("admin","admin"));
    }

    static void addProductsToDb(String kategoria, String emri, String cmimi, javax.swing.JLabel jLabelhandleMsg) {
        Connection c = null;
        Statement stmt = null;
        int goterror = 0;
        kategoria = kategoria.replaceAll("\\s","");
        System.out.println(kategoria);
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src\\com\\datBar\\Storage.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "INSERT INTO Produktet (Emri, Cmimi, Sasia, Kategoria) " +
                    "VALUES ('"+emri+"', '"+cmimi+"', '0', '"+kategoria+"' );"; 
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.err.println( e.getMessage() );
            if(e.getMessage().equals("UNIQUE constraint failed: Produktet.Emri")){
                System.out.println("Wtfdude?");
                jLabelhandleMsg.setText("Nje produkt me ate emer ekziston.");
                goterror = 1;
            }
            else{
                System.out.println("Not working");
                System.exit(0);
            }
        }
        if(goterror == 0){jLabelhandleMsg.setText("Produkti u shtua");}
        System.out.println("Records created successfully.");
        //return "?";
    }

    static int shtoFature(){
        Connection c = null;
        Statement stmt = null;
        int row = -1;
        int goterror = 0;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src\\com\\datBar\\Storage.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "INSERT INTO Fatura (ID_User) " +
                    "VALUES ('1');"; 
        stmt.executeUpdate(sql);
        row = stmt.getGeneratedKeys().getInt(1);
        stmt.close();
        c.commit();
        c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.err.println( e.getMessage() );
                System.exit(0);
        }
        System.out.println("Records created successfully.");
        return row;
    }
    
    public static String getKategori(int id){
        Connection c = null;
        String  kat = "";
        Statement stmt = null;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src\\com\\datBar\\Storage.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery( "SELECT Kategoria FROM Produktet WHERE ID_Prod = '"+id+"';" );
        while ( rs.next() ) {
            //int id = rs.getInt("id");
            kat = rs.getString("Kategoria");
            //int age  = rs.getInt("age");
            //String  address = rs.getString("address");
            //float salary = rs.getFloat("salary");
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
        }
        System.out.println("Operation done successfully");
        return fixKat(kat);
    }
    public static String fixKat(String kat){
        if(kat.equals("TeFtohta")){
            kat = "Te Ftohta";
        }
        else if(kat.equals("TeNxehta")){
            kat = "Te Nxehta";
        }
        return kat;
    }

    static void shtoShitje(int faturaID, int emriID, String sasia) {
        String kat = getKategori(emriID);
        fixKat(kat);
        Connection c = null;
        Statement stmt = null;
        int row = -1;
        int goterror = 0;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src\\com\\datBar\\Storage.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "INSERT INTO ProduktiShitur (ID_Prod, ID_Fatura, Sasia) " +
                    "VALUES ('"+emriID+"', '"+faturaID+"', '"+sasia+"' );"; 
        stmt.executeUpdate(sql);
        //works
        stmt.close();
        c.commit();
        c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.err.println( e.getMessage() );
                System.exit(0);
        }
        System.out.println("Records created successfully.");
    }

    /*static int getTotalin() {
        Connection c = null;
        int totali = 0;
        Statement stmt = null;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src\\com\\datBar\\Storage.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery( "SELECT Totali FROM Shitjet_Fatura ;" );
        while ( rs.next() ) {
            int tot = rs.getInt("Totali");
            totali += tot;
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
        }
        System.out.println(totali);
        return totali;

    }*/

    static int getProductID(String emri, String lloji) {
        Connection c = null;
        int id = 0;
        Statement stmt = null;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:contents.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery( "SELECT ID_"+lloji+" FROM "+lloji+" WHERE Emri = '"+emri+"' ;" );
        while ( rs.next() ) {
            id = rs.getInt("ID_"+lloji);
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return id;
    }

    static String getInfo(String lloji, String klienti, String tipi) {
        Connection c = null;
        String info = "";
        Statement stmt = null;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:contents.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery( "SELECT "+tipi+" FROM "+lloji+" WHERE ID_"+lloji+" = '"+klienti+"' ;" );
        while ( rs.next() ) {
            info = rs.getString(tipi);
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return info;
    }

    static int checkifExists(String tabela, String lloji, String value) {
        //Select count(1) from Klientet where Emri = 'asdas'  
        Connection c = null;
        int info = 0;
        Statement stmt = null;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:contents.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs;
        System.out.println( "SELECT count(1) FROM "+tabela+" WHERE "+lloji+" = '"+value+"' ;" );
        rs = stmt.executeQuery( "SELECT count(1) FROM "+tabela+" WHERE "+lloji+" = '"+value+"' ;" );
        while ( rs.next() ) {
            info = rs.getInt(1);
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return info;
    }

    static void ndryshoProdukt(String text, String text0, String text1, String text2, String text3, String text4, javax.swing.JLabel jLabelhandleMsg) {
        Connection c = null;
        Statement stmt = null;
        int goterror = 0;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:contents.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        System.out.println("UPDATE Produktet SET Emri = '"+text0+"', Sasia = '"+text1+"'"
                + ", Cmim_hyrje = '"+text2+"', Cmim_dalje = '"+text3+"', Pershkrimi = '"+text4+"'"
                + " WHERE Kodi = "+text+";");
        String sql = "UPDATE Produktet SET Emri = '"+text0+"', Sasia = '"+text1+"'"
                + ", Cmim_hyrje = '"+text2+"', Cmim_dalje = '"+text3+"', Pershkrimi = '"+text4+"'"
                + " WHERE Kodi = "+text+";";
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.err.println( e.getMessage() );
            if(e.getMessage().equals("UNIQUE constraint failed: Produktet.Emri")){
                jLabelhandleMsg.setText("Nje produkt me ate emer ekziston.");
                goterror = 1;
            }
            else{
                System.out.println("Not working");
                System.exit(0);
            }
        }
        if(goterror == 0){jLabelhandleMsg.setText("Klienti u shtua");}
        System.out.println("Records created successfully.");
        //return "?";    }

        }
    static void shtoProdukt(String text, String text0, String text1, String text2, String text3, String text4, javax.swing.JLabel jLabelhandleMsg) {
        Connection c = null;
        Statement stmt = null;
        int goterror = 0;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:contents.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "INSERT INTO Produktet (Kodi, Emri, Sasia, Cmim_hyrje, Cmim_dalje, Pershkrimi) " +
                    "VALUES ('"+text+"','"+text0+"', '"+text1+"', '"+text2+"', '"+text3+"', '"+text+"');";
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.err.println( e.getMessage() );
            if(e.getMessage().equals("UNIQUE constraint failed: Produktet.Emri")){
                System.out.println("Wtfdude?");
                jLabelhandleMsg.setText("Nje produkt me ate emer ekziston.");
                goterror = 1;
            }
            else{
                System.out.println("Not working");
                System.exit(0);
            }
        }
        if(goterror == 0){jLabelhandleMsg.setText("Klienti u shtua");}
        System.out.println("Records created successfully.");
        //return "?";    }
    }
}

final class Pair {
    private final String first;
    private final int second;

    public Pair(String first, int second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}
