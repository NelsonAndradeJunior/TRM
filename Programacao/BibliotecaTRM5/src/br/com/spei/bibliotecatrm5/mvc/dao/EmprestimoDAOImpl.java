package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;

public class EmprestimoDAOImpl implements EmprestimoDAO {

	@Override
	public List<Emprestimo> listAll() {
		List<Emprestimo> listaEmprestimo = new ArrayList<>();
		
		try {
			Connection conexao = Conexao.getInstance().getConnection();
			
			String query = "SELECT * FROM EMPRESTIMO";
			
			Statement stmt = conexao.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Emprestimo emprestimo = new Emprestimo();
				
				int idUsuario = rs.getInt("ID_USUARIO");
				String matUsuario = rs.getString("MAT_USUARIO");
				String nomeUsuario = rs.getString("NOME_USUARIO");
				String catUsuario = rs.getString("CAT_USUARIO");
				String dtEmprestimo = rs.getString("DT_EMPRESTIMO");
				
				
				emprestimo.setIdUsuario(idUsuario);
				emprestimo.setMatUsuario(matUsuario);
				emprestimo.setNomeUsuario(nomeUsuario);
				emprestimo.setCatUsuario(catUsuario);
				emprestimo.setDtEmprestimo(dtEmprestimo);
				
				listaEmprestimo.add(emprestimo);
			}
			
		} catch (SQLException e) {
			// TODO Tratar
			e.printStackTrace();
			return null;
		};
		
		return listaEmprestimo;
	}

}
