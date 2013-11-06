package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Reserva;

public class ReservaDAOImpl implements ReservaDAO {
	
	public List<Reserva> listAll() {
		List<Reserva> listaReserva = new ArrayList<>();
		
		try {
			Connection conexao = Conexao.getInstance().getConnection();
			
			String query = "SELECT * FROM RESERVA";
			
			Statement stmt = conexao.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Reserva reserva = new Reserva();
				
				int idReserva = rs.getInt("ID_RESERVA");
				String catUsuario = rs.getString("CAT_USUARIO");
				String nomeUsuario = rs.getString("NOME_USUARIO");
				String dtReserva = rs.getString("DT_RESERVA");
				
				
				reserva.setIdReserva(idReserva);
				reserva.setCatUsuario(catUsuario);
				reserva.setNomeUsuario(nomeUsuario);
				reserva.setDtReserva(dtReserva);
				
				listaReserva.add(reserva);
			}
			
		} catch (SQLException e) {
			// TODO Tratar
			e.printStackTrace();
			return null;
		};
		
		return listaReserva;
	}

}
