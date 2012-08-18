package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.OopmsBudget;
import openones.oopms.projecteye.model.OopmsCostDailyExpense;
import openones.oopms.projecteye.model.OopmsCostOneTimeExpense;
import openones.oopms.projecteye.model.OopmsCostType;
import openones.oopms.projecteye.model.OopmsExceptionalCost;
import openones.oopms.projecteye.model.OopmsProjectCost;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CostDao {
	private Session session;
	private Transaction tx = null;
	private static Logger log = Logger.getLogger(CreateProjectController.class);

	public boolean insertOneTimeExpense(OopmsCostOneTimeExpense oneTimeExpense) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(oneTimeExpense);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert success");
		return true;
	}

	public List<OopmsCostOneTimeExpense> getOneTimeExpenseList(String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostOneTimeExpense where projectId = :projectId";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			List<OopmsCostOneTimeExpense> expenseList = query.list();
			session.getTransaction().commit();
			return expenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<OopmsCostOneTimeExpense> getOneTimeExpensePlanList(
			String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostOneTimeExpense where projectId = :projectId and isPay is null";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			List<OopmsCostOneTimeExpense> expenseList = query.list();
			session.getTransaction().commit();
			return expenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<OopmsCostOneTimeExpense> getOneTimeExpenseInvoiceList(
			String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostOneTimeExpense where projectId = :projectId and isPay is not null";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			List<OopmsCostOneTimeExpense> expenseList = query.list();
			session.getTransaction().commit();
			return expenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public OopmsCostOneTimeExpense getOneTimeExpense(
			String oopmsCostOneTimeExpenseId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostOneTimeExpense where oopmsCostOneTimeExpenseId = :oopmsCostOneTimeExpenseId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsCostOneTimeExpenseId", new BigDecimal(
					oopmsCostOneTimeExpenseId));
			OopmsCostOneTimeExpense expense = (OopmsCostOneTimeExpense) query
					.uniqueResult();
			session.getTransaction().commit();
			return expense;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean updateOneTimeExpense(OopmsCostOneTimeExpense oneTimeExpense) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.merge(oneTimeExpense);
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

	public boolean insertCostType(OopmsCostType costType) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(costType);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert success");
		return true;
	}

	public List<OopmsCostType> getCostTypeList(String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostType where projectId = :projectId";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			List<OopmsCostType> costTypeList = query.list();
			session.getTransaction().commit();
			return costTypeList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public OopmsCostType getCostType(BigDecimal costTypeId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostType where oopmsCostTypeId = :oopmsCostTypeId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsCostTypeId", costTypeId);
			OopmsCostType costType = (OopmsCostType) query.uniqueResult();
			session.getTransaction().commit();
			return costType;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean updateCostType(OopmsCostType costType) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.merge(costType);
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

	public OopmsCostDailyExpense getDailyExpense(
			BigDecimal oopmsCostDailyExpenseId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostDailyExpense where oopmsCostDailyExpenseId = :oopmsCostDailyExpenseId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsCostDailyExpenseId",
					oopmsCostDailyExpenseId);
			OopmsCostDailyExpense cost = (OopmsCostDailyExpense) query
					.uniqueResult();
			session.getTransaction().commit();
			return cost;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean insertDailyExpense(OopmsCostDailyExpense dailyExpense) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(dailyExpense);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert success");
		return true;
	}

	public boolean updateDailyExpense(OopmsCostDailyExpense dailyExpense) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.merge(dailyExpense);
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

	public List<OopmsCostDailyExpense> getDailyExpenseList(String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostDailyExpense where projectId = :projectId";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			List<OopmsCostDailyExpense> dailyExpenseList = query.list();
			session.getTransaction().commit();
			return dailyExpenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<OopmsCostDailyExpense> getDailyExpensePlanList(String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostDailyExpense where projectId = :projectId and isPay is null";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			List<OopmsCostDailyExpense> dailyExpenseList = query.list();
			session.getTransaction().commit();
			return dailyExpenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<OopmsCostDailyExpense> getDailyExpenseInvoiceList(
			String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostDailyExpense where projectId = :projectId and isPay is not null";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			List<OopmsCostDailyExpense> dailyExpenseList = query.list();
			session.getTransaction().commit();
			return dailyExpenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean insertExceptionalCost(OopmsExceptionalCost exceptionalCost) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(exceptionalCost);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert success");
		return true;
	}

	public boolean updateExceptionalCost(OopmsExceptionalCost exceptionalCost) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.merge(exceptionalCost);
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

	public List<OopmsExceptionalCost> getExceptionalExpenseList(String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsExceptionalCost where projectId = :projectId and type = :ExpenseType";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			query.setParameter("ExpenseType", new BigDecimal(
					Constant.ExceptinalExpenseType));
			List<OopmsExceptionalCost> ExpenseList = query.list();
			session.getTransaction().commit();
			return ExpenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<OopmsExceptionalCost> getExceptionalExpensePlanList(
			String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsExceptionalCost where projectId = :projectId and type = :ExpenseType and isPay is null";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			query.setParameter("ExpenseType", new BigDecimal(
					Constant.ExceptinalExpenseType));
			List<OopmsExceptionalCost> ExpenseList = query.list();
			session.getTransaction().commit();
			return ExpenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<OopmsExceptionalCost> getExceptionalExpenseInvoiceList(
			String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsExceptionalCost where projectId = :projectId and type = :ExpenseType and isPay is not null";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			query.setParameter("ExpenseType", new BigDecimal(
					Constant.ExceptinalExpenseType));
			List<OopmsExceptionalCost> ExpenseList = query.list();
			session.getTransaction().commit();
			return ExpenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<OopmsExceptionalCost> getExceptionalDeductList(String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsExceptionalCost where projectId = :projectId and type = :DeductType";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			query.setParameter("DeductType", new BigDecimal(
					Constant.ExceptinalDeductType));
			List<OopmsExceptionalCost> ExpenseList = query.list();
			session.getTransaction().commit();
			return ExpenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<OopmsExceptionalCost> getExceptionalDeductPlanList(
			String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsExceptionalCost where projectId = :projectId and type = :DeductType and isPay is null";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			query.setParameter("DeductType", new BigDecimal(
					Constant.ExceptinalDeductType));
			List<OopmsExceptionalCost> ExpenseList = query.list();
			session.getTransaction().commit();
			return ExpenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<OopmsExceptionalCost> getExceptionalDeductInvoiceList(
			String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsExceptionalCost where projectId = :projectId and type = :DeductType and isPay is not null";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			query.setParameter("DeductType", new BigDecimal(
					Constant.ExceptinalDeductType));
			List<OopmsExceptionalCost> ExpenseList = query.list();
			session.getTransaction().commit();
			return ExpenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<OopmsCostDailyExpense> getDailyExpenseListOfAType(
			BigDecimal projectId, BigDecimal typeId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostDailyExpense where projectId = :projectId and oopmsCostTypeId = :typeId";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", projectId);
			query.setParameter("typeId", typeId);
			List<OopmsCostDailyExpense> dailyExpenseList = query.list();
			session.getTransaction().commit();
			return dailyExpenseList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean deleteOneTimeExpense(String oopmsCostOneTimeExpenseId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "Delete From OopmsCostOneTimeExpense where oopmsCostOneTimeExpenseId = :oopmsCostOneTimeExpenseId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsCostOneTimeExpenseId", new BigDecimal(
					oopmsCostOneTimeExpenseId));
			query.executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean deleteDailyExpense(String oopmsCostDailyExpenseId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "Delete From OopmsCostDailyExpense where oopmsCostDailyExpenseId = :oopmsCostDailyExpenseId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsCostDailyExpenseId", new BigDecimal(
					oopmsCostDailyExpenseId));
			query.executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean forcedDeleteDailyExpense(String oopmsCostDailyExpenseId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "Delete From OopmsCostDailyExpense where oopmsCostDailyExpenseId = :oopmsCostDailyExpenseId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsCostDailyExpenseId", new BigDecimal(
					oopmsCostDailyExpenseId));
			query.executeUpdate();
			String hql2 = "Delete From OopmsExceptionalCost where oopmsCostDailyExpenseId = :oopmsCostDailyExpenseId";
			Query query2 = session.createQuery(hql2);
			query2.setParameter("oopmsCostDailyExpenseId", new BigDecimal(
					oopmsCostDailyExpenseId));
			query2.executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean deleteExceptionalCost(String oopmsExceptionalCostId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "Delete From OopmsExceptionalCost where oopmsExceptionalCostId = :oopmsExceptionalCostId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsExceptionalCostId", new BigDecimal(
					oopmsExceptionalCostId));
			query.executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean deleteCostType(String oopmsCostTypeId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "Delete From OopmsCostType where oopmsCostTypeId = :oopmsCostTypeId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsCostTypeId", new BigDecimal(
					oopmsCostTypeId));
			query.executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean forcedDeleteCostType(String oopmsCostTypeId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "Delete From OopmsCostType where oopmsCostTypeId = :oopmsCostTypeId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsCostTypeId", new BigDecimal(
					oopmsCostTypeId));
			query.executeUpdate();
			// Delete ExceptionalCost that used this type
			String hql2 = "Delete From OopmsExceptionalCost where oopmsCostTypeId = :oopmsCostTypeId";
			Query query2 = session.createQuery(hql2);
			query2.setParameter("oopmsCostTypeId", new BigDecimal(
					oopmsCostTypeId));
			query2.executeUpdate();
			// Set oopmsCostTypeId to null for Daily Expense that using this
			// type
			String hql3 = "Update OopmsCostDailyExpense Set oopmsCostTypeId = null where oopmsCostTypeId = :oopmsCostTypeId";
			Query query3 = session.createQuery(hql3);
			query3.setParameter("oopmsCostTypeId", new BigDecimal(
					oopmsCostTypeId));
			query3.executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * This function will check whether a costType Is used by another expense or
	 * not
	 * 
	 * @param oopmsCostTypeId
	 * @return
	 */
	public String checkCostTypeUsed(String oopmsCostTypeId) {
		String result = Constant.CostTypeNotUsed;
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsExceptionalCost where oopmsCostTypeId = :oopmsCostTypeId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsCostTypeId", new BigDecimal(
					oopmsCostTypeId));
			List<OopmsExceptionalCost> ExceptinalCostList = query.list();
			String hql2 = "From OopmsCostDailyExpense where oopmsCostTypeId = :oopmsCostTypeId";
			Query query2 = session.createQuery(hql2);
			query2.setParameter("oopmsCostTypeId", new BigDecimal(
					oopmsCostTypeId));
			List<OopmsCostDailyExpense> DailyExpenseList = query2.list();
			if (ExceptinalCostList != null) {
				if (ExceptinalCostList.size() > 0) {
					if (DailyExpenseList != null) {
						if (DailyExpenseList.size() > 0) {
							result = Constant.CostTypeUsedByBothExceptionalCostAndDailyCost;
						} else {
							result = Constant.CostTypeUsedByExceptionalCost;
						}
					} else {
						result = Constant.CostTypeUsedByExceptionalCost;
					}
				}
			}
			if ((DailyExpenseList != null)
					&& (result.equals(Constant.CostTypeNotUsed))) {
				if (DailyExpenseList.size() > 0) {
					result = Constant.CostTypeUsedByDailyCost;
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error in checkCostTypeUsed");
			log.error(e.getMessage());
			return null;
		}
		return result;
	}

	/**
	 * This function will check whether a DailyExpense Is used by another
	 * Exceptional expense or not
	 * 
	 * @param oopmsCostTypeId
	 * @return
	 */
	public String checkDailyExpenseUsed(String oopmsCostDailyExpenseId) {
		String result = Constant.DailyExpenseNotUsed;
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsExceptionalCost where oopmsCostDailyExpenseId = :oopmsCostDailyExpenseId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsCostDailyExpenseId", new BigDecimal(
					oopmsCostDailyExpenseId));
			List<OopmsExceptionalCost> ExceptinalCostList = query.list();
			if (ExceptinalCostList != null) {
				if (ExceptinalCostList.size() > 0) {
					result = Constant.DailyExpenseUsed;
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error in checkDailyExpenseUsed");
			log.error(e.getMessage());
			return null;
		}
		return result;
	}

	public boolean insertBudgetRecord(OopmsBudget OopmsBudget) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(OopmsBudget);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert success");
		return true;
	}

	public boolean updateBudgetRecord(OopmsBudget OopmsBudget) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.merge(OopmsBudget);
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

	public boolean deleteBudgetRecord(String oopmsBudgetId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "Delete From OopmsBudget where oopmsBudgetId = :oopmsBudgetId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsBudgetId", new BigDecimal(oopmsBudgetId));
			query.executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean insertProjectCost(OopmsProjectCost OopmsProjectCost) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(OopmsProjectCost);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert success");
		return true;
	}

	public boolean updateProjectCost(OopmsProjectCost OopmsProjectCost) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.merge(OopmsProjectCost);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert success");
		return true;
	}

	public OopmsProjectCost getProjectCost(BigDecimal projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsProjectCost where projectId = :projectId";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", projectId);
			OopmsProjectCost projectCost = (OopmsProjectCost) query
					.uniqueResult();
			session.getTransaction().commit();
			return projectCost;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public OopmsBudget getBudgetRecord(String oopmsBudgetId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsBudget where oopmsBudgetId = :oopmsBudgetId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsBudgetId", new BigDecimal(oopmsBudgetId));
			OopmsBudget budgetRecord = (OopmsBudget) query.uniqueResult();
			session.getTransaction().commit();
			return budgetRecord;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<OopmsBudget> getProjectBudgetList(String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsBudget where projectId = :projectId";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			List<OopmsBudget> projectBudgetList = query.list();
			session.getTransaction().commit();
			return projectBudgetList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public OopmsExceptionalCost getExceptionalCost(String oopmsExceptionalCostId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsExceptionalCost where oopmsExceptionalCostId = :oopmsExceptionalCostId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsExceptionalCostId", new BigDecimal(
					oopmsExceptionalCostId));
			OopmsExceptionalCost exceptionalCost = (OopmsExceptionalCost) query
					.uniqueResult();
			session.getTransaction().commit();
			return exceptionalCost;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean payDailyExpense(String oopmsCostDailyExpenseId, Date payDate) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsCostDailyExpense Where oopmsCostDailyExpenseId = :oopmsCostDailyExpenseId";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsCostDailyExpenseId", new BigDecimal(
					oopmsCostDailyExpenseId));
			OopmsCostDailyExpense oldDailyExpense = (OopmsCostDailyExpense) query
					.uniqueResult();
			if (oldDailyExpense.getEndDate() == null) {
				log.error("endDate null");
				OopmsCostDailyExpense newDailyExpense = new OopmsCostDailyExpense();
				newDailyExpense.setCost(oldDailyExpense.getCost());
				newDailyExpense.setDateUsed(oldDailyExpense.getDateUsed());
				newDailyExpense
						.setDescription(oldDailyExpense.getDescription());
				newDailyExpense.setName(oldDailyExpense.getName());
				newDailyExpense.setOopmsCostTypeId(oldDailyExpense
						.getOopmsCostTypeId());
				newDailyExpense.setProjectId(oldDailyExpense.getProjectId());
				Long payDateLong = payDate.getTime();
				Long newStartDate = payDateLong + (1000 * 60 * 60 * 24);
				newDailyExpense.setStartDate(new Date(newStartDate));
				oldDailyExpense.setIsPay("1");
				session.merge(oldDailyExpense);
				session.save(newDailyExpense);
				session.flush();
				String hql2 = "Update From OopmsExceptionalCost set oopmsCostDailyExpenseId = :newOopmsCostDailyExpenseId Where oopmsCostDailyExpenseId = :oldOopmsCostDailyExpenseId and occurDate > :payDate";
				Query query2 = session.createQuery(hql2);
				query2.setParameter("oldOopmsCostDailyExpenseId",
						 new BigDecimal(oopmsCostDailyExpenseId));
				query2.setParameter("newOopmsCostDailyExpenseId",
						newDailyExpense.getOopmsCostDailyExpenseId());
				query2.setParameter("payDate", payDate);
				query2.executeUpdate();
			} else if (oldDailyExpense.getEndDate().compareTo(payDate) > 0) {
				log.error("EndDate > payDate");
				OopmsCostDailyExpense newDailyExpense = new OopmsCostDailyExpense();
				newDailyExpense.setCost(oldDailyExpense.getCost());
				newDailyExpense.setDateUsed(oldDailyExpense.getDateUsed());
				newDailyExpense
						.setDescription(oldDailyExpense.getDescription());
				newDailyExpense.setName(oldDailyExpense.getName());
				newDailyExpense.setOopmsCostTypeId(oldDailyExpense
						.getOopmsCostTypeId());
				newDailyExpense.setProjectId(oldDailyExpense.getProjectId());
				Long payDateLong = payDate.getTime();
				Long newStartDate = payDateLong + (1000 * 60 * 60 * 24);
				newDailyExpense.setStartDate(new Date(newStartDate));
				newDailyExpense.setEndDate(oldDailyExpense.getEndDate());
				oldDailyExpense.setIsPay("1");
				session.merge(oldDailyExpense);
				session.save(newDailyExpense);
				session.flush();
				String hql2 = "Update From OopmsExceptionalCost set oopmsCostDailyExpenseId = :newOopmsCostDailyExpenseId Where oopmsCostDailyExpenseId = :oldOopmsCostDailyExpenseId and occurDate > :payDate";
				Query query2 = session.createQuery(hql2);
				query2.setParameter("oldOopmsCostDailyExpenseId",
						 new BigDecimal(oopmsCostDailyExpenseId));
				query2.setParameter("newOopmsCostDailyExpenseId",
						newDailyExpense.getOopmsCostDailyExpenseId());
				query2.setParameter("payDate", payDate);
				query2.executeUpdate();
			} else {
				log.error("EndDate <= payDate");
				oldDailyExpense.setIsPay("1");
				session.merge(oldDailyExpense);
			}
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("pay Fail");
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean payOneTimeExpense(String oopmsCostOneTimeExpenseId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "Update From OopmsCostOneTimeExpense Set isPay = :isPay Where oopmsCostOneTimeExpenseId = :oopmsCostOneTimeExpenseId";
			Query query = session.createQuery(hql);
			query.setParameter("isPay", "1");
			query.setParameter("oopmsCostOneTimeExpenseId", new BigDecimal(
					oopmsCostOneTimeExpenseId));
			query.executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean payExceptionalCost(String oopmsExceptionalCostId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "Update From OopmsExceptionalCost Set isPay = :isPay Where oopmsExceptionalCostId = :oopmsExceptionalCostId";
			Query query = session.createQuery(hql);
			query.setParameter("isPay", "1");
			query.setParameter("oopmsExceptionalCostId", new BigDecimal(
					oopmsExceptionalCostId));
			query.executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	public String checkExceptionalCostUsed(String oopmsExceptionalCostId, String projectId) {
		String result = "0";
		try {
			log.error("costID :" + oopmsExceptionalCostId);
			log.error("project Id " + projectId);
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From OopmsExceptionalCost where oopmsExceptionalCostId= :oopmsExceptionalCostId)";
			Query query = session.createQuery(hql);
			query.setParameter("oopmsExceptionalCostId", new BigDecimal(
					oopmsExceptionalCostId));
			OopmsExceptionalCost exceptionalCost = (OopmsExceptionalCost) query.uniqueResult();
			if(exceptionalCost.getOopmsCostDailyExpenseId()!=null) {
				String hql2 = "From OopmsCostDailyExpense Where oopmsCostDailyExpenseId= :oopmsCostDailyExpenseId and isPay is null and startDate <= :occurDate1 and (endDate>= :occurDate2 or endDate is null)";
				Query query2 = session.createQuery(hql2);
				query2.setParameter("oopmsCostDailyExpenseId", exceptionalCost.getOopmsCostDailyExpenseId());
				query2.setParameter("occurDate1", exceptionalCost.getOccurDate());
				query2.setParameter("occurDate2", exceptionalCost.getOccurDate());
				List<OopmsCostDailyExpense> list = query2.list();
				if(list!=null) {
					if(list.size()>0) {
						return "1";
					}
				}
			} else {
				String hql2 = "From OopmsCostDailyExpense Where oopmsCostTypeId= :oopmsCostTypeId and isPay is null and startDate <= :occurDate1 and (endDate>= :occurDate2 or endDate is null)";
				Query query2 = session.createQuery(hql2);
				query2.setParameter("oopmsCostTypeId", exceptionalCost.getOopmsCostTypeId());
				query2.setParameter("occurDate1", exceptionalCost.getOccurDate());
				query2.setParameter("occurDate2", exceptionalCost.getOccurDate());
				List<OopmsCostDailyExpense> list = query2.list();
				if(list!=null) {
					if(list.size()>0) {
						return "1";
					}
				}
			}
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("check Fail");
			log.error(e.getMessage());
			return result;
		}
		return result;
	}

}
