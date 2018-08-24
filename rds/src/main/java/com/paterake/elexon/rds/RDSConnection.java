package com.paterake.elexon.rds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RDSConnection {

  public Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    Connection conn = DriverManager.getConnection(
        "jdbc:postgresql://hudsonenergy-poc.cxme3bvkiiwa.eu-west-1.rds.amazonaws.com:5432/hudsonenergypoc"
        , "hudsonenergypoc"
        , "hudsonenergypoc");
    conn.setAutoCommit(false);
    return conn;

  }

}

/*
mbadger@hudsonenergy.co.uk
Npower12
*/