package com.bupt.smarthome.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bupt.smarthome.dao.impl.UserDAOImpl;
import com.bupt.smarthome.j2py.Cli;
import com.bupt.smarthome.json.RequestToJSON;
import com.bupt.smarthome.vo.*;
import com.bupt.smarthome.vo.highchart.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/servlet/user/*")
public class UserServlet extends BaseServlet {
	private final UserDAOImpl dao = new UserDAOImpl();
	private static Cli cli = null;

	/**
	 * This method tackles user login
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void login (HttpServletRequest req, HttpServletResponse res) throws IOException {
		UserFamily user = dao.userFamilyDAO(JSON.toJavaObject(RequestToJSON.readRequest(req), UserFamily.class)); // like BeanPropertyRowMapper

		if (user == null)
			res.getWriter().print("Wrong username or password");
		else
			res.getWriter().print(JSON.toJSONString(user)); // Java Object -> JSON
	}

	/**
	 * This method handles user registration
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void register (HttpServletRequest req, HttpServletResponse res) throws IOException {
		UserFamily user = dao.registerDAO(JSON.toJavaObject(RequestToJSON.readRequest(req), UserFamily.class)); // like BeanPropertyRowMapper

		if (user == null)
			res.getWriter().print("Username already exists");
		else if (user.getFamilyId() == 0)
			res.getWriter().print("The family specified doesn't exist");
		else
			res.getWriter().print(JSON.toJSONString(user)); // Java Object -> JSON
	}

	/**
	 * This method resets user password
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void reset (HttpServletRequest req, HttpServletResponse res) throws IOException {
		if (dao.editPasswordDAO(JSON.toJavaObject(RequestToJSON.readRequest(req), UserFamily.class)))
			res.getWriter().print("Password successfully edited");
		else
			res.getWriter().print("Incorrect user information");
	}

	/**
	 * This method deregister user account
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void deregister (HttpServletRequest req, HttpServletResponse res) throws IOException {
		if (dao.deregisterDAO(JSON.toJavaObject(RequestToJSON.readRequest(req), UserFamily.class)))
			res.getWriter().print("Successfully deregistered");
		else
			res.getWriter().print("Fatal error");
	}

	/**
	 * This method queries hardware information and data
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void hardware (HttpServletRequest req, HttpServletResponse res) throws IOException {
		JSONObject jo = RequestToJSON.readRequest(req);
		UserFamily user = new UserFamily();
		user.setFamilyId(jo.getInteger("familyId"));
		List<FamilyHardware> hl = dao.familyHardwareDAO(user, jo.getInteger("hwTypeId"));

		if (hl == null)
			res.getWriter().print("Empty Set");
		else {
			HighChart hc = new HighChart();
			hc.setChart(new Chart("spline"));
			hc.setTitle(new Title(hl.get(0).getHwTypeName()));
			hc.setSubtitle(new Subtitle("Source: smarthome"));
			hc.setxAxis(new XAxis(new Title("Datetime")));
			hc.setyAxis(dao.yAxisDAO(hl.get(0), jo.getInteger("dataTypeId")));
			hc.setLegend(new Legend("right", "top", "proximate"));
			hc.setTooltip(new Tooltip("<b>{series.name}</b><br/>", "{point.x:%H:%M:%S, %e. %b}: {point.y:.2f}"));
			hc.setPlotOptions(new PlotOptions(new Spline(new Marker(true))));

			for (FamilyHardware hardware : hl)
				hc.getSeries().add(dao.seriesDAO(hardware));

			ArrayList<Object> al = new ArrayList<>();
			al.add(hl);
			al.add(hc);
			res.getWriter().print(JSON.toJSONString(al)); // JSON Array
		}
	}

	/**
	 * This method turn light on and off
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void light (HttpServletRequest req, HttpServletResponse res) throws IOException {
		JSONObject jo = RequestToJSON.readRequest(req);
		cli = new Cli("127.0.0.1", 9999);

		if (jo.getBoolean("state")) // TURN ON
			cli.sendRequest("on");
		else // TURN OFF
			cli.sendRequest("off");

		res.getWriter().print(cli.recv());
		cli.close();
	}
}
