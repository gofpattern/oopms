package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.Date;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Assignment;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AssignmentDao {
	private Session session;
	private Transaction tx = null;
	private static Logger log = Logger.getLogger(CreateProjectController.class);

	public boolean insertAssigment(Assignment assignment) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(assignment);
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

	public Assignment getUserRole(Project project, BigDecimal developerId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Assignment where developerId = :developerId and project= :projectId and ((endDate > :currentDate) or (endDate is null)) ";
			Query query = session.createQuery(hql);
			query.setParameter("developerId", developerId);
			query.setParameter("projectId", project);
			query.setParameter("currentDate", new Date());
			Assignment role = (Assignment) query.uniqueResult();
			session.getTransaction().commit();
			log.error("role la : " + role);
			return role;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean removeTeamMember(Project project, BigDecimal developerId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Assignment where developerId = :developerId and project= :projectId and ((endDate > :currentDate) or (endDate is null)) ";
			Query query = session.createQuery(hql);
			query.setParameter("developerId", developerId);
			query.setParameter("projectId", project);
			query.setParameter("currentDate", new Date());
			Assignment removeMember = (Assignment) query.uniqueResult();
			removeMember.setEndDate(new Date());
			session.merge(removeMember);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Insert ko duoc");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert ngon");
		return true;
	}

	public boolean removeProjectManager(Project project) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Assignment where (TYPE = :PMType or TYPE = :POaPMType) and project= :projectId and ((endDate > :currentDate) or (endDate is null)) ";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", project);
			query.setParameter("PMType", new BigDecimal(
					Constant.ProjectManagerType));
			query.setParameter("POaPMType", new BigDecimal(
					Constant.ProjectOwnerAndProjectManagerType));
			query.setParameter("currentDate", new Date());
			Assignment removeMember = (Assignment) query.uniqueResult();
			removeMember.setEndDate(new Date());
			session.merge(removeMember);
			session.getTransaction().commit();
			// assign new role after reject PM role
			Assignment assignment = new Assignment();
			assignment.setProject(project);
			assignment.setDeveloperId(removeMember.getDeveloperId());
			assignment.setBeginDate(new Date());
			if (Constant.ProjectManagerType.equals(String.valueOf(removeMember
					.getType()))) {
				assignment.setType(new Byte(Constant.DeveloperType));
			} else {
				assignment.setType(new Byte(Constant.ProjectOwnerType));
			}
			insertAssigment(assignment);
		} catch (Exception e) {
			log.error("Insert ko duoc");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert ngon");
		return true;
	}

}
