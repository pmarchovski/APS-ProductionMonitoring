package com.mdrain.servletPrepare.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Date;
import com.mdrain.logic.PictureGenerate;
import com.mdrain.logic.SetObjectInfo;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Comment;
import com.mdrain.objects.Task;
import com.mdrain.objects.Users;
import com.mdrain.singletons.Singleton;

public class TaskManager {

	static int idTaskNameForComment;
	static LocalDate firstDay = LocalDate.of(1900, 1, 1);

	public static void createNewTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String table                              = "tb_tsks";
		HttpSession session                       = req.getSession();
		DataBaseActivities dbActivities           = Singleton.getInstance();
		ArrayList<String> dataBaseTableColumnName = new ArrayList<String>();
		ArrayList<Object> dataBaseTableData       = new ArrayList<Object>();
		ArrayList<Users> usersCollection          = SetObjectInfo.getUsersFromDataBase();
		LocalDate today                           = LocalDate.now();

		dataBaseTableColumnName.add("tb_tsks_taskNumber");
		dataBaseTableColumnName.add("tb_tsks_creator");
		dataBaseTableColumnName.add("tb_tsks_owner");
		dataBaseTableColumnName.add("tb_tsks_createDate");
		dataBaseTableColumnName.add("tb_tsks_targetDate");
		dataBaseTableColumnName.add("tb_tsks_title");
		dataBaseTableColumnName.add("tb_tsks_content");
		dataBaseTableColumnName.add("tb_tsks_members");
		dataBaseTableColumnName.add("tb_tsks_status");

		int taskNumber = setTaskNumber(req, resp);
		dataBaseTableData.add(taskNumber);
		dataBaseTableData.add(session.getAttribute("user_name_avatar"));
		String taskOwner = req.getParameter("task_owner");
		dataBaseTableData.add(taskOwner);
		dataBaseTableData.add(today);
		dataBaseTableData.add(req.getParameter("task_target_date"));
		dataBaseTableData.add(req.getParameter("task_title"));
		dataBaseTableData.add(req.getParameter("task_description"));

		String[] membersCollection = req.getParameterValues("task_members[]");
		String members             = "";
		for (int i = 0; i < membersCollection.length; i++) {
			members = members + membersCollection[i] + ", ";
		}

		members = members.substring(0, members.length() - 2);

		dataBaseTableData.add(members);
		dataBaseTableData.add("Active");

		dbActivities.insertObject(table, dataBaseTableColumnName, dataBaseTableData);

		String subject = "Здравейте  " + taskOwner + " ,";
		String massageBody = "ТОВА Е АВТОМАТИЧНО ГЕНЕРИРАНО СЪОБЩЕНИЕ. \r\n" + "\r\n"
				+ "Имате нова задача в ситемата за мониторинг на задачи на www.aps-monitoring.eu, с номер: " + taskNumber + "."
				+ "\r\n" + "\r\n" + "Моля, влезте в системата да се запознаете със задачата " + "\r\n" + "\r\n"
				+ "http://aps-monitoring.eu/admin_servlet_display_explicite_tasks?id_task_name=" + idTaskNameForComment;
		String ownerMail = "";
		for (int i = 0; i < usersCollection.size(); i++) {
			if (taskOwner.equals(usersCollection.get(i).getFullName())) {
				ownerMail = usersCollection.get(i).getEmail();
			}
		}

		String[] recepients = EmailLists.taskEmailList(ownerMail);
		SendMail.bootstrap(subject, massageBody, recepients);

		req.getRequestDispatcher("display_tasks.jsp").forward(req, resp);
	}

	public static void displayTaskList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		session.removeAttribute("display_explicite_task");

		ArrayList<Task> taskArrayCollection         = SetObjectInfo.getTaskFromDataBase();
		ArrayList<Object> tableDataCollection       = new ArrayList<Object>();
		ArrayList<String> tableColumnNameCollection = new ArrayList<String>();
		Tables table                                = new Tables();

		String linkDisplay = "<a href=" + "\"" + "admin_servlet_display_explicite_tasks?id_task_name=";
		String linkEnd = "</a>";

		tableColumnNameCollection.add("Отговорник");
		tableColumnNameCollection.add("Номер");
		tableColumnNameCollection.add("Кратко описание на задачата");
		tableColumnNameCollection.add("Пълно описание на задачата");
		tableColumnNameCollection.add("Крайна дата за изпълнение");

		String ownerWepApp = req.getParameter("display_tasks_users");
		String monthWepApp = req.getParameter("display_tasks_by_month");
		System.out.println(monthWepApp);
		String statusWebApp = req.getParameter("display_tasks_status");

		if (ownerWepApp == null && monthWepApp == null && statusWebApp == null) {

			for (int i = 0; i < taskArrayCollection.size(); i++) {

				tableDataCollection.add(taskArrayCollection.get(i).getOwner().getFullName());
				tableDataCollection.add(taskArrayCollection.get(i).getTaskNumber());
				tableDataCollection.add(linkDisplay + taskArrayCollection.get(i).getTaskNumber() + "\"" + ">"
						+ taskArrayCollection.get(i).getTitle() + linkEnd);
				tableDataCollection.add(taskArrayCollection.get(i).getContent());
				tableDataCollection.add(taskArrayCollection.get(i).getTargetDate());

			}
		} else {

			if (ownerWepApp.equals("all") && monthWepApp.equals("") && statusWebApp.equals("all")) {

				for (int i = 0; i < taskArrayCollection.size(); i++) {

					tableDataCollection.add(taskArrayCollection.get(i).getOwner().getFullName());
					tableDataCollection.add(taskArrayCollection.get(i).getTaskNumber());
					tableDataCollection.add(linkDisplay + taskArrayCollection.get(i).getTaskNumber() + "\"" + ">"
							+ taskArrayCollection.get(i).getTitle() + linkEnd);
					tableDataCollection.add(taskArrayCollection.get(i).getContent());
					tableDataCollection.add(taskArrayCollection.get(i).getTargetDate());

				}
			} else {

				String tempOwner = "";
				String tempMonthWepApp = "";
				String tempStatusWebApp = "";

				for (int i = 0; i < taskArrayCollection.size(); i++) {

					String yearMonth = Date.convertDateNew(taskArrayCollection.get(i).getTargetDate());
					yearMonth = yearMonth.substring(0, yearMonth.length() - 3);

					if (ownerWepApp.equals("all")) {
						tempOwner = "all";
					} else {
						tempOwner = taskArrayCollection.get(i).getOwner().getFullName();
					}

					if (monthWepApp.equals("")) {
						tempMonthWepApp = "";
					} else {
						tempMonthWepApp = yearMonth;
					}

					if (statusWebApp.equals("all")) {
						tempStatusWebApp = "all";
					} else {
						tempStatusWebApp = taskArrayCollection.get(i).getStatus();
					}

					if (tempOwner.equals(ownerWepApp) && tempMonthWepApp.equals(monthWepApp)
							&& tempStatusWebApp.equals(statusWebApp)) {

						tableDataCollection.add(taskArrayCollection.get(i).getOwner().getFullName());
						tableDataCollection.add(taskArrayCollection.get(i).getTaskNumber());
						tableDataCollection.add(linkDisplay + taskArrayCollection.get(i).getTaskNumber() + "\"" + ">"
								+ taskArrayCollection.get(i).getTitle() + linkEnd);
						tableDataCollection.add(taskArrayCollection.get(i).getContent());
						tableDataCollection.add(taskArrayCollection.get(i).getTargetDate());
					}
				}

			}

		}

		String tableHead = table.createTableHead(tableColumnNameCollection);
		String tableBody = table.createTableBodyStringObject(tableColumnNameCollection, tableDataCollection);

		session.setAttribute("display_task_table_head", tableHead);
		session.setAttribute("display_task_table_body", tableBody);

		req.getRequestDispatcher("display_tasks.jsp").forward(req, resp);

	}

	public static void displayMyTaskList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session                         = req.getSession();	
		ArrayList<Task> taskArrayCollection         = SetObjectInfo.getTaskFromDataBase();
		ArrayList<Object> tableDataCollection       = new ArrayList<Object>();
		ArrayList<String> tableColumnNameCollection = new ArrayList<String>();
		Tables table                                = new Tables();
		String linkDisplay                          = "<a href=" + "\"" + "admin_servlet_display_explicite_tasks?id_task_name=";
		String linkEnd                              = "</a>";

		session.removeAttribute("display_explicite_task");
		
		tableColumnNameCollection.add("Отговорник");
		tableColumnNameCollection.add("Номер");
		tableColumnNameCollection.add("Кратко описание на задачата");
		tableColumnNameCollection.add("Пълно описание на задачата");
		tableColumnNameCollection.add("Крайна дата за изпълнение");

		String ownerWepApp = (String) session.getAttribute("user_full_name");
//		String monthWepApp = req.getParameter("display_tasks_by_month");
//		System.out.println(monthWepApp);
		String statusWebApp = "all";


				String tempOwner = "";
				String tempMonthWepApp = "";
				String tempStatusWebApp = "";

				for (int i = 0; i < taskArrayCollection.size(); i++) {

					String yearMonth = Date.convertDateNew(taskArrayCollection.get(i).getTargetDate());
					yearMonth = yearMonth.substring(0, yearMonth.length() - 3);

					if (ownerWepApp.equals("all")) {
						tempOwner = "all";
					} else {
						tempOwner = taskArrayCollection.get(i).getOwner().getFullName();
					}

//					if (monthWepApp.equals("") || monthWepApp == null) {
//						tempMonthWepApp = "";
//					} else {
//						tempMonthWepApp = yearMonth;
//					}

					if (statusWebApp.equals("all")) {
						tempStatusWebApp = "all";
					} else {
						tempStatusWebApp = taskArrayCollection.get(i).getStatus();
					}

//					if (tempOwner.equals(ownerWepApp) && tempMonthWepApp.equals(monthWepApp)
//							&& tempStatusWebApp.equals(statusWebApp)) {
					if (tempOwner.equals(ownerWepApp)
							&& tempStatusWebApp.equals(statusWebApp)) {

						tableDataCollection.add(taskArrayCollection.get(i).getOwner().getFullName());
						tableDataCollection.add(taskArrayCollection.get(i).getTaskNumber());
						tableDataCollection.add(linkDisplay + taskArrayCollection.get(i).getTaskNumber() + "\"" + ">"
								+ taskArrayCollection.get(i).getTitle() + linkEnd);
						tableDataCollection.add(taskArrayCollection.get(i).getContent());
						tableDataCollection.add(taskArrayCollection.get(i).getTargetDate());
					}
				}


		String tableHead = table.createTableHead(tableColumnNameCollection);
		String tableBody = table.createTableBodyStringObject(tableColumnNameCollection, tableDataCollection);

		session.setAttribute("display_task_table_head", tableHead);
		session.setAttribute("display_task_table_body", tableBody);

		req.getRequestDispatcher("display_tasks.jsp").forward(req, resp);

	}

	public static void displayExpliciteTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session                  = req.getSession();
		ArrayList<Task> taskArrayCollection  = SetObjectInfo.getTaskFromDataBase();
		String ownerTaskAvatar               = "";
		ArrayList<Object> taskDataCollection = new ArrayList<Object>();
		int idTaskName                       = 0;
		LocalDate today                      = LocalDate.now();
		String taskTitle                     = "";
		Tables table                         = new Tables();

		session.removeAttribute("display_task_table_head");
		session.removeAttribute("display_task_table_body");
		
		if (req.getParameter("id_task_name") == null) {
			idTaskName = idTaskNameForComment;
		} else {
			idTaskName = Integer.parseInt(req.getParameter("id_task_name"));
		}

		idTaskNameForComment = idTaskName;


		for (int i = 0; i < taskArrayCollection.size(); i++) {

			if (idTaskName == taskArrayCollection.get(i).getTaskNumber()) {

				String members = "";
				String comments = "";
				for (int j = 0; j < taskArrayCollection.get(i).getMembers().size(); j++) {

					String memberAvatar = PictureGenerate
							.getAvatar(taskArrayCollection.get(i).getMembers().get(j).getFullName());
					String memberAvatarAndName = returnQueryNameAndAvatar(memberAvatar,
							taskArrayCollection.get(i).getMembers().get(j).getFullName());

					members = members + memberAvatarAndName + "<br>";

				}

				comments = returnComment(idTaskName);

				members = members.substring(0, members.length() - 4);

				taskTitle = taskArrayCollection.get(i).getTitle();
				taskDataCollection.add(taskTitle);

				taskDataCollection.add(taskArrayCollection.get(i).getContent());

				String ownerName = taskArrayCollection.get(i).getOwner().getFullName();

				ownerTaskAvatar = PictureGenerate.getAvatar(ownerName);

				taskDataCollection.add(returnQueryNameAndAvatar(ownerTaskAvatar, ownerName));
				taskDataCollection.add(taskArrayCollection.get(i).getCreateDate());

				LocalDate targetDate = taskArrayCollection.get(i).getTargetDate();

				long daysBetweenFirstDayAndToday = Date.getDaysBetweenTwoDates(firstDay, today);
				long daysBetweenFirstDayAndTargetDate = Date.getDaysBetweenTwoDates(firstDay, targetDate);

				long resultDate = daysBetweenFirstDayAndToday - daysBetweenFirstDayAndTargetDate;
				String delayMessage = "";

				String taskTargetDateErrorMessage = "";

				if ((String) session.getAttribute("task_targete_date_error_massage") == null) {
					taskTargetDateErrorMessage = "";
				} else {
					taskTargetDateErrorMessage = (String) session.getAttribute("task_targete_date_error_massage");
				}

				if (resultDate > 0) {
					delayMessage = "Крайният срок за изпълнение на тази задача е изтекъл!!!!";
				}

				taskDataCollection.add(targetDate + " " + delayMessage + taskTargetDateErrorMessage);
				taskDataCollection.add(members);
				taskDataCollection.add(taskArrayCollection.get(i).getStatus());
				taskDataCollection.add(comments);

			}
		}

		String tableCreateData = table.createTableForTask(taskDataCollection);

		session.setAttribute("title_task", taskTitle);
		session.setAttribute("display_explicite_task", tableCreateData);
		req.getRequestDispatcher("display_explicite_task.jsp").forward(req, resp);

	}

	public static void addComment(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		DataBaseActivities dbActivities = Singleton.getInstance();
		String comment                  = req.getParameter("add_comment");
		String table                    = "tb_comment";
		HttpSession session             = req.getSession();

		ArrayList<Task> taskCollection                 = SetObjectInfo.getTaskFromDataBase();
		ArrayList<Users> membersCollection             = new ArrayList<Users>();

		String[] dataBaseColumnNameCollection = {
				"tb_comment_user",
				"tb_comment_taskNumber",
				"tb_comment_commentDate",
				"tb_comment_commentContent"
		};
		
		Object[] dataBaseDataCollection = {
				(String) session.getAttribute("user_full_name"),
				idTaskNameForComment,
				LocalDate.now(),
				comment
		};

		dbActivities.insertObject(table, dataBaseColumnNameCollection, dataBaseDataCollection);

		String taskName = "";
		for (int i = 0; i < taskCollection.size(); i++) {
			if (taskCollection.get(i).getTaskNumber() == idTaskNameForComment) {
				membersCollection = taskCollection.get(i).getMembers();
				taskName = taskCollection.get(i).getTitle();
			}
		}
		String subject = "Нов коментар по задача " + idTaskNameForComment;
		String massageBody = "ТОВА Е АВТОМАТИЧНО ГЕНЕРИРАНО СЪОБЩЕНИЕ. \r\n" + "\r\n"
				+ "Нов коментар по задача: " + taskName + "\r\n" + "\r\n"
				+ "от " + session.getAttribute("user_full_name") + "\r\n" + "\r\n"
				+ "http://aps-monitoring.eu/admin_servlet_display_explicite_tasks?id_task_name=" + idTaskNameForComment + "\r\n" + "\r\n"
				+ "КОМЕНТАР: " + "\r\n" + "\r\n"
				+ comment;

		String[] recepients = EmailLists.taskEmailList(membersCollection);
		SendMail.bootstrap(subject, massageBody, recepients);

		req.getRequestDispatcher("/admin_servlet_display_explicite_tasks").forward(req, resp);

	}

	public static void changeTaskStatus(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String status                   = req.getParameter("change_task_status");
		String table                    = "tb_tsks";
		DataBaseActivities dbActivities = Singleton.getInstance();

		dbActivities.update(table, "tb_tsks_status", status, "tb_tsks_taskNumber",
				String.valueOf(idTaskNameForComment));

		req.getRequestDispatcher("/admin_servlet_display_explicite_tasks").forward(req, resp);

	}

	public static void changeTaskTargetDate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String newDate                      = req.getParameter("change_target_date");
		String table                        = "tb_tsks";
		DataBaseActivities dbActivities     = Singleton.getInstance();
		ArrayList<Task> taskArrayCollection = SetObjectInfo.getTaskFromDataBase();
		HttpSession session                 = req.getSession();
		session.removeAttribute("task_targete_date_error_massage");

		for (int i = 0; i < taskArrayCollection.size(); i++) {

			int numberTaskTargetDateChanges = taskArrayCollection.get(i).getNumberTaskTargetDateChanges();
			session.removeAttribute("task_targete_date_error_massage");
			if (idTaskNameForComment == taskArrayCollection.get(i).getTaskNumber()
					&& numberTaskTargetDateChanges <= 1) {
				dbActivities.update(table, "tb_tsks_targetDate", newDate, "tb_tsks_taskNumber",
						String.valueOf(idTaskNameForComment));
				dbActivities.update(table, "tb_tsks_number_change_target_date", numberTaskTargetDateChanges + 1,
						"tb_tsks_taskNumber", String.valueOf(idTaskNameForComment));
				break;
			} else {

				session.setAttribute("task_targete_date_error_massage",
						"Датата не може да бъде променена. Крайната дата за тази задаче е променяна повече от два пъти. ");

			}
		}

		req.getRequestDispatcher("/admin_servlet_display_explicite_tasks").forward(req, resp);
	}

	private static int setTaskNumber(HttpServletRequest req, HttpServletResponse resp) {

		ArrayList<Task> taskId = SetObjectInfo.getTaskFromDataBase();
		int taskCount          = 1000;
		taskCount              = taskCount + (taskId.size() + 1);

		return taskCount;
	}

	private static String returnQueryNameAndAvatar(String avatar, String userName) {

		String query = "<table>" + "<tr>" + "<td Class=\"a\">" + avatar + "</td>" + "<td Class=\"a\">" + userName
				+ "</td>" + "</tr>" + "</table>";

		return query;
	}

	private static String returnComment(int idTaskName) {

		ArrayList<Comment> commentsCollection = SetObjectInfo.getCommentFromDataBase();

		String comments = "";
		for (int k = 0; k < commentsCollection.size(); k++) {
			if (commentsCollection.get(k).getTaskNumber() == idTaskName) {

				String userFullName = commentsCollection.get(k).getUser().getFullName();
				LocalDate commentDate = commentsCollection.get(k).getCommentDate();
				String commentContent = commentsCollection.get(k).getCommentContent();
				LocalTime commentTime = commentsCollection.get(k).getCommentTime();

				comments = comments + returnTableQuery(commentContent, userFullName, commentDate, commentTime);

			}
		}

		return comments;
	}

	private static String returnTableQuery(String comment, String userFullName, LocalDate commentDate,
			LocalTime commentTime) {

		String memberAvatar = PictureGenerate.getAvatar(userFullName);
		String tableQuery = "<table>\r\n" + "<tr><td Class=\"az\" rowspan=\"3\">" + memberAvatar + "</td></tr>\r\n"
				+ "<tr><td Class=\"ax\" colspan=\"2\">" + comment + "</td></tr>\r\n" + "<tr><td Class=\"ay\">"
				+ userFullName + "</td>\r\n" + "<td Class=\"ay\">" + commentDate + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"
				+ commentTime + "</td></tr>\r\n" + "</table>" + "<br><br>"
				+ "<p style=\"border-bottom:1px solid red;\">" + "</p>";

		return tableQuery;
	}

}
