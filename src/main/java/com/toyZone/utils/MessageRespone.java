package com.toyZone.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageRespone {
	public Map<String, String> getMessage(String message) {
		Map<String, String> result = new HashMap();
		if (message.equals("update_success")) {
			result.put("message", "Update success");
			result.put("alert", "success");
		} else if (message.equals("insert_success")) {
			result.put("message", "Insert success");
			result.put("alert", "success");
		} else if (message.equals("delete_success")) {
			result.put("message", "Delete success");
			result.put("alert", "success");
		} else if (message.equals("error_system")) {
			result.put("message", "Error system");
			result.put("alert", "danger");
		}else if (message.equals("error_upload")) {
			result.put("message", "Vui lòng chọn file");
			result.put("alert", "danger");
		}else if (message.equals("error_upload_exist")) {
			result.put("message", "Ảnh đã tồn tại! Xin chọn ảnh khác !");
			result.put("alert", "danger");
		}else if (message.equals("error_account_exist")) {
			result.put("message", "Tên tài khoản đã tòn tại!");
			result.put("alert", "danger");
		}else if (message.equals("error_find")) {
			result.put("message", "Không tồn tại dơn hàng!");
			result.put("alert", "danger");
		}else if (message.equals("error_register")) {
			result.put("message", "Tài khoản này đã tồn tại xin nhập tài khoản khác!");
			result.put("alert", "danger");
		}else if (message.equals("success_register")) {
			result.put("message", "Tạo tài khoản thành công!");
			result.put("alert", "success");
		}
		return result;
	}
}
