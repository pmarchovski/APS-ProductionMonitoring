package com.mdrain.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ClearSessionFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest) req).getSession();
		session.removeAttribute("update_operators_display_info_selected_operator");
		session.removeAttribute("update_operators_display_info");
		session.removeAttribute("massage_edit");
		session.removeAttribute("display_operators_info");
		session.removeAttribute("absence_table_head");
		session.removeAttribute("absence_table_body");
		session.removeAttribute("avsence_current_operator");
		session.removeAttribute("done_status");
		session.removeAttribute("input_production_card_table_head");
		session.removeAttribute("input_production_card_table_body");
		session.removeAttribute("input_production_card_current_date");
		session.removeAttribute("monthly_present_blank_table_head_date");
		session.removeAttribute("monthly_present_blank_table_head_week_day");
		session.removeAttribute("monthly_present_blank_table_body_data");
		session.removeAttribute("monthly_presence_blank_start_date");
		session.removeAttribute("monthly_presence_blank_end_date");
		session.removeAttribute("production_capacity_select_year");
		session.removeAttribute("production_capacity_select_month");	
		session.removeAttribute("test_card_display_choise_year");
		session.removeAttribute("test_card_display_choise_month");
		session.removeAttribute("test_card_display_choise_department");
		session.removeAttribute("test_card_display_choise_chart_type");
		session.removeAttribute("skills_matrix_table_head");
		session.removeAttribute("skills_matrix_table_data");	
		session.removeAttribute("planet_production_cost_head");
		session.removeAttribute("planet_production_cost_body");
		session.removeAttribute("real_production_cost_body_second_row");
		session.removeAttribute("materials_without_type_table_head");
		session.removeAttribute("materials_without_type_table_body");
		
		session.removeAttribute("change_password_message");
		session.removeAttribute("change_user_data_massage");
		
		session.removeAttribute("production_capacity_by_weeks_xValue");
		session.removeAttribute("production_capacity_by_weeks_yValue");
		session.removeAttribute("production_capacity_by_weeks_bar_color");
		session.removeAttribute("production_capacity_by_weeks_year");
		session.removeAttribute("production_available_time_by_week");
		session.removeAttribute("production_capacity_requirement_operators_per_main_head");
		session.removeAttribute("production_capacity_requirement_operators_per_month_head");
		session.removeAttribute("production_capacity_requirement_operators_per_month_data");
		session.removeAttribute("year");
		session.removeAttribute("display_task_table_body");
		session.removeAttribute("display_task_table_head");
		session.removeAttribute("task_targete_date_error_massage");
		session.removeAttribute("display_explicite_task");
		
		session.removeAttribute("heap_memory_total");
		session.removeAttribute("heap_memory_free");
		session.removeAttribute("heap_memory_max");
		session.removeAttribute("processor");
		session.removeAttribute("production_orders_dashboard_table_head");
		session.removeAttribute("production_orders_dashboard_table_body");
		
		   chain.doFilter(req, resp);
	}

}
