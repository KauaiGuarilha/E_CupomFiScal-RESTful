package br.com.vendaProduto.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.vendaProduto.DAO.Item_CupomDAO;
import br.com.vendaProduto.domain.Item_Cupom;

/**
*
* @author Kauai Guarilha
*/
@Path("itemCupom")
public class ItemCupomService {
	//http://localhost:8080/VendaProjeto/rest/itemCupom
	@GET
	public String listar() {
		
		Item_CupomDAO itemCupomDao = new Item_CupomDAO();
		
		List<Item_Cupom> itemCupons = itemCupomDao.listagem("produto");
		
		Gson gson = new Gson();
		
		String json = gson.toJson(itemCupons);
		
		return json;		
	}
	//http://localhost:8080/VendaProjeto/rest/itemCupom/1
	@GET
	@Path("{codigo}")
	public String buscar(@PathParam("codigo") Long codigo) {
		
		Item_CupomDAO itemCupomDao = new Item_CupomDAO();
		
		Item_Cupom itemCupom = itemCupomDao.buscar(codigo);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(itemCupom);
		
		return json;
	}
	
	@POST
	public String salvar(String json) {
		
		Gson gson = new Gson();
		
		Item_Cupom itemCupom = gson.fromJson(json, Item_Cupom.class);
		
		Item_CupomDAO itemCupomDao = new Item_CupomDAO();
		
		itemCupomDao.inclusao(itemCupom);
		
		String jsonSaida = gson.toJson(itemCupom);
		
		return jsonSaida;		
	}
	
	@PUT
	public String editar(String json){
		
		Gson gson = new Gson();
		
        Item_Cupom itemCupom = gson.fromJson(json, Item_Cupom.class);
		
		Item_CupomDAO itemCupomDao = new Item_CupomDAO();
		
		itemCupomDao.merge(itemCupom);
		
		String jsonSaida = gson.toJson(itemCupom);
		
		return jsonSaida;
	}
	
	@DELETE
	public String excluir(String json){
		
        Gson gson = new Gson();
		
        Item_Cupom itemCupom = gson.fromJson(json, Item_Cupom.class);
		
		Item_CupomDAO itemCupomDao = new Item_CupomDAO();
		
		itemCupomDao.exclusao(itemCupom);
		
		String jsonSaida = gson.toJson(itemCupom);
		
		return jsonSaida;
	}	
}
