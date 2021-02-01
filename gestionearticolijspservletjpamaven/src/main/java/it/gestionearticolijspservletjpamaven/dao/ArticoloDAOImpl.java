package it.gestionearticolijspservletjpamaven.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.gestionearticolijspservletjpamaven.model.Articolo;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;

	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();
	}

	@Override
	public Articolo findOne(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Valore input non valido");
		}
		input = entityManager.merge(input);

	}

	@Override
	public void insert(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Articolo input) throws Exception {
		if (input == null || input.getId() < 1) {
			throw new Exception("Problema valore di input");
		}
		entityManager.remove(entityManager.merge(input));

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Valore input non valido");
		}
		
		 Map<String, Object> paramaterMap = new HashMap<String, Object>();
		 List<String> whereCause = new ArrayList<String>();
		 
		 StringBuilder queryBuilder = new StringBuilder();
		 queryBuilder.append("select a from Articolo a ");
		 
		 if(!StringUtils.isEmpty(input.getDescrizione())) {
			 whereCause.add(" a.descrizione=:descrizione");
		     paramaterMap.put("descrizione", input.getDescrizione());
		 }
		 if(input.getPrezzo()!=null && input.getPrezzo()!=0) {
			 whereCause.add("a.prezzo=:prezzo");
			 paramaterMap.put("prezzo",input.getPrezzo());
		 }
		 if(!input.getCodice().isEmpty()) {
			 whereCause.add("a.codice=:codice");
			 paramaterMap.put("codice",input.getCodice());
		 }
		 
		 if(input.getDataArrivo()!=null) {
			 whereCause.add("a.dataarrivo=:dataarrivo");
			 paramaterMap.put("dataarrivo", input.getDataArrivo());
		 }
		 
		 queryBuilder.append(" where " + StringUtils.join(whereCause, " and "));
		 Query query = entityManager.createQuery(queryBuilder.toString(), Articolo.class);
		 
		 for(String key :paramaterMap.keySet()) {
	            query.setParameter(key, paramaterMap.get(key));
	    }
		
		
		return query.getResultList();
		
		
		
		
//		String query="select * from articolo where 1=1";
//		if(input.getDescrizione()!=null && input.getDescrizione().equals("")) {
//			query+=" and descrizione='" + input.getDescrizione() +"' ";
//		}
//		if(input.getCodice()!=null && input.getCodice().equals("")) {
//			query+=" and codice='" + input.getCodice() +"' ";
//		}
//		if(input.getPrezzo()!=null) {
//			query+=" and prezzo='" + input.getPrezzo() +"' ";
//		}
//		if(input.getDataArrivo()!=null) {
//			query += " and dataarrivo'" + new java.sql.Date(input.getDataArrivo().getTime()) + "' ";
//		}
//		
//		return entityManager.createQuery(query, Articolo.class).getResultList();
//		
		
		
	}

}
