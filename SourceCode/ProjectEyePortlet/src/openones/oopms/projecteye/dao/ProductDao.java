package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Language;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Workproduct;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProductDao {
	private Session session;
	private Transaction tx = null;
	private static Logger log = Logger.getLogger(CreateProjectController.class);

	public List<Workproduct> getWorkProductList() {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Workproduct";
			Query query = session.createQuery(hql);
			List<Workproduct> workProductList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("workProductList Count : " + workProductList.size());
			return workProductList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<Language> getProductSizeUnitList() {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Language";
			Query query = session.createQuery(hql);
			List<Language> productSizeUnitList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("productSizeUnitList Count : "
					+ productSizeUnitList.size());
			return productSizeUnitList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean insertProduct(Module product) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(product);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert ko duoc");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert ngon");
		return true;
	}
	
	public boolean updateProduct(Module product) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.merge(product);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Update fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Update success");
		return true;
	}
	
	public boolean deteleProduct(String productId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "Delete From Module where moduleId = :moduleId";
			Query query = session.createQuery(hql);
			query.setParameter("moduleId", new BigDecimal(productId));
			query.executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	public List<Module> getProjectProductList(Project project, String searchType) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "";
			if(searchType.equals("All")) {
				hql = "From Module where project = :projectId";
			} else {
				hql = "From Module where project = :projectId and workproduct = :workProductCode";				
			}
			Query query = session.createQuery(hql);
			query.setParameter("projectId", project);
			if(!searchType.equals("All")) {
				Workproduct workProduct = new Workproduct();
				workProduct.setCode(searchType);
				query.setParameter("workProductCode", workProduct);				
			}			
			List<Module> productList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("product list size : " + productList.size());
			return productList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public Workproduct getWorkProduct(String workProductCode) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Workproduct where code = :workProductCode";
			Query query = session.createQuery(hql);
			query.setParameter("workProductCode", workProductCode);
			Workproduct workProduct = (Workproduct) query.uniqueResult();
			session.flush();
			session.getTransaction().commit();
			return workProduct;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public Language getProductSizeUnit(BigDecimal languageId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Language where languageId = :languageId";
			Query query = session.createQuery(hql);
			query.setParameter("languageId", languageId);
			Language productSizeUnit = (Language)query.uniqueResult();
			session.flush();
			session.getTransaction().commit();
			return productSizeUnit;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public Module getProduct(BigDecimal productId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Module where moduleId = :moduleId";
			Query query = session.createQuery(hql);
			query.setParameter("moduleId", productId);
			Module product = (Module)query.uniqueResult();
			session.flush();
			session.getTransaction().commit();
			return product;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	

}
