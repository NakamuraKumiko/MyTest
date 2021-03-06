package com.tuyano.springboot;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public MyDataDaoImpl() {
		super();
	}

	public MyDataDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}

	@Override
	public List<MyData> getAll() {
		Query query = entityManager.createQuery("from MyData");
		@SuppressWarnings("unchecked")
		List<MyData> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public MyData findById(long id) {
		return (MyData) entityManager.createQuery("from MyData where id = "
				+ id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MyData> findByName(String name) {
		return (List<MyData>) entityManager.createQuery("from MyData where name = "
				+ name).getResultList();
	}

	/*	@Override
		public List<MyData> find(String fstr) {
			List<MyData> list = null;
			String qstr = "from MyData where id = :fstr";
			Query query = entityManager.createQuery(qstr)
					.setParameter("fstr", Long.parseLong(fstr));
			list = query.getResultList();
			return list;
		}*/

	/*	@SuppressWarnings("unchecked")
		@Override
		public List<MyData> find(String fstr){
			List<MyData> list = null;
			String qstr = "from MyData where id = :fid or name like :fname or mail like :fmail";
			Long fid = 0L;
			try {
				fid = Long.parseLong(fstr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			Query query = entityManager.createQuery(qstr).setParameter("fid", fid)
					.setParameter("fname", "%" + fstr + "%")
					.setParameter("fmail", fstr + "@%");
			list = query.getResultList();
			return list;
		}*/

	/*	@SuppressWarnings("unchecked")
		@Override
		public List<MyData> find(String fstr){
			List<MyData> list = null;
			String qstr = "from MyData where id = ?1 or name like ?2 or mail like ?3";
			Long fid = 0L;
			try {
				fid = Long.parseLong(fstr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			Query query = entityManager.createQuery(qstr).setParameter(1, fid)
				.setParameter(2, "%" + fstr + "%")
				.setParameter(3, fstr + "@%");
			list = query.getResultList();
			return list;
		}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<MyData> find(String fstr){
		List<MyData> list = null;
		//String qstr = "from MyData where id = ?1 or name like ?2 or mail like ?3";
		Long fid = 0L;
		try {
			fid = Long.parseLong(fstr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		Query query = entityManager
				.createNamedQuery("findWithName")
				.setParameter("fname", "%" + fstr + "%");

		/*		Query query = entityManager.createQuery(qstr).setParameter(1, fid)
					.setParameter(2, "%" + fstr + "%")
					.setParameter(3, fstr + "@%");*/
		/*		String qstr = "from MyData where id = :fstr";
				Query query = entityManager.createQuery(qstr)
					.setParameter("fstr", Long.parseLong(fstr));*/
		/*		Long fid = 0L;
				try {
					fid = Long.parseLong(fstr);
				} catch (NumberFormatException e) {
					//e.printStackTrace();
				}
				Query query = entityManager
						.createNamedQuery("findWithName")
						.setParameter("fname", "%" + fstr + "%");*/


		list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MyData> findByAge(int min, int max) {
		return (List<MyData>)entityManager
			.createNamedQuery("findByAge")
			.setParameter("min", min)
			.setParameter("max", max)
			.getResultList();
	}

}