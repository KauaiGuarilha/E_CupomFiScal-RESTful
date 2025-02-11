package br.com.vendaProduto.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.vendaProduto.DAO.ProdutoDAO;
import br.com.vendaProduto.domain.Produto;

/**
*
* @author Kauai Guarilha
*/
@ViewScoped
@ManagedBean
public class ProdutoBean {

	private Produto produto;	
	private List<Produto> produtos;
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	
	
	public void novo() {
		produto = new Produto();
	}
	
    public void salvar() {
		
		try{
			ProdutoDAO produtoDao = new ProdutoDAO();
			
			produtoDao.merge(produto);
			
			novo();
			
			produtos = produtoDao.listagem();
			
			Messages.addGlobalInfo("Salvo com sucesso !");
			
			
		}catch(RuntimeException ex) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Produto!" + ex);
			ex.printStackTrace();
		}
    }
    
    @PostConstruct 
    public void listar() {
			
		try {
			ProdutoDAO produtoDao = new ProdutoDAO();
				
			produtos = produtoDao.listagem();
				
				
		}catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu um erro ao tentar Listar" + ex);
			ex.printStackTrace();
		}
    }
    
    public void exclusao(ActionEvent evento) {
    	
        try {
			
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ProdutoDAO produtoDao = new ProdutoDAO();
			
			produtoDao.exclusao(produto);
			
			produtos = produtoDao.listagem();
			
			Messages.addGlobalInfo("Excluído com sucesso!");
			
		}catch(RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao fazer a exclusão!");
			erro.printStackTrace();
		}
    }
    
    public void alteracao(ActionEvent evento) {    	
    	produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
    }    
}
    
