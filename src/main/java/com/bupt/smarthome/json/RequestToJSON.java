package com.bupt.smarthome.json;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestToJSON {
	/**
	 * Static method is directly invoked by class name
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public static JSONObject readRequest (HttpServletRequest req) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));

		String line = null;
		StringBuilder sb = new StringBuilder();

		while ((line = br.readLine()) != null)
			sb.append(line);

		return JSONObject.parseObject(sb.toString());
	}
}
