package openones.oopms.projecteye.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import openones.oopms.projecteye.dao.CostDao;
import openones.oopms.projecteye.dao.ProjectDao;
import openones.oopms.projecteye.form.DailyExpense;
import openones.oopms.projecteye.form.ExceptionalCost;
import openones.oopms.projecteye.form.InvoiceDailyExpense;
import openones.oopms.projecteye.form.InvoiceExceptionalCost;
import openones.oopms.projecteye.model.OopmsCostDailyExpense;
import openones.oopms.projecteye.model.OopmsCostOneTimeExpense;
import openones.oopms.projecteye.model.OopmsCostType;
import openones.oopms.projecteye.model.OopmsExceptionalCost;
import openones.oopms.projecteye.model.Project;

public class CostUtil {
	private final static Logger log = Logger.getLogger("CostUtil");

	/**
	 * Create String represent date used form checkbox array
	 * 
	 * @param input
	 * @return
	 */
	public static String getDaysUsed(String[] input) {
		String result = "0000000";
		char[] temp = result.toCharArray();
		if (input.length > 0) {
			for (int i = 0; i < input.length; i++) {
				temp[Integer.parseInt(input[i]) - 1] = '1';
			}
		}
		result = String.valueOf(temp);
		return result;
	}

	/**
	 * Create checkbox array from a String represent day used
	 * 
	 * @param input
	 * @return
	 */
	public static String[] getDaysUsed(String input) {
		String temp = "";
		for (int i = 0; i < 7; i++) {
			if (input.charAt(i) == '1') {
				temp = temp + String.valueOf(i + 1) + ",";
			}
		}
		String[] result = temp.split(",");
		return result;
	}

	public static String getDaysUsedString(String input) {
		String result = "";
		if (input.charAt(1) == '1') {
			result = result + "Mon";
		}
		if (input.charAt(2) == '1') {
			result = result + " Tue";
		}
		if (input.charAt(3) == '1') {
			result = result + " Wed";
		}
		if (input.charAt(4) == '1') {
			result = result + " Thu";
		}
		if (input.charAt(5) == '1') {
			result = result + " Fri";
		}
		if (input.charAt(6) == '1') {
			result = result + " Sat";
		}
		if (input.charAt(0) == '1') {
			result = result + " Sun";
		}
		return result;
	}

	public static List<DailyExpense> getDailyExpenseListView(
			List<OopmsCostDailyExpense> input) {
		List<DailyExpense> dailyExpenseListView = new ArrayList<DailyExpense>();
		CostDao cDao = new CostDao();
		if (input == null) {
			return null;
		} else {
			for (int i = 0; i < input.size(); i++) {
				DailyExpense temp = new DailyExpense();
				temp.setOopmsCostDailyExpenseId(input.get(i)
						.getOopmsCostDailyExpenseId());
				temp.setCost(input.get(i).getCost());
				temp.setDateUsed(getDaysUsedString(input.get(i).getDateUsed()));
				temp.setDescription(input.get(i).getDescription());
				temp.setEndDate(input.get(i).getEndDate());
				temp.setName(input.get(i).getName());
				temp.setStartDate(input.get(i).getStartDate());
				if (input.get(i).getOopmsCostTypeId() != null) {
					OopmsCostType costType = cDao.getCostType(input.get(i)
							.getOopmsCostTypeId());
					temp.setType(costType.getName());
				}
				dailyExpenseListView.add(temp);
			}
		}
		return dailyExpenseListView;
	}

	public static List<ExceptionalCost> getExceptionalListView(
			List<OopmsExceptionalCost> input) {
		List<ExceptionalCost> exceptionalListView = new ArrayList<ExceptionalCost>();
		CostDao cDao = new CostDao();
		if (input == null) {
			return null;
		} else {
			for (int i = 0; i < input.size(); i++) {
				ExceptionalCost temp = new ExceptionalCost();
				temp.setName(input.get(i).getName());
				temp.setDescription(input.get(i).getDescription());
				temp.setOccurDate(input.get(i).getOccurDate());
				temp.setOopmsExceptionalCostId(input.get(i)
						.getOopmsExceptionalCostId());
				temp.setProjectId(input.get(i).getProjectId());
				if (input.get(i).getOopmsCostTypeId() != null) {
					OopmsCostType costType = cDao.getCostType(input.get(i)
							.getOopmsCostTypeId());
					temp.setAffectTo(costType.getName());
				} else if (input.get(i).getOopmsCostDailyExpenseId() != null) {
					OopmsCostDailyExpense dailyExpense = cDao
							.getDailyExpense(input.get(i)
									.getOopmsCostDailyExpenseId());
					temp.setAffectTo(dailyExpense.getName());
				}
				if (String.valueOf(input.get(i).getEffectType()).equals(
						Constant.ExceptinalFixCostEffectType)) {
					if (input.get(i).getEffect() != null) {
						temp.setAdditionEffect("Fixed "
								+ input.get(i).getEffect() + " $");
					}
				} else if (String.valueOf(input.get(i).getEffectType()).equals(
						Constant.ExceptinalRationCostEffectType)) {
					if (input.get(i).getEffect() != null) {
						temp.setAdditionEffect("x" + input.get(i).getEffect()
								+ " Payment");
					}
				} else if (String.valueOf(input.get(i).getEffectType()).equals(
						Constant.ExceptinalBonusCostEffectType)) {
					if (input.get(i).getEffect() != null) {
						temp.setAdditionEffect("Bonus "
								+ input.get(i).getEffect() + "  $");
					}
				}
				exceptionalListView.add(temp);
			}
		}
		return exceptionalListView;
	}

	/**
	 * Get Expense of a project at a specific date
	 * 
	 * @param projectId
	 * @param viewDate
	 * @return
	 */
	public static BigDecimal getExpense(String projectId, Date viewDate) {
		BigDecimal result = new BigDecimal("0");
		CostDao cDao = new CostDao();
		// Get expense from one time expense
		List<OopmsCostOneTimeExpense> oneTimeExpenseList = cDao
				.getOneTimeExpenseList(projectId);
		if (oneTimeExpenseList != null) {
			if (oneTimeExpenseList.size() > 0) {
				for (int i = 0; i < oneTimeExpenseList.size(); i++) {
					if (!oneTimeExpenseList.get(i).getOccurDate()
							.after(viewDate)) {
						result = result
								.add(oneTimeExpenseList.get(i).getCost());
					}
				}
			}
		}
		log.error("One Time Expense: " + result);
		// Get expense from daily expense
		List<OopmsCostDailyExpense> dailyExpenseList = cDao
				.getDailyExpenseList(projectId);
		if (dailyExpenseList != null) {
			if (dailyExpenseList.size() > 0) {
				for (int i = 0; i < dailyExpenseList.size(); i++) {
					if (!dailyExpenseList.get(i).getStartDate().after(viewDate)) {
						result = result.add(getTotalExpenseOfDailyExpense(
								dailyExpenseList.get(i), viewDate));
					}
				}
			}
		}
		log.error("Daily Expense: " + result);
		// Get expense from exceptional expense
		List<OopmsExceptionalCost> exceptionalExpenseList = cDao
				.getExceptionalExpenseList(projectId);
		if (exceptionalExpenseList != null) {
			if (exceptionalExpenseList.size() > 0) {
				for (int i = 0; i < exceptionalExpenseList.size(); i++) {
					if (!exceptionalExpenseList.get(i).getOccurDate()
							.after(viewDate)) {
						result = result
								.add(getTotalValueOfExceptionalCost(exceptionalExpenseList
										.get(i)));
					}
				}
			}
		}
		log.error("Exceptional Expense: " + result);
		// Get deduct from exceptional deduct
		List<OopmsExceptionalCost> exceptionalDeductList = cDao
				.getExceptionalDeductList(projectId);
		if (exceptionalDeductList != null) {
			if (exceptionalDeductList.size() > 0) {
				for (int i = 0; i < exceptionalDeductList.size(); i++) {
					if (!exceptionalDeductList.get(i).getOccurDate()
							.after(viewDate)) {
						result = result
								.subtract(getTotalValueOfExceptionalCost(exceptionalDeductList
										.get(i)));
					}
				}
			}
		}
		log.error("Exceptional Deduct: " + result);
		return result;
	}

	/**
	 * Get the total expense of a dailyExpense record from start date to view
	 * Date
	 * 
	 * @param dailyExpense
	 * @param viewDate
	 * @return
	 */
	public static BigDecimal getTotalExpenseOfDailyExpense(
			OopmsCostDailyExpense dailyExpense, Date viewDate) {
		BigDecimal result = new BigDecimal("0");
		if (dailyExpense.getEndDate() != null) {
			if (dailyExpense.getEndDate().after(viewDate)) {
				for (int i = 0; i < 7; i++) {
					if (dailyExpense.getDateUsed().charAt(i) == '1') {
						int temp = countDay(dailyExpense.getStartDate(),
								viewDate, i + 1);
						result = result.add(dailyExpense.getCost().multiply(
								new BigDecimal(temp)));
					}
				}
			} else {
				for (int i = 0; i < 7; i++) {
					if (dailyExpense.getDateUsed().charAt(i) == '1') {
						int temp = countDay(dailyExpense.getStartDate(),
								dailyExpense.getEndDate(), i + 1);
						result = result.add(dailyExpense.getCost().multiply(
								new BigDecimal(temp)));
					}
				}
			}
		} else {
			for (int i = 0; i < 7; i++) {
				if (dailyExpense.getDateUsed().charAt(i) == '1') {
					int temp = countDay(dailyExpense.getStartDate(), viewDate,
							i + 1);
					result = result.add(dailyExpense.getCost().multiply(
							new BigDecimal(temp)));
				}
			}
		}
		return result;
	}

	/**
	 * Count the number of a dayInWeek from startDate to endDate
	 * 
	 * @param startDate
	 * @param endDate
	 * @param dayInWeek
	 * @return
	 */
	public static int countDay(Date startDate, Date endDate, int dayInWeek) {
		// dayInWeek 1:Sunday, 2:Monday, ....7: Saturday
		int result = 0;
		long oneDayTime = 1000 * 60 * 60 * 24;
		long startDateLong = startDate.getTime();
		long endDateLong = endDate.getTime();
		Calendar temp = Calendar.getInstance();
		do {
			temp.setTimeInMillis(startDateLong);
			if (temp.get(Calendar.DAY_OF_WEEK) == dayInWeek) {
				result++;
			}
			startDateLong = startDateLong + oneDayTime;
		} while (startDateLong <= endDateLong);
		return result;
	}

	/**
	 * Get the total value of an ExceptionalCost record
	 * 
	 * @param exceptinalCost
	 * @return
	 */
	public static BigDecimal getTotalValueOfExceptionalCost(
			OopmsExceptionalCost exceptinalCost) {

		BigDecimal result = new BigDecimal("0");
		if (exceptinalCost.getOopmsCostTypeId() != null) {
			if (exceptinalCost.getEffect() != null) {
				if (String.valueOf(exceptinalCost.getEffectType()).equals(
						Constant.ExceptinalFixCostEffectType)) {
					CostDao cDao = new CostDao();
					List<OopmsCostDailyExpense> temp = cDao
							.getDailyExpenseListOfAType(
									exceptinalCost.getProjectId(),
									exceptinalCost.getOopmsCostTypeId());
					if (temp != null) {
						for (int i = 0; i < temp.size(); i++) {
							if ((!temp.get(i).getStartDate()
									.after(exceptinalCost.getOccurDate()))) {
								if (temp.get(i).getEndDate() != null) {
									if (!temp
											.get(i)
											.getEndDate()
											.before(exceptinalCost
													.getOccurDate())) {
										result = result.add(exceptinalCost
												.getEffect());
									}
								} else {
									result = result.add(exceptinalCost
											.getEffect());
								}

							}
						}
					}

				} else if (String.valueOf(exceptinalCost.getEffectType())
						.equals(Constant.ExceptinalRationCostEffectType)) {
					CostDao cDao = new CostDao();
					List<OopmsCostDailyExpense> temp = cDao
							.getDailyExpenseListOfAType(
									exceptinalCost.getProjectId(),
									exceptinalCost.getOopmsCostTypeId());
					if (temp != null) {
						for (int i = 0; i < temp.size(); i++) {
							if ((!temp.get(i).getStartDate()
									.after(exceptinalCost.getOccurDate()))) {
								if (temp.get(i).getEndDate() != null) {
									if (!temp
											.get(i)
											.getEndDate()
											.before(exceptinalCost
													.getOccurDate())) {
										result = result.add(exceptinalCost
												.getEffect().multiply(
														temp.get(i).getCost()));
									}
								} else {
									result = result.add(exceptinalCost
											.getEffect().multiply(
													temp.get(i).getCost()));
								}

							}
						}
					}

				} else if (String.valueOf(exceptinalCost.getEffectType())
						.equals(Constant.ExceptinalBonusCostEffectType)) {
					CostDao cDao = new CostDao();
					List<OopmsCostDailyExpense> temp = cDao
							.getDailyExpenseListOfAType(
									exceptinalCost.getProjectId(),
									exceptinalCost.getOopmsCostTypeId());
					if (temp != null) {
						for (int i = 0; i < temp.size(); i++) {
							if ((!temp.get(i).getStartDate()
									.after(exceptinalCost.getOccurDate()))) {
								if (temp.get(i).getEndDate() != null) {
									if (!temp
											.get(i)
											.getEndDate()
											.before(exceptinalCost
													.getOccurDate())) {
										result = result.add(exceptinalCost
												.getEffect().add(
														temp.get(i).getCost()));
									}
								} else {
									result = result.add(exceptinalCost
											.getEffect().add(
													temp.get(i).getCost()));
								}
							}

						}
					}

				}
			}
		} else if (exceptinalCost.getOopmsCostDailyExpenseId() != null) {
			CostDao cDao = new CostDao();
			OopmsCostDailyExpense temp = cDao.getDailyExpense(exceptinalCost
					.getOopmsCostDailyExpenseId());
			if (!temp.getStartDate().after(exceptinalCost.getOccurDate())) {
				if (temp.getEndDate() != null) {
					if (!temp.getEndDate()
							.before(exceptinalCost.getOccurDate())) {
						if (exceptinalCost.getEffect() != null) {
							if (String
									.valueOf(exceptinalCost.getEffectType())
									.equals(Constant.ExceptinalFixCostEffectType)) {

								result = result.add(exceptinalCost.getEffect());
							} else if (String.valueOf(
									exceptinalCost.getEffectType()).equals(
									Constant.ExceptinalRationCostEffectType)) {
								result = result.add(exceptinalCost.getEffect()
										.multiply(temp.getCost()));
							} else if (String.valueOf(
									exceptinalCost.getEffectType()).equals(
									Constant.ExceptinalBonusCostEffectType)) {
								result = result.add(exceptinalCost.getEffect()
										.add(temp.getCost()));
							}
						}
					}
				} else {
					if (exceptinalCost.getEffect() != null) {
						if (String.valueOf(exceptinalCost.getEffectType())
								.equals(Constant.ExceptinalFixCostEffectType)) {

							result = result.add(exceptinalCost.getEffect());
						} else if (String.valueOf(
								exceptinalCost.getEffectType()).equals(
								Constant.ExceptinalRationCostEffectType)) {
							result = result.add(exceptinalCost.getEffect()
									.multiply(temp.getCost()));
						} else if (String.valueOf(
								exceptinalCost.getEffectType()).equals(
								Constant.ExceptinalBonusCostEffectType)) {
							result = result.add(exceptinalCost.getEffect().add(
									temp.getCost()));
						}
					}
				}

			}
		}
		return result;
	}

	public static String getProjectCostStatus(String projectId,
			BigDecimal currentBudget) {
		ProjectDao pDao = new ProjectDao();
		Project project = pDao.getProject(projectId);
		BigDecimal projectExpense = getExpense(projectId,
				project.getPlanFinishDate());
		if (projectExpense.compareTo(currentBudget) == 1) {
			return Constant.ProjectCostStatusRed;
		} else if (projectExpense.compareTo(currentBudget
				.multiply(new BigDecimal("0.8"))) == -1) {
			return Constant.ProjectCostStatusGreen;
		} else {
			return Constant.ProjectCostStatusYellow;
		}
	}

	public static InvoiceDailyExpense getInvoiceDailyExpense(
			OopmsCostDailyExpense input) {
		InvoiceDailyExpense result = new InvoiceDailyExpense();
		result.setName(input.getName());
		result.setCostDay(String.valueOf(input.getCost()));
		BigDecimal total = new BigDecimal("0");
		int totalDays = 0;
		// get total and days
		for (int i = 0; i < 7; i++) {
			if (input.getDateUsed().charAt(i) == '1') {
				int temp = countDay(input.getStartDate(), input.getEndDate(),
						i + 1);
				totalDays = totalDays + temp;
				total = total.add(input.getCost()
						.multiply(new BigDecimal(temp)));
			}
		}
		result.setDays(String.valueOf(totalDays));
		result.setTotal(String.valueOf(total));
		result.setDescription(input.getDescription()
				+ " From "
				+ AppUtil.getDateAsFormat(input.getStartDate(),
						Constant.DateFormat)
				+ " To "
				+ AppUtil.getDateAsFormat(input.getEndDate(),
						Constant.DateFormat) + "( "
				+ getDaysUsedString(input.getDateUsed()) + " )");
		return result;
	}
	
	public static List<InvoiceDailyExpense> getInvoiceDailyExpense(
			List<OopmsCostDailyExpense> input) {
		List<InvoiceDailyExpense> result = new ArrayList<InvoiceDailyExpense>();
		for(int i=0; i<input.size(); i++) {
			InvoiceDailyExpense temp = getInvoiceDailyExpense(input.get(i));
			result.add(temp);
		}
		return result;
		
	}

	public static InvoiceExceptionalCost getInvoiceExceptionalCost(
			OopmsExceptionalCost input, OopmsCostDailyExpense dailyExpense) {
		InvoiceExceptionalCost result = new InvoiceExceptionalCost();
		result.setName(input.getName());
		// set effect
		if (String.valueOf(input.getEffectType()).equals(
				Constant.ExceptinalFixCostEffectType)) {
			if (input.getEffect() != null) {
				result.setEffect("Fixed " + input.getEffect() + " $");
			}
		} else if (String.valueOf(input.getEffectType()).equals(
				Constant.ExceptinalRationCostEffectType)) {
			if (input.getEffect() != null) {
				result.setEffect("x" + input.getEffect() + " Payment");
			}
		} else if (String.valueOf(input.getEffectType()).equals(
				Constant.ExceptinalBonusCostEffectType)) {
			if (input.getEffect() != null) {
				result.setEffect("Bonus " + input.getEffect() + "  $");
			}
		}
		// set affectTo
		result.setEffectTo(dailyExpense.getName()
				+ " ("
				+ String.valueOf(dailyExpense.getCost())
				+ "/day) on "
				+ AppUtil.getDateAsFormat(input.getOccurDate(),
						Constant.DateFormat));
		result.setDescription(input.getDescription());
		BigDecimal total = new BigDecimal("0");
		if (String.valueOf(input.getEffectType()).equals(
				Constant.ExceptinalFixCostEffectType)) {

			total = total.add(input.getEffect());
		} else if (String.valueOf(input.getEffectType()).equals(
				Constant.ExceptinalRationCostEffectType)) {
			total = total.add(input.getEffect()
					.multiply(dailyExpense.getCost()));
		} else if (String.valueOf(input.getEffectType()).equals(
				Constant.ExceptinalBonusCostEffectType)) {
			total = total.add(input.getEffect().add(dailyExpense.getCost()));
		}
		if (String.valueOf(input.getType()).equals(
				Constant.ExceptinalExpenseType)) {
			result.setTotal(String.valueOf(total));
		} else {
			result.setTotal("-" + String.valueOf(total));
		}
		return result;
	}

	public static List<InvoiceExceptionalCost> getInvoiceExceptionalCostList(
			List<OopmsExceptionalCost> input) {
		List<InvoiceExceptionalCost> output = new ArrayList<InvoiceExceptionalCost>();
		CostDao cDao = new CostDao();
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).getOopmsCostDailyExpenseId() != null) {
				OopmsCostDailyExpense dailyExpense = cDao.getDailyExpense(input
						.get(i).getOopmsCostDailyExpenseId());
				if ((!dailyExpense.getStartDate().after(
						input.get(i).getOccurDate()))) {
					if (dailyExpense.getEndDate() != null) {
						if (!dailyExpense.getEndDate().before(
								input.get(i).getOccurDate())) {
							InvoiceExceptionalCost temp = new InvoiceExceptionalCost();
							temp = getInvoiceExceptionalCost(input.get(i), dailyExpense);
							output.add(temp);
						}
					} else {
						InvoiceExceptionalCost temp = new InvoiceExceptionalCost();
						temp = getInvoiceExceptionalCost(input.get(i), dailyExpense);
						output.add(temp);
					}
				}

			} else {
				List<OopmsCostDailyExpense> dailyExpenseList = cDao
						.getDailyExpenseListOfAType(
								input.get(i).getProjectId(),
								input.get(i).getOopmsCostTypeId());
				for(int z = 0; z<dailyExpenseList.size();z++) {
					if ((!dailyExpenseList.get(z).getStartDate().after(
							input.get(i).getOccurDate()))) {
						if (dailyExpenseList.get(z).getEndDate() != null) {
							if (!dailyExpenseList.get(z).getEndDate().before(
									input.get(i).getOccurDate())) {
								InvoiceExceptionalCost temp = new InvoiceExceptionalCost();
								temp = getInvoiceExceptionalCost(input.get(i), dailyExpenseList.get(z));
								output.add(temp);
							}
						} else {
							InvoiceExceptionalCost temp = new InvoiceExceptionalCost();
							temp = getInvoiceExceptionalCost(input.get(i), dailyExpenseList.get(z));
							output.add(temp);
						}
					}
				}
			}
		}
		return output;
	}
	
}
