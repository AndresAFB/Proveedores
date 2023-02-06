package main.java.proveedores;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Principal {
	
	public static void main(String[] args) {
		if (args.length > 1) {
			System.out.println ("Se ha recibido mas de un parámetro. Introduzca un solo parámetro.");
		} else {
			String consulta="SELECT * FROM proveedores where id_proveedor = ";
	        try {
	            Statement sentencia=Conexion.obtener().createStatement();
	            ResultSet resultado=sentencia.executeQuery(consulta + args[0]);
	            if (resultado.next()){
	            	List<Object> lista = new ArrayList<>();
	            	for (int i = 1; i < 5; ++i) {
	            		if (i == 3) {
	            			String fecha = formatearFecha(resultado.getString(i));
	            			lista.add(fecha);
	            		} else {
	            			lista.add(resultado.getString(i));
	            		}
	            	}
	            	String res = lista.toString().replaceAll("\\[*\\]*", "");
	            	PrintWriter writer = new PrintWriter("proveedor.txt", "UTF-8");
	                writer.println("Proveedor:");
	                writer.println(res);
	                writer.close();
	            } else {
	            	System.out.println("El cliente no tiene proveedores asignados.");
	            }
	            
	        } catch (ClassNotFoundException | SQLException | FileNotFoundException | UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	private static String formatearFecha(String fecha) {
		String[] args = fecha.split("-");
		String y = args[0];
		String m = args[1];
		String d = args[2];
		String[] args2 = d.split(" ");
		d = args2[0];
		fecha = d + "/" + m + "/" + y;
		return fecha;
	}

}
