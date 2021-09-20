package com.mdrain.database;

public class DataBaseJoinMethods {

	
	public void innerJoin() {
		
//		SELECT tb_coois_prod.tb_coois_prod_order, tb_coois_prod.tb_coois_prod_material_number, tb_coois_prod.tb_coois_prod_quantity, tb_coois_operation.tb_coois_operation_processing_time 
//		FROM tb_coois_prod 
//		INNER JOIN tb_coois_operation 
//		ON tb_coois_prod.tb_coois_prod_order = tb_coois_operation.tb_coois_operation_order
	}
	
}


// SELECT tb_coois_prod_status, SUBSTRING(tb_coois_prod_status, 1,4) FROM tb_coois_prod



// DELETE FROM tb_coois_prod WHERE tb_coois_prod_status LIKE '%TECO%' AND tb_coois_prod_del_qty = 0


//CREATE TABLE result 		SELECT tb_coois_prod.tb_coois_prod_order, tb_coois_prod.tb_coois_prod_material_number, tb_coois_prod.tb_coois_prod_quantity, tb_coois_operation.tb_coois_operation_processing_time 
//FROM tb_coois_prod 
//INNER JOIN tb_coois_operation 
//ON tb_coois_prod.tb_coois_prod_order = tb_coois_operation.tb_coois_operation_order