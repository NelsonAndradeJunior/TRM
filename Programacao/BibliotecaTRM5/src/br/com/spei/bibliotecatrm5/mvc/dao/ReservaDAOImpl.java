package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
				
				
				// TODO Implementar
				
				listaReserva.add(reserva);
			}
			
		} catch (SQLException e) {
			// TODO Tratar
			e.printStackTrace();
			return null;
		};
		
		return listaReserva;
	}

	@Override
	public void insert(Reserva reserva) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();

		conexao.setAutoCommit(false);
		
		String query = "INSERT INTO RESERVA (ID_USUARIO, ID_EXEMPLAR, DT_RESERVA) VALUES (?, ?, ?)";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		pstmt.setInt(1, reserva.getUsuario().getCodUsuario());
		pstmt.setInt(2, reserva.getExemplar().getCodExemplar());
		
		java.sql.Date dataReserva = new java.sql.Date(reserva.getDataReserva().getTime()); 
		
		pstmt.setDate(3, dataReserva);
		
		pstmt.executeUpdate();
		
		ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
		exemplarDAO.update(reserva.getExemplar());
		
		conexao.commit();
		
		pstmt.close();
		conexao.close();
	}

}
