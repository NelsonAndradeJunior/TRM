package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;

public class TipoObraDAOImpl implements TipoObraDAO {

	@Override
	public List<TipoObra> listAll() {
		List<TipoObra> listaTipoObra = new ArrayList<>();
		
		try {
			Connection conexao = Conexao.getInstance().getConnection();
			
			String query = "SELECT * FROM TIPO_OBRA";
			
			Statement stmt = conexao.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				TipoObra tipoObra = new TipoObra();
				
				int codigoObra = rs.getInt("ID_TIPO_OBRA");
				String descricaoTipoObra = rs.getString("DS_TIPO_OBRA");
				
				tipoObra.setCodObra(codigoObra);
				tipoObra.setDescricaoTipoObra(descricaoTipoObra);
				
				listaTipoObra.add(tipoObra);
			}
			
		} catch (SQLException e) {
			// TODO Tratar
			e.printStackTrace();
			return null;
		};
		
		return listaTipoObra;
	}

}
