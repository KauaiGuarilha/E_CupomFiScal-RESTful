package br.com.vendaProduto.DAO;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.vendaProduto.util.HibernateUtil;

/**
*
* @author Kauai Guarilha
*/
public class GenericDAO<Entidade> {

	@SuppressWarnings("unused")
	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	public void inclusao(Entidade entidade) {

		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();

		Transaction transacao = null; // Controlar a transação

		try {
			transacao = sessao.beginTransaction();

			sessao.save(entidade);

			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;

		} finally {
			sessao.close();
		}
	}
	
	public void alteracao(Entidade entidade) {

		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();

			sessao.update(entidade);

			transacao.commit();

		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;

		} finally {
			sessao.close();
		}
	}
	
	public void exclusao(Entidade entidade) {

		Session sessao = HibernateUtil.getFabricaDeSessao().openSession(); 

		Transaction transacao = null; 

		try {
			transacao = sessao.beginTransaction();

			sessao.delete(entidade);

			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;

		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listagem() {

		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();

		try {
			Criteria consulta = sessao.createCriteria(classe);

			List<Entidade> resultado = consulta.list();

			return resultado;

		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listagem(String campoOrdenacao) { // Listar em Ordem Alfabética

		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();

		try {
			Criteria consulta = sessao.createCriteria(classe);

			consulta.addOrder(Order.asc(campoOrdenacao)); // Ordenação ascendente
			
			List<Entidade> resultado = consulta.list();

			return resultado;

		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
	}


	@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo) {

		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();

		try {
			Criteria consulta = sessao.createCriteria(classe);

		    consulta.add(Restrictions.idEq(codigo));
			                                            
			Entidade resultado = (Entidade) consulta.uniqueResult(); 

			return resultado;

		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}
	
	public void merge(Entidade entidade) {

		Session sessao = HibernateUtil.getFabricaDeSessao().openSession(); 

		Transaction transacao = null; 

		try {
			transacao = sessao.beginTransaction();

			sessao.merge(entidade);

			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;

		} finally {
			sessao.close();
		}
	}
}
